package com.udacity.jdnd.course3.critter.service;

import java.util.List;

import javax.transaction.Transactional;

import com.udacity.jdnd.course3.critter.data.entities.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PetService {

    @Autowired
    PetRepository petRepository;

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet findPetbyId(Long id) {
        return petRepository.getOne(id);
    }

    public List<Pet> findPetsByOwner(Long customerid) {
        return petRepository.getAllPetsByCustomerId(customerid);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

}
