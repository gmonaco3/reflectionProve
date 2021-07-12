package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Date;

public class MetaModelExample<T> {

	public static <T> Class<? extends Object> getClassFromObject(T obj) {
		return obj.getClass();
	}

	public static <T> Class<?> getClassFromNameOfObject(String nameOfClass) throws ClassNotFoundException {
		return Class.forName(nameOfClass);
	}

	public static <T extends Object> Class<?> getSuperClassFromObject(T obj) throws ClassNotFoundException {
		return obj.getClass().getSuperclass();
	}

	public static <T> Class<?>[] getInterfacesFromObject(T obj) throws ClassNotFoundException {
		return obj.getClass().getInterfaces();
	}

	public static <T> Field[] getFieldsFromClass(Class<?> clss)
			throws ClassNotFoundException, NoSuchFieldException, SecurityException {
		return clss.getDeclaredFields();
	}

	public static <T> Method[] getMethodsFromObject(T obj)
			throws ClassNotFoundException, NoSuchFieldException, SecurityException {
		return obj.getClass().getDeclaredMethods();
	}

	public static <T> Constructor<?>[] getConstructorsFromClassType(Class<?> clss)
			throws ClassNotFoundException, NoSuchFieldException, SecurityException {
		return clss.getDeclaredConstructors();
	}

	public static String getModifierName(int modifier) {
		if (Modifier.isPublic(modifier)) {
			return "public";
		} else if (Modifier.isPrivate(modifier)) {
			return "private";
		} else if (Modifier.isProtected(modifier)) {
			return "protected";
		}
		return "default";
	}

	//verify to add a collection type
	public static <T> T getInstance(Class<?> clss)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException, NoSuchFieldException {

		T instance = (T) clss.getConstructor().newInstance();

		Field[] fieldsFromClass = MetaModelExample.getFieldsFromClass(clss);
		Arrays.stream(fieldsFromClass).forEach((Field field) -> field.setAccessible(true));
		for (Field field : fieldsFromClass) {
			if (field.getType() == Long.class) {
				field.set(instance, 9L);
			} else if (field.getType() == String.class) {
				field.set(instance, field.getName());
			} else if (field.getType() == Date.class) {
				field.set(instance, new Date());
			}else if (field.getType() instanceof Object) {
				field.set(instance, getInstance(field.getType()));
			}
		}
		Arrays.stream(fieldsFromClass).forEach((Field field) -> field.setAccessible(false));
		return instance;
	}

}
