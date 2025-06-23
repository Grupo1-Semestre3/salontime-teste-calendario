package com.example.demo.`interface`

import com.example.demo.model.HorarioPadrao
import org.springframework.data.jpa.repository.JpaRepository

interface HorarioPadraoRepository : JpaRepository<HorarioPadrao, Long>{
    fun findByDiaSemana(diaSemana: String): HorarioPadrao?
}

