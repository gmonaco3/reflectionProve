package entitymanager;

import java.sql.SQLException;

import model.ClassExample;

public interface EntityManager<T> {

	static EntityManager<ClassExample> of(Class<ClassExample> class1) {
		return new EntityManagerImpl<>();
	}

	void persist(T t) throws IllegalArgumentException, IllegalAccessException, SQLException;

}
