package org.launchcode.cheesemvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=40)
    private String nameOfCustomer;

    @NotNull
    @Size(min=1, message="Email must not be empty")
    private String emailOfCustomer;


    public Customer(String nameOfCustomer, String emailOfCustomer) {
        this.nameOfCustomer = nameOfCustomer;
        this.emailOfCustomer = emailOfCustomer;
    }

    public Customer() {
    }


    public int getId() {
        return id;
    }

    public String getNameOfCustomer() {
        return nameOfCustomer;
    }

    public void setNameOfCustomer(String aNameOfCustomer) {
        this.nameOfCustomer = aNameOfCustomer;
    }

    public String getEmailOfCustomer() {
        return emailOfCustomer;
    }

    public void setEmailOfCustomer(String aEmailOfCustomer) {
        this.emailOfCustomer = aEmailOfCustomer;
    }

}
