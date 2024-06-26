package com.hj.leetcode.kotlin.problem995

/**
 * LeetCode page: [995. Minimum Number of K Consecutive Bit Flips](https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(k) where N is the size of nums;
     */
    fun minKBitFlips(nums: IntArray, k: Int): Int {
        var result = 0
        val flipsInWindow = ArrayDeque<Int>()

        for ((index, num) in nums.withIndex()) {
            if (flipsInWindow.isNotEmpty()
                && flipsInWindow.first() < index - k + 1
            ) {
                flipsInWindow.removeFirst()
            }

            val shouldFlipIndex = num xor (flipsInWindow.size % 2) == 0
            if (shouldFlipIndex) {
                if (index + k > nums.size) {
                    return -1
                }
                result++
                flipsInWindow.addLast(index)
            }
        }
        return result
    }
}