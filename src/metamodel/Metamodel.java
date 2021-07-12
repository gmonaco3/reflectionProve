package metamodel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import reflection.Column;
import reflection.PrimaryKey;

public class Metamodel<T> {

	private Class<T> clss;

	public Metamodel(Class<T> clss) {

		this.clss = clss;
	}

	public static <T> Metamodel<T> of(Class<T> clss) {

		return new Metamodel<>(clss);
	}

	public PrimaryKeyField getPrimaryKeyField() {

		Field[] fields = clss.getDeclaredFields();
		for (Field field : fields) {
			PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
			if (primaryKey != null) {
				PrimaryKeyField primaryKeyField = new PrimaryKeyField(field);
				return primaryKeyField;
			}
		}
		return null;
	}

	public List<ColumnField> getColumnField() {
		List<ColumnField> columnFields = new ArrayList<>();
		Field[] fields = clss.getDeclaredFields();

		for (Field field : fields) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				ColumnField columnField = new ColumnField(field);
				columnFields.add(columnField);
			}
		}
		return columnFields;
	}

	public String buildInserRequest() {
		// insert into Object (id,name,age) value (?,?,?)
		String columnElement = builColumNames();
		String questionMarksElement = buildQuestionMarksElement();
		return "insert into " + this.clss.getSimpleName() + " (" + columnElement + ") values (" + questionMarksElement
				+ ")";

	}

	private String builColumNames() {
		String primaryKeyColumnName = getPrimaryKeyField().getName();
		List<ColumnField> columnFields = getColumnField();
		List<String> columNames = columnFields.stream().map((ColumnField el) -> el.getName())
				.collect(Collectors.toList());
		columNames.add(0, primaryKeyColumnName);
		String columnElement = String.join(", ", columNames);
		return columnElement;

	}

	private String buildQuestionMarksElement() {
		int numberOfColumns = getColumnField().size() + 1;
		String questionMarksElement = IntStream.range(0, numberOfColumns).mapToObj(index -> "?")
				.collect(Collectors.joining(", "));
		return questionMarksElement;

	}

	private T buildInstanceOfTClass() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		return clss.getConstructor().newInstance();
	}

}
