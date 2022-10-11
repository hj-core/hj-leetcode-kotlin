package com.hj.leetcode.kotlin.problem334

/**
 * LeetCode page: [334. Increasing Triplet Subsequence](https://leetcode.com/problems/increasing-triplet-subsequence/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun increasingTriplet(nums: IntArray): Boolean {
        val auxTriplet = mutableListOf<Int>().also { it.add(nums[0]) }

        for (index in 1..nums.lastIndex) {
            val num = nums[index]

            when {
                num > auxTriplet.last() -> auxTriplet.add(num)
                num < auxTriplet.first() -> auxTriplet[0] = num
                num > auxTriplet.first() && num < auxTriplet.last() -> auxTriplet[1] = num
            }

            if (auxTriplet.size == 3) return true
        }
        return false
    }
}