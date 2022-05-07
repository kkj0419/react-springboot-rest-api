package com.example.gccoffe.model;

import org.springframework.util.Assert;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {
    private final String address;

    public Email(String address) {
        Assert.notNull(address, "address should not be null");
        Assert.isTrue(address.length() >= 4 && address.length() <= 50, "address length must be between 4 and 50 characters");
        Assert.isTrue(checkAddress(address),"Invalid Email Address");
        this.address = address;
    }

    //https://regexr.com
    private static boolean checkAddress(String address) {
        return Pattern.matches("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b", address);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null || getClass() != obj.getClass()) return false;
        Email email = (Email) obj;
        return Objects.equals(address, email.address);
    }

    public String getAddress() {
        return address;
    }
}
