package com.example.demo.model

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "horario_excecao")
class HorarioExcecao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @field:Column(name = "data", nullable = false)
    val data: LocalDate = LocalDate.now(),

    @field:Column(name = "aberto", nullable = false)
    val aberto: Boolean = true,

    @field:Column(name = "horario_abertura")
    val horarioAbertura: LocalTime? = null,

    @field:Column(name = "horario_fechamento")
    val horarioFechamento: LocalTime? = null
) {
    constructor() : this(0, LocalDate.now(), true, null, null) // Construtor vazio necessário para o JPA

    init {
        if (!aberto) {
            require(horarioAbertura == null && horarioFechamento == null) {
                "Se 'aberto' for falso, 'horarioAbertura' e 'horarioFechamento' devem ser nulos."
            }
        }
    }
}
