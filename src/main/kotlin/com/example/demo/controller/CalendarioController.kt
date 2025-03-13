package com.example.demo.controller

import com.example.demo.dto.DiaCalendarioDTO
import com.example.demo.`interface`.HorarioExcecaoRepository
import com.example.demo.`interface`.HorarioPadraoRepository
import com.example.demo.model.DiaSemana
import com.example.demo.service.CalendarioService
import com.example.demo.service.HorarioService
import org.springframework.web.bind.annotation.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.util.Date

@RestController
@RequestMapping("/calendario")
class CalendarioController(
    private val horarioPadraoRepository: HorarioPadraoRepository,
    private val horarioExcecaoRepository: HorarioExcecaoRepository,
    private val horarioService: HorarioService,
    private val calendarioService: CalendarioService
) {

    @GetMapping("/teste")
    fun teste(): String{
        return "Teste ok"
    }

//    @GetMapping("/{data}")
//    fun obterHorario(@PathVariable data: String): Any {
//        val dataFormatada = LocalDate.parse(data) // Converte a string para LocalDate
//        return horarioService.buscarHorarioPorData(dataFormatada)
//    }

    @GetMapping("/{data}")
    fun listarHorariosDisponiveis(@PathVariable data: String): List<Pair<LocalTime, LocalTime>> {
        val dataFormatada = LocalDate.parse(data) // Converte "2025-04-16" para LocalDate
        val horarioAbertura = LocalTime.of(8, 0)  // Horário fixo de abertura
        val horarioFechamento = LocalTime.of(18, 0) // Horário fixo de fechamento

        return calendarioService.listarHorariosDisponiveis(dataFormatada)
    }


    @GetMapping
    fun getCalendario(): List<DiaCalendarioDTO> {
        val hoje = LocalDate.now()
        val dataFim = hoje.plusMonths(3)

        val horariosPadrao = horarioPadraoRepository.findAll().associateBy { it.diaSemana }
        val horariosExcecao = horarioExcecaoRepository.findAll().associateBy { it.data }

        val calendario = mutableListOf<DiaCalendarioDTO>()

        var data = hoje
        while (!data.isAfter(dataFim)) {
            val diaSemanaEnum = DiaSemana.fromString(data.dayOfWeek.name) ?: throw IllegalArgumentException("Dia inválido")

            val horarioExcecao = horariosExcecao[data]
            val horarioPadrao = horariosPadrao[diaSemanaEnum.name]

            // Definição do estado "aberto"
            val aberto = when {
                horarioExcecao != null -> horarioExcecao.aberto // Exceção tem prioridade
                data.dayOfWeek == DayOfWeek.SUNDAY || data.dayOfWeek == DayOfWeek.MONDAY -> false // Domingo e segunda fechados
                horarioPadrao != null -> horarioPadrao.aberto // Segue o padrão
                else -> true // Se não houver exceção nem padrão, assume aberto
            }

            val diaCalendario = DiaCalendarioDTO(
                data = data,
                diaSemana = diaSemanaEnum.name.lowercase(),
                aberto = aberto
            )

            calendario.add(diaCalendario)
            data = data.plusDays(1)
        }

        return calendario
    }


}
