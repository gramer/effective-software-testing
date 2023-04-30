package com.gramer.effectivesoftwaretesting.chapter03

object LeftPad {

    @Suppress("ReturnCount")
    fun pad(str: String?, size: Int, padStr: String?): String? {
        if (str == null) {
            return null
        }

        if (padStr.isNullOrEmpty()) {
            return ""
        }

        val padLen = padStr.length
        val strLen = str.length
        val pads = size - strLen

        if (pads <= 0) {
            return str
        }

        return if (pads == padLen) {
            padStr + str
        } else if (pads < padLen) {
            padStr.substring(0, pads) + str
        } else {
            val padding = CharArray(pads)
            val padChars = padStr.toCharArray()

            for (i in 0 until pads) {
                padding[i] = padChars[i % padLen]
            }

            String(padding) + str
        }
    }
}
