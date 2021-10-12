package br.com.rfasioli.java8whatsnews;

import br.com.rfasioli.java8whatsnews.domain.Person;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.BiFunction;

public class FunctionalInterfacesDemo {
    private static List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    private static List<Person> persons = Arrays.asList(
        new Person("Mahesh", 45),
        new Person("Suresh", 20),
        new Person("Ramesh", 37),
        new Person("Naresh", 33),
        new Person("Kalpesh", 53)
    );

    public static void main(String[] args) {
        FunctionalInterfacesDemo.intPredicateSample();
        FunctionalInterfacesDemo.predicateSample();
        FunctionalInterfacesDemo.functionSimpleSample();
        FunctionalInterfacesDemo.functionSample();
        FunctionalInterfacesDemo.biFunctionSample();
    }

    public static void intPredicateSample() {
        System.out.println("--- IntPredicate Sample ---");

        System.out.println("Print all numbers:");
        eval(list, n -> true);

        System.out.println("Print even numbers:");
        eval(list, n-> n % 2 == 0 );

        System.out.println("Print numbers greater than 3:");
        eval(list, n-> n > 3 );
    }

    private static void eval(List<Integer> list, IntPredicate predicate) {
        for(Integer n: list) {
            if(predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

    public static void predicateSample() {
        System.out.println("--- Predicate Sample ---");

        System.out.println("Print all persons:");
        eval(persons, n -> true);

        System.out.println("Print persons for even ages:");
        eval(persons, n -> n.getAge() % 2 == 0 );

        System.out.println("Print persons with name shorter than 7 characters:");
        eval(persons, n -> n.getName().length() < 7 );
    }

    private static void eval(List<Person> list, Predicate<Person> predicate) {
        list.forEach(person -> {
            if(predicate.test(person)) {
                System.out.println(person.toString() + " ");
            }
        });
    }

    public static void functionSimpleSample() {
        System.out.println("--- Function Simple Sample ---");
        Function<String, Integer> func = x -> x.length();
        Integer apply = func.apply("test to sample of function");
        System.out.println(apply);
    }

    public static void functionSample() {
        System.out.println("--- Function Sample ---");
        Function<Person, Integer> func = x -> x.getName().length();
        persons.forEach(person -> System.out.println(func.apply(person)));
    }

    public static void biFunctionSample() {
        System.out.println("--- BiFunction Sample ---");

        System.out.println("BiFunction Sum Sample");
        BiFunction<Integer, Integer, Integer> func = (x1, x2) -> x1 + x2;
        Integer result = func.apply(2, 3);
        System.out.println(result); // 5

        BiFunction<Person, Year, Year> birthYear = (x1, x2) -> x2.minusYears(x1.getAge().longValue());
        persons.forEach(person ->
            System.out.println(person + ", born in " + birthYear.apply(person, Year.now())));

    }

}
