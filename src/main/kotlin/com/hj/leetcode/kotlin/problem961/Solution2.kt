package com.hj.leetcode.kotlin.problem961

import kotlin.random.Random

/**
 * LeetCode page: [961. N-Repeated Element in Size 2N Array](https://leetcode.com/problems/n-repeated-element-in-size-2n-array/);
 */
class Solution2 {
    // Complexity:
    // Expected Time O(rand) and Space O(rand).
    fun repeatedNTimes(nums: IntArray): Int {
        while (true) {
            val i = Random.nextInt(nums.size)
            val j = Random.nextInt(nums.size)

            if (i != j && nums[i] == nums[j]) {
                return nums[i]
            }
        }
    }
}
