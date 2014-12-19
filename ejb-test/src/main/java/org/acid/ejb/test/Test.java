package org.acid.ejb.test;

import javax.ejb.Stateless;

@Stateless(mappedName = "testEJB")
public class Test implements TestRemote {

    public Test() {
    }

    @Override
    public String test() {
        System.out.println("OK");
        return "TEST";
    }
}
