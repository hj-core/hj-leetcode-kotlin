package com.hj.leetcode.kotlin.problem713

/**
 * LeetCode page: [713. Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
        var result = 0
        var start = 0
        var product = 1

        for (end in nums.indices) {
            product *= nums[end]

            while (k <= product && start <= end) {
                product /= nums[start]
                start += 1
            }
            result += end - start + 1
        }
        return result
    }
}