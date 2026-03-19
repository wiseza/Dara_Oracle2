package com.example.dara

import kotlin.random.Random
import java.util.Calendar

object FortuneCalculator {

    private fun dayToNumber(day: String): Int {
        return when(day){
            "วันอาทิตย์" -> 1
            "วันจันทร์" -> 2
            "วันอังคาร" -> 3
            "วันพุธ" -> 4
            "วันพฤหัสบดี" -> 5
            "วันศุกร์" -> 6
            "วันเสาร์" -> 7
            else -> 1
        }
    }

    private fun zodiacToNumber(zodiac: String): Int {
        return when(zodiac){
            "ราศีเมษ" -> 1
            "ราศีพฤษภ" -> 2
            "ราศีเมถุน" -> 3
            "ราศีกรกฎ" -> 4
            "ราศีสิงห์" -> 5
            "ราศีกันย์" -> 6
            "ราศีตุลย์" -> 7
            "ราศีพิจิก" -> 8
            "ราศีธนู" -> 9
            "ราศีมังกร" -> 10
            "ราศีกุมภ์" -> 11
            "ราศีมีน" -> 12
            else -> 1
        }
    }

    private fun baseSeed(day:String, zodiac:String): Int {
        val today = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        return day.hashCode() + zodiac.hashCode() + today
    }

    private fun weightedRandomIndex(weights: List<Double>, rng: Random): Int {
        val totalWeight = weights.sum()
        var random = rng.nextDouble(totalWeight)
        weights.forEachIndexed { index, weight ->
            if (random < weight) return index
            random -= weight
        }
        return weights.indices.last()
    }


    private fun calculateWeights(day: String, zodiac: String): List<Double> {
        val baseWeight = 1.0
        val bonusWeight = 3.0

        return TarotDeck.cards.map { card ->
            var weight = baseWeight
            if (card.dayAffinity.contains(day)) {
                weight *= bonusWeight
            }
            if (card.zodiacAffinity.contains(zodiac)) {
                weight *= bonusWeight
            }
            weight
        }
    }

    fun getWorkCard(day:String, zodiac:String): Int {
        val seed = baseSeed(day, zodiac) * 3
        val rng = Random(seed)
        val weights = calculateWeights(day, zodiac)
        return weightedRandomIndex(weights, rng)
    }

    fun getMoneyCard(day:String, zodiac:String): Int {
        val seed = baseSeed(day, zodiac) * 7
        val rng = Random(seed)
        val weights = calculateWeights(day, zodiac)
        return weightedRandomIndex(weights, rng)
    }

    fun getLoveCard(day:String, zodiac:String): Int {
        val seed = baseSeed(day, zodiac) * 11
        val rng = Random(seed)
        val weights = calculateWeights(day, zodiac)
        return weightedRandomIndex(weights, rng)
    }

    fun getHealthCard(day:String, zodiac:String): Int {
        val seed = baseSeed(day, zodiac) * 17
        val rng = Random(seed)
        val weights = calculateWeights(day, zodiac)
        return weightedRandomIndex(weights, rng)
    }
}