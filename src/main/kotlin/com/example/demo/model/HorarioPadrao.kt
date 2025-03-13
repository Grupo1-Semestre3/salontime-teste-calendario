package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "horario_padrao")
data class HorarioPadrao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    val aberto: Boolean,

    @Column(name = "dia_semana")
    val diaSemana: String,

    @Column(name = "horario_abertura")
    val horarioAbertura: String,

    @Column(name = "horario_fechamento")
    val horarioFechamento: String
) {
    constructor() : this(null, false, "", "", "")
}



