package com.app.model;

public class UserDtls {
    private int id;
    private String name;
    private String phoneNumber;
    private String role;
    private String password;

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
   
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
