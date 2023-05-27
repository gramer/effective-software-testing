package com.gramer.effectivesoftwaretesting.chapter08

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class RomanNumberConverterTest : FunSpec({

    context("one char") {
        listOf(
            "I" to 1,
            "V" to 5,
            "X" to 10,
            "L" to 50,
        ).forEach { (romanChar, expected) ->
            test("$romanChar is $expected") {
                val result = RomanNumberConverter().convert(romanChar)
                result shouldBe expected
            }
        }
    }

    context("multi char") {
        listOf(
            "III" to 3,
            "VI" to 6,
            "IV" to 4,
        ).forEach { (romanChar, expected) ->
            test("$romanChar is $expected") {
                val result = RomanNumberConverter().convert(romanChar)
                result shouldBe expected
            }
        }
    }
})
