package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gaukharmassabayeva on 09/11/15.
 */
public class InputReader {
    public static final Pattern GALACTIC_NUMERAL_REGEX = Pattern.compile("^([a-z]+) is ([I|V|X|L|C|D|M])$");
    public static final Pattern GALACTIC_NUMERAL_MULTIPLIER_REGEX = Pattern.compile("((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$");
    public static final Pattern HOW_MANY_REGEX = Pattern.compile("^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$");
    public static final Pattern BEGINS_WITH_HOW_MANY_REGEX = Pattern.compile("^how many.*$");
    public static final Pattern HOW_MUCH_REGEX = Pattern.compile("^how much (is) ((?:\\w+[^0-9] )+)\\?$");
    public static final Pattern BEGINS_WITH_HOW_MUCH_REGEX = Pattern.compile("^how much.*$");
    private String inputFile;

    public InputReader(String inputFile) {
        this.inputFile = inputFile;
    }

    public List<String> getGalacticToRomanNumeralsNotes() {
        return getNotesMatchedBy(GALACTIC_NUMERAL_REGEX);
    }

    public List<String> getGalacticNumeralMutliplierNotes() {
        return getNotesMatchedBy(GALACTIC_NUMERAL_MULTIPLIER_REGEX);
    }

    public List<String> getHowManyQuestionNotes() {
        return getNotesMatchedBy(BEGINS_WITH_HOW_MANY_REGEX);
    }

    public List<String> getHowMuchQuestionNotes() {
        return getNotesMatchedBy(BEGINS_WITH_HOW_MUCH_REGEX);
    }

    private List<String> getNotesMatchedBy(Pattern pattern) {
        return getFileStreamForProcessing().
                filter(pattern.asPredicate()).
                collect(Collectors.toList());
    }

    private Stream<String> getFileStreamForProcessing() {
        Path filePath = Paths.get(inputFile);
        try {
            return Files.lines(filePath).
                    parallel();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Sorry, file path you specified was not found. Please re-run with a valid path");
        }
        return Stream.empty();
    }


}
