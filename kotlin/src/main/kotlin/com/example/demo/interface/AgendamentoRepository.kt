package com.example.demo.`interface`

import com.example.demo.model.Agendamento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface AgendamentoRepository : JpaRepository<Agendamento, Long> {
    fun findByData(data: LocalDate): List<Agendamento>
}