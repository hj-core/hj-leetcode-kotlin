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
        val existPositive = BooleanArray(101)

        for (num in nums) {
            maxElem = maxOf(maxElem, num)
            if (num > 0) {
                existPositive[num] = true
            }
        }

        if (maxElem <= 0) {
            return maxElem
        }

        return existPositive.foldIndexed(0) { num, result, exist ->
            if (exist) result + num else result
        }
    }
}
