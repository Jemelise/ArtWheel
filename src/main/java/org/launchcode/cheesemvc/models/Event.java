package org.launchcode.cheesemvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1, message="Must not be empty")
    private String name;

    @OneToMany
    @JoinColumn(name = "event_id")
    private List<Mileage> miles = new ArrayList<>();

    public Event() {

    }

    public Event(String name) {
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Mileage> getMiles() {
        return miles;
    }
}


