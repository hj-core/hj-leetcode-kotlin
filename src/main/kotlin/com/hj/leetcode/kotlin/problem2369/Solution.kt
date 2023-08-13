package com.hj.leetcode.kotlin.problem2369

/**
 * LeetCode page: [2369. Check if There is a Valid Partition For The Array](https://leetcode.com/problems/check-if-there-is-a-valid-partition-for-the-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun validPartition(nums: IntArray): Boolean {
        // dp[i] ::= validPartition(nums[i:])
        val dp = BooleanArray(nums.size + 3)
        dp[nums.size] = true
        dp[nums.size + 1] = false
        dp[nums.size + 2] = false

        for (start in nums.indices.reversed()) {
            dp[start] = ((dp[start + 2] && nums.isAllEqual(start..start + 1))
                    || (dp[start + 3] && nums.isAllEqual(start..start + 2))
                    || (dp[start + 3] && nums.isConsecutiveIncreasing(start..start + 2)))
        }
        return dp[0]
    }


    private fun IntArray.isAllEqual(indexRange: IntRange): Boolean {
        return indexRange.all { index -> this[index] == this[indexRange.first] }
    }

    private fun IntArray.isConsecutiveIncreasing(indexRange: IntRange): Boolean {
        return indexRange.withIndex().all { (difference, index) ->
            this[index] == this[indexRange.first] + difference
        }
    }
}