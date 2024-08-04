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

        for (subArrayStart in nums.indices) {
            var subArraySum = 0
            for (subArrayEnd in subArrayStart..nums.lastIndex) {
                subArraySum += nums[subArrayEnd]
                result[resultIndex] = subArraySum
                resultIndex++
            }
        }
        return result
    }
}