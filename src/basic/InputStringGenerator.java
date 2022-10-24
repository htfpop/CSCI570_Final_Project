package basic;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class InputStringGenerator {

	//constant vars

	private static HashMap<String,Integer> alphaTableMap;
	private static final int DELTA= 30; 

	// private instance varibles 
	private char[] dnaA; 
	private char[] dnaB;
	private String dnaAOut = "";
	private String dnaBOut = "";
	private int[][] OPT;
	private int optVal;


	/**
	 * This is the constructor of the InputString Generator. 
	 * This is the main constructor for the class.
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public InputStringGenerator(String[] args) throws FileNotFoundException {
		initAlphaTableMap();
		parseText(args);
		initOPTArray();
		buildOptArray();
		backTrack();


	}

	/**
	 * This constructor is only used for unit testing only
	 * @throws FileNotFoundException 
	 */
	public InputStringGenerator(String pathToFile) throws FileNotFoundException {
		String[] strArry = new String[] {pathToFile}; 
		initAlphaTableMap();
		parseText(strArry);
		initOPTArray();
		buildOptArray();
		backTrack();



	}

	private void backTrack() {

		int ii = OPT.length-1; 
		int jj = OPT[0].length-1;
		int aa = OPT.length-2;
		int bb = OPT[0].length-2;
		// dnaAOut = "" + dnaA[ii-1];
		// dnaBOut = "" + dnaB[jj-1];

		while( aa >= 0 || bb >= 0) {
			if(ii == OPT.length-1 && jj == OPT.length-1) {
				dnaAOut = Character.toString(dnaA[aa]);
				dnaBOut = Character.toString(dnaB[bb]);
				aa = aa-1;
				bb = bb-1;

			}

			int case1_Diag = OPT[ii-1][jj-1];
			int case2_above = OPT[ii-1][jj]; 
			int case3_left = OPT[ii][jj-1];
			int minVal = Math.min(case1_Diag,Math.min(case3_left, case2_above));


			if(minVal == case1_Diag) {
				// update pointer
				//TODO: check this 
				if(aa <0) {
					dnaBOut = Character.toString(dnaB[bb]).concat(dnaBOut);
					dnaAOut = "_".concat(dnaAOut);
					bb = bb -1; 
				}
				else if (bb <0) {
					dnaAOut = Character.toString(dnaA[aa]).concat(dnaAOut);
					dnaBOut = "_".concat(dnaBOut);
					aa=aa-1;

				}
				else {
					dnaAOut = Character.toString(dnaA[aa]).concat(dnaAOut);
					dnaBOut = Character.toString(dnaB[bb]).concat(dnaBOut);
					ii = ii-1;
					jj = jj-1;
					aa = aa-1;
					bb = bb -1;
					}

			}
			else if(minVal == case2_above) {
				if(aa <0) {
					dnaBOut = Character.toString(dnaB[bb]).concat(dnaBOut);
					dnaAOut = "_".concat(dnaAOut);
					bb = bb -1; 
				}
				else if (bb <0) {
					dnaAOut = Character.toString(dnaA[aa]).concat(dnaAOut);
					dnaBOut = "_".concat(dnaBOut);
					aa=aa-1;

				}
				else { 
					dnaAOut = "_".concat(dnaAOut);
					dnaBOut = Character.toString(dnaB[bb]).concat(dnaBOut);
					ii = ii-1;
					bb = bb -1; 
				}
			}
			else if(minVal == case3_left) {
				if(aa <0) {
					dnaBOut = Character.toString(dnaB[bb]).concat(dnaBOut);
					dnaAOut = "_".concat(dnaAOut);
					bb = bb -1; 
				}
				else if (bb <0) {
					dnaAOut = Character.toString(dnaA[aa]).concat(dnaAOut);
					dnaBOut = "_".concat(dnaBOut);
					aa=aa-1;

				}
				else { 
				dnaAOut = Character.toString(dnaA[aa]).concat(dnaAOut);
				dnaBOut = "_".concat(dnaBOut);
				jj = jj-1;
				aa = aa-1;
				}
			}


		}

	}


	/**
	 * 
	 */

	private void initOPTArray() {
		OPT = new int[dnaA.length+1][dnaB.length+1];
		for(int ii = 0; ii < OPT.length; ii++) {
			OPT[ii][0] = ii*DELTA; 
		}
		for(int ii = 0; ii < OPT[0].length; ii++) {
			OPT[0][ii] = ii*DELTA;
		}

	}
	/**
	 * 
	 */
	private void buildOptArray() {

		for(int ii =1; ii<= dnaA.length; ii++) {
			for(int jj =1; jj<= dnaB.length; jj++) {
				int case1 = getAlpha(dnaA[ii-1],dnaB[jj-1]) + OPT[ii-1][jj-1]; // diagonal 
				int case2 = DELTA + OPT[ii-1][jj]; // vertical index above
				int case3 =  DELTA + OPT[ii][jj-1]; //horizontal to the left 
				OPT[ii][jj]= Math.min(case1, Math.min(case2, case3));

			}
		}
		optVal =OPT[OPT.length-1][OPT[0].length-1];

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

		this.dnaA = dnaSeq1.toCharArray();
		this.dnaB = dnaSeq2.toCharArray(); 

		//System.out.printf("--- END FILE CONTENTS ---\r\n");
		kb.close();

	}

	/**
	 * This method returns the first seq. of DNA
	 * @return
	 */
	public String getDnaA() {
		return String.valueOf(dnaA);
	}
	/**
	 * This method returns the second seq. of DNA 
	 * @return
	 */
	public String getDnaB() {
		return String.valueOf(dnaB);
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

	/**
	 * 
	 */
	private void initAlphaTableMap() {


		//private static final int[][] alphaTable = new int[][] {
		//  A    C     G    T 
		//	0  , 110 , 48 , 94 }, //A
		//	110, 0   , 118, 48 }, //C
		//	48 , 118 , 0  , 110}, //G
		//	94 , 48  , 110, 0  }  //T
		//};


		alphaTableMap = new HashMap<>();
		//row 1
		alphaTableMap.put("AA", 0);
		alphaTableMap.put("AC", 110);
		alphaTableMap.put("AG", 48);
		alphaTableMap.put("AT", 94);

		//row 2 
		alphaTableMap.put("CA", 110);
		alphaTableMap.put("CC", 0);
		alphaTableMap.put("CG", 118);
		alphaTableMap.put("CT", 48);
		//row 3 
		alphaTableMap.put("GA", 48);
		alphaTableMap.put("GC", 118);
		alphaTableMap.put("GG", 0);
		alphaTableMap.put("GT", 110);
		//row 4 
		alphaTableMap.put("TA", 94);
		alphaTableMap.put("TC", 48);
		alphaTableMap.put("TG", 110);
		alphaTableMap.put("TT", 0);

	}

	/**
	 * 
	 * @param charSeq This a two character string array to take
	 * @return
	 */

	public int getAlpha(char charSeq1,char charSeq2 ){

		String charSeq = "" + charSeq1 + charSeq2;
		return alphaTableMap.get(charSeq.toUpperCase());

	}
	public void printArray() {

		// Loop through all rows
		for (int i = 0; i < OPT.length; i++) {

			// Loop through all elements of current row
			for (int j = 0; j < OPT[i].length; j++) {

				System.out.print(OPT[i][j] + " ");
			}
			System.out.println();
		}  

	}

	public void printdnaAOut() {
		System.out.println(dnaAOut);
	}
	public void printdnaBOut() {
		System.out.println(dnaBOut);
	}
	public void printOptVal() {
		System.out.println(String.valueOf(optVal));
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
