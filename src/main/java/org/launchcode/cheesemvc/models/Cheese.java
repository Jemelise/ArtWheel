package org.launchcode.cheesemvc.models;

import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Cheese {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String nameOfCheese;

    @NotNull
    @Size(min=1, message="Description must not be empty")
    private String descriptionOfCheese;

    @ManyToOne
    private Category category;

    public Cheese(String nameOfCheese, String descriptionOfCheese) {
        this.nameOfCheese = nameOfCheese;
        this.descriptionOfCheese = descriptionOfCheese;
    }

    public Cheese() {
    }


    public int getId() {
        return id;
    }

    public String getNameOfCheese() {
        return nameOfCheese;
    }

    public void setNameOfCheese(String aNameOfCheese) {
       this.nameOfCheese = aNameOfCheese;
    }

    public String getDescriptionOfCheese() {
        return descriptionOfCheese;
    }

    public void setDescriptionOfCheese(String aDescriptionOfCheese) {
        this.descriptionOfCheese = aDescriptionOfCheese;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
