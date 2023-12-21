package com.hj.leetcode.kotlin.problem1913

import kotlin.math.max
import kotlin.math.min

/**
 * LeetCode page: [1913. Maximum Product Difference Between Two Pairs](https://leetcode.com/problems/maximum-product-difference-between-two-pairs/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun maxProductDifference(nums: IntArray): Int {
        val (largest, secondLargest) = findTwoLargest(nums)
        val (smallest, secondSmallest) = findTwoSmallest(nums)
        return largest * secondLargest - smallest * secondSmallest
    }

    private fun findTwoLargest(nums: IntArray): Pair<Int, Int> {
        var largest = max(nums[0], nums[1])
        var secondLargest = min(nums[0], nums[1])
        for (index in 2..<nums.size) {
            when {
                largest < nums[index] -> {
                    secondLargest = largest
                    largest = nums[index]
                }

                secondLargest < nums[index] -> {
                    secondLargest = nums[index]
                }
            }
        }
        return largest to secondLargest
    }

    private fun findTwoSmallest(nums: IntArray): Pair<Int, Int> {
        var smallest = min(nums[0], nums[1])
        var secondSmallest = max(nums[0], nums[1])
        for (index in 2..<nums.size) {
            when {
                nums[index] < smallest -> {
                    secondSmallest = smallest
                    smallest = nums[index]
                }

                nums[index] < secondSmallest -> {
                    secondSmallest = nums[index]
                }
            }
        }
        return smallest to secondSmallest
    }
}