package basic.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import basic.InputStringGenerator;
import junit.framework.Assert;

public class TestInputStringGenerator {
	InputStringGenerator parsedFile;
	ArrayList<String> listOfPaths;
	String[] dnaA;
	String[] dnaB;

	/**
	 * SETTING UP BEFORE TEST
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {

		//need to be relative path for this to work on everyones machine
		String filePath = "ProjectRequirements\\\\datapoints\\\\";

		String[] listOfFiles = new String[]{"in0.txt",
											"in1.txt",
											"in2.txt",
											"in3.txt",
											"in4.txt",
											"in5.txt",
											"in6.txt",
											"in7.txt",
											"in8.txt",
											"in9.txt",
											"in10.txt",
											"in11.txt",
											"in12.txt",
											"in13.txt",
											"in14.txt",
											"in15.txt"};
		

		listOfPaths = new ArrayList<>();
		for(String str : listOfFiles) {
			listOfPaths.add(filePath + str); 
		}
		
		
		//testing aginst 
		dnaA = new String[]{"ACACTGACTACTGACTGGTGACTACTGACTGG",
				"CCACCAGG",
				"CTTCTCTTCTTCTTCCCTTCCTCTTCCCTTCC",
				"GGTGTGTGTCACACACATGTGTGTCAGGTGTGTGTCACACACATGTGTGTCACACACACACACA",
				"AAGGAAGGAAAGGAAGGAGGAAGGAGGAAGAAGGAAGAAGGAAGGAAAGGAAGGAGGAAGGAGGAAGAAGGAAGGAAAGGAAGGAGGAAGGAGGAAGGAGAGAAAGGAAGGAGGAAGGAGGAAGGAGA",
				"TTTTTTTCTTTTTTCTTCCTTCTTTCTTTTTTTTCTTTTTTCTTCCTTCTTTCTTCCTTCTCCTTCTTTCTTCCTTCTTTCTTTTTTCTTCCTTCTTTCTTCCTTCTCCTTCTTTCTTCCTTCTCCTTCTCCTTCTTTCTTCCTTCTTTCTTTTTTCTTCCTTCTTTCTTCCTTCTCCTTCTTTCTTCCTTC",
				"GTGTCGTGTGTGTCGTGTGTCGTGTCACAACAGTGTCGGTGTCGTGTGTGTCGTGTGTCGTGTCACAACAGTGTCGTGTCACAACAGTCACAACAGTGTCGTGTCACAACAGTCGTGTCACAACAGTGTCGTGTCACAACAGTCACAACAGTGTCGTGTCACAACATGTCACAACAGTCACAACAGTGTCGTGTCACAACAGTCGTGTCACAACAGTGTCGTGTCACAACAGTCACAACAGTGTCGTGTCACAACA",
				"TGTGGGTGTGTGTGTGGGTGTGTGGGTGTGGTGTGGGTGTGTGTTGTGGGTGTGTGTGTGGGTGTGTGGGTGTGGTGTGGGTGTGTGTGTGGGTGTGTGGGTGTGGTGTGGGTGTGTGTGTGGGTGTGTGGGTGTGGGTGGGGGTGTGGGTGGGGTGGGGGTGTGGGTGGGTGTGGGTGTGTGTGTGGGTGTGTGGGTGTGGGTGGGGGTGTGGGTGGGGTGGGGGTGTGGGTGGGGTGGGTGTGTGGGTGTGGTGTGGGTGTGTGTGTGGGTGTGTGGGTGTGGGTGGGGGTGTGGGTGGGGTGGGGGTGTGGGTGGGTGTGGGTGTGTGTGTGGGTGTGTGGGTGTGGGTGGGGGTGTGGGTGGGGTGGGGGTGTGGGTGGG",
				"GTGTGTCGTGTGTGTCGTGTGTCTCTGTCTCTTCTGTCGTGTGTCGTGTGTGTCGTGTGTCTCTGTCTCTTCTGTCTCTGTGTCTCTGTCTCTTCTGTCTCTTCTGGTGTGTCGTGTGTGTCGTGTGTCTCTGTCTCTTCTGTCGTGTGTCGTGTGTGTCGTGTGTCTCTGTCTCTTCTGTCTCTGTGTCTCTGTCTCTTCTGTCTCTTCTGTGTCTCTGTCTCTTCTGTCTCTTGTGTGTGTCGTGTGTGTCGTGTGTCTCTGTCTCTTCTGTCGTGTGTCGTGTGTGTCGTGTGTCTCTGTCTCTTCTGTCTCTGTGTCTCTGTCTCTTCTGTCTCTTCTGGTGTGTCGTGTGTGTCGTGTGTCTCTGTCTCTTCTGTCGTGTGTCGTGTGTGTCGTGTGTCTCTGTCTCTTCTGTCTCTGTGTCTCTGTCTCTTCTGTCTCTTCTGTGTCTCTGTCTCTTCTGTCTCTTGTCTCTGTCTCTTCTGTCTCTCTCTGTCTCTTCTGTCTCT",
				"AAACCTACCTTTCCTACCTTTAAACCTACCTTTCCAAAAAACCTACCTTTCCTACCTTTAAACCTACCTTTCCAAACCTACCTTTCCTACCTTTAAACCTACCTTTCCTACCTTTACCTACCTTTCCTACCTTTACCTACCTTTCCTACCAAACCTACCTTTCCTACCTTTAAACCTACCTTTCCAAAAAACCTACCTTTCCTACCTTTAAACCTACCTTTCCAAACCTACCTTTCCTACCTTTAAACCTACCTTTCCTACCTTTACCTACCTTTCCTACCTTTACCTACCTTTCCTACCTTTTACCTTTACCTACCTTTCCTACCTTTACCTACCTTTCCTACCTTTCCTACCTTTCCTACCTTTAAACCTACCTTTCCTACCTTTACCTACCTTTCCTACCTTTACCTACCTTTCCTACCTTTTACCTTTACCTACCTTTCCTACCTTTACCTACCTTTCCTACCTTTTTTTACCTTTACCTACCTTTCCTACCTTTACCTACCTTTCCTACCTTTCCTACCTTTCCTACCTTTAAACCTACCTTTCCTACCTTTACCTACCTTTCCTACCTTTACCTACCTTTCCTACCTTTTACCTTTACCTACCTTTCCTACCTTTACCTACCTTTCCTACCTTT",
				"CACACACACACCCCCACACACACCCCCACACACCCCCACACACACACACACCCCCACACACACCCCCACACACCCCCACACACACCCACACACACACCCCCACACACACCCCCACACACCCCCACACACACACACACCCCCACACACACCCCCACACACCCCCACACACACCCCCCACACACACACCCCCACACACACCCCCACACACCCCCACACACACACACACCCCCACACACACCCCCACACACCCCCACACACACCCACACACACACCCCCACACACACCCCCACACACCCCCACACACACACACACACCCCCACACACACCCCCACACACCCCCACACACACACACACCCCCACACACACCCCCACACACCCCCACACACACCCACACACACACCCCCACACACACCCCCACACACCCCCACACACACACACACCCCCACACACACCCCCACACACCCCCACACACACCCCCCACACACACACCCCCACACACACCCCCACACACCCCCACACACACACACACCCCCACACACACCCCCACACACCCCCACACACACCCACACACACACCCCCACACACACCCCCACACACCCCCACACACACACACACCCCCACACACACCCCCACACACCCCCACACACACCCCCACACCCCCCCACACCCCACACCCCCCCACACCCCCACACACACCCCCACACACACCCCCACACACCCCCACACACACCCCCACACCCCCCCACACCCCACACCCCCCCACACCCC",
				"TATTATTATTATAGTGTAGTGTTATTATAGTGTAGTGTAGTATTATTATTATAGTGTAGTGTTATTATAGTGTAGTGTATATTATTATTATAGTGTAGTGTTATTATAGTGTAGTGTAGTATTATTATTATAGTGTAGTGTTATTATAGTGTAGTGTAGTGTAGTGTTATTATAGTGTAGTTATTATTATTATAGTGTAGTGTTATTATAGTGTAGTGTAGTTATTATTATTATAGTGTAGTGTTATTATAGTGTAGTGTAGTATTATTATTATAGTGTAGTGTTATTATAGTGTAGTGTAGTGTAGTGTTATTATAGTGTAGTTATTATTATTATAGTGTAGTGTTATTATAGTGTAGTGTAGTGTAGTGTTATTATAGTGTAGTGTGTTGTAGTGTTATTATAGTGTAGTTATTATTATTATAGTGTAGTGTTATTATAGTGTAGTGTAGTGTAGTGTTATTATAGTGTAGTGTGTGTAGTGTTATTATAGTGTAGTGTGTTGTAGTGTTATTATAGTGTAGTTATTATTATTATAGTGTAGTGTTATTATAGTGTAGTGTAGTGTAGTGTTATTATAGTGTAGTGTGTGTGTAGTGTTATTATAGTGTAGTTATTATTATTATAGTGTAGTGTTATTATAGTGTAGTGTAGTTATTATTATTATAGTGTAGTGTTATTATAGTGTAGTGTAGTATTATTATTATAGTGTAGTGTTATTATAGTGTAGTGTAGTGTAGTGTTATTATAGTGTAGTTATTATTATTATAGTGTAGTGTTATTATAGTGTAGTGTAGTGTAGTGTTATTATAGTGTAGTGTGTTGTAGTGTTATTATAGTGTAGTTATTATTATTATAGTGTAGTGTTATTATAGTGTAGTGTAGTGTAGTGTTATTATAGTGTAGTGTGTGTAGTGTTATTATAGTGTAGTGTGTTGTAGTGTTATTATAGTGTAGTTATTATTATTATAGTGTAGTGTTATTATAGTGTAGTGTAGTGTAGTGTTATTATAGTGTAGTGTGT",
				"CATCATTGCATTGCATTGCATTGCATTGCATTGCATTGCATTCATCATTGCATTGCACATCATTGCATTGCATTGCATTGCATTGCATTCATCATTGCATTGCATTGCATTGCATTGCATTGCATTGCATTCATCATTGCATTGCACATCATTGCATTGCATTGCATTGCATTGCATTGCATTGCATTCATCATTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGGTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGTTGCATTGCATTGCATTGCATTGCATTGTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGGTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGGCATTGCATTCATCATTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGGTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGTTGCATTGCATTGCATCATTGCATTGCATTGCATTGCATTGCATTGCATTGCATTCATCATTGCATTGCACATCATTGCATTGCATTGCATTGCATTGCATTCATCATTGCATTGCATTGCATTGCATTGCATTGCATTGCATTCATCATTGCATTGCACATCATTGCATTGCATTGCATTGCATTGCATTGCATTGCATTCATCATTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGGTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGTTGCATTGCATTGCATTGCATTGCATTGTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGGTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGGCATTGCATTCATCATTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGGTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGTTGCATTGCATTGCATTGCATTGCATTGTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGGTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGTGCATTGCATTGCATTGCATTGCATTGCATTGCATTGGTGCATTGCATTGCATTGCATTGCATTGCATTGCATTG",
				"ACACAACACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACCCACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACCCACCACCCACCCCACCCACCAACACCACCCACCCCACCCACACACAACACCACCCACACACAACACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACCCACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACCCACCACCCACCCCACCCACCAACACCACCCACCCCACCCACACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACCCACACAACACCACCCACCCCAACACAACACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACCCACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACCCACCACCCACCCCACCCACCAACACCACCCACCCCACCCACACACAACACCACCCACACACAACACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACCCACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACCCACCACCCACCCCACCCACCAACACCACCCACCCCACCCACACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACCCACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACCCCCCACCCACCAACACCACCCACCCCACCCACCCACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACCCCCCACCAACACCACCCACCCCACCCACACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACCCCCCACCCACCAACACCACCCACCCCACCCACCCACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACACACAACACCACCCACCCCACCCACCAACACCACCCACCCCACCCACCC",
				"AAGAGAAAAGAGAAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAAGAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAAGAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAGAGATTAGAGATTAAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAAGAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAAGAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAGAGATTAGAGATTAAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATTAGAGAAAAGAGAAAGAGATTAGAGATTAAGAGAAAAGAGAAAGAGATTAGAGATT",
				"GTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGTCTGATGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGTCTGATGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGTCTGATCTACCCGAGTCTGATCTACCCGAAGTGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGTCTGATGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGTCTGATGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGCCGCCTGGTGCCGGGCCAGTCTGGTGCCGGGCCGCCCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGCCGCCTGGTGCCGGGCCAGTCTGGTGCCGGGCCGCCGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGCCGCCTGGTGCCGGGCCAGTCTGGTGCCGGGCCGCCCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGCCGCCTGGTGCCGGGCCAGTCTGGTGCCGGGCCGCCCTGGTGCCGGGCCAGTCTGGTGCCGGGCCGCCTGGTGCCGGGCCAGTCTGGTGCCGGGCCGCCCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGCCGCCTGGTGCCGGGCCAGTCTGGTGCCGGGCCGCCGTCTGATCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGCCGCCTGGTGCCGGGCCAGTCTGGTGCCGGGCCGCCCTACCCGAGTCTGATCTACCCGAAGTCTGGTGCCGGGCCAGTCTGGTGCCGGGCCGCCTGGTGCCGGGCCAGTCTGGTGCCGGGCCGCC"
				
				};
		
		dnaB = new String[]{"TATTATACGCTATTATACGCGACGCGGACGCG",
				"CATGCATG",
				"CCCCGACGACCCCCGACGACCGACGACGACGA",
				"AAAAAAAATTTTAAAAAAATTTTATTTTATTTTAAAAAATTTTAAAAAAATTTTATTTTATTTT",
				"GCGCCGCGCCCGCGCCCGCGCCCCCCCGCGCCCCCCCCGCGCCGCGCCCGCGCCCGCGCCCCCCCGCGCCCCCCCCGCGCCCGCGCCCCCCCGCGCCCCCCCGCGCCCGCGCCCCCCCGCGCCCCCCC",
				"AGAAGAGTTAGAAAAGAAGAGTTAGAAAGAAGAGTTAGAAGAGTTGTTGTTGAGTTGTTGTTGAAGAGTTAGAAGAGTTGTTGTTGAGTTGTAGAAGAGTTAGAAAAGAAGAGTTAGAAAGAAGAGTTAGAAGAGTTGTTGTTGAGTTGTTGTTGAAGAGTTAGAAGAGTTGTTGTTGAGTTGTTGTTTGTT",
				"AGTAAGTAGTAAGTAAAGTAAGTAGTAAGTAAGTAGTAAGTAAGTAAGTAAAGTAAAGTAGTAAGTAAGTAAGTAAAGTAGTAGTAAGTAAGTAAGTAAAGTAAAGTAGTAAAGTAAGTAGTAAGTAAAGTAAGTAGTAAGTAAGTAGTAAGTAAGTAAGTAAAGTAAAGTAGTAAGTAAGTAAGTAAAGTAGTAGTAAGTAAGTAAGTAAAGTAAAGTAGTAAGTAAGTAAGTAAAGTAGTAAGTAAGTAAAGTA",
				"GGAGAGGAGATGGAGATGATTGAGATGATTGATGATAGATGATTGAGATGATTGATGATGATTGAGATGATTGATGATAGATGATTGAGATGATTGATGATTGGAGAGGAGATGGAGATGATTGAGATGATTGATGATAGATGATTGAGATGATTGATGATGATTGAGATGATTGATGATAGATGATTGAGATGATTGATGATTGGAGATGATTGAGATGATTGATGATAGATGATTGAGATGATTGATGATGATTGAGATGATTGATGATAGATGATTGAGATGATTGATGATGGAGATGATTGAGATGATTGATGATAGATGATTGAGATGATTGATGATGATTGAGATGATTGATGATAGATGATTGAGATGATTGATGAT",
				"GTATGGTATGGTATGTATGTATGTATGGTATGGTATGTATGTATGGTATGTATTAGTATGGTATGTATTATTTAGTATGGTATGTATGTATGGTATGTATTAGTATGGTATGTATTATTTAGTATGGTATGTATTATTGTATGGTATGTATTATTGTATGTATGTATGGTATGTATTAGTATGGTATGTATTATTTAGTATGGTATGTATGTATGGTATGTATTAGTATGGTATGTATTATTTAGTATGGTATGTATTATTGTATGGTATGTATTATTGGTATGTATTAGTATGGTATGTATTATTTAGTATGGTATGTATGTATGGTATGTATTAGTATGGTATGTATTATTTAGTATGGTATGTATTATTGTATGGTATGTATTATTGTATGTATGTATGGTATGTATTAGTATGGTATGTATTATTTAGTATGGTATGTATGTATGGTATGTATTAGTATGGTATGTATTATTTAGTATGGTATGTATTATTGTATGGTATGTATTATT",
				"GGGGTTGGGGTTGGTTAATTGGTTAAGGTTAATTGGTTAGGGGTTGGGGTTGGTTAGGGGTTGGGGTTGGTTAATTGGTTAAGGTTAATTGGTTAGGGGTTGGGGTTGGTGGGGTTGGGGTTGGTTAATTGGTTAAGGTTAATTGGTTAGGGGTTGGGGTTGGTTAGGGGTTGGGGTTGGTTAATTGGTTAAGGTTAATTGGTTAGGGGTTGGGGTTGGTTAATTGGTTAAGGTTAATTGGGGGTTGGGGTTGGTTAATTGGTTAAGGTTAATTGGTTAGGGGTTGGGGTTGGTTAATTGGTTAAGGTTAATTGGTTAAAGTTAAAATTGGTTAAGGTTAATTGGGGGTTGGGGTTGGTTAATTGGTTAAGGTTAATTGGTTAGGGGTTGGGGTTGGTTAATTGGTTAAGGTTAATTGGTTAAAGTTAAATAATTGGTTAAGGTTAATTGGGGGTTGGGGTTGGTTAATTGGTTAAGGTTAATTGGTTAGGGGTTGGGGTTGGTTAATTGGTTAAGGTTAATTGGTTAAAGTTAAAATTGGTTAAGGTTAATTGGGGGTTGGGGTTGGTTAATTGGTTAAGGTTAATTGGTTAGGGGTTGGGGTTGGTTAATTGGTTAAGGTTAATTGGTTAAAGTTAAA",
				"TTTTTCTTTTTCTCTCTTTTTCTTTTTCTCTCTTTTTTTCTTTTTCTCTCTTTTTCTTTTTCTCTCTCTCTCTCTTTTTCTTTTTCTCTCTTTTTCTTTTTCTCTCTCTCTTTTTTCTTTTTCTCTCTTTTTCTTTTTCTCTCTCTCTCTCTTTTTCTTTTTCTCTCTTTTTCTTTTTCTCTCTCTCTCTCTCTCTCTCTCTCTCTCCTCTCTCTCTCTCTCTCTCTTTCTTTTTCTCTCTCTCTCTCTTTTTCTTTTTCTCTCTTTTTCTTTTTCTCTCTCTCTTTTTTCTTTTTCTCTCTTTTTCTTTTTCTCTCTCTCTCTCTTTTTCTTTTTCTCTCTTTTTCTTTTTCTCTCTCTCTCTCTCTCTCTCTCTCTCTCCTCTCTCTCTCTCTCTCTCTTTTTTTCTTTTTCTCTCTTTTTCTTTTTCTCTCTCTCTCTCTTTTTCTTTTTCTCTCTTTTTCTTTTTCTCTCTCTCTTTTTTCTTTTTCTCTCTTTTTCTTTTTCTCTCTCTCTCTCTTTTTCTTTTTCTCTCTTTTTCTTTTTCTCTCTCTCTCTCTCTCTCTCTCTCTCTCCTCTCTCTCTCTCTCTCTCTTTCTTTTTCTCTCTCTCTCTCTTTTTCTTTTTCTCTCTTTTTCTTTTTCTCTCTCTCTTTTTTCTTTTTCTCTCTTTTTCTTTTTCTCTCTCTCTCTCTTTTTCTTTTTCTCTCTTTTTCTTTTTCTCTCTCTCTCTCTCTCTCTCTCTCTCTCCTCTCTCTCTCTCTCTCTC",
				"GCCGCGCCGCCAAGCCGCGCCGCGCCGCCAAGCCGCCGCGCCGCCAAGCCGCGCCGCGCCGCCAAGCCGCCAACAAGCCGCCAACAACAAGCCGCCGCCGCGCCGCCAAGCCGCGCCGGCCGCGCCGCCAAGCCGCGCCGCGCCGCCAAGCCGCCGCGCCGCCAAGCCGCGCCGCGCCGCCAAGCCGCCAACAAGCCGCCAACAACAAGCCGCCGCCGCGCCGCCAAGCCGCGCCGCGCCGCCAAGCCGCCGCGCCGCCAAGCCGCGCCGCGCCGCCAAGCCGCCAACAAGCCGCCAACAACAAGCCGCCAAGCCAACAGCCGCGCCGCCAAGCCGCGCCGCGCCGCCAAGCCGCCGCGCCGCCAAGCCGCGCCGCGCCGCCAAGCCGCCAACAAGCCGCCAACAACAAGCCGCCAAGCCAACAAGCCGCCAACAACAAGCCGCCAAAGCCGCCAACAACAAGCCGCCAAAAGCCAACAGCCGCGCCGCCAAGCCGCGCCGCGCCGCCAAGCCGCCGCGCCGCCAAGCCGCGCCGCGCCGCCAAGCCGCCAACAAGCCGCCAACAACAAGCCGCCAAGCCAACAAGCCGCCAACAACAAGCCGCCAAAGCCGCCAACAACAAGCCGCCAACGCCGCCAAGCCGCCGCGCCGCCAAGCCGCGCCGCGCCGCCAAGCCGCCAACAAGCCGCCAACAACAAGCCGCCAAGCCAACAGCCGCGCCGCCAAGCCGCGCCGCGCCGCCAAGCCGCCGCGCCGCCAAGCCGCGCCGCGCCGCCAAGCCGCCAACAAGCCGCCAACAACAAGCCGCCAAGCCAACAAGCCGCCAACAACAAGCCGCCAAAGCCGCCAACAACAAGCCGCCAAAAGCCAACAGCCGCGCCGCCAAGCCGCGCCGCGCCGCCAAGCCGCCGCGCCGCCAAGCCGCGCCGCGCCGCCAAGCCGCCAACAAGCCGCCAACAACAAGCCGCCAAGCCAACAAGCCGCCAACAACAAGCCGCCAAAGCCGCCAACAACAAGCCGCCAA",
				"AAGAGAAGAGTGAAAGAGAAGAGTGAAAGAGAAGAGTGAGTTGAGTGTTGAGTAAGAAGAGAAGAGTGAAAGAGAAGAGTGAAAGAGAAGAGTGAGTTGAGTGTTGAGTAAGAAGAGAAGAGTGAAAGAGAAGAGTGAAAGAGAAGAGTGAGTTGAGTGTTGAGTAAGAGAAGAGTGAGTTGAGTGTTGAGTAGAAGAGTGAGTTGAGTGTTGAGTAAGAGAAGAGTGAAAGAGAAGAGTGAAAGAGAAGAGTGAGTTGAGTGTTGAGTAAGAGAAGAGTGAGTTAAGAGAAGAGTGAAAGAGAAGAGTGAAAGAGAAGAGTGAGTTGAGTGTTGAGTAAGAAGAGAAGAGTGAAAGAGAAGAGTGAAAGAGAAGAGTGAGTTGAGTGTTGAGTAAGAAGAGAAGAGTGAAAGAGAAGAGTGAAAGAGAAGAGTAAGAGAAGAGTGAAAGAGAAGAGTGAAAGAGAAGAGTGAGTTGAGTGTTGAGTAAGAAGAGAAGAGTGAAAGAGAAGAGTGAAAGAGAAGAGTGAGTTGAGTGTTGAGTAAGAAGAGAAGAGTGAAAGAGAAGAGTGAAAGAGAAGAGTGAGTTGAGTGTTGAGTAAGAGAAGAGTGAGTTGAGTGTTGAGTAGAAGAGTGAGTTGAGTGTTGAGTAAGAGAAGAGTGAAAGAGAAGAGTGAAAGAGAAGAGTGAGTTGAGTGTTGAGTAAGAGAAGAGTGAGTTAAGAGAAGAGTGAAAGAGAAGAGTGAAAGAGAAGAGTGAGTTGAGTGTTGAGTAAGAAGAGAAGAGTGAAAGAGAAGAGTGAAAGAGAAGAGTGAGTTGAGTGTTGAGTAAGAAGAGAAGAGTGAAAGAGAAGAGTGAAAGAGAAGAGTGAGTTGAGTGTTGAGTAAGAGAAGAGTGAGTTGAGTGTTGAGTAGAAGAGTGAGTTGAGTGTTGAGTAAGAGAAGAGTGAAAGAGAAGAGTGAAAGAGAAGAGTGAGTTGAGTGTTGAGTAAGAGAAGAGTGAGTTGAGTGTTGAGTAGAAGAGTGAGTTGAGTGTTGAGTGAGTGTTGAGTAGAAGAGTGAGTTGAGTGTTGAGTGAGTTGAGTGTTGAGTAAGAGAAGAGTGAGTTGAGTGTTGAGTAGAAGAGTGAGTTGAGTGTTGAGTAAGAGAAGAGTGAAAGAGAAGAGTGAAAGAGAAGAGTGAGTTGAGTGTTGAGTAAGAGAAGAGTGAGTTGAGTGTTGAGTAGAAGAGTGAGTTGAGTGTTGAGTGAGTGTTGAGTAGAAGAGTGAGTTGAGTGTTGAGT",
				"AAGAGGGAAGAGGGAAGAGGGAAGAGGGGAAGAGGGAAGAGGGAAGAAAGAGGGAAGAGGGAAGAGGGAAGAGGGGAAGAGGGAAGAGGGAAGAGGGAAGAGGGGAAAGAGGGAAGAGGGGAGAAGAGGGAAGAGGGAAGAGGGAAGAGGGGAAGAGGGAAGAGGGAAGAGGGAAGAGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGGGAGGGGGGGAAAGAGGGAAGAGGGAAGAGGGAAGAGGGGAAGAGGGAAGAGGGAAGAGGGAAGAGGGGAAAGAGGGAAGAGGGGAGAAGAGGGAAGAGGGAAGAGGGAAGAGGGGAAGAGGGAAGAGGGAAGAGGGAAGAGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGGGAGGGGGGGAGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGGGAAGAGGGGAAAGAGGGAAGAGGGGAGAAGAGGGAAGAGGGAAGAGGGAAGAGGGGAAGAGGGAAGAGGGAAGAGGGAAGAGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGGGAGGGGGGGAAAGAGGGAAGAGGGAAGAGGGAAGAGGGGAAGAGGGAAGAGGGAAGAGGGAAGAGGGGAAAGAGGGAAGAGGGGAGAAGAGGGAAGAGGGAAGAGGGAAGAGGGGAAGAGGGAAGAGGGAAGAGGGAAGAGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGGGAGGGGGGGAGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGGGAAAGAGGGAAGAGGGGAGGGGAGGGGGGGAGGG",
				"AAGAAGATCAAAGAAGAAGATCAAGAAGATCAAAGAAGAAGATCAAAGAAGATCAAAGAAGATCAAAGAAGAAGATCAAGAAGATCAAAGAAGAAGATCAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAAGAAGATCAATCAATCAAAGAAGATCAAAGAAGAAGATCAAGAAGATCAAAGAAGAAGATCAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAGATCAATCAATCAAAAGAAGATCAAAGAAGAAGATCAAGAAGATCAAAGAAGAAGATCAAGAAGATCAAAGAAGAAGATCAAAGAAGATCAAAGAAGATCAAAGAAGAAGATCAAGAAGATCAAAGAAGAAGATCAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAAGAAGATCAATCAATCAAAGAAGATCAAAGAAGAAGATCAAGAAGATCAAAGAAGAAGATCAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAGATCAATCAATCAAAAGAAGATCAAAGAAGAAGATCAAGAAGATCAAAGAAGAAGATCAAAGAAGATCAAAGAAGATCAAAGAAGAAGATCAAGAAGATCAAAGAAGAAGATCAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAAGAAGATCAATCAATCAAAGAAGATCAAAGAAGAAGATCAAGAAGATCAAAGAAGAAGATCAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAGATCAATCAATCAATCAATCAAAGATCAATCAATCAAAAGAAGATCAATCAATCAAAGAAGATCAAAGAAGAAGATCAAGAAGATCAAAGAAGAAGATCAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAGATCAATCAATCATCAATCAAAGATCAATCAATCAAAAGAAGATCAATCAATCAAAGAAGATCAAAGAAGAAGATCAAGAAGATCAAAGAAGAAGATCAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAGATCAATCAATCAAAGAAGATCAAAGAAGAAGATCAAAGAAGATCAAAGAAGATCAAAGAAGAAGATCAAGAAGATCAAAGAAGAAGATCAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAAGAAGATCAATCAATCAAAGAAGATCAAAGAAGAAGATCAAGAAGATCAAAGAAGAAGATCAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAGATCAATCAATCAATCAATCAAAGATCAATCAATCAAAAGAAGATCAATCAATCAAAGAAGATCAAAGAAGAAGATCAAGAAGATCAAAGAAGAAGATCAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAGATCAATCAATCATCAATCAAAGATCAATCAATCAAAAGAAGATCAATCAATCAAAGAAGATCAAAGAAGAAGATCAAGAAGATCAAAGAAGAAGATCAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAAGAAGATCAATCAATCAAAGATCAATCAATCAAAGATCAATCAATCA",
				"AAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTAAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAATGACGCAAGCTTAATGACGCAAGATGACGCAAGCTTAATGACGCAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAATGACGCAAGCTTAATGACGCAAGATGACGCAAGCTTAATGACGCAAGAGTACATGTTTGAAGTAAATACATGTTTGAAGTACTTAAATACATGTTTGAAGTAAAAAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTAAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAATGACGCAAGCTTAATGACGCAAGATGACGCAAGCTTAATGACGCAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAATGACGCAAGCTTAATGACGCAAGATGACGCAAGCTTAATGACGCAAGAGTACATGTTTGAAGTAAATACATGTTTGAAGTACTTAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAATGACGCAAGCTTAATGACGCAAGATGACGCAAGCTTAATGACGCAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAATGACGCAAGCTTAATGACGCAAGATGACGCAAGCTTAATGACGCAAGAGTAATGACGCAAGCTTAATGACGCAAGATGACGCAAGCTTAATGACGCAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAATGACGCAAGCTTAATGACGCAAGATGACGCAAGCTTAATGACGCAAGAGTACATGTTTGAAGTAAATACATGTTTGAAGTACTTAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAATGACGCAAGCTTAATGACGCAAGATGACGCAAGCTTAATGACGCAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAATGACGCAAGCTTAATGACGCAAGATGACGCAAGCTTAATGACGCAAGAGTACATGTTTGAAGTACTTAATGACGCAAGCTTAATGACGCAAGATGACGCAAGCTTAATGACGCAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAATGACGCAAGCTTAATGACGCAAGATGACGCAAGCTTAATGACGCAAGAGTAATGACGCAAGCTTAATGACGCAAGATGACGCAAGCTTAATGACGCAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAATGACGCAAGCTTAATGACGCAAGATGACGCAAGCTTAATGACGCAAGAGTACATGTTTGAAGTAAATACATGTTTGAAGTACTTAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAATGACGCAAGCTTAATGACGCAAGATGACGCAAGCTTAATGACGCAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAAATACATGTTTGAAGTAAATACATGTTTGAAGTACTTAATGACGCAAGCTTAATGACGCAAGATGACGCAAGCTTAATGACGCAAGAG"
				}; 


	}

	/**
	 * MAIN TEST
	 */
	@Test
	public void test() {
		try {
			int cnt=0; 
			for(String filePath : listOfPaths) {
				InputStringGenerator parsedFile = new InputStringGenerator(filePath);
				System.out.println("Testing file: " + filePath);
				String dnaSeqA = parsedFile.getDnaA();
				String dnaSeqB = parsedFile.getDnaB();
				System.out.println("A :" + dnaSeqA);
				System.out.println("B :" + dnaSeqB);
				assertEquals("DNA A", dnaA[cnt], dnaSeqA);
				assertEquals("DNA B", dnaB[cnt], dnaSeqB);
				System.out.println("=================================================");
				cnt++; 
			}
		}
		catch(FileNotFoundException fnf) {
			System.out.println("File not FOUND");
			fnf.printStackTrace();
		}
		catch(Exception ee) {
			System.out.println("There was a fail in the InputStringGenerator");
			ee.printStackTrace();
		}




	}
}

