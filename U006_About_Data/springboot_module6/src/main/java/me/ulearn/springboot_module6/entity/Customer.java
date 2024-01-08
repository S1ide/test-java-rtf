package me.ulearn.springboot_module6.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

//#region Task
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String email;
    @JsonIgnore
    private String password;
    @OneToMany
    private List<Product> cart;

    public Boolean isAdult(){
        return getAge() >= 18;
    }

    private Long getAge() {
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }
}
//#endregion Task