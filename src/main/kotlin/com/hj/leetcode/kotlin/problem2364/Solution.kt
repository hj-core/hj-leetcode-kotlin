package com.hj.leetcode.kotlin.problem2364

/**
 * LeetCode page: [2364. Count Number of Bad Pairs](https://leetcode.com/problems/count-number-of-bad-pairs/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of `nums`.
    fun countBadPairs(nums: IntArray): Long = totalPairs(nums) - goodPairs(nums)

    private fun totalPairs(nums: IntArray): Long = (nums.size - 1L) * nums.size / 2

    private fun goodPairs(nums: IntArray): Long {
        val diffFreq = HashMap<Int, Int>() // entry=(nums[i]-i, freq)
        var result = 0L

        for (j in nums.indices) {
            val diff = nums[j] - j
            val oldCount = diffFreq[diff] ?: 0
            result += oldCount
            diffFreq[diff] = oldCount + 1
        }
        return result
    }
}
