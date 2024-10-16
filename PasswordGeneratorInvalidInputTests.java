package codevault.pwgenerator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordGeneratorInvalidInputTests 
{

    @Test
    void testInvalidPasswordLength() 
    {
        // Simulate invalid length input (e.g., length of 0)
        int invalidLength = 0;
        boolean includeLowercase = true;
        boolean includeUppercase = true;
        boolean includeDigits = true;
        boolean includeSpecial = true;

        //  an invalid length should throw an exception or return a specific result
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
        {
            generatePassword(invalidLength, includeLowercase, includeUppercase, includeDigits, includeSpecial);
        });

        assertEquals("Password length must be greater than 0", exception.getMessage());
    }

    @Test
    void testNoCharacterTypeSelected() 
    {
        // simulate input where no character types are selected
        int length = 10;
        boolean includeLowercase = false;
        boolean includeUppercase = false;
        boolean includeDigits = false;
        boolean includeSpecial = false;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
        {
            generatePassword(length, includeLowercase, includeUppercase, includeDigits, includeSpecial);
        });

        assertEquals("At least one character type must be selected", exception.getMessage());
    }

    // Mock method simulating password generation
    private String generatePassword(int length, boolean includeLowercase, boolean includeUppercase, boolean includeDigits, boolean includeSpecial) 
    {
        // Example validation logic based on input
        if (length <= 0) 
        {
            throw new IllegalArgumentException("Password length should be greater than 0");
        }
        if (!includeLowercase && !includeUppercase && !includeDigits && !includeSpecial) 
        {
            throw new IllegalArgumentException("At least one character type must be selected");
        }
        // This would return the password generation logic from the real generator
        return "mockPassword";
    }
}
