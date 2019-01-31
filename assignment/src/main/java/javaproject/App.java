package javaproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class App {

	static List<String> mainList;

	public static boolean stringContainsNumber(String s) { // method that checks if a string contains only digits
		return Pattern.compile("[0-9]").matcher(s).find();
	}

	public static void stringPossibilities(String input) {
		String inputWithoutGaps = input.replaceAll("\\p{Z}", ""); // we remove the spaces from the input string
		if (stringContainsNumber(inputWithoutGaps)) {
			List<String> inputPieces = new ArrayList<String>(Arrays.asList(input.split(" ")));
			int index = 0;
			while (index <= (inputPieces.size() - 1)) {
				String str1 = inputPieces.get(index);
				System.out.println(str1 + "while first line");
				if (str1.length() < 4) {
					if (str1.contains("0")) {
						if (str1.length() == 1) {
							addingNewPossibilitiesMethod(str1);
						}
						if (str1.length() == 2) {
							int indexOfZero = str1.indexOf("0");
							if (indexOfZero == 0)
								addingNewPossibilitiesMethod(str1);
							if (indexOfZero == 1) {
								String nextInput = inputPieces.get(index + 1);
								if (nextInput.length() == 1) {
									if (nextInput.equals("0")) {
										String newStr1 = str1.concat(nextInput);
										addingNewPossibilitiesMethod(newStr1);
										inputPieces.remove(index + 1);
									} else {
										String first = str1.concat(nextInput);
										String second = str1.substring(0, 1).concat(nextInput);
										addingNewPossibilitiesMethod(first, second);
										inputPieces.remove(index + 1);
									}
								}
								if (nextInput.length() > 1)
									addingNewPossibilitiesMethod(str1);
							}
						}
						if (str1.length() == 3) {
							int countZero = StringUtils.countMatches(str1, "0");
							int indexOfZero = str1.indexOf("0");
							if (indexOfZero == 0 && countZero == 1) { // case 0xx
								String newNextListElement = str1.substring(1, 3);
								inputPieces.add(index + 1, newNextListElement);
								addingNewPossibilitiesMethod("0");
							}
							if (indexOfZero == 1 && countZero == 1) { // case x0x
								String str2 = str1.substring(0, 2) + "0" + str1.substring(2, 3);
								addingNewPossibilitiesMethod(str1, str2);
							}
							if (indexOfZero == 2 && countZero == 1) { // xx0
								String nextInput = inputPieces.get(index + 1);
								if (nextInput.length() == 1) {
									if (nextInput.equals("0")) {
										String newStr1 = str1.concat(nextInput);
										addingNewPossibilitiesMethod(newStr1);
										inputPieces.remove(index + 1);
									} else {
										String first = str1.concat(nextInput);
										String second = str1.substring(0, 2).concat(nextInput);
										addingNewPossibilitiesMethod(first, second);
										inputPieces.remove(index + 1);
									}
								}
								if (nextInput.length() > 1)
									addingNewPossibilitiesMethod(str1);
							}
							if (indexOfZero == 1 && countZero == 2) { // case x00
								String nextInput = inputPieces.get(index + 1);

								if (nextInput.length() == 1) {
									if (nextInput.equals("0")) {
										String newStr1 = str1.concat(nextInput);
										addingNewPossibilitiesMethod(newStr1);
										inputPieces.remove(index + 1);
									} else {
										String first = str1.concat(nextInput);
										String second = str1.substring(0, 2).concat(nextInput);
										addingNewPossibilitiesMethod(first, second);
										inputPieces.remove(index + 1);
									}
								}
								if (nextInput.length() == 2) {
									int nextInputCountZero = StringUtils.countMatches(nextInput, "0");
									int nextInputIndexOfZero = str1.indexOf("0");
									if (nextInputCountZero == 2) { // case 00
										String newStr1 = str1.concat(nextInput);
										addingNewPossibilitiesMethod(newStr1);
										inputPieces.remove(index + 1);
									}
									if (nextInputCountZero == 0) { // case xx
										String first = str1.substring(0, 1).concat(nextInput);
										String second = str1.concat(nextInput);
										String nextInputWithZeroInside = nextInput.substring(0, 1) + "0"
												+ nextInput.substring(1, 2);
										String third = str1.concat(nextInputWithZeroInside);
										addingNewPossibilitiesMethod(first, second, third);
										inputPieces.remove(index + 1);
									}
									if (nextInputCountZero == 1 && nextInputIndexOfZero == 0) { // case 0x
										String newStr = str1.concat(nextInput);
										addingNewPossibilitiesMethod(newStr);
										inputPieces.remove(index + 1);
									}
									if (nextInputCountZero == 1 && nextInputIndexOfZero == 1) { // case x0
										String secondNextInput = inputPieces.get(index + 2);
										if (secondNextInput.length() == 1) {
											if (secondNextInput.equals("0")) {
												String first = str1.substring(0, 1).concat(nextInput);
												String second = str1.concat(nextInput);
												addingNewPossibilitiesMethod(first, second);
												inputPieces.remove(index + 1);
											} else {
												String substr1 = str1.substring(0, 1);
												String substr2 = nextInput.substring(0, 1);
												String first = substr1.concat(substr2).concat(secondNextInput);
												String second = substr1.concat(nextInput).concat(secondNextInput);
												String third = str1.concat(substr2).concat(secondNextInput);
												String forth = str1.concat(nextInput).concat(secondNextInput);
												addingNewPossibilitiesMethod(first, second, third, forth);
												inputPieces.remove(index + 1);
												inputPieces.remove(index + 1);
											}
										}
										if (secondNextInput.length() > 1) {
											String first = str1.substring(0, 1).concat(nextInput);
											String second = str1.concat(nextInput);
											addingNewPossibilitiesMethod(first, second);
											inputPieces.remove(index + 1);
										}
									}
								}
								if (nextInput.length() == 3)
									addingNewPossibilitiesMethod(str1);
							}
						}
					}
					if (!(str1.contains("0"))) {
						if (str1.length() == 1)
							addingNewPossibilitiesMethod(str1);
						if (str1.length() == 2) {
							char[] number = str1.toCharArray();
							String str2 = number[0] + "0" + number[1];
							addingNewPossibilitiesMethod(str1, str2);
						}
						if (str1.length() == 3) {
							String str2 = str1.substring(0, 2) + "0" + str1.substring(2, 3);
							String str3 = str1.substring(0, 1) + "0" + str1.substring(1, 3);
							String str4 = str1.substring(0, 1) + "0" + str1.substring(1, 2) + "0"
									+ str1.substring(2, 3);
							addingNewPossibilitiesMethod(str1, str2, str3, str4);
						}
					}
				}
				if (str1.length() > 3)
					System.out.println("Invalid input, each number may be up to 3 digits");
				index++;
			}
		} else
			System.out.println("Invalid input, it may contain only numbers");
	}

	public static void addingNewPossibilitiesMethod(String... inputStr) {
		List<String> localList = new ArrayList<String>();
		for (int i = 0; i < inputStr.length; i++) {
			localList.add(i, inputStr[i]);
		}

		List<String> newList = new ArrayList<String>();
		for (int i = 0; i < mainList.size(); i++) {
			for (int j = 0; j < localList.size(); j++) {
				String newStr = mainList.get(i).concat(localList.get(j));
				newList.add(newStr);
			}
		}
		mainList.clear();
		mainList.addAll(newList);
	}

	public static void main(String[] args) {
		mainList = new ArrayList<String>();
		mainList.add("");

		stringPossibilities("69 700 20 6");

		for (int i = 0; i < mainList.size(); i++) {
			System.out.println(i + ": " + mainList.get(i));
		}
	}
}
