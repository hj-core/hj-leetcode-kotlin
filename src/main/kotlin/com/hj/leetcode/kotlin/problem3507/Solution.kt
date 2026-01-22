package com.hj.leetcode.kotlin.problem3507

/**
 * LeetCode page: [3507. Minimum Pair Removal to Sort Array I](https://leetcode.com/problems/minimum-pair-removal-to-sort-array-i/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(N) where N is the length of nums.
    fun minimumPairRemoval(nums: IntArray): Int {
        val nums = nums.clone()

        for (newLen in nums.size downTo 2) {
            var isSorted = nums[0] <= nums[1]
            var minIndex = 0
            var minPairSum = nums[0] + nums[1]

            for (i in 0..<newLen - 1) {
                if (nums[i] > nums[i + 1]) {
                    isSorted = false
                }

                val pairSum = nums[i] + nums[i + 1]
                if (pairSum < minPairSum) {
                    minPairSum = pairSum
                    minIndex = i
                }
            }

            if (isSorted) {
                return nums.size - newLen
            }

            nums[minIndex] = minPairSum
            for (i in minIndex + 1..<newLen - 1) {
                nums[i] = nums[i + 1]
            }
        }

        return nums.size - 1
    }
}
