package com.hj.leetcode.kotlin.problem3487

/**
 * LeetCode page: [3487. Maximum Unique Subarray Sum After Deletion](https://leetcode.com/problems/maximum-unique-subarray-sum-after-deletion/);
 */
class Solution {
    // Complexity:
    // Time O(N+M) and Space O(M) where N is the length of
    // nums and M is the upper bound of element values.
    fun maxSum(nums: IntArray): Int {
        var maxElem = nums[0]
        val seenPositive = BooleanArray(101)
        var result = 0

        for (num in nums) {
            if (num > 0 && !seenPositive[num]) {
                seenPositive[num] = true
                result += num
            }
            maxElem = maxOf(maxElem, num)
        }
        return if (maxElem <= 0) maxElem else result
    }
}
