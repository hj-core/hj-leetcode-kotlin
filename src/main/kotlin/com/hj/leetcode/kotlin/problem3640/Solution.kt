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
        // - prefixR covers the range q..=r with a min length of two.
        // - maxSuffixR covers the range q..<r (i.e., the next l..<p).
        var maxSum = minInf
        var maxLM = minInf
        var prefixR = nums[0].toLong()
        var maxSuffixR = minInf
        var outsideM = true

        for (i in 0..<nums.size - 1) {
            if (nums[i] < nums[i + 1]) {
                prefixR += nums[i + 1]
                maxSum = maxOf(maxSum, maxLM + prefixR)
                maxSuffixR = maxOf(maxSuffixR, 0) + nums[i]
                outsideM = true
            } else if (nums[i] > nums[i + 1]) {
                if (outsideM) {
                    maxLM = maxSuffixR
                    maxSuffixR = minInf
                    outsideM = false
                }
                maxLM += nums[i]
                prefixR = nums[i + 1].toLong()
            } else {
                maxLM = minInf
                prefixR = nums[i + 1].toLong()
                maxSuffixR = minInf
            }
        }

        return maxSum
    }
}
