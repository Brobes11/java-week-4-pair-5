package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FindAndReplace {

	private static Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Enter the word you'd like to search: ");
		String wordSearch = userInput.nextLine();

		System.out.println("Enter the word you'd like to replace it with: ");
		String replaceWord = userInput.nextLine();

		System.out.print("Enter the path of a file to search: ");
		String sourcePath = userInput.nextLine();
		File sourceFile = new File(sourcePath);

		if (sourceFile.exists() && sourceFile.isFile()) {
			System.out.print("Enter the path for the new file: ");
			String destPath = userInput.nextLine();
			File destFile = new File(destPath);

			try (Scanner sourceScanner = new Scanner(sourceFile)) {
				if (destFile.createNewFile() || destFile.exists()) {
					try (PrintWriter destWriter = new PrintWriter(destFile)) {
						while (sourceScanner.hasNextLine()) {
							String line = sourceScanner.nextLine();
							destWriter.println(line.replaceAll(wordSearch, replaceWord));
						}
					}
					System.out.println("New file finished.");
				} else {
					throw new IOException();
				}
			} catch (FileNotFoundException e) {
				System.out.println("File was not found: " + e.getMessage());
			} catch (IOException e) {
				System.out.println("Couldn't create the new file.");
			}

		} else {
			System.out.println("That's not an existing file!");
		}

	}

}
