package test.java;

import main.java.NumeralsConverter;
import main.java.RomanNumeral;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.InputMismatchException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class NumeralsConverterTest {
    private static HashMap<String, RomanNumeral> romanNumeralsMapping;

    @BeforeClass
    public static void setUp(){
        romanNumeralsMapping = new HashMap<>();
        romanNumeralsMapping.put("iiii", RomanNumeral.I);
        romanNumeralsMapping.put("vvvv", RomanNumeral.V);
        romanNumeralsMapping.put("xxxx", RomanNumeral.X);
        romanNumeralsMapping.put("llll", RomanNumeral.L);
        romanNumeralsMapping.put("cccc", RomanNumeral.C);
        romanNumeralsMapping.put("dddd", RomanNumeral.D);
        romanNumeralsMapping.put("mmmm", RomanNumeral.M);
    }

    @Test(expected = InputMismatchException.class)
    public void throwsIfGivenUnknownGalacticNumeral(){
        convert(new String[]{"blah"});
    }

    @Test
    public void canRecognise1(){
        assertThat("Didn't manage to convert I", convert(new String[]{"iiii"}), is(equalTo(1)));
    }

    @Test
    public void canAdd(){
        assertThat("Didn't manage to convert II", convert(new String[]{"iiii", "iiii"}), is(equalTo(2)));
    }

    @Test
    public void canSubstract(){
        assertThat("Didn't manage to convert IV", convert(new String[]{"iiii", "vvvv"}), is(equalTo(4)));
    }
    
    @Test
    public void canRecognise5(){
        assertThat("Didn't manage to convert V", convert(new String[]{"vvvv"}), is(equalTo(5)));
    }

    @Test
    public void canRecognise10(){
        assertThat("Didn't manage to convert X", convert(new String[]{"xxxx"}), is(equalTo(10)));
    }

    @Test
    public void canRecognise50(){
        assertThat("Didn't manage to convert L", convert(new String[]{"llll"}), is(equalTo(50)));
    }

    @Test
    public void canRecognise100(){
        assertThat("Didn't manage to convert C", convert(new String[]{"cccc"}), is(equalTo(100)));
    }

    @Test
    public void canRecognise500(){
        assertThat("Didn't manage to convert D", convert(new String[]{"dddd"}), is(equalTo(500)));
    }

    @Test
    public void canRecognise1000(){
        assertThat("Didn't manage to convert M", convert(new String[]{"mmmm"}), is(equalTo(1000)));
    }

    @Test
    public void canFigureOutMultiplier(){
        assertThat("Didn't manage to figure out multiplier", convertMultiplier(new String[]{"iiii", "iiii"}, 34), is(equalTo(17f)));
    }

    private Integer convert(String[] galacticNumerals) {
        return NumeralsConverter.convertGalacticNumeralsIntoHumanReadableValue(galacticNumerals, romanNumeralsMapping);
    }

    private Float convertMultiplier(String[] galacticNumerals, Integer total) {
        return NumeralsConverter.convertUnitToMultiplier(galacticNumerals, total, romanNumeralsMapping);
    }

}