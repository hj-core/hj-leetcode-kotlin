package com.hj.leetcode.kotlin.problem1508

/**
 * LeetCode page: [1508. Range Sum of Sorted Subarray Sums](https://leetcode.com/problems/range-sum-of-sorted-subarray-sums/);
 */
class Solution {
    /* Complexity:
     * Time O((n^2)Log(n)) and Space O(n^2);
     */
    fun rangeSum(nums: IntArray, n: Int, left: Int, right: Int): Int {
        val subArraySums = findAllSubArraySums(nums)
        subArraySums.sort()

        val modulo = 1_000_000_007
        return ((left - 1)..<right).fold(0) { acc, i ->
            (acc + subArraySums[i]) % modulo
        }
    }

    private fun findAllSubArraySums(nums: IntArray): IntArray {
        val result = IntArray(nums.size * (nums.size + 1) / 2)
        var resultIndex = 0

        for (subArrayLength in 1..nums.size) {
            // For sub array starts at 0
            var subArraySum = (0..<subArrayLength).sumOf { nums[it] }
            result[resultIndex] = subArraySum
            resultIndex++

            for (subArrayStart in 1..nums.size - subArrayLength) {
                subArraySum -= nums[subArrayStart - 1]
                subArraySum += nums[subArrayStart + subArrayLength - 1]
                result[resultIndex] = subArraySum
                resultIndex++
            }
        }
        return result
    }
}