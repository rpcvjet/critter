package com.udacity.jdnd.course3.critter.data.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "customer", targetEntity = Pet.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pet> pets;

    private String name;
    private String phoneNumber;
    private String notes;

    public Customer() {
        //
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return this.pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

}
