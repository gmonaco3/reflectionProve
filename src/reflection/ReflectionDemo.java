package reflection;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

import model.ClassExample;
import model.OtherClassExample;

public class ReflectionDemo {

	public static void main(String[] args) throws Exception{

		ClassExample classExample = new ClassExample();
		
		
//		Class<? extends Object> classFromObject = MetaModelExample.getClassFromObject(classExample);
		
		//Array of construcotrs
		Constructor<?>[] constructorsFromObject = MetaModelExample.getConstructorsFromClassType(ClassExample.class);
		//System All constructors
		System.out.println("the constructors of the class \"" + ClassExample.class.getSimpleName() + "\" are: ");
		Arrays.asList(constructorsFromObject).stream().forEach(System.out::println);
		
		List<Constructor> constructors = Arrays.asList(constructorsFromObject);
	
		Constructor constructor = constructors.get(1);
		
		System.out.println("the number of parameters are: " + constructor.getParameterCount());
		
//		Parameter[] parameters = constructor.getParameters();
//		List<Parameter> parametersAsList = Arrays.asList(parameters);
//		
//		System.out.println(parametersAsList.get(0).getName());
		
		
		System.out.println("the type of the parameters are: ");
		List<Class> parameterTypes = Arrays.asList(constructor.getParameterTypes());
		parameterTypes.stream().forEach(System.out::println);
		
		
//		Field[] fieldsFromClass = MetaModelExample.getFieldsFromClass(ClassExample.class);
//		
//		System.out.println("the class has: " + fieldsFromClass.length + " fields");
//		
//		
//		Arrays.stream(fieldsFromClass).forEach(System.out::println);
//		
//		Arrays.stream(fieldsFromClass).forEach((Field field) -> field.setAccessible(true));
//		
//		System.out.println(fieldsFromClass[0].getType().getSimpleName());
//		System.out.println(fieldsFromClass[0].getName());
//		System.out.println(fieldsFromClass[0].get(classExample));
//
//		for (Field field2 : fieldsFromClass) {
//			if(field2.getType() == Long.class) {
//				field2.set(classExample, 9L);
//			}else if (field2.getType() == String.class) {
//				field2.set(classExample, field2.getName());
//			}else if (field2.getType() == Date.class) {
//				field2.set(classExample, new Date());
//
//			}
//		}
		
		ClassExample classExample2 = MetaModelExample.getInstance(ClassExample.class);
		
		OtherClassExample classOtherExample = MetaModelExample.getInstance(OtherClassExample.class);

		System.out.println(classExample2);
		
		System.out.println(classOtherExample);

	}

}
