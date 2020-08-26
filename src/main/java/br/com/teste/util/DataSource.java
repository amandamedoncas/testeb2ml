package br.com.teste.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataSource {

	public static void main(String[] args) {
        Persistence.createEntityManagerFactory("TestePU");
    }
	
	private static EntityManagerFactory factory ;
		
	static { factory = Persistence.createEntityManagerFactory("TestePU"); }
	
	public static EntityManager getEntityManager() 
	{
		return factory.createEntityManager();
	}
}
