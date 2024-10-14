package com.hj.leetcode.kotlin.problem2530

import java.util.*

/**
 * LeetCode page: [2530. Maximal Score After Applying K Operations](https://leetcode.com/problems/maximal-score-after-applying-k-operations/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN+kLogN) and Space O(N) where N is the size of nums.
     */
    fun maxKelements(
        nums: IntArray,
        k: Int,
    ): Long {
        val maxPq = PriorityQueue<Int>(Comparator.reverseOrder())
        for (num in nums) {
            maxPq.offer(num)
        }

        var result = 0L
        repeat(k) {
            val maxValue = maxPq.poll()
            result += maxValue

            val putBack = (maxValue + 2) / 3
            maxPq.offer(putBack)
        }
        return result
    }
}
