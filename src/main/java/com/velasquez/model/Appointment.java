package com.velasquez.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_appointment")
public class Appointment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String hospitalName;

    private String address;

    private String cause;

    private String room;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_code")
    private Doctor doctor;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_dni")
    private Client client;
}
