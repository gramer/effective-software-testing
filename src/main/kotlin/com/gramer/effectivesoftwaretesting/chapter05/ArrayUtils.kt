package com.gramer.effectivesoftwaretesting.chapter05

import java.util.TreeSet

class ArrayUtils private constructor() {

    companion object {
        fun unique(data: IntArray): IntArray {
            val values = TreeSet<Int>()
            data.forEach(values::add)

            val count = values.size
            val out = IntArray(count)

            val iterator = values.iterator()
            var i = 0
            while (iterator.hasNext()) {
                out[count - ++i] = iterator.next()
            }

            return out
        }

        fun indexOf(array: IntArray, valueToFind: Int, startIndex: Int): Int {
            val modifiedStartIndex = if (startIndex < 0) 0 else startIndex

            for (i in modifiedStartIndex until array.size) {
                if (valueToFind == array[i]) {
                    return i
                }
            }

            return -1
        }
    }
}
