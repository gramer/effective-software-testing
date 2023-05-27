package com.gramer.effectivesoftwaretesting.chapter08

class RomanNumberConverter {

    private val romanCharTable = mapOf(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000,
    )

    fun convert(romanNumberString: String): Int {
        var result = 0
        var lastNumber = 0

        romanNumberString.reversed().forEach { ch ->
            val currentNumber = checkNotNull(romanCharTable[ch]) { "$ch 는 로마숫자가 아닙니다." }
            var multiplier = if (currentNumber < lastNumber) -1 else 1

            result += currentNumber * multiplier
            lastNumber = currentNumber
        }

        return result
    }
}
