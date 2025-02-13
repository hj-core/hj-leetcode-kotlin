package com.hj.leetcode.kotlin.problem3066

import java.util.PriorityQueue

/**
 * LeetCode page: [3066. Minimum Operations to Exceed Threshold Value II](https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-ii/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of `nums`.
    fun minOperations(
        nums: IntArray,
        k: Int,
    ): Int {
        val pq = PriorityQueue<Int>()
        for (num in nums) {
            if (num < k) {
                pq.add(num)
            }
        }

        var result = 0
        while (2 <= pq.size) {
            val x = pq.poll()
            val y = pq.poll()
            if (x * 2 < k - y) {
                pq.add(x * 2 + y)
            }
            result++
        }
        if (pq.isNotEmpty()) {
            result++
        }
        return result
    }
}
