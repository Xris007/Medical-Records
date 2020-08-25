package com.velasquez.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.velasquez.model.Appointment;
import com.velasquez.model.Client;
import com.velasquez.model.Doctor;
import com.velasquez.service.AppointmentService;
import com.velasquez.service.ClientService;
import com.velasquez.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppointmentResource {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    ClientService clientService;


    @GetMapping("/appointments")
    public ResponseEntity getAppointments(){
        List<Appointment> appointments = appointmentService.getAll();
        if (appointments.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(appointments, HttpStatus.OK);
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        Appointment currentAppointment = appointmentService.findById(id);

        if(currentAppointment == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(currentAppointment, HttpStatus.OK);
    }

    @PostMapping("/appointments")
    public ResponseEntity addNewAppointment(@RequestBody Appointment appointment){


        String code = appointment.getDoctor().getDoctorCode();

        Doctor currentDoctor = doctorService.findById(code);

        if (currentDoctor == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        appointment.setDoctor(currentDoctor);

        clientService.save(appointment.getClient());

        appointmentService.save(appointment);

        return new ResponseEntity(appointment, HttpStatus.OK);
    }

    @PutMapping("/appointments/{id}")
    public ResponseEntity updateAppointment(@PathVariable Long id,
                                            @RequestBody Appointment appointment){

        Appointment currentAppointment = appointmentService.findById(id);

        if (currentAppointment == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        String code = appointment.getDoctor().getDoctorCode();

        Doctor currentDoctor = doctorService.findById(code);

        if (currentDoctor == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        currentAppointment.setDoctor(currentDoctor);

        Client currentClient = currentAppointment.getClient();
        currentClient.setDni(appointment.getClient().getDni());
        currentClient.setFullName(appointment.getClient().getFullName());
        currentClient.setBirthDate(appointment.getClient().getBirthDate());
        currentClient.setAge(appointment.getClient().getAge());
        currentClient.setSex(appointment.getClient().getSex());
        currentClient.setHeight(appointment.getClient().getHeight());

        currentAppointment.setHospitalName(appointment.getHospitalName());
        currentAppointment.setAddress(appointment.getAddress());
        currentAppointment.setCause(appointment.getCause());
        currentAppointment.setRoom(appointment.getRoom());
        currentAppointment.setDate(appointment.getDate());
        currentAppointment.setClient(currentClient);

        clientService.update(currentClient);
        appointmentService.update(currentAppointment);

        return new ResponseEntity(currentAppointment, HttpStatus.OK);
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity appointmentCancellation(@PathVariable Long id){
        Appointment currentAppointment = appointmentService.findById(id);

        if (currentAppointment == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        appointmentService.delete(currentAppointment);

        return new ResponseEntity(HttpStatus.OK);
    }

}
