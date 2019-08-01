package org.launchcode.cheesemvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Artwork {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=40)
    private String nameOfArtwork;

    @NotNull
    @Size(min=1, message="Size must not be empty")
    private String sizeOfArtwork;

    @NotNull
    @Size(min=1, message="Price must be determined")
    private String priceOfArtwork;

    @ManyToOne
    private Category category;

    public Artwork(String nameOfArtwork, String sizeOfArtwork, String priceOfArtwork) {
        this.nameOfArtwork = nameOfArtwork;
        this.sizeOfArtwork = sizeOfArtwork;
        this.priceOfArtwork = priceOfArtwork;
    }

    public Artwork() {
    }


    public int getId() {
        return id;
    }

    public String getNameOfArtwork() {
        return nameOfArtwork;
    }

    public void setNameOfArtwork(String aNameOfArtwork) {
        this.nameOfArtwork = aNameOfArtwork;
    }

    public String getSizeOfArtwork() {
        return sizeOfArtwork;
    }

    public void setSizeOfArtwork(String aSizeOfArtwork) {
        this.sizeOfArtwork = aSizeOfArtwork;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPriceOfArtwork() {
        return priceOfArtwork;
    }

    public void setPriceOfArtwork(String priceOfArtwork) {
        this.priceOfArtwork = priceOfArtwork;
    }
}
