package com.gramer.effectivesoftwaretesting.chapter02

class CustomStringUtils private constructor() {
    // private constructor

    companion object {

        fun substringsBetween(str: String, open: String, close: String): Array<String>? {
            if (open.isEmpty() || close.isEmpty()) {
                return null
            }

            val strLen = str.length
            if (strLen == 0) {
                return emptyArray()
            }

            val closeLen = close.length
            val openLen = open.length
            val list = mutableListOf<String>()
            var pos = 0

            while (pos < strLen - closeLen) {
                var start = str.indexOf(open, pos)
                if (start < 0) break

                start += openLen
                var end = str.indexOf(close, start)
                if (end < 0) break

                list.add(str.substring(start, end))
                pos = end + closeLen
            }

            if (list.isEmpty()) return null

            return list.toTypedArray()
        }
    }
}
