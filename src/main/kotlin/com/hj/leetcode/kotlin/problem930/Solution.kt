package com.hj.leetcode.kotlin.problem930

/**
 * LeetCode page: [930. Binary Subarrays With Sum](https://leetcode.com/problems/binary-subarrays-with-sum/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun numSubarraysWithSum(nums: IntArray, goal: Int): Int {
        var result = 0
        var prefixSum = 0
        val countPrefixSum = hashMapOf(0 to 1)

        for (num in nums) {
            prefixSum += num
            countPrefixSum[prefixSum - goal]?.let {
                result += it
            }
            countPrefixSum.compute(prefixSum) { _, count -> 1 + (count ?: 0) }
        }
        return result
    }
}