package com.velasquez.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.velasquez.model.Doctor;
import com.velasquez.repository.DoctorRepository;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public List<Doctor> getAll(){
        return  doctorRepository.findAll();
    }

    public void save(Doctor doctor){
        doctorRepository.save(doctor);
    }

    public void update(Doctor doctor){
        doctorRepository.save(doctor);
    }

    public void delete(Doctor doctor){
        doctorRepository.delete(doctor);
    }

    public Doctor findById(String code){
        return doctorRepository.findById(code).orElse(null);
    }

}
