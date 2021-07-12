package methodhandles;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

import model.ClassExample;

public class MethodhandlesDemo {

	public static void main(String[] args) throws Throwable {
		Lookup lookup = MethodHandles.lookup();

		MethodType emptyConstructorMethodType = MethodType.methodType(void.class);
		MethodHandle emptyConstructor = lookup.findConstructor(ClassExample.class, emptyConstructorMethodType);

		ClassExample classExample = (ClassExample) emptyConstructor.invoke();
		System.out.println(classExample);

		MethodType constructorMethodType = MethodType.methodType(void.class, Long.class, String.class, String.class);
		MethodHandle constructor = lookup.findConstructor(ClassExample.class, constructorMethodType);

		ClassExample classExample2 = (ClassExample) constructor.invoke(1L,"nome","cognome");
		System.out.println(classExample2);
		
		MethodType nameGetterMethodType = MethodType.methodType(String.class);
		MethodHandle nameGetter = lookup.findVirtual(ClassExample.class,"getName", nameGetterMethodType);
		
		System.out.println(nameGetter.invoke(classExample2));
		
		MethodType nameSetterMethodType = MethodType.methodType(void.class, String.class);
		MethodHandle nameSetter = lookup.findVirtual(ClassExample.class,"setName", nameSetterMethodType);
		
		nameSetter.invoke(classExample2,"jack");
		System.out.println(classExample2);
		
		Lookup privateLookup = MethodHandles.privateLookupIn(ClassExample.class, lookup);
		MethodHandle nameReader= privateLookup.findGetter(ClassExample.class,"name", String.class);
		System.out.println(nameReader.invoke(classExample2));

		

	}

}
