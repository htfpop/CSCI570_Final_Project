import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputStringGenerator {
    public static void main (String[] args)
    {
        System.out.printf("--- Input File Generator main ---\r\n");

        if(args.length < 1)
        {
            System.err.println("[ERROR]: There are no input files! Exiting Now...");
            System.exit(-1);
        }

        System.out.printf("Attempting to %s\r\n", args[0]);
/*
        try
        {
            File f = new File(args[0]);
            FileInputStream fis = new FileInputStream(f);



        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

*/
    }
}
