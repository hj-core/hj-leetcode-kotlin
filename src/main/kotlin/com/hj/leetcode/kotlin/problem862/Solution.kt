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
        var result = nums.size + 1
        var prefixSum = 0L
        val leftCandidates =
            ArrayDeque<PrefixSum>().apply {
                addLast(PrefixSum(-1, prefixSum))
            }

        for ((right, num) in nums.withIndex()) {
            prefixSum += num
            val maxSubtraction = prefixSum - k

            with(leftCandidates) {
                while (isNotEmpty() && first().value <= maxSubtraction) {
                    val left = removeFirst().endInclusive
                    result = min(result, right - left)
                }

                while (isNotEmpty() && prefixSum <= last().value) {
                    removeLast()
                }
                addLast(PrefixSum(right, prefixSum))
            }
        }
        return if (nums.size < result) -1 else result
    }

    private class PrefixSum(
        val endInclusive: Int,
        val value: Long,
    )
}
