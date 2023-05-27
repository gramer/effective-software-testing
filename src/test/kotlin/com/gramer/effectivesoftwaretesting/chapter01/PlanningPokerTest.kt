package com.gramer.effectivesoftwaretesting.chapter01

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.property.Arb
import io.kotest.property.arbitrary.ArbitraryBuilder
import io.kotest.property.arbitrary.int
import io.kotest.property.arbs.firstName
import io.kotest.property.checkAll

class PlanningPokerTest : FunSpec({

    val planningPoker = PlanningPoker()
    test("Reject Empty List") {
        shouldThrowExactly<IllegalArgumentException> {
            planningPoker.identifyExtremes(emptyList())
        }
    }

    test("Reject Single Estimate") {
        shouldThrowExactly<IllegalArgumentException> {
            planningPoker.identifyExtremes(listOf(Estimate("zeno", 1)))
        }
    }

    test("Two Estimate") {
        planningPoker.identifyExtremes(
            listOf(
                Estimate("zeno", 10),
                Estimate("frank", 5),
            ),
        ).shouldContainInOrder("frank", "zeno")
    }

    test("Many Estimate") {
        planningPoker.identifyExtremes(
            listOf(
                Estimate("zeno", 10),
                Estimate("frank", 5),
                Estimate("baba", 3),
            ),
        ).shouldContainInOrder("baba", "zeno")
    }

    test("In Any Order") {
        checkAll(estimates()) { randoEstimate ->
            val list = listOf(
                Estimate("zeno", 1),
                Estimate("baba", 100),
                randoEstimate,
            ).shuffled()

            planningPoker.identifyExtremes(list).shouldContainInOrder("zeno", "baba")
        }
    }

    test("All Same estimate") {
        val list = listOf(
            Estimate("zeno", 100),
            Estimate("baba", 100),
            Estimate("joel", 100),
        )

        planningPoker.identifyExtremes(list).shouldBeEmpty()
    }
})

fun estimates(): Arb<Estimate> {
    val names = Arb.firstName()
    val values = Arb.int(2..99)
    return ArbitraryBuilder.create {
        Estimate(names.sample(it).value.name, values.sample(it).value)
    }.build()
}
