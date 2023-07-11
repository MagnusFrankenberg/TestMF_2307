package magnus.Find_Links;

import magnus.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;

public class WebLinkScanner {
    private boolean isTest;
    Scanner scanner;
    WebDriver webDriver;

    public WebLinkScanner(boolean isTest) {
        this.isTest = isTest;
        webDriver = new ChromeDriver();
        if(!isTest) {
            getUserInput();
        }
    }

    public void getUserInput() {
        List<WebElement> webelements;
        String input;
        scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a URL: (exit = exit)");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                webDriver.quit();
                break;
            } else if (isValidURL(input)) {
                webelements = findWebElements();
                printWebElements(webelements);
            }
        }
        Main.main(null);
    }

    public boolean isValidURL(String userInputURL) {
        try {
            webDriver.get(userInputURL);
        } catch (InvalidArgumentException e) {
            System.out.println("URL not valid, try again");
            return false;
        }
        return true;
    }

    public List<WebElement> findWebElements() {
        List<WebElement> webElements;
        try {
            webElements = webDriver.findElements(By.tagName("a"));
            return webElements;
        } catch (Exception e) {
            System.out.println("Could not find any webelements");
            return null;
        }
    }

    public boolean printWebElements(List<WebElement> webElements) {
        try {
            for (WebElement element : webElements) {
                String linkURL = element.getAttribute("href");
                System.out.println(linkURL);
            }
            return true;
        } catch (Exception e) {
            System.out.println("There were no elements to print");
            return false;
        }
    }
}
