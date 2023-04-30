package com.gramer.effectivesoftwaretesting.chapter03

import java.lang.Character.isLetter

class CountWords {

    fun count(str: String): Int {
        var words = 0
        var last = ' '

        str.forEach { ch ->
            if (!isLetter(ch) && last == 's' || last == 'r') {
                words++
            }

            last = ch
        }

        if (last == 'r' || last == 's') {
            words++
        }

        return words
    }
}
