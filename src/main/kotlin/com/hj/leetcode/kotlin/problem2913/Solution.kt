package com.hj.leetcode.kotlin.problem2913

/**
 * LeetCode page: [2913. Subarrays Distinct Element Sum of Squares I](https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-i/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(M) where N is the length of nums and
    // M is the maximum value in nums.
    fun sumCounts(nums: List<Int>): Int {
        val max = nums.max()
        var sum = 0
        for (i in nums.indices) {
            val seen = BooleanArray(max + 1)
            var distinct = 0
            for (j in i..<nums.size) {
                val num = nums[j]
                if (!seen[num]) {
                    distinct++
                    seen[num] = true
                }
                sum += distinct * distinct
            }
        }
        return sum
    }
}
