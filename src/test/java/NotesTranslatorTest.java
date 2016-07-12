package test.java;

import main.java.InputReader;
import main.java.NotesTranslator;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class NotesTranslatorTest {
    private static final String INPUT_FILE = Paths.get("files", "input.txt");

    @Test
    public void canTranslateAndProduceConversionAnswers()  {
        InputReader inputReader = new InputReader(INPUT_FILE);
        NotesTranslator notesTranslator = new NotesTranslator(inputReader);

        List<String> answersToQuestions = notesTranslator.getAnswersToQuestions();

        assertThat("Incorrect number of answers", answersToQuestions.size(), is(equalTo(5)));
        assertThat("Didn't manage to convert using roman numerals", answersToQuestions, hasItem("pish tegj glob glob is 42"));
        assertThat("Didn't manage to convert using multipliers", answersToQuestions, hasItem("glob prok Silver is 68 Credits"));
        assertThat("Didn't manage to convert using multipliers", answersToQuestions, hasItem("glob prok Gold is 57800 Credits"));
        assertThat("Didn't manage to convert using multipliers", answersToQuestions, hasItem("glob prok Iron is 782 Credits"));
        assertThat("Didn't manage to convert using multipliers", answersToQuestions, hasItem("I have no idea what you are talking about"));

    }
}