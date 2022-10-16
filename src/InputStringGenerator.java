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



        try
        {
            System.out.printf("Attempting to open %s\r\n", args[0]);
            File f = new File(args[0]);
            Scanner kb = new Scanner(f);

            System.out.printf("--- START FILE CONTENTS ---\r\n");
            while(kb.hasNextLine())
            {
                System.out.println(kb.nextLine());
            }

            System.out.printf("--- END FILE CONTENTS ---\r\n");

            kb.close();
        }catch(Exception e)
        {
            System.err.println("[ERROR]: Could not open file");
            e.printStackTrace();
            System.exit(-1);

        }

    }
}
