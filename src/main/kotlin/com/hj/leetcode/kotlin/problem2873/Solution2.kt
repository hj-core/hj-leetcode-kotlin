package com.hj.leetcode.kotlin.problem2873

/**
 * LeetCode page: [2873. Maximum Value of an Ordered Triplet I](https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-i/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun maximumTripletValue(nums: IntArray): Long {
        // The maximum element and the maximum nums[i]-nums[j] before k
        var maxElem = maxOf(nums[0], nums[1])
        var maxDiff = nums[0] - nums[1]
        var result = 0L

        for (k in 2..<nums.size) {
            result = maxOf(result, nums[k].toLong() * maxDiff)
            maxDiff = maxOf(maxDiff, maxElem - nums[k]) // DP based on whether nums[k] changes maxDiff
            maxElem = maxOf(maxElem, nums[k])
        }
        return result
    }
}
