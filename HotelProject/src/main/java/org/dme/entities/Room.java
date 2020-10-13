package org.dme.entities;

import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Where(clause ="active = 1")
public class Room {

    private int number;
    private int finaleBasePrice;
    private int occupation;
    private int active;
    private Registration registration;

    public Room() {
    }


    public Room(int finaleBasePrice, int occupation, int active) {
        this.finaleBasePrice = finaleBasePrice;
        this.occupation = occupation;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public int getFinaleBasePrice() {
        return finaleBasePrice;
    }

    public void setFinaleBasePrice(int finaleBasePrice) {
        this.finaleBasePrice = finaleBasePrice;
    }

    public int getOccupation() {
        return occupation;
    }

    public void setOccupation(int occupation) {
        this.occupation = occupation;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Access(AccessType.PROPERTY)
    @OneToOne//(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="room")
    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }
}
