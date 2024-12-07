package com.hj.leetcode.kotlin.problem1760

/**
 * LeetCode page: [1760. Minimum Limit of Balls in a Bag](https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogM) and Space O(1)
     * where N is the size of nums and M is the maximum value in nums.
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
            if (requiredOps(nums, middle) <= maxOperations) {
                high = middle - 1
            } else {
                low = middle + 1
            }
        }
        return low
    }

    private fun requiredOps(
        bagSizes: IntArray,
        reducedSize: Int,
    ): Int = bagSizes.sumOf { requiredOps(it, reducedSize) }

    private fun requiredOps(
        bagSize: Int,
        reducedSize: Int,
    ): Int = (bagSize - 1) / reducedSize
}
