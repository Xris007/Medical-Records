package com.velasquez.controller;

import com.velasquez.model.Client;
import com.velasquez.model.Doctor;
import com.velasquez.service.AppointmentService;
import com.velasquez.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.velasquez.model.Appointment;
import com.velasquez.service.ClientService;

import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    ClientService clientService;

    @GetMapping("/appointments")
    public String getAppointmentsList(Model model){
        List<Appointment> appointments = appointmentService.getAll();
        model.addAttribute("appointments", appointments);
        return "appointment";
    }

    @GetMapping("/appointments/add")
    public String addAppointments(Model model){
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("doctors", doctorService.getAll());
        return "appointment-add";
    }

    @PostMapping("/appointments/save")
    public String saveAppointment(Appointment appointment){

        String code = appointment.getDoctor().getDoctorCode();

        Doctor currentDoctor = doctorService.findById(code);

        appointment.setDoctor(currentDoctor);

        clientService.save(appointment.getClient());

        appointmentService.save(appointment);

        return "redirect:/appointments";
    }

    @GetMapping("/appointments/edit/{id}")
    public String editAppointment(@PathVariable Long id, Model model){

        Appointment currentAppointment = appointmentService.findById(id);

        if (currentAppointment != null){
            model.addAttribute("appointment", currentAppointment);
        }
        model.addAttribute("doctors", doctorService.getAll());
        return "appointment-edit";
    }

    @PostMapping("/appointments/update/{id}")
    public String appointmentUpdate(@PathVariable Long id, Appointment appointment){

        Appointment currentAppointment = appointmentService.findById(id);


        String code = appointment.getDoctor().getDoctorCode();

        Doctor currentDoctor = doctorService.findById(code);


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

        return "redirect:/appointments";
    }

    @GetMapping("/appointments/delete/{id}")
    public String appointmentCancellation(@PathVariable Long id){

        Appointment currentAppointment = appointmentService.findById(id);

        if (currentAppointment != null){
            appointmentService.delete(currentAppointment);
        }

        return "redirect:/appointments";
    }

    @GetMapping("/appointments/{id}/clients")
    public String getAppointmentClient(Model model, @PathVariable String id){
        model.addAttribute("id", id);
        model.addAttribute("clients", clientService.findById(id));
        return "client";
    }

}
