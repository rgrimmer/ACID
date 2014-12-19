package org.acid.ejb.pwhash;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;

@Stateless(mappedName = "pwhash")
public class PasswordHashImpl implements PasswordHash {

    public static final String ALGORITHM = "SHA-256";
    public static final Charset CHARSET = StandardCharsets.UTF_8;

    private final MessageDigest md;

    public PasswordHashImpl() throws NoSuchAlgorithmException {
        md = MessageDigest.getInstance(ALGORITHM);
    }

    @Override
    public String hash(String src) {
        byte[] hashBytes = md.digest(src.getBytes());
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
