package com.hj.leetcode.kotlin.problem169

class Solution2 {
    /* Complexity:
     * Time Expected O(N) and Space O(1) where N is the size of nums;
     */
    fun majorityElement(nums: IntArray): Int {
        val maxTrials = 32
        repeat(maxTrials) {
            val result = nums.random()
            if (nums.count { it == result } > nums.size / 2) {
                return result
            }
        }
        throw IllegalStateException("You are so unlucky!")
    }
}