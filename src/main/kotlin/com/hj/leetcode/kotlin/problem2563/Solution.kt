package com.hj.leetcode.kotlin.problem2563

/**
 * LeetCode page: [2563. Count the Number of Fair Pairs](https://leetcode.com/problems/count-the-number-of-fair-pairs/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of nums.
    fun countFairPairs(
        nums: IntArray,
        lower: Int,
        upper: Int,
    ): Long {
        val sortedNums = nums.sorted()

        // The valid pairs for the left are in the range of (minRight, maxRight]
        var minRight = sortedNums.lastIndex
        var maxRight = sortedNums.lastIndex
        var result = 0L

        for (left in sortedNums.indices) {
            while (left < maxRight && upper < sortedNums[left] + sortedNums[maxRight]) {
                maxRight--
            }

            minRight = maxOf(left, minOf(minRight, maxRight))
            while (left < minRight && lower <= sortedNums[left] + sortedNums[minRight]) {
                minRight--
            }

            if (maxRight == left) {
                break
            }
            result += maxRight - minRight
        }
        return result
    }
}
