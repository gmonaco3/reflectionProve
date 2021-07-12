package entitymanager;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import metamodel.ColumnField;
import metamodel.Metamodel;

public class EntityManagerImpl<T> implements EntityManager<T> {
	
	private AtomicLong idGenerator = new AtomicLong(0L);

	@Override
	public void persist(T t) throws IllegalArgumentException, IllegalAccessException, SQLException {
		Metamodel<? extends Object> metamodel = Metamodel.of(t.getClass());

		String sql = metamodel.buildInserRequest();
		PreparedStatement statement = prepareStatementWith(sql).andParameters(t);

		statement.executeUpdate();

	}

	private PrepareStatementWrapper prepareStatementWith(String sql) throws SQLException {

		Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

		PreparedStatement statement = connection.prepareStatement(sql);
		return new PrepareStatementWrapper(statement);
	}

	private class PrepareStatementWrapper {

		private PreparedStatement statement;

		public PrepareStatementWrapper(PreparedStatement statement) {
			this.statement = statement;
		}

		public PreparedStatement andParameters(T t) throws SQLException, IllegalArgumentException, IllegalAccessException {
			Metamodel<? extends Object> metamodel = Metamodel.of(t.getClass());	
			Class<?> primaryKeyType = metamodel.getPrimaryKeyField().getType();
			if(primaryKeyType == Long.class) {
				long id = idGenerator.incrementAndGet();
				statement.setLong(1, id);
				Field field = metamodel.getPrimaryKeyField().getField();
				field.setAccessible(true);
				field.set(t, id);
			}
			
			int i = 0;
			for (ColumnField columnField : metamodel.getColumnField()) {
				Class<?> fieldType = columnField.getType();
				Field field = columnField.getField();
				field.setAccessible(true);
				Object value = field.get(t);
				
				if(fieldType == Integer.class) {
					statement.setInt(i + 2,(int)value);
				}else if(fieldType == String.class) {
					statement.setString(i + 2, (String)value);
				}
				i++;
			}
			
			
			return statement;
		}

	}

}
