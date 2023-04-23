package com.gramer.effectivesoftwaretesting.chapter02

import com.gramer.effectivesoftwaretesting.chapter02.CustomStringUtils.Companion.substringsBetween
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

class CustomStringUtilsStringSpecTest : FreeSpec({

    "argument is empty" - {
        "str is empty" {
            substringsBetween("", "a", "b").shouldBeEmpty()
        }

        "open is empty" {
            substringsBetween("abc", "", "b").shouldBeNull()
        }

        "close is empty" {
            substringsBetween("abc", "a", "").shouldBeNull()
        }
    }

    "str of length: 1" - {
        table(
            headers("str", "open", "close", "expected"),
            row("a", "a", "b", null),
            row("a", "b", "a", null),
            row("a", "b", "b", null),
            row("a", "a", "a", null)
        ).forAll { str, open, close, expected ->
            "[str: $str, open:$open, close:$close] is $expected" {
                substringsBetween(str, open, close) shouldBe expected
            }
        }
    }

    "open and close of length: 1" - {
        table(
            headers("str", "open", "close", "expected"),
            row("abc", "x", "y", null),
            row("abc", "a", "y", null),
            row("abc", "x", "c", null),
            row("abc", "a", "c", arrayOf("b")),
            row("abcabc", "a", "c", arrayOf("b", "b")),
        ).forAll { str, open, close, expected ->
            "[str: $str, open:$open, close:$close] is ${expected?.joinToString(",")}" {
                substringsBetween(str, open, close) shouldBe expected
            }
        }
    }

    "open and close of different size" - {
        table(
            headers("str", "open", "close", "expected"),
            row("aabbc", "xx", "yy", null),
            row("aabbc", "aa", "yy", null),
            row("aabbc", "xx", "cc", null),
            row("aabbcc", "aa", "cc", arrayOf("bb")),
            row("aabbccaaeecc", "aa", "cc", arrayOf("bb", "ee")),
        ).forAll { str, open, close, expected ->
            "[str: $str, open:$open, close:$close] is ${expected?.joinToString(",")}" {
                substringsBetween(str, open, close) shouldBe expected
            }
        }
    }

    "aggressive tests" - {
        "non substrings between open and close" {
            substringsBetween("aabb", "aa", "bb") shouldBe arrayOf("")
        }

        "open and close of length: 1" {
            substringsBetween("abcabyt byrc", "a", "c") shouldBe arrayOf("b", "byt byr")
        }

        "open and close of different size" {
            substringsBetween("a abb ddc ca abbcc", "a a", "c c") shouldBe arrayOf("bb dd")
        }
    }

})
