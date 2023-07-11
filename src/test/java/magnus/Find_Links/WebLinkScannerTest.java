package magnus.Find_Links;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

class WebLinkScannerTest {

    WebLinkScanner webLinkScanner;

    @BeforeEach
    void setUp() {
        webLinkScanner = new WebLinkScanner(true);
    }

    @Test
    void isValidURL() {
        String invalidURL = "NotValidURL";
        String validURL = "https://github.com/MagnusFrankenberg";

        assertEquals(false,webLinkScanner.isValidURL(invalidURL));
        assertEquals(true,webLinkScanner.isValidURL(validURL));
    }

    @Test
    void printWebElements() {
        assertEquals(false,webLinkScanner.printWebElements(null));
    }
}