package com.hj.leetcode.kotlin.problem169

/**
 * LeetCode page: [169. Majority Element](https://leetcode.com/problems/majority-element/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun majorityElement(nums: IntArray): Int {
        /* https://en.wikipedia.org/wiki/Boyer-Moore_majority_vote_algorithm;
         * Note that if the existence of majority element is not guaranteed, second pass
         * is required to verify the found element is really a majority;
         */
        var result = nums[0]
        var votes = 1

        for (i in 1..<nums.size) {
            when {
                nums[i] == result -> votes++
                votes > 0 -> votes--
                else -> {
                    votes = 1
                    result = nums[i]
                }
            }
        }
        return result
    }
}