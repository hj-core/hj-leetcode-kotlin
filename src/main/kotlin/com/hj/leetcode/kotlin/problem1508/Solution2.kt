package com.hj.leetcode.kotlin.problem1508

import kotlin.math.max

class Solution2 {
    /* Complexity:
     * Time O(nLogM) and Space O(n) where M is the sum of nums;
     */
    fun rangeSum(nums: IntArray, n: Int, left: Int, right: Int): Int {
        val modulo = 1_000_000_007
        return (sumKSmallestSubarraySums(nums, right, modulo)
                - sumKSmallestSubarraySums(nums, left - 1, modulo))
    }

    private fun sumKSmallestSubarraySums(nums: IntArray, k: Int, modulo: Int): Int {
        val kthSum = kthSmallestSubarraySum(nums, k)
        val countSums = countSubarraysWithSumNotGreaterThan(nums, kthSum)
        val sumSums = sumSubarraysWithSumNotGreaterThan(nums, kthSum, modulo)
        return (sumSums - kthSum * (countSums - k)).mod(modulo)
    }

    private fun kthSmallestSubarraySum(nums: IntArray, k: Int): Int {
        require(0 <= k && k <= (nums.size + 1) * nums.size / 2)
        if (k == 0) {
            return 0
        }
        var lowerSum = 0
        var upperSum = nums.sum()
        while (lowerSum <= upperSum) {
            val guess = (lowerSum + upperSum) ushr 1
            val count = countSubarraysWithSumNotGreaterThan(nums, guess)
            when {
                count < k -> lowerSum = guess + 1
                count > k -> upperSum = guess - 1
                else -> upperSum = guess - 1 // Coerce the guess to the kth sum
            }
        }
        return lowerSum
    }

    private fun countSubarraysWithSumNotGreaterThan(nums: IntArray, limit: Int): Int {
        var result = 0
        var rightMostEnd = 0 // Exclusive
        var subarraySum = 0
        for (start in nums.indices) {
            require(nums[start] >= 0) { "All numbers should be non-negative." }
            rightMostEnd = max(start, rightMostEnd)
            while (rightMostEnd < nums.size && subarraySum + nums[rightMostEnd] <= limit) {
                subarraySum += nums[rightMostEnd]
                rightMostEnd += 1
            }
            result += rightMostEnd - start

            if (start < rightMostEnd) {
                subarraySum -= nums[start]
            }
        }
        return result
    }

    private fun sumSubarraysWithSumNotGreaterThan(nums: IntArray, limit: Int, modulo: Int): Int {
        var result = 0
        var rightMostEnd = 0
        var subarraySum = 0
        val rightmostEnds = ArrayDeque<Int>()
        var sumRightmostEnds = 0
        for (start in nums.indices) {
            require(nums[start] >= 0) { "All numbers should be non-negative." }
            rightMostEnd = max(start, rightMostEnd)
            while (rightMostEnd < nums.size && subarraySum + nums[rightMostEnd] <= limit) {
                subarraySum += nums[rightMostEnd]
                rightMostEnd += 1
            }

            rightmostEnds.addLast(rightMostEnd)
            sumRightmostEnds += rightMostEnd
            while (rightmostEnds.isNotEmpty() && rightmostEnds.first() <= start) {
                sumRightmostEnds -= rightmostEnds.first()
                rightmostEnds.removeFirst()
            }
            result += nums[start] * (sumRightmostEnds - start * rightmostEnds.size)
            result %= modulo

            if (start < rightMostEnd) {
                subarraySum -= nums[start]
            }
        }
        return result
    }
}