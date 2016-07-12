package test.java;

import main.java.InputReader;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InputReaderTest {
    private static final String INPUT_FILE = "files/input.txt";
    private static final String CUSTOM_INPUT_FILE = "files/abc.data";
    private static final String INVALID_INPUT_FILE = "files/invalid.txt";
    private static final String RANDOM_POEM_INPUT_FILE = "files/haiku.txt";
    private static final String EMPTY_INPUT_FILE = "files/empty.txt";
    private static final int ZERO = 0;

    @Test()
    public void givenNonExistingFilePathShouldNotThrowYetStillDoNothing() {
        InputReader inputReader = new InputReader(INVALID_INPUT_FILE);

        assertThat("Empty file was not acceptable, and weirdly there is output", inputReader.getGalacticToRomanNumeralsNotes().size(), is(equalTo(ZERO)));
        assertThat("Empty file was not acceptable, and weirdly there is output", inputReader.getGalacticNumeralMutliplierNotes().size(), is(equalTo(ZERO)));
        assertThat("Empty file was not acceptable, and weirdly there is output", inputReader.getHowManyQuestionNotes().size(), is(equalTo(ZERO)));
        assertThat("Empty file was not acceptable, and weirdly there is output", inputReader.getHowMuchQuestionNotes().size(), is(equalTo(ZERO)));
    }

    @Test
    public void givenEmptyExistingFilePathShouldNotThrowYetStillDoNothing() {
        InputReader inputReader = new InputReader(EMPTY_INPUT_FILE);

        assertThat("Empty file was not acceptable, and weirdly there is output", inputReader.getGalacticToRomanNumeralsNotes().size(), is(equalTo(ZERO)));
        assertThat("Empty file was not acceptable, and weirdly there is output", inputReader.getGalacticNumeralMutliplierNotes().size(), is(equalTo(ZERO)));
        assertThat("Empty file was not acceptable, and weirdly there is output", inputReader.getHowManyQuestionNotes().size(), is(equalTo(ZERO)));
        assertThat("Empty file was not acceptable, and weirdly there is output", inputReader.getHowMuchQuestionNotes().size(), is(equalTo(ZERO)));
    }

    @Test
    public void shouldReadFileWithCustomExtensions() {
        InputReader inputReader = new InputReader(CUSTOM_INPUT_FILE);

        assertThat("Custom file read unsuccessful", inputReader.getGalacticToRomanNumeralsNotes().size(), is(equalTo(4)));
        assertThat("Custom file read unsuccessful", inputReader.getGalacticNumeralMutliplierNotes().size(), is(equalTo(3)));
        assertThat("Custom file read unsuccessful", inputReader.getHowManyQuestionNotes().size(), is(equalTo(3)));
        assertThat("Custom file read unsuccessful", inputReader.getHowMuchQuestionNotes().size(), is(equalTo(2)));
    }

    @Test
    public void shouldReadExistingFile() {
        InputReader inputReader = new InputReader(INPUT_FILE);
        assertThat("File read unsuccessful", inputReader.getGalacticToRomanNumeralsNotes().size(), is(equalTo(4)));
        assertThat("File read unsuccessful", inputReader.getGalacticNumeralMutliplierNotes().size(), is(equalTo(3)));
        assertThat("File read unsuccessful", inputReader.getHowManyQuestionNotes().size(), is(equalTo(3)));
        assertThat("File read unsuccessful", inputReader.getHowMuchQuestionNotes().size(), is(equalTo(2)));
    }

    @Test
    public void shouldIgnoreIrrelevantNotes() {
        InputReader inputReader = new InputReader(RANDOM_POEM_INPUT_FILE);

        assertThat("Failed to ignore irrelevant notes", inputReader.getGalacticToRomanNumeralsNotes().size(), is(equalTo(ZERO)));
        assertThat("Failed to ignore irrelevant notes", inputReader.getGalacticNumeralMutliplierNotes().size(), is(equalTo(ZERO)));
        assertThat("Failed to ignore irrelevant notes", inputReader.getHowManyQuestionNotes().size(), is(equalTo(ZERO)));
        assertThat("Failed to ignore irrelevant notes", inputReader.getHowMuchQuestionNotes().size(), is(equalTo(ZERO)));
    }
}
