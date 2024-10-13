// PasswordGenerator.test.js
const { PasswordGenerator } = require('./PasswordGenerator'); // Import the class

// Test 1: Generate a Password with All Criteria
test('should generate password with all criteria', () => {
    const generator = new PasswordGenerator(12, true, true, true, true);
    const password = generator.generatePassword();
    expect(password.length).toBe(12);
    expect(password).toMatch(/[A-Z]/);  // At least one uppercase letter
    expect(password).toMatch(/[a-z]/);  // At least one lowercase letter
    expect(password).toMatch(/[0-9]/);  // At least one number
    expect(password).toMatch(/[!@#$%^&*]/);  // At least one special character
});

// Test 2: Generate a Password with Only Numbers
test('should generate password with only numbers', () => {
    const generator = new PasswordGenerator(8, false, false, true, false);
    const password = generator.generatePassword();
    expect(password.length).toBe(8);
    expect(password).toMatch(/^[0-9]+$/);  // Only numbers
});

// Test 3: Throw Error When No Criteria Are Selected
test('should throw error when no criteria are selected', () => {
    expect(() => {
        new PasswordGenerator(8, false, false, false, false).generatePassword();
    }).toThrow('No criteria selected for password generation.');
});