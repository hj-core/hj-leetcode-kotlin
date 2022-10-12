package com.hj.leetcode.kotlin.problem976

/**
 * LeetCode page: [976. Largest Perimeter Triangle](https://leetcode.com/problems/largest-perimeter-triangle/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of nums;
     */
    fun largestPerimeter(nums: IntArray): Int {
        val sortedLengths = nums.clone().apply { sort() }
        require(sortedLengths.first() > 0)

        for (index in sortedLengths.lastIndex downTo 2) {
            val longestSide = sortedLengths[index]
            val sumOfShorterSides = sortedLengths[index - 1] + sortedLengths[index - 2]

            val canFormTriangle = sumOfShorterSides > longestSide
            if (canFormTriangle) return sumOfShorterSides + longestSide
        }
        return 0
    }
}