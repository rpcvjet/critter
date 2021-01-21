package com.udacity.jdnd.course3.critter.service;

import java.util.List;

import javax.transaction.Transactional;

import com.udacity.jdnd.course3.critter.data.entities.Employee;
import com.udacity.jdnd.course3.critter.data.entities.Pet;
import com.udacity.jdnd.course3.critter.data.entities.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesByPet(Pet pet) {
        return scheduleRepository.findSchedulesByPets(pet);
    }

    public List<Schedule> getSchedulesByEmployee(Employee employee) {
        return scheduleRepository.findSchedulesByEmployees(employee);
    }
}
