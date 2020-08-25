package com.velasquez.resource;

import com.velasquez.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.velasquez.model.Doctor;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DoctorResource {

    @Autowired
    DoctorService doctorService;

    @GetMapping("/doctors")
    public ResponseEntity getDoctors(){
        List<Doctor> doctors = doctorService.getAll();
        if (doctors.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(doctors, HttpStatus.OK);
    }

    @GetMapping("/doctors/{code}")
    public ResponseEntity findById(@PathVariable String code){
        Doctor currentDoctor = doctorService.findById(code);

        if(currentDoctor == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(currentDoctor, HttpStatus.OK);
    }

    @PostMapping("/doctors")
    public ResponseEntity addNewDoctor(@RequestBody Doctor doctor){
        doctorService.save(doctor);
        return new ResponseEntity(doctor, HttpStatus.OK);
    }

    @PutMapping("/doctors/{code}")
    public ResponseEntity updateDoctor(@PathVariable String code,
                                       @RequestBody Doctor doctor){

        Doctor currentDoctor = doctorService.findById(code);
        if (currentDoctor == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        doctor.setDoctorCode(code);
        doctorService.update(doctor);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/doctors/{code}")
    public ResponseEntity deleteDoctor(@PathVariable String code){
        Doctor currentDoctor = doctorService.findById(code);

        if (currentDoctor == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        doctorService.delete(currentDoctor);
        return new ResponseEntity(HttpStatus.OK);
    }
}
