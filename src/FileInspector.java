import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.*;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class FileInspector {
    public static void main(String[] args) {
        //Declare Variables and set up file reader
        Scanner inFile;
        String line;
        JFileChooser chooser = new JFileChooser();
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src"); // set the chooser to the project src directory
        chooser.setCurrentDirectory(target.toFile());

        try {
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                target = chooser.getSelectedFile().toPath();
                inFile = new Scanner(target);

                while (inFile.hasNextLine()) {
                    line = inFile.nextLine();
                    System.out.println(line);

                    lineCount = lineCount + 1; // Add one for every time through

                    // Count the number of words
                    wordCount = line.split("\\s+").length + wordCount; //every space equals a new word

                    // Count the number of characters in the line
                    charCount = line.length() + charCount;
                }

                // Print the summary report
                System.out.println("\nSummary Report:");
                System.out.println("File: " + target.toFile().getName());
                System.out.println("Number of lines: " + lineCount);
                System.out.println("Number of words: " + wordCount);
                System.out.println("Number of characters: " + charCount);

                inFile.close();
            } else { // User did not pick a file
                System.out.println("Sorry, you must select a file! Terminating!");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Error");
            e.printStackTrace();
        } catch (Exception e) { // code to handle exception
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}
