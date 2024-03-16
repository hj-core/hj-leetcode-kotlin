package com.hj.leetcode.kotlin.problem238

/**
 * LeetCode page: [238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun productExceptSelf(nums: IntArray): IntArray {
        val result = nums.suffixProducts()
        var prefixProduct = 1
        for (i in result.indices) {
            result[i] = prefixProduct * result.getOrElse(i + 1) { 1 }
            prefixProduct *= nums[i]
        }
        return result
    }

    private fun IntArray.suffixProducts(): IntArray {
        val result = IntArray(this.size)
        for (i in result.indices.reversed()) {
            result[i] = this[i] * result.getOrElse(i + 1) { 1 }
        }
        return result
    }
}