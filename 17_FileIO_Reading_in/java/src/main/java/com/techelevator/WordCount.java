package com.techelevator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class WordCount {

	public static void main(String[] args) {
		File myFile = new File("alice.txt");
		int wordCount = 0;
		try (Scanner myScanner = new Scanner(myFile)) {
			while (myScanner.hasNextLine()) {
				String currentLine = myScanner.nextLine().trim();
				if (currentLine.isEmpty() == false) {
					String[] words = currentLine.split(" +");
					wordCount += words.length;
				}
			}
			System.out.println("Word Count: " + wordCount);
		} catch (FileNotFoundException e) {
			System.out.println("File was not found...");
		}
		int sentanceCount = 0;

		try (Scanner myScanner = new Scanner(myFile)) {
			while (myScanner.hasNextLine()) {
				String currentLine = myScanner.nextLine();
				if (currentLine.contains(".")) {
					sentanceCount++;
				}
			}
			System.out.print("Sentance Count: " + sentanceCount);
		} catch (FileNotFoundException e) {
			System.out.println("File was not found...");
		}

	}
}
