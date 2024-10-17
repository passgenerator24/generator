package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class CRUDRunner {

    public static void main(String[] args) {
        try {
            ClassLoader classLoader = CRUDRunner.class.getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("accountkey.json")).getFile());
            FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://code-vault-7a6d5-default-rtdb.firebaseio.com")
                .build();

            FirebaseApp.initializeApp(options);
            SpringApplication.run(CRUDRunner.class, args);
            
            String path = classLoader.getResource("accountkey.json").getPath();
            System.out.println("Resolved file path: " + path);
            
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error initializing Firebase: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("File not found: accountkey.json");
        }
    }
}
