package magnus.Detect_Anagram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DetectAnagramTest {

    DetectAnagram detectAnagram;

    @BeforeEach
    void setUp() {
        detectAnagram = new DetectAnagram(true);

    }

    @Test
    void isAnagram() {
        String[] isAnagram= {"design", "signed"};
        assertEquals(true,detectAnagram.isAnagram(isAnagram));

        String[] isAnagram2= {"army", "Mary"};
        assertEquals(true,detectAnagram.isAnagram(isAnagram2));

        String[] isNotAnagram1= {"husare", "mmmmmm"};
        assertNotEquals(true,detectAnagram.isAnagram(isNotAnagram1));

        String[] isNotAnagram2= {"kalle", "kalle"};
        assertNotEquals(true,detectAnagram.isAnagram(isNotAnagram2));

        String[] isNotAnagram3= {"army", "Marys"};
        assertNotEquals(true,detectAnagram.isAnagram(isNotAnagram3));

    }

    @Test
    void isValidInput() {
        String valid = "Mary";
        String notValid1 = "hello2";
        String notValid2 = "hello%";
        String notValid3 = "mary mary";

        assertEquals(true,detectAnagram.isValidInput(valid));
        assertEquals(false,detectAnagram.isValidInput(notValid1));
        assertEquals(false,detectAnagram.isValidInput(notValid2));
        assertEquals(false,detectAnagram.isValidInput(notValid3));
    }

    @Test
    void readInput() {
        String testInput1 = "mary\narmy\n";
        assertEquals("mary",detectAnagram.readInput(testInput1)[0]);

        String testInput2 = "%notValid\nnot Valid&\nmary\narmy\n";
        assertEquals("army",detectAnagram.readInput(testInput2)[1]);

        String testInput3 = "%notValid1\nnot Valid&2\n%notValid3\nnot Valid&4\nmary\narmy\n";
        assertEquals("army",detectAnagram.readInput(testInput3)[1]);

    }
}