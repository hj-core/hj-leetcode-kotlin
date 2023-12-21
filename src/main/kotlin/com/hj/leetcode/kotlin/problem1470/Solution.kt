package com.hj.leetcode.kotlin.problem1470

/**
 * LeetCode page: [1470. Shuffle the Array](https://leetcode.com/problems/shuffle-the-array/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(n);
     */
    fun shuffle(nums: IntArray, n: Int): IntArray {
        require(nums.size == n * 2)

        val shuffled = IntArray(nums.size)
        repeat(n) {
            shuffled[it * 2] = nums[it]
            shuffled[it * 2 + 1] = nums[it + n]
        }
        return shuffled
    }
}