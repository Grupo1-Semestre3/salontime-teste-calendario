package com.example.demo.service

import com.example.demo.`interface`.HorarioExcecaoRepository
import com.example.demo.`interface`.HorarioPadraoRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Service
class HorarioService(
    private val horarioExcecaoRepository: HorarioExcecaoRepository,
    private val horarioPadraoRepository: HorarioPadraoRepository
) {
    fun buscarHorarioPorData(data: LocalDate): Any {
        // Verifica se há um registro na tabela de exceção para essa data
        val horarioExcecao = horarioExcecaoRepository.findByData(data)
        if (horarioExcecao != null) {
            return horarioExcecao
        }

        // Se não houver exceção, buscar pelo padrão do dia da semana correspondente
        val diaSemana = data.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("pt", "BR"))
        val horarioPadrao = horarioPadraoRepository.findByDiaSemana(diaSemana)

        return horarioPadrao ?: "Nenhum horário encontrado para $diaSemana"
    }
}
