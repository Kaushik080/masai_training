package com.hdfc.minibank.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    private String customerID;
    private String name;
    private String phone;
    private String email;
    private String password;
    private LocalDate dateOfBirth;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String MOBILE_REGEX = "^\\d{10}$";

    public Customer() {
    }

    public Customer(String customerID, String name, String phone, String email, String password, LocalDate dateOfBirth) {
        this.customerID = customerID;
        this.name = name;
        setPhone(phone);
        setEmail(email);
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            throw new IllegalArgumentException("Invalid E-Mail ID entered : " + email);
        }
        this.email = email;
    }

    public void setPhone(String mobile) {
        if (!Pattern.matches(MOBILE_REGEX, mobile)) {
            throw new IllegalArgumentException("Invalid Mobile Number Entered : " + mobile);
        }
        this.phone = mobile;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(this == obj){
            return  true;
        }
        Customer customer = (Customer) obj;
        return Objects.equals(customerID, customer.getCustomerID()) && Objects.equals(email, customer.getEmail());
    }

    @Override
    public int hashCode(){
        return Objects.hash(customerID, email);
    }
}
