package org.acid.ejb.test;

import javax.ejb.Remote;

@Remote
public interface TestRemote {

    String test();
}
