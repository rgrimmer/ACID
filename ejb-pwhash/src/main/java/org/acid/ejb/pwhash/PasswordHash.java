package org.acid.ejb.pwhash;

import javax.ejb.Remote;

@Remote
public interface PasswordHash {

    String hash(String src);

    boolean equals(String src, String hashString);
}
