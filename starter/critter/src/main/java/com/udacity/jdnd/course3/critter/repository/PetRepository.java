package com.udacity.jdnd.course3.critter.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.udacity.jdnd.course3.critter.data.entities.Pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> getAllPetsByCustomerId(Long customer_id);
}
