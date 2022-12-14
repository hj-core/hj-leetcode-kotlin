package com.hj.leetcode.kotlin.problem198

/**
 * LeetCode page: [198. House Robber](https://leetcode.com/problems/house-robber/description/);
 */
class Solution {
    /* Complexity:
     * Time O(|nums|) and Space O(1);
     */
    fun rob(nums: IntArray): Int {
        val suffixResults = ArrayDeque<Int>().apply {
            addFirst(0) // result for suffix nums.size;
            addFirst(0) // result for suffix nums.size + 1;
        }

        for (suffix in nums.indices.reversed()) {
            val suffixResult = maxOf(
                nums[suffix] + suffixResults.last(), suffixResults.first()
            )
            suffixResults.apply {
                addFirst(suffixResult)
                removeLast() // drop the suffix result that will not be used later;
            }
        }
        return suffixResults.max()!!
    }
}