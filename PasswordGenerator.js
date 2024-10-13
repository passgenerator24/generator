class PasswordGenerator {
    constructor(length, hasUpperCase, hasLowerCase, hasNumbers, hasSpecialChars) {
        this.length = length;
        this.hasUpperCase = hasUpperCase;
        this.hasLowerCase = hasLowerCase;
        this.hasNumbers = hasNumbers;
        this.hasSpecialChars = hasSpecialChars;
    }

    generatePassword() {
        const upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        const lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        const numbers = "0123456789";
        const specialChars = "!@#$%^&*";

        let chars = '';
        if (this.hasUpperCase) chars += upperCaseLetters;
        if (this.hasLowerCase) chars += lowerCaseLetters;
        if (this.hasNumbers) chars += numbers;
        if (this.hasSpecialChars) chars += specialChars;

        if (chars.length === 0) throw new Error('No criteria selected for password generation.');

        let password = '';
        for (let i = 0; i < this.length; i++) {
            const randomIndex = Math.floor(Math.random() * chars.length);
            password += chars[randomIndex];
        }

        return password;
    }
}

// Example usage
const passwordGenerator = new PasswordGenerator(12, true, true, true, true);
console.log(passwordGenerator.generatePassword());
