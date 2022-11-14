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

	private int[][] spaceEfficientOpt;

	
	
	
	public Basic(char[] dnaA,char[] dnaB,Map<String,Integer> alphaTableMap, int gap) {
		this.dnaA = dnaA;
		this.dnaB = dnaB;
		this.alphaTableMap = alphaTableMap;
		initOPTArray();
		buildOptArray();
		backTrace();

		//space efficient
		spaceEfficientAlgo();
		
	}

	private void spaceEfficientAlgo()
	{
		int maxLen = Math.max(dnaA.length, dnaB.length);

		//Array B[0 .. m, 0 .. 1]
		spaceEfficientOpt = new int[maxLen][2];

		//Initialize B[i,0] = i * Delta
		for(int i = 0; i < spaceEfficientOpt.length; i++)
		{
			spaceEfficientOpt[i][0] = i * DELTA;
		}

		//TODO: for j=1,...,n
		for(int j = 1; j <= dnaA.length; j++)
		{
			spaceEfficientOpt[0][1] = j * DELTA;

			//TODO: for 1=1,...,m
			for(int i = 1; i <= dnaB.length; i++)
			{
				int f_term = getAlpha(String.valueOf(dnaA).charAt(i), String.valueOf(dnaB).charAt(j)) + spaceEfficientOpt[i - 1][0];
				int s_term = DELTA + spaceEfficientOpt[i - 1][1];
				int t_term = DELTA + spaceEfficientOpt[i][0];
				spaceEfficientOpt[i][1] = Math.min(f_term, Math.min(s_term, t_term));
			}

			//Column copy from column 1 to 0
			for(int k = 0; k < spaceEfficientOpt.length; k++)
			{
				spaceEfficientOpt[k][0] = spaceEfficientOpt[k][1];
			}


		}


	}
/*
	/**
	 * This is the constructor of the InputString Generator. 
	 * This is the main constructor for the class.
	 * @param args
	 * @throws FileNotFoundException 
	 
	public Basic(String[] args) throws FileNotFoundException {
		initAlphaTableMap();
		parseText(args);
		initOPTArray();
		buildOptArray();
		backTrace();


	}

	/**
	 * This constructor is only used for unit testing only
	 * @throws FileNotFoundException 
	 
	public Basic(String pathToFile) throws FileNotFoundException {
		String[] strArry = new String[] {pathToFile}; 		
		initOPTArray();
		buildOptArray();
		backTrace();
	}
	*/
	
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

	/**
	 * This method returns the first seq. of DNA
	 * @return - DNA_A as a String
	 */
	public String getDnaA() {
		return String.valueOf(dnaA);
	}

	/**
	 * This method returns the sequence alignment for DNA_A
	 * @return - DNA_A sequence alignment as a String
	 */
	public String getDnaAOut() {
		return this.dnaAOut;
	}

	/**
	 * This method returns the second seq. of DNA 
	 * @return - DNA_B as a String
	 */
	public String getDnaB() {
		return String.valueOf(dnaB);
	}

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

	/**
	 * This method is used for debugging.
	 * Print out entire OPT array
	 */
	public void printArray() {

		// Loop through all rows
		for (int[] ints : OPT) {

			// Loop through all elements of current row
			for (int anInt : ints) {

				System.out.print(anInt + "   ");
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
	public static double getMemoryInKB() {
		double total = Runtime.getRuntime().totalMemory();
		return (total-Runtime.getRuntime().freeMemory())/10e3;
		}
	public static double getTimeInMilliseconds() {
		return System.nanoTime()/10e6;
		}
	
//============================main basic call =======================================

	public static void main (String[] args)
	{
		Basic algo = null;
		try {
			String[] dnaStrings = parseStrings(args);
			Map<String,Integer> alphaTableMap = initAlphaTableMap();
			double beforeUsedMem=getMemoryInKB();
			double startTime = getTimeInMilliseconds();
			assert dnaStrings != null;
			algo = new Basic(dnaStrings[0].toCharArray(),dnaStrings[1].toCharArray(),alphaTableMap,DELTA);
			double afterUsedMem = getMemoryInKB();
			double endTime = getTimeInMilliseconds();
			double totalUsage = afterUsedMem-beforeUsedMem;
			double totalTime = endTime - startTime;
			toFile(algo.getOptVal(), algo.getDnaAOut(), algo.getDnaBOut(), totalUsage, totalTime, "output.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assert algo != null;

		algo.printOptVal();
		algo.printdnaAOut();
		algo.printdnaBOut();
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

			pw.write(String.valueOf(totalMemory));
			pw.println();

			pw.write(String.valueOf(totalTime));
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
