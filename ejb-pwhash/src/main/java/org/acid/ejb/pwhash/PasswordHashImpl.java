package org.acid.ejb.pwhash;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.acid.ejb.logger.Logger;

@Stateless(mappedName = "pwhash")
public class PasswordHashImpl implements PasswordHash {

    public static final String ALGORITHM = "SHA-256";
    public static final Charset CHARSET = StandardCharsets.UTF_8;

    private final MessageDigest md;

    @EJB(mappedName = "logger")
    private Logger logger;

    public PasswordHashImpl() throws NoSuchAlgorithmException {
        try {
            md = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            logger.fatal("EJB-pwhash", "EJB 'PasswordHash' could not initialized the algorithm '" + ALGORITHM + "'", ex);
            throw ex;
        }
    }

    @Override
    public String hash(String src) {
        byte[] hashBytes = md.digest(src.getBytes(CHARSET));
        StringBuilder hexString = new StringBuilder();
        for (Byte b : hashBytes) {
            hexString.append(Integer.toHexString(0xFF & b));
        }
        return hexString.toString();
    }

    @Override
    public boolean equals(String src, String hashString) {
        return hash(src).equals(hashString);
    }

}
