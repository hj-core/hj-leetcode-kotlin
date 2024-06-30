package com.hj.leetcode.kotlin.problem78

/**
 * LeetCode page: [78. Subsets](https://leetcode.com/problems/subsets/);
 */
class Solution {
    /* Complexity:
     * Time O(N * 2^N) and Space O(N * 2^N) where N is the size of nums;
     */
    fun subsets(nums: IntArray): List<List<Int>> {
        val result = mutableListOf(listOf<Int>())
        for (num in nums) {
            repeat(result.size) { index ->
                result.add(result[index] + num)
            }
        }
        return result
    }
}