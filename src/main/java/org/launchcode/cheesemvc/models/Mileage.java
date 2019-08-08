package org.launchcode.cheesemvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Mileage {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=40)
    private String eventOfMileage;

    @NotNull
    @Size(min=1, message="Email must not be empty")
    private String numberOfMileage;

    @ManyToOne
    private Event event;


    public Mileage(String eventOfMileage, String numberOfMileage) {
        this.eventOfMileage = eventOfMileage;
        this.numberOfMileage = numberOfMileage;
    }

    public Mileage() {
    }


    public int getId() {
        return id;
    }

    public String getEventOfMileage() {
        return eventOfMileage;
    }

    public void setEventOfMileage(String eventOfMileage) {
        this.eventOfMileage = eventOfMileage;
    }

    public String getNumberOfMileage() {
        return numberOfMileage;
    }

    public void setNumberOfMileage(String numberOfMileage) {
        this.numberOfMileage = numberOfMileage;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}

