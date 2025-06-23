package com.example.demo.model

enum class DiaSemana {
    SEGUNDA, TERÇA, QUARTA, QUINTA, SEXTA, SABADO, DOMINGO;

    companion object {
        fun fromString(nome: String): DiaSemana? {
            return when (nome.lowercase()) {
                "monday" -> SEGUNDA
                "tuesday" -> TERÇA
                "wednesday" -> QUARTA
                "thursday" -> QUINTA
                "friday" -> SEXTA
                "saturday" -> SABADO
                "sunday" -> DOMINGO
                else -> null
            }
        }
    }
}

