package com.hj.leetcode.kotlin.problem2616

/**
 * LeetCode page: [2616. Minimize the Maximum Difference of Pairs](https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN+NLogV) and Space O(1) where N is the size of nums, V is the (maxValue - minValue) of nums;
     */
    fun minimizeMax(nums: IntArray, p: Int): Int {
        if (p == 0) {
            return 0
        }

        val sortedNums = nums.sorted()
        var lowerBound = 0
        var upperBound = sortedNums.last() - sortedNums[0]
        while (lowerBound < upperBound) {
            val mid = lowerBound + (upperBound - lowerBound) / 2

            if (hasEnoughPairs(sortedNums, p, mid)) {
                upperBound = mid
            } else {
                lowerBound = mid + 1
            }
        }
        return upperBound
    }

    private fun hasEnoughPairs(sortedNums: List<Int>, numPairs: Int, maxDifference: Int): Boolean {
        var index = 0
        var pairCount = 0

        while (index < sortedNums.lastIndex) {
            if (sortedNums[index + 1] - sortedNums[index] <= maxDifference) {
                pairCount++
                index += 2
            } else {
                index++
            }

            if (pairCount >= numPairs) {
                return true
            }
        }
        return false
    }
}