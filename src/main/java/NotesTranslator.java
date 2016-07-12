package main.java;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by gaukharmassabayeva on 10/11/15.
 */
public class NotesTranslator {
    private static final String ERROR_MESSAGE = "I have no idea what you are talking about";
    private InputReader inputReader;

    private String unit;

    public NotesTranslator(InputReader inputReader) {

        this.inputReader = inputReader;
    }

    private HashMap<String, RomanNumeral> getGalacticToRomanNumeralsMapping() {
        HashMap<String, RomanNumeral> romanNumeralsMap = new HashMap<>();
        inputReader.getGalacticToRomanNumeralsNotes().stream().
                forEach(line -> {
                    Matcher matcher = getMatcher(InputReader.GALACTIC_NUMERAL_REGEX, line);
                    romanNumeralsMap.putIfAbsent(matcher.group(1), RomanNumeral.valueOf(matcher.group(2)));
                });

        return romanNumeralsMap;
    }


    private HashMap<String, Float> getGalacticNumeralMutliplierMapping(HashMap<String, RomanNumeral> romanNumeralsMapping) {
        HashMap<String, Float> galacticNumeralMultiplierMap = new HashMap<>();
        inputReader.getGalacticNumeralMutliplierNotes().stream().
                forEach(line -> {
                    Matcher matcher = getMatcher(InputReader.GALACTIC_NUMERAL_MULTIPLIER_REGEX, line);
                    unit = matcher.group(4).trim();
                    String[] galacticNumerals = matcher.group(1).split(" ");
                    Integer total = Integer.parseInt(matcher.group(3).trim());
                    Float multiplier = NumeralsConverter.convertUnitToMultiplier(galacticNumerals, total, romanNumeralsMapping);
                    galacticNumeralMultiplierMap.putIfAbsent(matcher.group(2), multiplier);
                });


        return galacticNumeralMultiplierMap;
    }

    public List<String> getAnswersToQuestions() {
        HashMap<String, RomanNumeral> romanNumeralsMapping = getGalacticToRomanNumeralsMapping();
        HashMap<String, Float> galacticNumeralMutliplierMapping = getGalacticNumeralMutliplierMapping(romanNumeralsMapping);

        List<String> answers = getAnswersToHowManyQuestions(romanNumeralsMapping, galacticNumeralMutliplierMapping);
        List<String> moreAnswers = getAnswersToHowMuchQuestions(romanNumeralsMapping);
        answers.addAll(moreAnswers);
        return answers;
    }

    private List<String> getAnswersToHowMuchQuestions(HashMap<String, RomanNumeral> romanNumeralsMapping) {
        return inputReader.getHowMuchQuestionNotes().stream().
                map(line -> {
                    Matcher matcher = getMatcher(InputReader.HOW_MUCH_REGEX, line);
                    try {
                    String[] galacticNumerals = matcher.group(2).split(" ");
                    Integer galacticTotalFromRomanConversion = NumeralsConverter.convertGalacticNumeralsIntoHumanReadableValue(galacticNumerals, romanNumeralsMapping);
                    return matcher.group(2) + "is " + galacticTotalFromRomanConversion;
                    } catch (IllegalStateException e) {
                        return ERROR_MESSAGE;
                    }
                }).collect(Collectors.toList());
    }

    private List<String> getAnswersToHowManyQuestions(HashMap<String, RomanNumeral> romanNumeralsMapping, HashMap<String, Float> galacticNumeralMutliplierMapping) {
        return inputReader.getHowManyQuestionNotes().stream().
                map(line -> {
                    Matcher matcher = getMatcher(InputReader.HOW_MANY_REGEX, line);
                    if (!isRecognisedUnit(matcher.group(1))) { return ERROR_MESSAGE; }
                    try {
                        String[] galacticNumerals = matcher.group(2).split(" ");
                        Integer galacticTotalFromRomanConversion = NumeralsConverter.convertGalacticNumeralsIntoHumanReadableValue(galacticNumerals, romanNumeralsMapping);
                        String mutiplierName = matcher.group(3).trim();
                        Float multiplier = galacticNumeralMutliplierMapping.get(mutiplierName);
                        int total = (int) (new Float(galacticTotalFromRomanConversion) * multiplier);
                        return matcher.group(2) + mutiplierName + " is " + total + " " + unit;
                    } catch (IllegalStateException e){
                        return ERROR_MESSAGE;
                    }
                }).collect(Collectors.toList());
    }

    private Matcher getMatcher(Pattern pattern, String line) {
        Matcher matcher = pattern.matcher(line);
        matcher.matches();
        return matcher;
    }

    private boolean isRecognisedUnit(String galacticNumeralInTheQuestion) {
        return unit.equals(galacticNumeralInTheQuestion);
    }
}
