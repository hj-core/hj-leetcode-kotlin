package com.hj.leetcode.kotlin.problem976

/**
 * LeetCode page: [976. Largest Perimeter Triangle](https://leetcode.com/problems/largest-perimeter-triangle/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length
    // of nums.
    fun largestPerimeter(nums: IntArray): Int {
        val sortedNums = nums.sortedArray()
        for (i in sortedNums.lastIndex downTo 2) {
            if (sortedNums[i] - sortedNums[i - 1] < sortedNums[i - 2]) {
                return sortedNums[i] + sortedNums[i - 1] + sortedNums[i - 2]
            }
        }
        return 0
    }
}
