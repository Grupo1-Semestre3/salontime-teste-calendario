package com.example.demo.`interface`

import com.example.demo.model.HorarioExcecao
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface HorarioExcecaoRepository : JpaRepository<HorarioExcecao, Long> {
    fun findByData(data: LocalDate): HorarioExcecao?
}