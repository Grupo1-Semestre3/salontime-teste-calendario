package com.example.demo.service

import com.example.demo.`interface`.AgendamentoRepository
import com.example.demo.`interface`.HorarioPadraoRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.*

@Service
class CalendarioService(private val agendamentoRepository: AgendamentoRepository,
    private val horarioPadraoRepository: HorarioPadraoRepository) {

    //fun listarHorariosDisponiveis(data: LocalDate, horarioAbertura: LocalTime, horarioFechamento: LocalTime): List<Pair<LocalTime, LocalTime>> {
    fun listarHorariosDisponiveis(data: LocalDate): List<Pair<LocalTime, LocalTime>> {
        val agendamentos = agendamentoRepository.findByData(data)

        val horariosOcupados = agendamentos.map { it.inicio to it.fim }

        val diaSemana = data.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("pt", "BR"))
        val horarioPadrao = horarioPadraoRepository.findByDiaSemana(diaSemana)

        val horarioAbertura = horarioPadrao?.horarioAbertura?.let { LocalTime.parse(it) }
        val horarioFechamento = horarioPadrao?.horarioFechamento?.let { LocalTime.parse(it) }

        val horariosDisponiveis = mutableListOf<Pair<LocalTime, LocalTime>>()
        var horarioAtual = horarioAbertura

        while (horarioAtual?.plusMinutes(30)!! <= horarioFechamento) {
            val proximoHorario = horarioAtual.plusMinutes(30)

            // Verifica corretamente se o horário está ocupado
            val ocupado = horariosOcupados.any { (inicio, fim) ->
                horarioAtual!! in inicio..fim || proximoHorario in inicio..fim || (horarioAtual!! <= inicio && proximoHorario >= fim)
            }

            if (!ocupado) {
                horariosDisponiveis.add(horarioAtual to proximoHorario)
            }

            horarioAtual = proximoHorario
        }

        return horariosDisponiveis
    }

}
