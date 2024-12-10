package com.example.bus_booking.entities;

import com.example.bus_booking.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @CreationTimestamp
    private LocalDateTime registrationDate;

    @UpdateTimestamp
    private LocalDateTime lastLoginDate;


    @Lob
    byte[] profilePhotos;
    private boolean isVerifiedMail = false;
//    private boolean isVerifiedPhone = false;

}
