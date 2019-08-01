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
    @Size(min=3, max=15)
    private String nameOfArtwork;

    @NotNull
    @Size(min=1, message="Size must not be empty")
    private String sizeOfArtwork;

    @ManyToOne
    private Category category;

    public Artwork(String nameOfArtwork, String sizeOfArtwork) {
        this.nameOfArtwork = nameOfArtwork;
        this.sizeOfArtwork = sizeOfArtwork;
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
}
