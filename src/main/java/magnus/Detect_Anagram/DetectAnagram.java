package magnus.Detect_Anagram;

import magnus.Main;

import java.util.Arrays;
import java.util.Scanner;

public class DetectAnagram {
    private boolean isTest;
    Scanner scanner;

    public DetectAnagram(boolean isTest) {
        this.isTest = isTest;
        if (!isTest) {
            run();
        }
    }

    public void run(){
        while(true) {
            isAnagram(readInput(null));
        }
    }


    public boolean isValidInput(String string) {
        boolean notValid = string.matches(".*[\\d\\W].*");
        return !notValid;
    }

    public String[] readInput(String testStr) {
        String[] strings = new String[2];
        scanner = isTest ? new Scanner(testStr) : new Scanner(System.in);

        while (true) {
            System.out.println("***Insert two words to check if they are anagrams (type a digit 0-9 to exit)***\nWord1:");
            if(scanner.hasNextInt()){
                scanner.nextLine();
                Main.main(null);
            }

            strings[0] = scanner.nextLine();
            System.out.println("Word2:");
            strings[1] = scanner.nextLine();

            if (isValidInput(strings[0]) && isValidInput(strings[1])) {
                return strings;
            } else {
                System.out.println("Your input is not valid, try again\n");
            }
        }
    }


    public boolean isAnagram(String[] strings) {

        if (strings[0].length() != strings[1].length()) {
            System.out.println("False");
            return false;
        }

        if (strings[0].equals(strings[1])) {
            System.out.println("You have input the same word two times");
            return false;
        }

        char[] string1 = strings[0].toLowerCase().toCharArray();
        char[] string2 = strings[1].toLowerCase().toCharArray();

        Arrays.sort(string1);
        Arrays.sort(string2);

        for (int i = 0; i < string1.length; i++) {
            if (string1[i] != string2[i]) {
                System.out.println("False");
                return false;
            }
        }
        System.out.println("True");
        return true;
    }



}



