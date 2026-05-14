package com.hj.leetcode.kotlin.problem2784

/**
 * LeetCode page: [2784. Check if Array is Good](https://leetcode.com/problems/check-if-array-is-good/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun isGood(nums: IntArray): Boolean {
        val n = nums.size - 1
        val seen = BooleanArray(n)
        var countN = 0
        for (num in nums) {
            when (num) {
                in 1..<n -> {
                    if (seen[num]) {
                        return false
                    }
                    seen[num] = true
                }

                n -> {
                    countN++
                    if (countN > 2) {
                        return false
                    }
                }

                else -> {
                    return false
                }
            }
        }
        return countN == 2
    }
}
