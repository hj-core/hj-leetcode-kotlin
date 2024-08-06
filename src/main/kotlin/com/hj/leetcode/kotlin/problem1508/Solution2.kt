package com.hj.leetcode.kotlin.problem1508

import kotlin.math.max

class Solution2 {
    /* Complexity:
     * Time O(nLogM) and Space O(1) where M is the sum of nums;
     */
    fun rangeSum(nums: IntArray, n: Int, left: Int, right: Int): Int {
        val modulo = 1_000_000_007
        return (sumKSmallestSubarraySums(nums, right, modulo)
                - sumKSmallestSubarraySums(nums, left - 1, modulo))
    }

    private fun sumKSmallestSubarraySums(nums: IntArray, k: Int, modulo: Int): Int {
        val kthSum = nums.kthSmallestSubarraySum(k)
        val countSums = nums.countSubarraysWithSumNotGreaterThan(kthSum)
        val sumSums = nums.sumSubarraysWithSumNotGreaterThan(kthSum, modulo)
        return (sumSums - kthSum * (countSums - k)).mod(modulo)
    }

    private fun IntArray.kthSmallestSubarraySum(k: Int): Int {
        require(0 <= k && k <= (size + 1) * size / 2)
        if (k == 0) {
            return 0
        }
        var lowerSum = 0
        var upperSum = sum()
        while (lowerSum <= upperSum) {
            val guess = (lowerSum + upperSum) ushr 1
            val count = this.countSubarraysWithSumNotGreaterThan(guess)
            when {
                count < k -> lowerSum = guess + 1
                count > k -> upperSum = guess - 1
                else -> upperSum = guess - 1 // Coerce the guess to the kth sum
            }
        }
        return lowerSum
    }

    private fun IntArray.countSubarraysWithSumNotGreaterThan(limit: Int): Int {
        var result = 0
        var rightMostEnd = 0 // Exclusive
        var subarraySum = 0
        for (start in indices) {
            require(this[start] >= 0) { "All numbers should be non-negative." }
            rightMostEnd = max(start, rightMostEnd)
            while (rightMostEnd < size && subarraySum + this[rightMostEnd] <= limit) {
                subarraySum += this[rightMostEnd]
                rightMostEnd += 1
            }
            result += rightMostEnd - start

            // Prepare subarraySum for next start
            if (start < rightMostEnd) {
                subarraySum -= this[start]
            }
        }
        return result
    }

    private fun IntArray.sumSubarraysWithSumNotGreaterThan(limit: Int, modulo: Int): Int {
        /* Imaging a triangle shape stack of subarrays that consists of all the
         * valid subarrays that end at a given end index.
         *
         * These subarrays are aligned to the right and the higher, the shorter.
         */

        var result = 0
        var earliestStart = 0
        var subarraySum = 0 // Sum of subarray from earliestStart to end (inclusive)
        var stackSum = 0 // Sum of all valid subarrays that end at the given end

        for (end in this.indices) {
            // Extend the stack to include current end
            subarraySum += this[end]
            stackSum += this[end] * (end - earliestStart + 1)

            // Adjust the stack in case the subarraySum exceeds the limit
            while (subarraySum > limit) {
                stackSum -= subarraySum // Pop the bottom layer of stack
                subarraySum -= this[earliestStart]
                earliestStart++
            }

            result = (result + stackSum) % modulo
        }
        return result
    }
}