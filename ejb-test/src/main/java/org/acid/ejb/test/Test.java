package org.acid.ejb.test;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.spi.PersistenceUnitTransactionType;
import org.hibernate.ejb.EntityManagerImpl;

@Stateless(mappedName = "testEJB")
public class Test implements TestRemote {
    
    @PersistenceContext
    public  EntityManager manager;
    
    
    public Test() {
        
    }
    
    @Override
    public String test() {
        System.out.println("OK");
        return "TEST";
    }

  
}
