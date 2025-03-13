package com.example.demo.dto

import java.time.LocalDate
import java.time.LocalTime

data class DiaCalendarioDTO(
    val data: LocalDate,
    val diaSemana: String,
    val aberto: Boolean
    //val horarioAbertura: LocalTime?,
    //val horarioFechamento: LocalTime?
)
