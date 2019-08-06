package com.example.user.entity;

import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "test")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = 'id')
//    @GenericGenerator(name = "id",strategy = "uuid")
    Integer id;
//    @Transient
    @NotNull
    String name;
}
