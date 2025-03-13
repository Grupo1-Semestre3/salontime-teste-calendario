package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "usuario")
class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(nullable = false)
    val nome: String,

    @Column(nullable = false, length = 11)
    val telefone: String,

    @Column(nullable = false, unique = true, length = 14)
    val cpf: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val senha: String,

    @ManyToOne
    @JoinColumn(name = "fk_permissao", nullable = false)
    val permissao: Permissao
){
    constructor() : this(0, "", "", "", "", "", Permissao()) // Construtor vazio
}
