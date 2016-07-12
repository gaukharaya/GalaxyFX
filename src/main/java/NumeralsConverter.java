package main.java;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by gaukharmassabayeva on 10/11/15.
 */
public class NumeralsConverter {

    public static Integer convertGalacticNumeralsIntoHumanReadableValue(String[] galacticNumerals, Map<String, RomanNumeral> romanNumeralsMapping){
        boolean foundUnknownGalacticNumeral = Arrays.stream(galacticNumerals).anyMatch(n -> !romanNumeralsMapping.containsKey(n));

        if(!foundUnknownGalacticNumeral){
            List<Integer> numeralsAsHumanReadableNumbers = Arrays.stream(galacticNumerals).
                    map(gn -> romanNumeralsMapping.get(gn).getValue()).collect(Collectors.toList());
            return negateNumeralsIfRequired(numeralsAsHumanReadableNumbers).stream().reduce(0, (a, b) -> a + b);
        }

        throw new InputMismatchException("Error while converting to roman numerals. Unrecognised galactic numeral.");
    }

    public static Float convertUnitToMultiplier(String[] galacticNumerals, Integer total, Map<String, RomanNumeral> romanNumeralsMapping){
        Integer galacticTotalFromRomanConversion = convertGalacticNumeralsIntoHumanReadableValue(galacticNumerals, romanNumeralsMapping);
        return Float.valueOf(total) / Float.valueOf(galacticTotalFromRomanConversion);
    }

    private static List<Integer> negateNumeralsIfRequired(List<Integer> numbers)
    {
        for(int i = 0 ; i < numbers.size() -1; i++)
        {
            if( numbers.get(i) < numbers.get(i+1))
            {
                numbers.set(i, numbers.get(i) * -1);
            }
        }
        return numbers;
    }

}
