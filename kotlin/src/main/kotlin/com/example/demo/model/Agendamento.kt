package com.example.demo.model

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "agendamento")
class Agendamento(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = 0,

    @ManyToOne
    @JoinColumn(name = "fk_usuario", nullable = false)
    val usuario: Usuario,

    @ManyToOne
    @JoinColumn(name = "fk_situacao", nullable = false)
    val situacao: Situacao,

    @Column(nullable = false)
    val data: LocalDate,

    @Column(nullable = false)
    val inicio: LocalTime,

    @Column(nullable = false)
    val fim: LocalTime

    // Construtor vazio necessário para o Hibernate

){
    // Construtor vazio necessário para o Hibernate
    constructor() : this(
        null,
        Usuario(),
        Situacao(),
        LocalDate.now(),
        LocalTime.MIN,
        LocalTime.MAX
    )
}
