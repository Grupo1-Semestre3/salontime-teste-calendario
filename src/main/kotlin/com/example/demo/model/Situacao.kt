package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "situacao")
data class Situacao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(nullable = false)
    val nome: String
){
    constructor() : this(0, "") // Construtor vazio
}

