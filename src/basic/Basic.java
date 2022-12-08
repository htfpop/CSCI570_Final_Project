package basic;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Basic {

	//constant vars
	private static final int DELTA= 30; 

	// private instance variables 
	private Map<String,Integer> alphaTableMap;
	private char[] dnaA; 
	private char[] dnaB;
	private String dnaAOut = "";
	private String dnaBOut = "";
	private int[][] OPT;
	private int[][] pathArry;
	private int optVal;

	//	private int[][] spaceEfficientOpt;




	public Basic(char[] dnaA,char[] dnaB,Map<String,Integer> alphaTableMap, int gap) {
		this.dnaA = dnaA;
		this.dnaB = dnaB;
		this.alphaTableMap = alphaTableMap;
		initOPTArray();
		buildOptArray();
		backTrace();

	}




	/**
	 * 
	 */
	private void backTrace() {
		int ii = OPT.length-1; 
		int jj = OPT[0].length-1;
		int aa = OPT.length-2;
		int bb = OPT[0].length-2;

		while(aa>= 0 || bb >=0)
		{
			int cameFrom = pathArry[ii][jj];

			if(jj == 0)
			{
				dnaAOut = Character.toString(dnaA[aa]).concat(dnaAOut);
				dnaBOut = "_".concat(dnaBOut);
				aa = aa -1;
				ii = ii-1; //could be jj	
			}
			else if(ii == 0 )
			{
				dnaBOut = Character.toString(dnaB[bb]).concat(dnaBOut);
				dnaAOut = "_".concat(dnaAOut);
				bb = bb -1;
				jj = jj-1; //could be ii	
			}
			if(cameFrom == 2) {
				dnaAOut = Character.toString(dnaA[aa]).concat(dnaAOut);
				dnaBOut = Character.toString(dnaB[bb]).concat(dnaBOut);
				aa = aa-1;
				bb = bb-1;
				ii=ii-1;
				jj=jj-1; 

			}
			else if (cameFrom == 1)
			{
				//string b gets gap character
				dnaAOut = Character.toString(dnaA[aa]).concat(dnaAOut);
				dnaBOut = "_".concat(dnaBOut);
				aa = aa -1;
				ii = ii-1; //could be jj	

			}
			else if(cameFrom == -1)
			{
				//string a get gap character
				dnaBOut = Character.toString(dnaB[bb]).concat(dnaBOut);
				dnaAOut = "_".concat(dnaAOut);
				bb = bb -1;
				jj = jj-1; //could be ii	
			}
		}
	}



	/**
	 * 
	 */

	private void initOPTArray() {
		OPT = new int[dnaA.length+1][dnaB.length+1];
		pathArry = new int[dnaA.length+1][dnaB.length+1];

		for(int ii = 0; ii < OPT.length; ii++)
		{
			OPT[ii][0] = ii*DELTA; 
		}
		for(int ii = 0; ii < OPT[0].length; ii++)
		{
			OPT[0][ii] = ii*DELTA;
		}


	}
	/**
	 * 
	 */
	private void buildOptArray() {

		for(int ii =1; ii<= dnaA.length; ii++)
		{
			for(int jj =1; jj<= dnaB.length; jj++)
			{
				int case1 = getAlpha(dnaA[ii-1],dnaB[jj-1]) + OPT[ii-1][jj-1]; // diagonal 
				int case2 = DELTA + OPT[ii-1][jj]; // vertical index above
				int case3 =  DELTA + OPT[ii][jj-1]; //horizontal to the left
				int curr =  Math.min(case1, Math.min(case2, case3));
				OPT[ii][jj] = curr;

				if (curr == case1)
				{
					pathArry[ii][jj] = 2; //diag
				}
				else if (curr == case2)
				{
					pathArry[ii][jj] = 1; //top
				}
				else
				{
					pathArry[ii][jj] = -1; //left
				}
			}
		}
		optVal =OPT[OPT.length-1][OPT[0].length-1];
	}

	//	/**
	//	 * This method returns the first seq. of DNA
	//	 * @return - DNA_A as a String
	//	 */
	//	public String getDnaA() {
	//		return String.valueOf(dnaA);
	//	}

	/**
	 * This method returns the sequence alignment for DNA_A
	 * @return - DNA_A sequence alignment as a String
	 */
	public String getDnaAOut() {
		return this.dnaAOut;
	}

	//	/**
	//	 * This method returns the second seq. of DNA
	//	 * @return - DNA_B as a String
	//	 */
	//	public String getDnaB() {
	//		return String.valueOf(dnaB);
	//	}

	/**
	 * This method returns the sequence alignment for DNA_B
	 * @return - DNA_B sequence alignment as a String
	 */
	public String getDnaBOut() {
		return this.dnaBOut;
	}

	/**
	 * This method returns the optimal value for this sequence alignment
	 * @return - optimal value as an integer
	 */
	public int getOptVal() { return optVal; }



	/**
	 * This function calculates the Alpha value given 2
	 * character sequences from DNA_A and DNA_B
	 * @param charSeq1 - First character for comparison
	 * @param charSeq2 - second character for comparison
	 * @return - Returns mismatch/match cost
	 */
	public int getAlpha(char charSeq1,char charSeq2 ){

		String charSeq = "" + charSeq1 + charSeq2;
		return alphaTableMap.get(charSeq.toUpperCase());

	}

	//	/**
	//	 * This method is used for debugging.
	//	 * Print out entire OPT array
	//	 */
	//	public void printArray() {
	//
	//		// Loop through all rows
	//		for (int[] ints : OPT) {
	//
	//			// Loop through all elements of current row
	//			for (int anInt : ints) {
	//
	//				System.out.print(anInt + "   ");
	//			}
	//			System.out.println();
	//		}
	//
	//	}

	public void printdnaAOut() {
		System.out.println(dnaAOut);
	}
	public void printdnaBOut() {
		System.out.println(dnaBOut);
	}
	public void printOptVal() {
		System.out.println(String.valueOf(optVal));
	}
	//================================static helper classes =============================================
	/**
	 * This method is the initalphaTableMap this just constructs the alpah map that will be 
	 * in this algorithm
	 * @return : this returns a key value map whose key is the combination of the strings and the value 
	 * is the mismatch given by the problem statement  
	 * 
	 */
	public static Map<String, Integer> initAlphaTableMap() {


		//private static final int[][] alphaTable = new int[][] {
		//  A    C     G    T 
		//	0  , 110 , 48 , 94 }, //A
		//	110, 0   , 118, 48 }, //C
		//	48 , 118 , 0  , 110}, //G
		//	94 , 48  , 110, 0  }  //T
		//};


		Map<String, Integer> alphaTableMap = new HashMap<>();
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
		return alphaTableMap;

	}

	/**
	 * This is a public static method who parses the arguments given... and gives the correct strings 
	 * to be used in the basic algorithm. 
	 * 	@param: args[0] should be the input file; args[1] should be the outputfile
	 *  @return: this returns strings A and B 
	 * 
	 */
	public static String[] parseStrings(String[] args) throws FileNotFoundException {
		String[] dnaSeqs = new String[2];

		if(args.length < 1)
		{
			System.err.println("[ERROR]: There are no input files! Exiting Now...");
			return null;
		}
		boolean isSecondStr = false;
		File f = new File(args[0]);
		Scanner kb = new Scanner(f);
		String dnaSeq1 = kb.nextLine();
		String dnaSeq2 = "";
		int i = 0;

		while(kb.hasNext())
		{

			if(kb.hasNextInt())
			{
				i = kb.nextInt();
				if(!isSecondStr)
				{
					dnaSeq1 = strInsert(dnaSeq1, i);
				}
				else
				{
					dnaSeq2 = strInsert(dnaSeq2, i);
				}

			}
			else
			{
				dnaSeq2 = kb.nextLine();
				isSecondStr = true;
			}
		}

		dnaSeqs[0] = dnaSeq1;
		dnaSeqs[1] = dnaSeq2; 

		kb.close();
		return dnaSeqs;
	}

	/**
	 * Private static helper method to insert substrings into a string
	 * @param s - original string
	 * @param index - insert location
	 * @return - New string with inserted substring
	 */
	private static String strInsert(String s, int index)
	{
		//Nulls or invalid range check
		if(s.length() == 0)
		{
			return "";
		}

		return s.substring(0,index+1)+s+s.substring(index+1);
	}
	public static double getMemoryInKBDouble()
	{
		double total = Runtime.getRuntime().totalMemory();
		return (total - Runtime.getRuntime().freeMemory()) / 1E+3;
	}
	public static double getTimeInMillisecondsDouble()
	{
		return (double) (System.nanoTime()/1E+6);
	}
	//============================main basic call =======================================

	public static void main (String[] args)
	{
		double beforeUsedMem = 0;
		double startTime = 0;
		double afterUsedMem = 0;
		double endTime = 0;
		double totalUsage = 0;
		double totalTime = 0;
		Basic algo = null;
		String[] dnaStrings = null;
		String outputFile = null; 

		if(args[1].length() == 0){
			outputFile = "output.txt";
		}else {

			outputFile = args[1];

		}
		try {
			dnaStrings= parseStrings(args);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Map<String,Integer> alphaTableMap = initAlphaTableMap();

		System.gc();
		beforeUsedMem=getMemoryInKBDouble();
		startTime = getTimeInMillisecondsDouble();
		algo = new Basic(dnaStrings[0].toCharArray(),dnaStrings[1].toCharArray(),alphaTableMap,DELTA);
		endTime = getTimeInMillisecondsDouble();
		afterUsedMem = getMemoryInKBDouble();
		totalUsage = afterUsedMem-beforeUsedMem;
		totalTime = endTime - startTime;
		//System.out.printf("M+N         basic: | %d\r\n",dnaStrings[0].length()+dnaStrings[1].length());
		//System.out.println("totalUsage basic: |"+args[0]+" |"+totalUsage);
		//System.out.println("totalTime  basic: |"+args[0]+" |"+totalTime);
		System.out.printf("%s, %d, %.5f, %.5f \r\n","basic",dnaStrings[0].length()+dnaStrings[1].length(),  totalUsage,  totalTime);

		toFile(algo.getOptVal(), algo.getDnaAOut(), algo.getDnaBOut(), totalUsage, totalTime, outputFile);
	}

	public static void toFile(int optVal, String DNA_A, String DNA_B, double totalMemory, double totalTime, String outFile)
	{
		try
		{
			FileWriter fw = new FileWriter(outFile);
			PrintWriter pw = new PrintWriter(fw);

			pw.write(String.valueOf(optVal));
			pw.println();

			pw.write(DNA_A);
			pw.println();

			pw.write(DNA_B);
			pw.println();

			pw.write(String.valueOf(totalTime));

			pw.println();
			pw.write(String.valueOf(totalMemory));

			pw.println();

			pw.close();

		}
		catch(FileNotFoundException e)
		{
			System.out.printf("[ERROR]: Could not create new file with path %s. Exiting now\r\n",outFile);
			System.exit(-1);
		} catch (IOException e)
		{
			System.out.print("[ERROR]: Could not write to file\r\n");
			throw new RuntimeException(e);
		}
	}
}
