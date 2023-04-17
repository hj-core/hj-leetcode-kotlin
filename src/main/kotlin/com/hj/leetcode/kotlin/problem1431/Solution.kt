package com.hj.leetcode.kotlin.problem1431

/**
 * LeetCode page: [1431. Kids With the Greatest Number of Candies](https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of candies;
     */
    fun kidsWithCandies(candies: IntArray, extraCandies: Int): List<Boolean> {
        val greatestCandies = candies.max()!!
        return candies.map { it + extraCandies >= greatestCandies }
    }
}