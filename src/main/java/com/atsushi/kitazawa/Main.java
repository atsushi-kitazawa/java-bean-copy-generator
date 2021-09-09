package com.atsushi.kitazawa;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.err.println("please specify src, dest class name.");
      System.exit(-1);
    }
    createCopyCode(Class.forName(args[0]), Class.forName(args[1]));
  }

  private static void createCopyCode(Class<?> src, Class<?> dest) {
    List<Field> targetFields = new ArrayList<>();

    List<String> srcFieldsName = getClassFieldsNameList(src);

    while (true) {
      src = src.getSuperclass();
      // System.out.println("debug:" + src.getSimpleName());
      if (!src.equals(Object.class)) {
        List<String> superClassFieldsName = getClassFieldsNameList(src);
        srcFieldsName.addAll(superClassFieldsName);
      } else {
        break;
      }
    }
    for (Field f : dest.getDeclaredFields()) {
      if (srcFieldsName.contains(f.getName())) targetFields.add(f);
    }

    // System.out.println("debug srcFieldsName:" + srcFieldsName);
    // System.out.println("debug targetFields:" + targetFields);

    StringBuffer code = new StringBuffer();
    for (Field f : targetFields) {
      code.append(
          "dest.set"
              + firstCharUpperCase(f.getName())
              + "("
              + createGetterCode(f.getName())
              + ");");
      code.append(System.getProperty("line.separator"));
    }
    System.out.println(code.toString());
  }

  private static String createGetterCode(String field) {
    return "src.get" + firstCharUpperCase(field) + ")";
  }

  private static String firstCharUpperCase(String s) {
    return s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
  }

  private static List<String> getClassFieldsNameList(Class<?> clazz) {
    List<String> list = new ArrayList<>();
    for(Field f : clazz.getDeclaredFields()) {
	if(f.getType().equals(List.class)) {
	    ParameterizedType type = (ParameterizedType)f.getGenericType();
	    Class<?> genericClass = (Class<?>)type.getActualTypeArguments()[0];
	    if(isRequireRecursiveClass(genericClass)) {
		System.out.println("debug no support:");
	    } else {
		list.add(f.getName());
	    }
	} else {
	    list.add(f.getName());	
	}
    }
    return list;
  }

  private static boolean isRequireRecursiveClass(Class<?> clazz) {
      if(checkTargetClasses().contains(clazz)) {
	  return false;
      } else {
      return true;
      }
  }

  private static List<Class<?>> checkTargetClasses() {
      return Arrays.asList(String.class, Integer.class, Long.class);
  }

  // test code.
  private static void test() {
      Src2 src2 = new Src2();
      Src3 src31 = new Src3();
      src31.setStr("aaa");
      src31.setI(0);
      Src3 src32 = new Src3();
      src32.setStr("bbb");
      src32.setI(1);
      List<Src3> src3List = Arrays.asList(src31, src32);
      src2.setList(src3List);

      Dest2 dest2 = new Dest2();
      List<Dest3> dest3List = new ArrayList<>(); 
      for(Src3 s : src2.getList()) {
	  Dest3 d = new Dest3();
	  d.setStr(s.getStr());
	  d.setI(s.getI());
	  dest3List.add(d);
      }
      dest2.setList(dest3List);
  }
}
