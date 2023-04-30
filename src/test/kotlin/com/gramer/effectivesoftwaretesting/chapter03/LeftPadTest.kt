package com.gramer.effectivesoftwaretesting.chapter03

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class LeftPadTest : FunSpec({

    context("leftPad") {
        table(
            headers("str", "size", "padStr", "expected"),
            row(null, 0, "", null),
            row("", 3, "-", "---"),
            row("abc", 0, "-", "abc"),
            row("abc", 1, "-", "abc"),
            row("abc", 3, "-", "abc"),
            row("abc", 4, "-", "-abc"),
            row("abc", 0, null, ""),
            row("abc", 0, "", ""),
            row("abc", 5, "--", "--abc"),
            row("abc", 5, "---", "--abc"),
        ).forAll { str, size, padStr, expected ->
            test("str is '$str' with '$padStr' * $size: expected $expected") {
                LeftPad.pad(str, size, padStr) shouldBe expected
            }
        }
    }
})
