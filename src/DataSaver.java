import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.ArrayList;
import java.util.Scanner;


public class DataSaver {
    public static void main(String[] args) {
        //Declare and initialize variables
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> recs = new ArrayList<>();
        int idCounter = 1;
        String firstName = "";
        String lastName = "";
        String email = "";
        String fileName = "";
        int yearOfBirth;
        boolean notDone;

        do {
            firstName = SafeInput.getNonZeroLenString(scanner, "Enter First Name");
            lastName = SafeInput.getNonZeroLenString(scanner, "Enter Last Name");
            email = SafeInput.getRegExString(scanner, "Enter Email", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
            yearOfBirth = SafeInput.getRangedInt(scanner, "Enter Year of Birth", 0, 9999);

            String idNumber = String.format("%06d", idCounter++); //format as a 6 digit number
            recs.add(String.format("%s, %s, %s, %s, %04d", firstName, lastName, idNumber, email, yearOfBirth));

            notDone = SafeInput.getYNConfirm(scanner, "Do you want to enter another record? (Y/N)");
        } while (notDone);

        fileName = SafeInput.getNonZeroLenString(scanner, "Enter the filename (with .csv extension)"); //Get file name to save to

        //Write to file, code from video
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath(), "src", fileName); //Put in user file name here
        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));
            for (String rec : recs) {
                writer.write(rec, 0, rec.length());

                writer.newLine();
            }

            writer.close();
            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}