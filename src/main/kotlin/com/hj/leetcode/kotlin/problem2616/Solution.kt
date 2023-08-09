package com.hj.leetcode.kotlin.problem2616

/**
 * LeetCode page: [2616. Minimize the Maximum Difference of Pairs](https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN+NLogV) and Space O(1) where N is the size of nums, V is the (max - min) of nums;
     */
    fun minimizeMax(nums: IntArray, p: Int): Int {
        if (p == 0) {
            return 0
        }

        val sorted = nums.sorted()
        var low = 0
        var high = sorted.last() - sorted[0]
        while (low < high) {
            val mid = low + (high - low) / 2
            if (isFeasible(sorted, p, mid)) {
                high = mid
            } else {
                low = mid + 1
            }
        }
        return high
    }

    private fun isFeasible(sorted: List<Int>, p: Int, threshold: Int): Boolean {
        var count = 0
        var index = 0
        while (index < sorted.lastIndex) {
            if (sorted[index + 1] - sorted[index] <= threshold) {
                count++
                index += 2
            } else {
                index++
            }
            if (count >= p) {
                return true
            }
        }
        return false
    }
}