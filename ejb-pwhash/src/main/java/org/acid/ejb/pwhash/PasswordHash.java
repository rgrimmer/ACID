package org.acid.ejb.pwhash;

public interface PasswordHash {

    String hash(String src);

    boolean equals(String src, String hashString);
}
