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
        val deque = ArrayDeque<PrefixSum>()
        var prefixSum = 0L
        var result = nums.size + 1

        for ((right, num) in nums.withIndex()) {
            prefixSum += num
            if (k <= prefixSum) { // Handle empty deque
                result = min(result, right + 1)
            }

            val target = prefixSum - k
            while (deque.isNotEmpty() && deque.first().value <= target) {
                val left = deque.removeFirst().endInclusive
                result = min(result, right - left)
            }

            while (deque.isNotEmpty() && prefixSum <= deque.last().value) {
                deque.removeLast()
            }
            deque.addLast(PrefixSum(right, prefixSum))
        }
        return if (nums.size < result) -1 else result
    }

    private class PrefixSum(
        val endInclusive: Int,
        val value: Long,
    )
}
