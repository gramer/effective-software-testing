package com.gramer.effectivesoftwaretesting.chapter03

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CountClumpsTest : FunSpec({

    context("Parameterized test") {
        forAll(
            row("nums is null", null, 0),
            row("nums is empty", IntArray(0), 0),
            row("nums is 1", intArrayOf(1), 0),
            row("nums is 1,1", intArrayOf(1, 1), 1),
            row("nums is 1,1,2,2,1,1", intArrayOf(1, 1, 2, 2, 1, 1), 3),
        ) { desc, nums, expected ->
            test(desc) {
                CountClumps().count(nums) shouldBe expected
            }
        }
    }
})
