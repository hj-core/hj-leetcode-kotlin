package com.hj.leetcode.kotlin.problem1760

/**
 * LeetCode page: [1760. Minimum Limit of Balls in a Bag](https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogM) and Space O(1)
     * where N is the size of nums and M is the maximum possible balls in a bag.
     */
    fun minimumSize(
        nums: IntArray,
        maxOperations: Int,
    ): Int {
        val maxBallsPerBag = 1_000_000_000
        var low = 1
        var high = maxBallsPerBag

        while (low <= high) {
            val middle = low + (high - low) / 2
            if (canReduce(nums, middle, maxOperations)) {
                high = middle - 1
            } else {
                low = middle + 1
            }
        }
        return low
    }

    private fun canReduce(
        bagSizes: IntArray,
        reducedSize: Int,
        maxOperations: Int,
    ): Boolean {
        var totalOps = 0
        for (size in bagSizes) {
            totalOps += requiredOps(size, reducedSize)
            if (totalOps > maxOperations) {
                return false
            }
        }
        return true
    }

    private fun requiredOps(
        bagSize: Int,
        reducedSize: Int,
    ): Int = (bagSize - 1) / reducedSize
}
