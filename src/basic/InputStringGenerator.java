package basic;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputStringGenerator {


	private String dnaA; 
	private String dnaB; 

	/**
	 * This is the constructor of the InputString Generator. 
	 * This is the main constructor for the class.
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public InputStringGenerator(String[] args) throws FileNotFoundException {
		parseText(args);
	}

	/**
	 * This constructor is only used for unit testing only
	 * @throws FileNotFoundException 
	 */
	public InputStringGenerator(String pathToFile) throws FileNotFoundException {
		String[] strArry = new String[] {pathToFile}; 
		parseText(strArry);
	}

	/**
	 * private Helper method that does the heavy lifting of parsing the file  
	 * @param args
	 */
	private void parseText(String[] args) throws FileNotFoundException {
		//System.out.printf("--- Input File Generator main ---\r\n");

		if(args.length < 1)
		{
			System.err.println("[ERROR]: There are no input files! Exiting Now...");
			return;
		}
		boolean str2 = false;
		System.out.printf("Attempting to open %s\r\n", args[0]);
		File f = new File(args[0]);
		Scanner kb = new Scanner(f);


		//System.out.printf("--- START FILE CONTENTS ---\r\n");
		String dnaSeq1 = kb.nextLine();
		String dnaSeq2 = "";
		int i = 0;
		//System.out.printf("this is string a: %s\n", dnaSeq1);
		while(kb.hasNext())
		{
			if(kb.hasNextInt()){
				if(!str2){
					i = kb.nextInt();
					dnaSeq1 = strInsert(dnaSeq1, i);
					//System.out.printf("SubString Insert @ index %d - %s\r\n", i, dnaSeq1);
				}
				else{
					i = kb.nextInt();
					dnaSeq2 = strInsert(dnaSeq2, i);
					//System.out.printf("SubString Insert @ index %d - %s\r\n", i, dnaSeq2);
				}

			}
			else{
				dnaSeq2 = kb.nextLine();
				str2 = true;
			}
		}

		this.dnaA = dnaSeq1;
		this.dnaB = dnaSeq2; 

		//System.out.printf("--- END FILE CONTENTS ---\r\n");
		kb.close();

	}

	/**
	 * This method returns the first seq. of DNA
	 * @return
	 */
	public String getDnaA() {
		return dnaA;
	}
	/**
	 * This method returns the second seq. of DNA 
	 * @return
	 */
	public String getDnaB() {
		return dnaB;
	}

	/**
	 * Private Helper method to insert substrings into a string
	 * @param s - original string
	 * @param index - insert location
	 * @return - New string with inserted substring
	 */
	private String strInsert(String s, int index)
	{
		//Nulls or invalid range check
		if(s.length() == 0)
		{
			return "";
		}

		return s.substring(0,index+1)+s+s.substring(index+1);
	}


	public static void main (String[] args)
	{
		InputStringGenerator parsedFile = null;
		try {
			parsedFile = new InputStringGenerator(args);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String dnaSeqA = parsedFile.getDnaA();
		String dnaSeqB = parsedFile.getDnaB();

		System.out.printf("A - %s\r\n", dnaSeqA);
		System.out.printf("B - %s\r\n", dnaSeqB);
	}


}
