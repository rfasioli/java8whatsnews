package br.com.rfasioli.java8whatsnews;

import java.util.ArrayList;
import java.util.List;

public class MethodReferenceDemo {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("Mahesh");
        names.add("Suresh");
        names.add("Ramesh");
        names.add("Naresh");
        names.add("Kalpesh");

        // Method reference is describe using ::
        names.forEach(System.out::println);
    }
}
