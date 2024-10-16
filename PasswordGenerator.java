package com.password.password;

import java.util.Random;

public class PasswordGenerator {
    private final int length;
    private final boolean includeUppercase;
    private final boolean includeLowercase;
    private final boolean includeNumbers;
    private final boolean includeSpecialChars;

    public PasswordGenerator(int length, boolean includeUppercase, boolean includeLowercase,
                             boolean includeNumbers, boolean includeSpecialChars) {
        if (length <= 0) {
            throw new IllegalArgumentException("Password length must be positive.");
        }
        if (!includeUppercase && !includeLowercase && !includeNumbers && !includeSpecialChars) {
            throw new IllegalArgumentException("No criteria selected for password generation.");
        }
        this.length = length;
        this.includeUppercase = includeUppercase;
        this.includeLowercase = includeLowercase;
        this.includeNumbers = includeNumbers;
        this.includeSpecialChars = includeSpecialChars;
    }

    public String generatePassword() {
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        StringBuilder characters = new StringBuilder();

        if (includeUppercase) {
            characters.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
        if (includeLowercase) {
            characters.append("abcdefghijklmnopqrstuvwxyz");
        }
        if (includeNumbers) {
            characters.append("0123456789");
        }
        if (includeSpecialChars) {
            characters.append("!@#$%^&*");
        }

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }
}
