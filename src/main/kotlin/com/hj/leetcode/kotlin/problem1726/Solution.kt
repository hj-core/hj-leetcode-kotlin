package com.hj.leetcode.kotlin.problem1726

/**
 * LeetCode page: [1726. Tuple with Same Product](https://leetcode.com/problems/tuple-with-same-product/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(N^2) where N is the length of `nums`.
    fun tupleSameProduct(nums: IntArray): Int {
        val productFreq = mutableMapOf<Int, Int>()
        var result = 0
        for (i in nums.indices) {
            for (j in i + 1..<nums.size) {
                val product = nums[i] * nums[j]
                val oldFreq = productFreq[product] ?: 0
                // Each visited pair with the product is completely distinct from the current pair
                // because we have limited the index order, and all numbers are distinct.
                // Each of these pairs produces 8 different combinations when paired with the current pair.
                result += oldFreq * 8
                productFreq[product] = oldFreq + 1
            }
        }
        return result
    }
}
