package metamodel;

import java.util.List;

import model.ClassExample;

public class MetamodelDemo {

	public static void main(String[] args) {

		Metamodel<ClassExample> metamodel = Metamodel.of(ClassExample.class);
		
		PrimaryKeyField PrimaryKeyField = metamodel.getPrimaryKeyField();
		
		List<ColumnField> columnsField = metamodel.getColumnField();
		
		System.out.println("Primary key name = " + PrimaryKeyField.getName() + ", type = " + PrimaryKeyField.getType().getSimpleName());
	}

}
