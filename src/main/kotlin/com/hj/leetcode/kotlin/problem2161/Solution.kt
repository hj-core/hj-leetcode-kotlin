package com.hj.leetcode.kotlin.problem2161

/**
 * LeetCode page: [2161. Partition Array According to Given Pivot](https://leetcode.com/problems/partition-array-according-to-given-pivot/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of `nums`.
    fun pivotArray(
        nums: IntArray,
        pivot: Int,
    ): IntArray {
        var i = 0 // The index in `nums` to place the next number
        val largerNums = mutableListOf<Int>() // The larger numbers in their original order

        // Handle the smaller numbers and collect the larger numbers
        for (num in nums) {
            if (num < pivot) {
                nums[i++] = num
            } else if (num > pivot) {
                largerNums.add(num)
            }
        }
        // Handle the pivots
        repeat(nums.size - largerNums.size - i) {
            nums[i++] = pivot
        }
        // Handle the larger numbers
        for (num in largerNums) {
            nums[i++] = num
        }
        return nums
    }
}
