package com.hj.leetcode.kotlin.problem135

/**
 * LeetCode page: [135. Candy](https://leetcode.com/problems/candy/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of ratings.
    fun candy(ratings: IntArray): Int {
        // minCandy for each index that considers only the constraint from its right
        val minCandyRight = IntArray(ratings.size)
        minCandyRight[ratings.size - 1] = 1
        for (i in ratings.size - 2 downTo 0) {
            if (ratings[i] > ratings[i + 1]) {
                minCandyRight[i] = minCandyRight[i + 1] + 1
            } else {
                minCandyRight[i] = 1
            }
        }

        // minCandy for current index that considers only the constraint from its left
        var minCandyLeft = 1

        var result = minCandyRight[0]
        for (i in 1..<ratings.size) {
            if (ratings[i - 1] < ratings[i]) {
                minCandyLeft++
            } else {
                minCandyLeft = 1
            }
            result += maxOf(minCandyLeft, minCandyRight[i])
        }
        return result
    }
}
