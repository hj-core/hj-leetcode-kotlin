package com.hj.leetcode.kotlin.problem862

import kotlin.math.min

/**
 * LeetCode page: [862. Shortest Subarray with Sum at Least K](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums.
     */
    fun shortestSubarray(
        nums: IntArray,
        k: Int,
    ): Int {
        val leftCandidates = ArrayDeque<PrefixSum>() // Consider subarray nums[left+1..right]
        var prefixSum = 0L
        var result = nums.size + 1

        for ((right, num) in nums.withIndex()) {
            prefixSum += num
            val maxSubtraction = prefixSum - k

            leftCandidates.let {
                if (k <= prefixSum) { // Handle the case that we cannot remove any left
                    result = min(result, right + 1)
                }
                while (it.isNotEmpty() && it.first().value <= maxSubtraction) {
                    val left = it.removeFirst().endInclusive
                    result = min(result, right - left)
                }

                while (it.isNotEmpty() && prefixSum <= it.last().value) {
                    it.removeLast()
                }
                it.addLast(PrefixSum(right, prefixSum))
            }
        }
        return if (nums.size < result) -1 else result
    }

    private class PrefixSum(
        val endInclusive: Int,
        val value: Long,
    )
}
