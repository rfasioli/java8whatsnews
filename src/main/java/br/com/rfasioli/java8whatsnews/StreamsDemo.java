package br.com.rfasioli.java8whatsnews;

import br.com.rfasioli.java8whatsnews.domain.Person;

import java.time.Year;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamsDemo {
    private static final List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
    private static final List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
    private static final List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);
    private static final Random random = new Random();

    private static final List<Person> persons = Arrays.asList(
        new Person("Mahesh", 45),
        new Person("Suresh", 20),
        new Person("Ramesh", 37),
        new Person("Naresh", 33),
        new Person("Kalpesh", 53)
    );

    public static void main(String[] args) {
        testStrings();
        testIntegers();
        testRandom();
        testStreamWithListPersonObject();
    }

    private static void testStrings() {
        System.out.println("List: " + strings);

        long count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println("Empty Strings: " + count);

        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("Strings of length 3: " + count);

        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("Filtered List: " + filtered);

        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("Merged String: " + mergedString);
    }

    private static void testIntegers() {
        List<Integer> squaresList = numbers.stream().map( i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("Squares List: " + squaresList);

        System.out.println("List: " + integers);

        IntSummaryStatistics stats = integers.stream().mapToInt(x -> x).summaryStatistics();

        System.out.println("Highest number in List : " + stats.getMax());
        System.out.println("Lowest number in List : " + stats.getMin());
        System.out.println("Sum of all numbers : " + stats.getSum());
        System.out.println("Average of all numbers : " + stats.getAverage());
        System.out.println("Random Numbers: ");
    }

    private static void testRandom() {
        random.ints().limit(10).sorted().forEach(System.out::println);

        //parallel processing
        long count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("Empty Strings: " + count);

    }

    private static void testStreamWithListPersonObject() {
        IntFunction<Integer> birthYear = age -> Year.now().minusYears(age).getValue();
        IntFunction<Integer> ageIn1999 = age -> Year.of(1999).minusYears(birthYear.apply(age)).getValue();
        Function<Person, Person> personAgeIn1999 = person -> new Person(person.getName(), ageIn1999.apply(person.getAge()));
        Predicate<Person> alreadyBorn = p -> p.getAge() >= 0;

        final List<Person> personAlreadyBorn = persons.stream()
            .map(personAgeIn1999::apply)
            .peek(System.out::println)
            .filter(alreadyBorn::test)
            .collect(Collectors.toList());

        System.out.println(personAlreadyBorn);

    }
}
