package com.hj.leetcode.kotlin.problem334

/**
 * LeetCode page: [334. Increasing Triplet Subsequence](https://leetcode.com/problems/increasing-triplet-subsequence/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun increasingTriplet(nums: IntArray): Boolean {
        val auxList = mutableListOf<Int>().also { it.add(nums[0]) }

        for (index in 1..nums.lastIndex) {
            val num = nums[index]

            when {
                num < auxList.first() -> auxList[0] = num
                num > auxList.first() && num < auxList.last() -> auxList[1] = num
                num > auxList.last() -> auxList.add(num)
            }

            if (auxList.size == 3) return true
        }
        return false
    }
}