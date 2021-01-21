package com.udacity.jdnd.course3.critter.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.udacity.jdnd.course3.critter.data.entities.Employee;
import com.udacity.jdnd.course3.critter.data.entities.Pet;
import com.udacity.jdnd.course3.critter.data.entities.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    public List<Schedule> findSchedulesByPets(Pet pet);

    public List<Schedule> findSchedulesByEmployees(Employee employee);
}
