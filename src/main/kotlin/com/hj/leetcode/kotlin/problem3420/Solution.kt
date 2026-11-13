package com.hj.leetcode.kotlin.problem3420

/**
 * LeetCode page: [3420. Count Non-Decreasing Subarrays After K Operations](https://leetcode.com/problems/count-non-decreasing-subarrays-after-k-operations/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun countNonDecreasingSubarrays(
        nums: IntArray,
        k: Int,
    ): Long {
        val n = nums.size
        // Increasing indices where nums[index] is also strictly increasing
        val indexDeque = ArrayDeque<Int>(n)
        var count = 0L
        var ops = 0L // ops to make nums[start..<end] non-decreasing
        var end = n
        for (start in n - 1 downTo 0) {
            while (indexDeque.isNotEmpty() && nums[indexDeque[0]] <= nums[start]) {
                // nums[next..<nextNext] = nums[next]
                val next = indexDeque.removeFirst()
                val nextNext = indexDeque.firstOrNull() ?: end
                // Bring nums[next..<nextNext] to nums[start]
                val diff = (nums[start] - nums[next]).toLong()
                val width = nextNext - next
                ops += diff * width
            }
            indexDeque.addFirst(start)

            while (ops > k) {
                end--
                ops -= nums[indexDeque.last()] - nums[end]
                if (indexDeque.last() == end) {
                    indexDeque.removeLast()
                }
            }

            count += end - start
        }

        return count
    }
}
