package com.gramer.effectivesoftwaretesting.chapter05

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.property.Arb
import io.kotest.property.arbitrary.choice
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.numericFloat
import io.kotest.property.checkAll
import io.kotest.property.forAll

class PassingGradeTest : FunSpec({

    val pg = PassingGrade()

    test("not passed 1 to 4.99") {
        checkAll(Arb.numericFloat(min = 1f, max = 4.99f)) { grade ->
            collect(grade.toInt())
            pg.passed(grade).shouldBeFalse()
        }
    }

    test("not passed 1 to 4.99 by forAll") {
        forAll(Arb.numericFloat(min = 1f, max = 4.99f)) { grade ->
            !pg.passed(grade)
        }
    }

    test("passed 5 .. 10 maxInclude") {
        checkAll(Arb.numericFloat(min = 5f, max = 10f)) { grade ->
            collect(grade.toInt())
            pg.passed(grade).shouldBeTrue()
        }
    }

    test("invalid Grade") {
        checkAll(invalidGrades()) { grade ->
            collect(if (grade < 1f) "less" else "over")
            shouldThrowExactly<IllegalArgumentException> { pg.passed(grade) }
        }
    }
})

fun invalidGrades(): Arb<Float> {
    val over = Arb.numericFloat().filter { it > 10f }
    val less = Arb.numericFloat().filter { it < 1f }
    return Arb.choice(over, less)
}
