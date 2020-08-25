package com.velasquez.controller;

import com.velasquez.model.Doctor;
import com.velasquez.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping("/doctors")
    public String getDoctorsList(Model model){
        List<Doctor> doctors = doctorService.getAll();
        model.addAttribute("doctors", doctors);
        return "doctor";
    }

    @GetMapping("/doctors/add")
    public String addDoctors(Model model){
        model.addAttribute("doctor", new Doctor());
        return "doctor-add";
    }

    @PostMapping("/doctors/save")
    public String saveEmployee(Doctor doctor){

        doctorService.save(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/doctors/edit/{code}")
    public String editEmployee(@PathVariable String code, Model model){
        Doctor currentDoctor = doctorService.findById(code);
        if (currentDoctor != null){
            model.addAttribute("doctor", currentDoctor);
        }
        return "doctor-edit";
    }

    @PostMapping("/employees/update/{code}")
    public String employeeUpdate(@PathVariable String code, Doctor doctor){

        doctorService.update(doctor);

        return "redirect:/doctors";
    }


    @GetMapping("/doctors/delete/{code}")
    public String doctorDelete(@PathVariable String code){

        Doctor currentDoctor = doctorService.findById(code);

        if (currentDoctor != null){
            doctorService.delete(currentDoctor);
        }

        return "redirect:/doctors";
    }
}
