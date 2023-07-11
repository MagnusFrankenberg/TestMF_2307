package magnus;


import magnus.Calculator_Problem3.Calculator3;
import magnus.Calculator_Problem4.Calculator4;
import magnus.Calculator_Problem5.Calculator5;
import magnus.Detect_Anagram.DetectAnagram;
import magnus.Find_Links.WebLinkScanner;

import java.util.Scanner;

public class Main {



    public static void main(String[] args) {

        System.out.println("Choose program to run: ");
        System.out.printf("1. Detect Anagram\n" +
                "2. Find Links\n" +
                "3. Calculator One Operator\n" +
                "4. Calculator Multiple operators same precedence\n" +
                "5. Calculator Multiple operators different precedence\n" +
                "6. Exit\n");

        Scanner scanner = new Scanner(System.in);
        int choice;

        while(true){
            if(scanner.hasNextInt()){
                choice = scanner.nextInt();

                switch(choice){
                    case 1 -> new DetectAnagram(false);
                    case 2 -> new WebLinkScanner(false);
                    case 3 -> new Calculator3(false);
                    case 4 -> new Calculator4(false);
                    case 5 -> new Calculator5(false);
                    case 6 -> System.exit(0);
                }
            }
        }
    }
}
