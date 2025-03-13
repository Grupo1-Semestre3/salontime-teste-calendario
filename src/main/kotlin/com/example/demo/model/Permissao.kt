package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "permissao")
data class Permissao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = 0,

    @Column(nullable = false)
    val nome: String,

    val descricao: String? = null
){
    constructor(): this(null, "", "")
}

