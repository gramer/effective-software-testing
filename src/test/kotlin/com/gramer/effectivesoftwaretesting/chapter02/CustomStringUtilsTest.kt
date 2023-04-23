package com.gramer.effectivesoftwaretesting.chapter02

import com.gramer.effectivesoftwaretesting.chapter02.CustomStringUtils.Companion.substringsBetween
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

class CustomStringUtilsTest : FunSpec({

    context("argument is empty") {
        test("str is empty") {
            substringsBetween("", "a", "b").shouldBeEmpty()
        }

        test("open is empty") {
            substringsBetween("abc", "", "b").shouldBeNull()
        }

        test("close is empty") {
            substringsBetween("abc", "a", "").shouldBeNull()
        }
    }

    context("str of length: 1") {
        table(
            headers("str", "open", "close", "expected"),
            row("a", "a", "b", null),
            row("a", "b", "a", null),
            row("a", "b", "b", null),
            row("a", "a", "a", null)
        ).forAll { str, open, close, expected ->
            test("[str: $str, open:$open, close:$close] is $expected") {
                substringsBetween(str, open, close) shouldBe expected
            }
        }
    }

    context("open and close of length: 1") {
        table(
            headers("str", "open", "close", "expected"),
            row("abc", "x", "y", null),
            row("abc", "a", "y", null),
            row("abc", "x", "c", null),
            row("abc", "a", "c", arrayOf("b")),
            row("abcabc", "a", "c", arrayOf("b", "b")),
        ).forAll { str, open, close, expected ->
            test("[str: $str, open:$open, close:$close] is ${expected?.joinToString(",")}") {
                substringsBetween(str, open, close) shouldBe expected
            }
        }
    }

    context("open and close of different size") {
        table(
            headers("str", "open", "close", "expected"),
            row("aabbc", "xx", "yy", null),
            row("aabbc", "aa", "yy", null),
            row("aabbc", "xx", "cc", null),
            row("aabbcc", "aa", "cc", arrayOf("bb")),
            row("aabbccaaeecc", "aa", "cc", arrayOf("bb", "ee")),
        ).forAll { str, open, close, expected ->
            test("[str: $str, open:$open, close:$close] is ${expected?.joinToString(",")}") {
                substringsBetween(str, open, close) shouldBe expected
            }
        }
    }

    context("aggressive tests") {
        test("non substrings between open and close") {
            substringsBetween("aabb", "aa", "bb") shouldBe arrayOf("")
        }

        test("open and close of length: 1") {
            substringsBetween("abcabyt byrc", "a", "c") shouldBe arrayOf("b", "byt byr")
        }

        test("open and close of different size") {
            substringsBetween("a abb ddc ca abbcc", "a a", "c c") shouldBe arrayOf("bb dd")
        }
    }

})
