package com.hj.leetcode.kotlin.problem3066

/**
 * LeetCode page: [3066. Minimum Operations to Exceed Threshold Value II](https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-ii/);
 */
class Solution2 {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of `nums`.
    fun minOperations(
        nums: IntArray,
        k: Int,
    ): Int {
        val sortedNums = nums.clone().apply { sort() } // [q2 | q1]
        var q1Head = 0
        var q2Head = 0
        var q2Tail = 0 // Invariant: q1Head-q2Tail = q2Tail-q2Head >= 0
        var result = 0

        fun pollMinElement(): Int =
            when {
                q1Head == sortedNums.size && q2Head == q2Tail -> throw IllegalStateException()
                q1Head == sortedNums.size -> sortedNums[q2Head].also { q2Head++ }
                q2Head == q2Tail || sortedNums[q1Head] <= sortedNums[q2Head] -> sortedNums[q1Head].also { q1Head++ }
                else -> sortedNums[q2Head].also { q2Head++ }
            }

        while (true) {
            val x = pollMinElement()
            if (k <= x) {
                return result
            }

            val y = pollMinElement()
            sortedNums[q2Tail] = minOf(x * 2, k - y) + y // Avoid potential overflow of x*2+y
            q2Tail++
            result++
        }
    }
}
