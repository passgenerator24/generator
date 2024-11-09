package com.example.cvuser.repository;

import java.util.Optional;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import com.example.cvuser.model.UserModel;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface UserRepository extends JpaRepositoryImplementation<UserModel, Integer>{
    Optional<UserModel> findByLoginAndPassword(String login, String password);
    
}
