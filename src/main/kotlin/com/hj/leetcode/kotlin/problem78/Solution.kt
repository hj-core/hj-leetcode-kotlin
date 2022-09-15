package com.hj.leetcode.kotlin.problem78

/**
 * LeetCode page: [78. Subsets](https://leetcode.com/problems/subsets/);
 */
class Solution {
    /* Complexity:
     * Time O(N * 2^N) and Space O(N * 2^N) where N is the size of nums;
     */
    fun subsets(nums: IntArray): List<List<Int>> {
        val subsets = mutableListOf(listOf<Int>())
        for (num in nums) {
            repeat(subsets.size) { index ->
                subsets.add(subsets[index] + num)
            }
        }
        return subsets
    }
}