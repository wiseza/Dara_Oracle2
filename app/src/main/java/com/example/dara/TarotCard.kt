package com.example.dara

data class TarotCard(
    val image: Int,
    val name: String,
    val meanings: Map<String, String>,
    val dayAffinity: List<String> = emptyList(),
    val zodiacAffinity: List<String> = emptyList()
)