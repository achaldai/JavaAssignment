package javaproject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class App {

	static List<String> mainList;

	public static boolean stringContainsNumber(String s) { // method that checks if a string contains only digits
		return Pattern.compile("[0-9]").matcher(s).find();
	}

	public static void stringPossibilities(String input) {		
		String str1 = input.replaceAll("\\p{Z}", ""); // we remove the spaces from the input string
		if (stringContainsNumber(str1)) {
			String[] array = input.split(" ", input.length());
			for (int i = 0; i < array.length; i++) {
				if (array[i].length() == 1) {

				} else if (array[i].length() == 2) {
					if (array[i].contains("0")) {
						int index = array[i].indexOf("0");
						if (index == 0) {

						} else {

						}
					} else {
						String one = array[i];
						char[] number = array[i].toCharArray();
						String two = number[0] + "0" + number[1];
						addingNewPossibilitiesMethod(one, two);
					}
				} else if (array[i].length() == 3) {

				} else
					System.out.println("Invalid input, each number may be up to 3 digits");
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

	public static void test() {
		addingNewPossibilitiesMethod("69", "609");
		addingNewPossibilitiesMethod("77");
		addingNewPossibilitiesMethod("96", "906");
		addingNewPossibilitiesMethod("72", "702");
		addingNewPossibilitiesMethod("55", "505");
	}

	public static void main(String[] args) {
		mainList = new ArrayList<String>();
		mainList.add("");

		stringPossibilities("69 77 96 72 55");;
		for (int i = 0; i < mainList.size(); i++) {
			System.out.println(i + ": " + mainList.get(i));
		}
	}
}
