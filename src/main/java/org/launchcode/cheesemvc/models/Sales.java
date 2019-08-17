package org.launchcode.cheesemvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Sales {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=40)
    private String itemOfSale;

    @NotNull
    @Size(min=1, message="Size must not be empty")
    private String dateOfSale;

    @NotNull
    private Double priceOfSale;


    public Sales(String itemOfSale, String dateOfSale, Double priceOfSale) {
        this.itemOfSale = itemOfSale;
        this.dateOfSale = dateOfSale;
        this.priceOfSale = priceOfSale;
    }

    public Sales() {
    }


    public int getId() {
        return id;
    }

    public String getItemOfSale() {
        return itemOfSale;
    }

    public void setItemOfSale(String itemOfSale) {
        this.itemOfSale = itemOfSale;
    }

    public String getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(String dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public Double getPriceOfSale() {
        return priceOfSale;
    }

    public void setPriceOfSale(Double priceOfSale) {
        this.priceOfSale = priceOfSale;
    }
}
