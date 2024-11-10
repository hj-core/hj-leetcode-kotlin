package com.hj.leetcode.kotlin.problem3097

import kotlin.math.min

/**
 * LeetCode page: [3097. Shortest Subarray With OR at Least K II](https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums.
     */
    fun minimumSubarrayLength(
        nums: IntArray,
        k: Int,
    ): Int {
        if (k == 0) {
            return 1
        }
        var result = nums.size + 1
        val bitCount = IntArray(31)
        var right = 0
        for (left in nums.indices) {
            while (right < nums.size && toInt(bitCount) < k) {
                if (k <= nums[right]) {
                    return 1
                }
                addBitCount(nums[right], bitCount)
                right += 1
            }
            if (toInt(bitCount) < k) {
                break
            }
            result = min(result, right - left)
            subtractBitCount(nums[left], bitCount)
        }
        return if (result > nums.size) -1 else result
    }

    private fun toInt(bitCount: IntArray): Int =
        bitCount.foldIndexed(0) { index, acc, elem ->
            if (elem > 0) acc + (1 shl index) else acc
        }

    private fun addBitCount(
        num: Int,
        toCount: IntArray,
    ) {
        for (i in toCount.indices) {
            if (num and (1 shl i) != 0) {
                toCount[i] += 1
            }
        }
    }

    private fun subtractBitCount(
        num: Int,
        fromCount: IntArray,
    ) {
        for (i in fromCount.indices) {
            if (num and (1 shl i) != 0) {
                fromCount[i] -= 1
            }
        }
    }
}
