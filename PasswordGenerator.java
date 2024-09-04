package passwordpioneers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator 
{

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        
        //user input
        System.out.println("What will your username be?");
        String user = scanner.nextLine();

        System.out.println("Hello " + user + ", how many characters would you like the password to have?:");
        int length = scanner.nextInt();
        scanner.nextLine();
         
        System.out.print("Next, you will be prompted to state your requirements for the password. Please respond with y/n. Press enter to continue.");
        scanner.nextLine();
       
        System.out.println("Include lowercase letters? (y/n)");
        boolean includeLowercase = scanner.next().equalsIgnoreCase("y");
        
        System.out.println("Include uppercase letters? (y/n)");
        boolean includeUppercase = scanner.next().equalsIgnoreCase("y");
        
        System.out.println("Include digits? (y/n)");
        boolean includeDigits = scanner.next().equalsIgnoreCase("y");
        
        System.out.println("Include special characters? (y/n)");
        boolean includeSpecial = scanner.next().equalsIgnoreCase("y");

        scanner.close();
        
        //character sets
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        String special = "!@#$%^&*()-_=+[]{}|;:,.<>?/~`";
        
        StringBuilder charPool = new StringBuilder();
        
        if (includeLowercase) 
        {
            charPool.append(lowercase);
        }
        if (includeUppercase) 
        {
            charPool.append(uppercase);
        }
        if (includeDigits) 
        {
            charPool.append(digits);
        }
        if (includeSpecial) 
        {
            charPool.append(special);
        }
        
        // Step 3: Password Generation
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        List<Character> requiredCharacters = new ArrayList<>();
        
        if (includeLowercase) 
        {
            requiredCharacters.add(lowercase.charAt(random.nextInt(lowercase.length())));
        }
        if (includeUppercase) 
        {
            requiredCharacters.add(uppercase.charAt(random.nextInt(uppercase.length())));
        }
        if (includeDigits) 
        {
            requiredCharacters.add(digits.charAt(random.nextInt(digits.length())));
        }
        if (includeSpecial) 
        {
            requiredCharacters.add(special.charAt(random.nextInt(special.length())));
        }
        
        // Add required characters first
        for (char ch : requiredCharacters) 
        {
            password.append(ch);
        }
        
        // Fill the rest of the password length with random characters
        for (int i = requiredCharacters.size(); i < length; i++) 
        {
            password.append(charPool.charAt(random.nextInt(charPool.length())));
        }
        
        // Shuffle the password to ensure randomness
        char[] passwordArray = password.toString().toCharArray();
        for (int i = 0; i < passwordArray.length; i++) 
        {
            int randomIndex = random.nextInt(passwordArray.length);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[randomIndex];
            passwordArray[randomIndex] = temp;
        }
        
        // Convert to string and output the result
        String finalPassword = new String(passwordArray);
        System.out.println("Your username is: " + user);
        System.out.println("Your password is: " + finalPassword);
    }
}

