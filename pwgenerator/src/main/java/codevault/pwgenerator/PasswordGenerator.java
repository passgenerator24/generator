package codevault.pwgenerator;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;
import com.mysql.cj.xdevapi.Statement;


public class PasswordGenerator 
{

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

         // Load database configuration
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("src/config.properties")) 
        {
            props.load(in);
        } catch (IOException e) 
        {
            e.printStackTrace();
            return;
        }


        //Database connection
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String mypassword = props.getProperty("db.password");


        try ( Connection myConn = DriverManager.getConnection(url, user, mypassword) ) 
        {   
            //connection attempt
            System.out.println("Connection successful!");
            
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }

        //user input
        System.out.println("What will your username be?");
        String user_names = scanner.nextLine();
        
        System.out.println("How many characters would you like the password to have?:");
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
        System.out.println("Your username is: " + user_names);
        System.out.println("Your password is: " + finalPassword);
        
        

  	    
    }
}


