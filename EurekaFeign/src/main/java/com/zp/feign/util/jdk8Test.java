package com.zp.feign.util;

import java.util.*;
import java.util.function.Supplier;

public class jdk8Test {
    public static void main(String[] args) {
        List<String> names1 = new ArrayList<>();
        names1.add("Google ");
        names1.add("Runoob ");
        names1.add("Taobao ");
        names1.add("Baidu ");
        names1.add("Sina ");
        Collections.sort(names1, (s1,s2) -> s1.compareTo(s2));

        System.out.println(names1);

        Operation jia = (a,b) -> a+b;
        System.out.println(new jdk8Test().jstest(1,2,jia));
        System.out.println(jia.js(3,4));
        jdk8Test test = jdk8Test.create(jdk8Test::new);
        Arrays.asList(test).forEach(test::print);
        names1.forEach(System.out::println);

        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }
    public static jdk8Test create(final Supplier<jdk8Test> supplier) {
        System.out.println("create");
        return supplier.get();
    }
    public void print(jdk8Test t){
        System.out.println(t.toString());
    }
    @FunctionalInterface
    interface Operation{
        int js(int a, int b);
    }
    public int jstest(int a,int b, Operation fn){
        return fn.js(a,b);
    }
}
