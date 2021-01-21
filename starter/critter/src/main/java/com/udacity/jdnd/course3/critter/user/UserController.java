package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.udacity.jdnd.course3.critter.data.entities.Customer;
import com.udacity.jdnd.course3.critter.data.entities.Employee;
import com.udacity.jdnd.course3.critter.data.entities.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into
 * separate user and customer controllers would be fine too, though that is not
 * part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        List<Long> petIds = customerDTO.getPetIds();
        List<Pet> pets = new ArrayList<>();

        if (petIds != null) {
            for (Long petId : petIds) {
                pets.add(petService.findPetbyId(petId));
            }
        }

        Customer customer = convertCustomerDTOToCustomer(customerDTO);
        customer.setPets(pets);
        Customer savedCustomer = customerService.saveCustomer(customer);
        return convertCustomerToCustomerDTO(savedCustomer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
        List<CustomerDTO> customerDTOS = new ArrayList<CustomerDTO>();
        for (Customer customer : customers) {
            customerDTOS.add(convertCustomerToCustomerDTO(customer));
        }
        return customerDTOS;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        Pet pet = petService.findPetbyId(petId); // find the petid
        if (pet.getCustomer() == null) { // if the owner can not be found
            throw new UnsupportedOperationException("Pet doesn't have an owner");
        } else {
            return convertCustomerToCustomerDTO(pet.getCustomer());
        }

    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee emp = convertEmployeeDTOToEmployee(employeeDTO);
        Employee savedemployee = employeeService.postEmployee(emp);
        employeeDTO.setId(savedemployee.getId());
        return employeeDTO;
    }

    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    private Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return convertEmployeeToEmployeeDTO(employeeService.findEmployeeById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setDaysAvailable(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {

        List<Employee> employees = employeeService.findEmployeesForService(employeeDTO.getDate(),
                employeeDTO.getSkills());
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for (Employee employee : employees) {
            employeeDTOS.add(convertEmployeeToEmployeeDTO(employee));
        }

        return employeeDTOS;
    }

    private CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        List<Pet> pets = customer.getPets();

        if (pets != null) {
            List<Long> petIds = new ArrayList<>();

            for (Pet pet : pets) {
                petIds.add(pet.getId());
            }

            customerDTO.setPetIds(petIds);
        }
        return customerDTO;
    }

    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        List<Long> petIds = customerDTO.getPetIds();

        if (petIds != null) {
            List<Pet> pets = new ArrayList<Pet>();

            for (Long petId : petIds) {
                pets.add(petService.findPetbyId(petId));
            }

            customer.setPets(pets);
        }

        return customer;
    }

}
