import java.io.File;
import java.util.Scanner;
import java.lang.*;

public class InputStringGenerator {
    //public String a;
    //public String b;

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
            
            boolean str2 = false;
            System.out.printf("Attempting to open %s\r\n", args[0]);
            File f = new File(args[0]);
            Scanner kb = new Scanner(f);


            System.out.printf("--- START FILE CONTENTS ---\r\n");
            //setting a as first string (will need to make this global later)
            String a = kb.nextLine();
            String b = "";
            int i = 0;
            System.out.printf("this is string a: %s\n", a);
            while(kb.hasNext())
            {
                if(kb.hasNextInt()){
                    if(!str2){
                        i = kb.nextInt();
                        //System.out.println(i);
                        a = strInsert(a, i);      
                        System.out.printf("SubString Insert @ index %d - %s\r\n", i, a);
                    }
                    else{
                        //System.out.println("we are now in b");
                        i = kb.nextInt();
                        //System.out.println(i);
                        b = strInsert(b, i);      
                        System.out.printf("SubString Insert @ index %d - %s\r\n", i, b);
                    }

                }
                else{
                    b = kb.nextLine();
                    //System.out.println("this is string b: " + b);
                    str2 = true;
                }
            }

            System.out.printf("A = %s\r\n",a);
            System.out.printf("B = %s\r\n",b);

            System.out.printf("--- END FILE CONTENTS ---\r\n");

            kb.close();
        }catch(Exception e)
        {
            System.err.println("[ERROR]: Could not open file");
            e.printStackTrace();
            System.exit(-1);

        }

        //test_strInsert();

        
    }
    /**
     * Helper method to insert substrings into a string
     * @param s - original string
     * @param index - insert location
     * @return - New string with inserted substring
     */
    public static String strInsert(String s, int index)
    {
        //Nulls or invalid range check
        if(s.length() == 0 || index == 0)
        {
            return "";
        }

        return s.substring(0,index+1)+s+s.substring(index+1);
    }
    // public static void test_strInsert()
    // {
    //     String s1 = "ACTG";

    //     System.out.printf("Initial String - %s\r\n",s1);
    //     s1 = strInsert(s1, 3);
    //     System.out.printf("SubString Insert @ index 3 - %s\r\n", s1);
    //     s1 = strInsert(s1, 6);
    //     System.out.printf("SubString Insert @ index 6 - %s\r\n", s1);
    //     s1 = strInsert(s1, 1);
    //     System.out.printf("SubString Insert @ index 6 - %s\r\n", s1);
    //     String s2 = "TACG";
    //     System.out.printf("Initial String - %s\r\n",s2);
    //     s2 = strInsert(s2, 1);
    //     System.out.printf("SubString Insert @ index 1 - %s\r\n", s2);
    //     s2 = strInsert(s2, 2);
    //     System.out.printf("SubString Insert @ index 2 - %s\r\n", s2);
    //     s2 = strInsert(s2, 9);
    //     System.out.printf("SubString Insert @ index 9 - %s\r\n", s2);
    // }
}
