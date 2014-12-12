package org.acid.ejb.test;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TestMain {

    public static final String JNDI_NAME = "testEJB";

    public static void main(String[] args) {

        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put("java.naming.factory.initial", "org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");
        env.put("java.naming.provider.url", "rmi://localhost:1099");
        env.put("java.naming.factory.url.pkgs", "org.objectweb.jonas.naming");

        try {
            Context ctx = new InitialContext(env);
            TestRemote ref = (TestRemote) ctx.lookup(JNDI_NAME);
            System.out.println(ref.test());
        } catch (NamingException ex) {
            System.err.println(ex);
        }
    }
}
