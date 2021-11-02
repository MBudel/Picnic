package com.example.Picnic.model.rest.post;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class LoginPost {
    private String username;
    private String password;
    private final String client_id = "1";

    public LoginPost() {
    }

    public LoginPost(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private static String createMD5Password(String passWord) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(passWord.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        StringBuilder hashedPassword = new StringBuilder(bigInt.toString(16));
        while(hashedPassword.length() < 32 ){
            hashedPassword.insert(0, "0");
        }
        return hashedPassword.toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        try {
            return  "{" +
                    "\"key\":" + "\"" + username + "\"" + "," +
                    "\"secret\":" + "\"" + createMD5Password(password) + "\"" + "," +
                    "\"client_id\":" + "\"" + client_id + "\"" +
                    "}";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
