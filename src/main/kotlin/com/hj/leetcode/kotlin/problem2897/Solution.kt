package com.hj.leetcode.kotlin.problem2897

/**
 * LeetCode page: [2897. Apply Operations on Array to Maximize Sum of Squares](https://leetcode.com/problems/apply-operations-on-array-to-maximize-sum-of-squares/);
 */
class Solution {
    // Complexity:
    // Time O(NW) and Space O(W) where N is the length of nums and W is the
    // width of Int.
    fun maxSum(
        nums: List<Int>,
        k: Int,
    ): Int {
        val count = IntArray(32)
        for (num in nums) {
            for (shf in 0..<32) {
                count[shf] += num ushr shf and 1
            }
        }

        val modulo = 1_000_000_007
        var sum = 0L
        for (picked in 0..<minOf(count.max(), k)) {
            val num = (0..<32).sumOf { if (count[it] > picked) 1L shl it else 0L }
            sum = (sum + num * num) % modulo
        }

        return sum.toInt()
    }
}
