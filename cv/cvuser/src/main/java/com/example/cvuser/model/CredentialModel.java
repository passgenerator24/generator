package com.example.cvuser.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="credential_table")
public class CredentialModel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String account;
    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserModel user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CredentialModel that = (CredentialModel) o;
        return Objects.equals(id, that.id) && Objects.equals(account, that.account)
                && Objects.equals(password, that.password) && Objects.equals(username, that.username);

    }
     @Override
     public int hashCode() {
         return Objects.hash(id, account, password, username);
     }

    @Override
    public String toString() {
        return "UserModel{" + "id=" + id + ",account=" + account + ",username=" + username + ",password=" + password+"}";
    } 
}
