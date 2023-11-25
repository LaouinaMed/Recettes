package com.springboot.springmvc.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
public class Recette {

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Long id;

    @NotEmpty
    private String name;


    @NotEmpty
    private String ingredients;

    @NotEmpty
    private String etapesPreparation;


    private Long dureePreparation;
}
