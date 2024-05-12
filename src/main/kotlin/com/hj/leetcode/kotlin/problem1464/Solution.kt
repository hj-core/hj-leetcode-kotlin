package com.hj.leetcode.kotlin.problem1464

import kotlin.math.max
import kotlin.math.min

/**
 * LeetCode page: [1464. Maximum Product of Two Elements in an Array](https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun maxProduct(nums: IntArray): Int {
        val (largest, secondLargest) = findTwoLargest(nums)
        return (largest - 1) * (secondLargest - 1)
    }

    private fun findTwoLargest(nums: IntArray): Pair<Int, Int> {
        var largest = max(nums[0], nums[1])
        var secondLargest = min(nums[0], nums[1])

        for (index in 2..<nums.size) {
            when {
                nums[index] > largest -> {
                    secondLargest = largest
                    largest = nums[index]
                }

                nums[index] > secondLargest -> {
                    secondLargest = nums[index]
                }
            }
        }
        return Pair(largest, secondLargest)
    }
}