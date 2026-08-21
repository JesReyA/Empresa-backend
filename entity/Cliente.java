package com.tuempresa.empresa_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name= "clientes")  //nos indica que la tabla en la base de datos se llamará así
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //deja que en manejador de base de datos asigne la columna autoincremental
    private Long id;

    @NotBlank
    @Column(nullable = false, length= 120)
    private String nombre;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true, length=150)
    private String email;

    public Cliente(){

    }

    public Cliente(Long id, String nombre, String email){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
