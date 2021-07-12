package entitymanager;

import java.sql.SQLException;

import model.ClassExample;

public class WritingObjects {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, SQLException {


		EntityManager<ClassExample> entityManager = EntityManager.of(ClassExample.class);
		
		ClassExample classExample = new ClassExample();
		
		entityManager.persist(classExample);
	}

}
