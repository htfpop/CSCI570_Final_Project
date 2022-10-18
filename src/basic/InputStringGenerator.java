package basic;
import java.io.File;
import java.util.Scanner;
import java.lang.*;

public class InputStringGenerator {
	
	
	private String dnaA; 
	private String dnaB; 
	
	
	public InputStringGenerator(String[] args) {
		parseText(args);
	}
	/**
	 * 
	 * @param args
	 */
	private void parseText(String[] args) {
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
	            String dnaSeq1 = kb.nextLine();
	            String dnaSeq2 = "";
	            int i = 0;
	            System.out.printf("this is string a: %s\n", dnaSeq1);
	            while(kb.hasNext())
	            {
	                if(kb.hasNextInt()){
	                    if(!str2){
	                        i = kb.nextInt();
	                        dnaSeq1 = strInsert(dnaSeq1, i);
	                        System.out.printf("SubString Insert @ index %d - %s\r\n", i, dnaSeq1);
	                    }
	                    else{
	                        i = kb.nextInt();
	                        dnaSeq2 = strInsert(dnaSeq2, i);
	                        System.out.printf("SubString Insert @ index %d - %s\r\n", i, dnaSeq2);
	                    }

	                }
	                else{
	                    dnaSeq2 = kb.nextLine();
	                    str2 = true;
	                }
	            }

	            this.dnaA = dnaSeq1;
	            this.dnaB = dnaSeq2; 
	            
	            System.out.printf("--- END FILE CONTENTS ---\r\n");
	            kb.close();

	        }catch(Exception e)
	        {
	            System.err.println("[ERROR]: Could not open file");
	            e.printStackTrace();
	            System.exit(-1);
	        }
	}

	public String getDnaA() {
		return dnaA;
	}
	public String getDnaB() {
		return dnaB;
	}
	

    public static void main (String[] args)
    {
        InputStringGenerator parsedFile = new InputStringGenerator(args);
        String dnaSeqA = parsedFile.getDnaA();
    	String dnaSeqB = parsedFile.getDnaB();

        System.out.printf("A - %s\r\n", dnaSeqA);
        System.out.printf("B - %s\r\n", dnaSeqB);
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
        if(s.length() == 0)
        {
            return "";
        }

        return s.substring(0,index+1)+s+s.substring(index+1);
    }
     public static void test_strInsert()
     {
         String s1 = "ACTG";

         System.out.printf("Initial String - %s\r\n",s1);
         s1 = strInsert(s1, 3);
         System.out.printf("SubString Insert @ index 3 - %s\r\n", s1);
         s1 = strInsert(s1, 6);
         System.out.printf("SubString Insert @ index 6 - %s\r\n", s1);
         s1 = strInsert(s1, 1);
         System.out.printf("SubString Insert @ index 6 - %s\r\n", s1);
         String s2 = "TACG";
         System.out.printf("Initial String - %s\r\n",s2);
         s2 = strInsert(s2, 1);
         System.out.printf("SubString Insert @ index 1 - %s\r\n", s2);
         s2 = strInsert(s2, 2);
         System.out.printf("SubString Insert @ index 2 - %s\r\n", s2);
         s2 = strInsert(s2, 9);
         System.out.printf("SubString Insert @ index 9 - %s\r\n", s2);
     }
}
