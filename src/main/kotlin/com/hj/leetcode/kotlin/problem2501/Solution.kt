package com.hj.leetcode.kotlin.problem2501

import kotlin.math.sqrt

/**
 * LeetCode page: [2501. Longest Square Streak in an Array](https://leetcode.com/problems/longest-square-streak-in-an-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of nums.
     */
    fun longestSquareStreak(nums: IntArray): Int {
        val pending = nums.toMutableSet()
        val overflowNext = sqrt(Int.MAX_VALUE.toDouble()).toInt() // avoid overflow of next
        var result = -1
        while (pending.isNotEmpty()) {
            val num = pending.first()
            var length = 0

            var next = num
            while (next in pending) {
                length += 1
                pending.remove(next)
                if (next > overflowNext) {
                    break
                }
                next *= next
            }

            var prev = sqrtOrNull(num)
            while (prev in pending) {
                length += 1
                pending.remove(prev)
                prev = sqrtOrNull(checkNotNull(prev))
            }

            if (result < length) {
                result = length
            }
        }
        return if (1 < result) result else -1 // min length is 2
    }

    private fun sqrtOrNull(x: Int): Int? {
        val candidate = sqrt(x.toDouble()).toInt()
        return if (candidate * candidate == x) candidate else null
    }
}
