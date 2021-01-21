package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.udacity.jdnd.course3.critter.data.entities.Customer;
import com.udacity.jdnd.course3.critter.data.entities.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Customer customer = null;

        if ((Long) petDTO.getOwnerId() != null) {
            customer = customerService.findCustomerById(petDTO.getOwnerId());
        }

        Pet pet = convertPetDTOToPet(petDTO); // convert from the front end
        pet.setCustomer(customer);
        Pet savedPet = petService.savePet(pet);

        if (customer != null) {
            customer.addPet(savedPet);
        }

        return convertPetToPetDTO(savedPet); // return to the front what we want them to see
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.findPetbyId(petId); // find the pet by id

        if (pet != null) { // if it's NOT null, return the pet to front end in DTO format
            return convertPetToPetDTO(pet);
        }

        return null;
    }

    @GetMapping
    public List<PetDTO> getPets() {
        List<Pet> retrievedPets = petService.getAllPets(); // get all the pets from DB
        List<PetDTO> petDTOS = new ArrayList<>(); // create an array of petDTOS

        for (Pet pet : retrievedPets) { // lets loop through all the pets from the DB and convert to petDTOs
            petDTOS.add(convertPetToPetDTO(pet));
        }

        return petDTOS; // return the pets in DTO format to the users
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.findPetsByOwner(ownerId);
        List<PetDTO> petDTOS = new ArrayList<PetDTO>();

        for (Pet pet : pets) {
            petDTOS.add(convertPetToPetDTO(pet));
        }

        return petDTOS;
    }

    private PetDTO convertPetToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        if (pet.getCustomer() != null) {
            petDTO.setOwnerId(pet.getCustomer().getId());
        }

        return petDTO;
    }

    private Pet convertPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }
}
