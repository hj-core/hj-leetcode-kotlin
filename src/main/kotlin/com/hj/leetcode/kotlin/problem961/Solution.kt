package com.hj.leetcode.kotlin.problem961

/**
 * LeetCode page: [961. N-Repeated Element in Size 2N Array](https://leetcode.com/problems/n-repeated-element-in-size-2n-array/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun repeatedNTimes(nums: IntArray): Int {
        for (i in nums.indices step 2) {
            if (nums[i] == nums[i + 1]) {
                return nums[i]
            }
        }

        return if (nums[1] == nums[3]) nums[1] else nums[0]
    }

    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    //
    // A step of 2 improves the worst case over a step of 1, though
    // its impact on common cases is unverified.
    fun repeatedNTimesStep2(nums: IntArray): Int {
        for (i in nums.indices step 2) {
            if (nums[i] == nums[i + 1]) {
                return nums[i]
            }
        }

        return if (
            nums[0] == nums[2] || nums[0] == nums[3]
        ) {
            nums[0]
        } else {
            nums[1]
        }
    }
}
