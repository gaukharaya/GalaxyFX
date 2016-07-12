package main.java;

public class GalaxyFX {

    public static void main(String[] args) {
        String filePath = args[0];
        System.out.print("Proceeding to read note's file saved at: " + filePath);

        InputReader inputReader = new InputReader(filePath);
        NotesTranslator notesTranslator = new NotesTranslator(inputReader);
        notesTranslator.getAnswersToQuestions().stream().forEach(System.out::println);
    }
}
