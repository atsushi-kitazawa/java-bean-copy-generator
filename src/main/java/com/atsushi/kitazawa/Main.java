package com.atsushi.kitazawa;

import java.lang.reflect.Field;
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
    List<String> srcFieldsName =
        Arrays.asList(src.getDeclaredFields()).stream()
            .map(f -> f.getName())
            .collect(Collectors.toList());
    for (Field f : dest.getDeclaredFields()) {
      if (srcFieldsName.contains(f.getName())) targetFields.add(f);
    }

    // for (Field f : targetFields) {
    //  System.out.println(f.getName());
    //  System.out.println("hoge");
    // }

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
}
