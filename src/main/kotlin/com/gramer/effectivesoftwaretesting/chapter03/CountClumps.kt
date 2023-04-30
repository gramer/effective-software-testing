package com.gramer.effectivesoftwaretesting.chapter03

class CountClumps {

    companion object

    fun count(nums: IntArray?): Int {
        if (nums == null || nums.isEmpty()) {
            return 0
        }

        var count = 0
        var prev = nums[0]
        var inClump = false

        for (i in 1 until nums.size) {
            if (nums[i] == prev && !inClump) {
                inClump = true
                count++
            }

            if (nums[i] != prev) {
                prev = nums[i]
                inClump = false
            }
        }

        return count
    }
}
