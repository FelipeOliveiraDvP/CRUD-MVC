/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.security.*;
import java.math.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author felipe
 */
public class User {
    
    private int iduser;    
    private String username;
    private String password;
    private String role;
   
    public User(String username, String password)
    {
        this.setUsername(username);
        this.setPassword(password);
    }
    
    public User(){}
     // GETTERS E SETTERS
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
    
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = toMD5(password);
    }
    
     public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
  
    // Converte string em md5
    private String toMD5(String password)
    {
       try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            
            m.update(password.getBytes(),0,password.length());
            
            return  new BigInteger(1, m.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
       return "";
    }
     // 

    @Override
    public String toString() {
        return "User{" + "iduser=" + iduser + ", username=" + username + ", password=" + password + ", role=" + role + '}';
    }
    
   
    
}
