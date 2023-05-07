package com.gramer.effectivesoftwaretesting.chapter05

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeSortedWith
import io.kotest.matchers.collections.shouldBeUnique
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.ArbitraryBuilder
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.intArray
import io.kotest.property.checkAll

class ArrayUtilsTest : FunSpec({

    test("unique") {
        checkAll(Arb.intArray(length = Arb.int(10, 100), content = Arb.int(1..20))) { numbers ->
            println(numbers)
            ArrayUtils.unique(numbers).toTypedArray().shouldBeUnique().shouldBeSortedWith(reverseOrder())
        }
    }

    test("indexOf") {
        checkAll(indexOfParams()) { (numbers, value, indexToAddElement, startIndex) ->
            numbers[indexToAddElement] = value
            val expectedIndex = if (indexToAddElement >= startIndex) indexToAddElement else -1

            ArrayUtils.indexOf(numbers, value, startIndex) shouldBe expectedIndex
        }
    }
})

fun indexOfParams(): Arb<IndexOfData> {
    val numbers = Arb.intArray(length = Arb.int(100, 100), content = Arb.int(-1000..1000))
    val value = Arb.int(min = 1001, max = 2000)
    val indexToAddElement = Arb.int(min = 1, max = 99)
    val startIndex = Arb.int(min = 0, max = 99)

    return ArbitraryBuilder.create {
        IndexOfData(
            numbers.sample(it).value,
            value.sample(it).value,
            indexToAddElement.sample(it).value,
            startIndex.sample(it).value,
        )
    }.build()
}

data class IndexOfData(val numbers: IntArray, val value: Int, val indexToAddElement: Int, val startIndex: Int)
