package com.hj.leetcode.kotlin.problem3640

/**
 * LeetCode page: [3640. Trionic Array II](https://leetcode.com/problems/trionic-array-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun maxSumTrionic(nums: IntArray): Long {
        val minInf = -(1L shl 60)

        //  increasing    decreasing   increasing    decreasing
        // |-----L-----|-----Mid1-----|-----R-----|-----Mid2-----|
        //
        // For the indices (l, p, q, r):
        // - maxSuffixL covers the range l..<p.
        // - midSum covers the range p..<q.
        // - maxPrefixR covers the range q..=r.
        var maxSum = minInf
        var maxSuffixL = minInf
        var midSum = 0L
        var prefixR = nums[0].toLong()
        var maxPrefixR = minInf
        var maxSuffixR = minInf

        for (i in 0..<nums.size - 1) {
            if (nums[i] < nums[i + 1]) {
                prefixR += nums[i + 1]
                maxPrefixR = maxOf(maxPrefixR, prefixR)
                maxSuffixR = maxOf(maxSuffixR, 0) + nums[i]
                maxSum = maxOf(maxSum, maxSuffixL + midSum + maxPrefixR)
            } else if (nums[i] > nums[i + 1]) {
                if (maxPrefixR > minInf) {
                    maxSuffixL = maxSuffixR
                    midSum = 0L
                    maxPrefixR = minInf
                    maxSuffixR = minInf
                }
                midSum += nums[i]
                prefixR = nums[i + 1].toLong()
            } else {
                maxSuffixL = minInf
                midSum = 0L
                prefixR = nums[i].toLong()
                maxPrefixR = minInf
                maxSuffixR = minInf
            }
        }

        return maxSum
    }
}
