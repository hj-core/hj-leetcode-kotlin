package com.hj.leetcode.kotlin.problem3640

/**
 * LeetCode page: [3640. Trionic Array II](https://leetcode.com/problems/trionic-array-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun maxSumTrionic(nums: IntArray): Long {
        val minInf = -(1L shl 60)

        //  increasing   decreasing   increasing   decreasing
        // |-----L-----|-----M_1-----|-----R-----|-----M_2-----|
        //
        // For the indices (l, p, q, r):
        // - maxLM covers the range l..<q.
        // - maxPrefixR covers the range q..=r with a min length of two.
        // - maxSuffixR covers the range q..<r (i.e., the next l..<p).
        var maxSum = minInf
        var maxLM = minInf
        var prefixR = nums[0].toLong()
        var maxPrefixR = minInf
        var maxSuffixR = minInf

        for (i in 0..<nums.size - 1) {
            if (nums[i] < nums[i + 1]) {
                prefixR += nums[i + 1]
                maxPrefixR = maxOf(maxPrefixR, prefixR)
                maxSuffixR = maxOf(maxSuffixR, 0) + nums[i]
                maxSum = maxOf(maxSum, maxLM + maxPrefixR)
            } else if (nums[i] > nums[i + 1]) {
                if (maxPrefixR > minInf) {
                    maxLM = maxSuffixR
                    maxPrefixR = minInf
                    maxSuffixR = minInf
                }
                maxLM += nums[i]
                prefixR = nums[i + 1].toLong()
            } else {
                maxLM = minInf
                prefixR = nums[i + 1].toLong()
                maxPrefixR = minInf
                maxSuffixR = minInf
            }
        }

        return maxSum
    }
}
