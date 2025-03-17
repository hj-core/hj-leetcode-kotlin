package com.hj.leetcode.kotlin.problem2206

/**
 * LeetCode page: [2206. Divide Array Into Equal Pairs](https://leetcode.com/problems/divide-array-into-equal-pairs/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun divideArray(nums: IntArray): Boolean =
        nums
            .asList()
            .groupingBy { it }
            .eachCount()
            .values
            .all { it and 1 == 0 }
}
