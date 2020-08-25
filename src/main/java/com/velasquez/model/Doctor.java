package com.velasquez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_doctor")
public class Doctor {

    @Id
    private String doctorCode;

    private String fullName;

    private String specialty;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Appointment> appointments;

}
