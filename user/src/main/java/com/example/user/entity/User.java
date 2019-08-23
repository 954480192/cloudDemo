package com.example.user.entity;

import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "test")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = 'id')
//    @GenericGenerator(name = "id",strategy = "uuid")
    Integer id;
//    @Transient
    @NotNull
    String name;
    String password;
//    @ManyToMany
//    @JoinTable(name = "UserRole",joinColumns = {@JoinColumn(name = "userId")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Role> roles;
}
