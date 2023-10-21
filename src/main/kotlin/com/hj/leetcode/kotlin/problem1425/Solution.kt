package com.hj.leetcode.kotlin.problem1425

/**
 * LeetCode page: [1425. Constrained Subsequence Sum](https://leetcode.com/problems/constrained-subsequence-sum/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(k) where N is the size of nums;
     */
    fun constrainedSubsetSum(nums: IntArray, k: Int): Int {
        var result = nums.last()
        val sortedQueue = ArrayDeque<Pair<Int, Int>>() // contains pair(index, localMax)

        for (i in nums.indices.reversed()) {
            while (sortedQueue.isNotEmpty() && sortedQueue.last().first > i + k) {
                sortedQueue.removeLast()
            }

            // max sequence sum with nums[i] included from suffix array nums[i:]
            val localMax =
                nums[i] + (sortedQueue.lastOrNull()?.second?.coerceAtLeast(0) ?: 0)

            while (sortedQueue.isNotEmpty() && sortedQueue.first().second <= localMax) {
                sortedQueue.removeFirst()
            }
            sortedQueue.addFirst(Pair(i, localMax))
            result = maxOf(result, localMax)
        }
        return result
    }
}