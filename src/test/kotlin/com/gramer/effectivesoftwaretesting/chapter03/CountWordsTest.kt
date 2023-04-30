package com.gramer.effectivesoftwaretesting.chapter03

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class CountWordsTest : FunSpec({

    /**
     * 결정개수 n + 1 의 경우를 고려 하여 4건의 테스트 케이스
     */
    context("MC/DC") {
        table(
            headers("description", "str", "expected"),
            row("ending s", "dogs cats", 2),
            row("ending r", "gear near", 2),
            row("empty string", " ", 0),
            row("not ending s,r", "doc cat", 0),
        ).forAll { description, str, expected ->
            test("$description: $str is $expected") {
                CountWords().count(str) shouldBe expected
            }
        }
    }
})
