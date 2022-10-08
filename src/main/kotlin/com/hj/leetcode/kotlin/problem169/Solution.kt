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
         * Note that if the existence of majority element is not guaranteed, second pass is required to
         * verify the found element is really a majority;
         */
        var votes = 1
        var candidate = nums[0]

        for (index in 1..nums.lastIndex) {
            when {
                votes == 0 -> {
                    candidate = nums[index]
                    votes = 1
                }
                candidate == nums[index] -> votes++
                else -> votes--
            }
        }
        return candidate
    }
}