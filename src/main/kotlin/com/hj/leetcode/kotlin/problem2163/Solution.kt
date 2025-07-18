package com.hj.leetcode.kotlin.problem2163

import java.util.*

/**
 * LeetCode page: [2163. Minimum Difference in Sums After Removal of Elements](https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of nums.
    fun minimumDifference(nums: IntArray): Long {
        val n = nums.size / 3

        // nSmallestSumL[i]:= the sum of the n smallest elements from
        // nums[0..<n+i].
        val nSmallestSumL = computeNSmallestSumL(nums)

        // nLargestSumR@i:= the sum of the n largest elements from
        // nums[n+i..<3n], initialized with i = n.
        var nLargestSumR = 0L
        val nLargestR = PriorityQueue<Int>()
        for (j in 2 * n..<3 * n) {
            nLargestR.add(nums[j])
            nLargestSumR += nums[j]
        }

        // Try every valid split
        var result = nSmallestSumL[n] - nLargestSumR
        for (i in n - 1 downTo 0) {
            nLargestR.add(nums[n + i])
            nLargestSumR += nums[n + i] - nLargestR.poll()
            result = minOf(result, nSmallestSumL[i] - nLargestSumR)
        }
        return result
    }

    // Returns a length n+1 array where the value at index i is the sum
    // of the n smallest elements from nums[0..<n+i]. The length of nums
    // is 3*n.
    private fun computeNSmallestSumL(nums: IntArray): LongArray {
        val n = nums.size / 3
        val result = LongArray(n + 1)
        val nSmallestL = PriorityQueue<Int>(reverseOrder())

        for (j in 0..<n) {
            nSmallestL.add(nums[j])
            result[0] += nums[j]
        }
        for (i in 1..n) {
            nSmallestL.add(nums[n + i - 1])
            result[i] = result[i - 1] + nums[n + i - 1] - nSmallestL.poll()
        }
        return result
    }
}
