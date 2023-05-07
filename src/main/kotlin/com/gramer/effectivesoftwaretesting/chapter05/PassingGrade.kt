package com.gramer.effectivesoftwaretesting.chapter05

class PassingGrade {

    fun passed(grade: Float): Boolean {
        require(grade in 1.0..10.0)
        return grade >= 5.0
    }
}
