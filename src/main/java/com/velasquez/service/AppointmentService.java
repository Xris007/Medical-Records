package com.velasquez.service;

import com.velasquez.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.velasquez.model.Appointment;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public List<Appointment> getAll(){
        return appointmentRepository.findAll();
    }

    public void save(Appointment appointment){
        appointmentRepository.save(appointment);
    }

    public void update(Appointment appointment){
        appointmentRepository.save(appointment);
    }

    public void delete(Appointment appointment){
        appointmentRepository.delete(appointment);
    }

    public Appointment findById(Long id){
        return appointmentRepository.findById(id).orElse(null);
    }

    public List<Appointment> findByDoctor(String code){
        return appointmentRepository.findByDoctor(code);
    }
}
