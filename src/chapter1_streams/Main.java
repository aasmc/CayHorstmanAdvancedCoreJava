package chapter1_streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        parallelStreams();
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, String> languageNames = locales.collect(
                Collectors.toMap(
                        Locale::getDisplayLanguage,
                        loc -> loc.getDisplayLanguage(loc),
                        (existingValue, newValue) -> existingValue
                )
        );
        for (Map.Entry<String, String> entry :
                languageNames.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryLanguages = locales.collect(
                Collectors.toMap(
                        Locale::getDisplayCountry,
                        l -> Collections.singleton(l.getDisplayLanguage()),
                        (a, b) -> {
                            var union = new HashSet<String>(a);
                            union.addAll(b);
                            return union;
                        }
                )
        );

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, List<Locale>> countryToLocale = locales.collect(
                Collectors.groupingBy(Locale::getCountry)
        );
        List<Locale> swissLocales = countryToLocale.get("CH");
        for (Locale loc :
                swissLocales) {
            System.out.println("loc = " + loc.getDisplayLanguage());
        }
    }

    static void parallelStreams() throws IOException {
        var contents = new String(Files.readAllBytes(
                Paths.get("src/streams/alice.txt")
        ), StandardCharsets.UTF_8);
        List<String> wordList = List.of(contents.split("\\PL+"));

        var shortWords = new int[10];
        Arrays.fill(shortWords, 0);
        Map<Integer, Long> shortWordsCount = wordList.parallelStream()
                .filter(s -> s.length() < 10)
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));

        System.out.println("shortWordsCount = " + shortWordsCount);

        // downstream order not deterministic
        Map<Integer, List<String>> result = wordList.parallelStream()
                .collect(
                        Collectors.groupingByConcurrent(String::length)
                );
        System.out.println("result = " + result.get(14));

        Map<Integer, Long> wordCounts = wordList.parallelStream().collect(
                Collectors.groupingByConcurrent(String::length, Collectors.counting())
        );
        System.out.println("wordCounts = " + wordCounts);
    }
}



























