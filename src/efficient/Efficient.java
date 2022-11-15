package efficient;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import basic.Basic;

public class Efficient {
	//constant vars
	private static final int DELTA= 30; 

	// private instance variables 
	private Map<String,Integer> alphaTableMap;
	private char[] dnaA; 
	private char[] dnaB;
	private String dnaAOut = "";
	private String dnaBOut = "";
	private int[][] pathArry;
	private int optVal;

	private int[][] OPT;



	public Efficient(char[] dnaA,char[] dnaB,Map<String,Integer> alphaTableMap, int gap){
		//this.dnaA = dnaA;
		//this.dnaB = dnaB;
		this.alphaTableMap = alphaTableMap;
		optVal= divideCAlignment(dnaA,dnaB);



	}


	private int divideCAlignment(char[] dnaA, char[] dnaB) {
		//BaseCases 
		Basic algo;
		int opt1 =0;
		int opt2 =0;
		ArrayList<Integer> P = new ArrayList<>();
		if(dnaA.length <= 2 || dnaB.length <= 2 ){
			algo = new Basic(dnaA, dnaB, alphaTableMap,DELTA);
			return algo.getOptVal();
		}
		int[][] BL = spaceEfficientAlignment(dnaA,Arrays.copyOfRange(dnaB, 0, dnaB.length/2));
		int[][] BR = spaceEfficientAlignmentBackward(dnaA,Arrays.copyOfRange(dnaB, dnaB.length/2, dnaB.length));

		int q  = findOptimalQ(BL, BR);
		P.add(q);
		P.add(dnaB.length/2);

		opt1 += divideCAlignment(Arrays.copyOfRange(dnaA, 0  , q         ),   Arrays.copyOfRange(dnaB  ,0              , dnaB.length/2));
		opt2 += divideCAlignment(Arrays.copyOfRange(dnaA,  q, dnaA.length), Arrays.copyOfRange(dnaB  , dnaB.length/2, dnaB.length));

		return opt1 + opt2; 
	}



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

	public int findOptimalQ (int[][]BL, int[][]BR)
	{
		int Q = 0;
		int last_sum = Integer.MAX_VALUE;//[0][0] + BR[0][0];
		//Loop through all values of BL/BR
		for(int i = 0; i < BL.length; i++)
		{
			int sum = BL[i][1] + BR[i][1];
			if(sum < last_sum ) {
				last_sum = sum;
				Q = i; 
			}

		}
		//Find index value in BL/BR
		return Q;
	}
	/**
	 * 
	 */
	//x to be dna A -->m
	//y to be dna B -->n 
	private int[][] spaceEfficientAlignment(char[] X, char[] Y)
	{

		//Array B[0 .. m, 0 .. 1]
		int[][] B = new int[X.length+1][2];


		//Initialize B[i,0] = i * Delta
		for(int ii=0; ii < X.length+1; ii++) {
			B[ii][0] = ii*DELTA;
		}


		for(int j = 1; j <= Y.length; j++)
		{
			B[0][1] = j * DELTA;

			for(int i = 1; i <= X.length; i++) {
				//B[0][1] = i * DELTA;

				int f_term = getAlpha(X[i-1], Y[j-1]) + B[i - 1][0]; //diag
				int s_term = DELTA + B[i-1][1]; // top
				int t_term = DELTA + B[i][0]; //left
				B[i][1] = Math.min(f_term, Math.min(s_term, t_term));

			}

			//Column copy from column 1 to 0
			for(int k = 0; k <= X.length; k++)
			{
				B[k][0] = B[k][1];
			}
		}
		return B; 
	}

	private int[][] spaceEfficientAlignmentBackward(char[] X, char[] Y)
	{

		//Array B[0 .. m, 0 .. 1]
		int[][] B = new int[X.length+1][2];


		//Initialize B[i,0] = i * Delta
		for(int ii=X.length-1; ii >= 0; ii--) {
			B[ii][0] = (X.length - ii)*DELTA;
		}


		for(int j = Y.length-1; j >=0; j--)//col
		{
			B[X.length][1] = (Y.length -j) * DELTA;

			for(int i = X.length-1; i >= 0; i--) {//rows
				//B[0][1] = i * DELTA;

				int f_term = getAlpha(X[i], Y[j]) + B[i + 1][0]; //diag
				int s_term = DELTA + B[i+1][1]; // top
				int t_term = DELTA + B[i][0]; //left
				B[i][1] = Math.min(f_term, Math.min(s_term, t_term));

			}
			//Column copy from column 1 to 0
			for(int k = X.length; k >= 0; k--)
			{
				B[k][0] = B[k][1];
			}

		}			

		return B; 

		/*
		String myX = String.valueOf(X);
		String myY = String.valueOf(Y);
		myX =  new StringBuffer(myX).reverse().toString();
		myY =  new StringBuffer(myY).reverse().toString();
		X = myX.toCharArray();
		Y = myY.toCharArray();
		int[][] B = spaceEfficientAlignment(X,Y);
		int[][] Brev = new int[B.length][B[0].length];
		int jj = B.length -1; 
		for(int ii = 0; ii < B.length; ii++) {
			Brev[ii][0] = B[jj][0];
			Brev[ii][1] = B[jj][1];
			jj--; 		
		}


		return B;
		 */

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
	 * This method returns the optimal value for this sequence alignment
	 * @return - optimal value as an integer
	 */
	public int getOptVal() { return optVal; }

	public void printdnaAOut() {
		System.out.println(dnaAOut);
	}
	public void printdnaBOut() {
		System.out.println(dnaBOut);
	}
	public void printOptVal() {
		System.out.println(String.valueOf(optVal));
	}
	/**
	 * This method returns the first seq. of DNA
	 * @return - DNA_A as a String
	 */
	public String getDnaA() {
		return String.valueOf(dnaA);
	}
	/**
	 * This method returns the second seq. of DNA 
	 * @return - DNA_B as a String
	 */
	public String getDnaB() {
		return String.valueOf(dnaB);
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

	//====================
	public static double getMemoryInKB() {
		double total = Runtime.getRuntime().totalMemory();
		return (total-Runtime.getRuntime().freeMemory())/10e3;
	}
	public static double getTimeInMilliseconds() {
		return System.nanoTime()/10e6;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
