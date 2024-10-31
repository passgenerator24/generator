package com.password.password;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTests {

    @Test
    void shouldThrowErrorWhenNoCriteriaAreSelected() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new PasswordGenerator(10, false, false, false, false);
        });
        assertEquals("No criteria selected for password generation.", exception.getMessage());
    }

    @Test
    void shouldThrowErrorWhenLengthIsZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new PasswordGenerator(0, true, true, true, true);
        });
        assertEquals("Password length must be positive.", exception.getMessage());
    }

    @Test
    void shouldGeneratePasswordWithUppercase() {
        PasswordGenerator generator = new PasswordGenerator(10, true, false, false, false);
        String password = generator.generatePassword();
        assertTrue(password.chars().anyMatch(Character::isUpperCase));
    }

    @Test
    void shouldGeneratePasswordWithLowercase() {
        PasswordGenerator generator = new PasswordGenerator(10, false, true, false, false);
        String password = generator.generatePassword();
        assertTrue(password.chars().anyMatch(Character::isLowerCase));
    }

    @Test
    void shouldGeneratePasswordWithNumbers() {
        PasswordGenerator generator = new PasswordGenerator(10, false, false, true, false);
        String password = generator.generatePassword();
        assertTrue(password.chars().anyMatch(Character::isDigit));
    }

    @Test
    void shouldGeneratePasswordWithSpecialChars() {
        PasswordGenerator generator = new PasswordGenerator(10, false, false, false, true);
        String password = generator.generatePassword();
        assertTrue(password.chars().anyMatch(ch -> "!@#$%^&*".indexOf(ch) >= 0));
    }

    @Test
    void shouldGeneratePasswordWithAllCriteria() {
        PasswordGenerator generator = new PasswordGenerator(10, true, true, true, true);
        String password = generator.generatePassword();
        assertTrue(password.chars().anyMatch(Character::isUpperCase));
        assertTrue(password.chars().anyMatch(Character::isLowerCase));
        assertTrue(password.chars().anyMatch(Character::isDigit));
        assertTrue(password.chars().anyMatch(ch -> "!@#$%^&*".indexOf(ch) >= 0));
    }
}
