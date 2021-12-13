package chapter2_io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class HrefMatch {
    public static void main(String[] args) {
        try {
            String urlString = "https://lenta.ru/";
            InputStream in = new URL(urlString).openStream();
            var input = new String(in.readAllBytes(), StandardCharsets.UTF_8);

            // search for all occurrences of a pattern
            var patternString = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
            Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
            pattern.matcher(input)
                    .results()
                    .map(MatchResult::group)
                    .forEach(System.out::println);

        } catch(IOException | PatternSyntaxException e) {
            e.printStackTrace();
        }

        String result = Pattern.compile("\\pL{4,}")
                .matcher("Mart had a little lamb")
                .replaceAll(m -> m.group().toUpperCase(Locale.ROOT));
        System.out.println("result = " + result);
    }

}



























