package com.hj.leetcode.kotlin.problem3471

/**
 * LeetCode page: [3471. Find the Largest Almost Missing Integer](https://leetcode.com/problems/find-the-largest-almost-missing-integer/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun largestInteger(
        nums: IntArray,
        k: Int,
    ): Int {
        if (k == 1) {
            return maxUniqueNumber(nums)
        }
        if (k == nums.size) {
            return nums.max()
        }
        if (nums[0] == nums[nums.lastIndex]) {
            return -1
        }

        var a = nums[0]
        var b = nums[nums.lastIndex]
        for (i in 1..<nums.lastIndex) {
            if (nums[i] == a) {
                a = -1
            } else if (nums[i] == b) {
                b = -1
            }
        }
        return maxOf(a, b)
    }

    private fun maxUniqueNumber(nums: IntArray): Int {
        val freq = hashMapOf<Int, Int>()
        for (num in nums) {
            freq[num] = 1 + (freq[num] ?: 0)
        }
        return freq.maxOf { (num, freq) -> if (freq == 1) num else -1 }
    }
}
