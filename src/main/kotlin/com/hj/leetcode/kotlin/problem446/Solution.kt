package com.hj.leetcode.kotlin.problem446

/**
 * LeetCode page: [446. Arithmetic Slices II - Subsequence](https://leetcode.com/problems/arithmetic-slices-ii-subsequence/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N^2) where N is the size of nums;
     */
    fun numberOfArithmeticSlices(nums: IntArray): Int {
        // dpPair[first][step]::= the number of subsequences (first, first+step)
        val dpPair = hashMapOf<Int, MutableMap<Long, Int>>()

        /* dpAs[first][step]::= the number of arithmetic subsequences that start
         * with first and has a step of step
         */
        val dpAS = hashMapOf<Int, MutableMap<Long, Int>>()

        val visited = hashMapOf<Int, Int>()
        for (first in nums.asList().asReversed()) {
            for ((second, count) in visited) {
                val step = first.toLong() - second

                dpAS.computeIfAbsent(first) { hashMapOf() }[step] =
                    ((dpAS[first]?.get(step) ?: 0)
                            + (dpPair[second]?.get(step) ?: 0)
                            + (dpAS[second]?.get(step) ?: 0))

                dpPair.computeIfAbsent(first) { hashMapOf() }[step] =
                    count + (dpPair[first]?.get(step) ?: 0)
            }
            visited[first] = 1 + (visited[first] ?: 0)
        }
        return dpAS.values.sumOf { it.values.sum() }
    }
}