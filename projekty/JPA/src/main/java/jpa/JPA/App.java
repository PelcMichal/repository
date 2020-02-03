package jpa.JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Alien;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {/*
    	Alien a = new Alien();
    	a.setAid(6);
    	a.setAname("G");
    	a.setTech("G");*/

    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    	EntityManager em = emf.createEntityManager();
    	/*
    	em.getTransaction().begin();
    	em.persist(a);
    	em.getTransaction().commit();*/
    	
    	
    	Alien a = em.find(Alien.class,4);
    	
    	
        System.out.println(a);
    }
}
