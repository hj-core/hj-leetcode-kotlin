package com.hj.leetcode.kotlin.problem3551

/**
 * LeetCode page: [3551. Minimum Swaps to Sort by Digit Sum](https://leetcode.com/problems/minimum-swaps-to-sort-by-digit-sum/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of nums.
    fun minSwaps(nums: IntArray): Int {
        val packed =
            LongArray(nums.size) {
                pack(it, nums[it], digitSum(nums[it]))
            }
        packed.sort()

        var swaps = 0
        var i = 0
        while (i < nums.size) {
            val j = getIndex(packed[i])
            if (j == i) {
                i++
            } else {
                swaps++
                packed[i] = packed[j].also { packed[j] = packed[i] }
            }
        }
        return swaps
    }

    private fun pack(
        index: Int,
        value: Int,
        digitSum: Int,
    ): Long {
        val index = index.toLong()
        val value = value.toLong()
        val digitSum = digitSum.toLong()
        return (digitSum shl 48) or (value shl 18) or index
    }

    private fun getIndex(packed: Long): Int = (packed and 0x1_FFFF).toInt()

    private fun digitSum(value: Int): Int {
        var value = value
        var sum = 0
        while (value > 0) {
            sum += value % 10
            value /= 10
        }
        return sum
    }
}
