package com.velasquez.data;

import com.velasquez.model.Client;
import com.velasquez.model.Doctor;
import com.velasquez.repository.AppointmentRepository;
import com.velasquez.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.velasquez.model.Appointment;
import com.velasquez.repository.DoctorRepository;

import java.time.LocalDate;

@Component
public class ApplicationData implements CommandLineRunner {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {

        Doctor doctor = new Doctor();
        doctor.setDoctorCode("D001");
        doctor.setFullName("Connor Blake");
        doctor.setSpecialty("General Medicine");

        doctorRepository.save(doctor);

        Doctor doctor1 = new Doctor();
        doctor1.setDoctorCode("D002");
        doctor1.setFullName("Stefany Garcia");
        doctor1.setSpecialty("Pediatrician");

        doctorRepository.save(doctor1);

        Doctor doctor2 = new Doctor();
        doctor2.setDoctorCode("D003");
        doctor2.setFullName("Patrick Kerkhoff");
        doctor2.setSpecialty("Dermatologist");

        doctorRepository.save(doctor2);

        Doctor doctor3 = new Doctor();
        doctor3.setDoctorCode("D004");
        doctor3.setFullName("Chris Roudebush");
        doctor3.setSpecialty("Surgeon");

        doctorRepository.save(doctor3);


        Client client = new Client();
        client.setDni("78010543");
        client.setFullName("Jane Doe");
        client.setBirthDate(LocalDate.of(1999,5,13));
        client.setAge(21);
        client.setSex("F");
        client.setHeight("168 cm");
        clientRepository.save(client);


        Appointment appointment = new Appointment();
        appointment.setHospitalName("General Hospital");
        appointment.setAddress("Av. Miraflores 123");
        appointment.setCause("Head ache");
        appointment.setRoom("Room 1");
        appointment.setDate(LocalDate.of(2020,7,26));
        appointment.setDoctor(doctor3);
        appointment.setClient(client);
        appointmentRepository.save(appointment);


    }
}
