package com.hj.leetcode.kotlin.problem1535

/**
 * LeetCode page: [1535. Find the Winner of an Array Game](https://leetcode.com/problems/find-the-winner-of-an-array-game/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(1);
     */
    fun getWinner(arr: IntArray, k: Int): Int {
        var result = arr[0]
        var winStreak = 0

        for (index in 1..<arr.size) {
            if (arr[index] < result) {
                winStreak++
            } else {
                result = arr[index]
                winStreak = 1
            }

            if (winStreak == k) {
                return result
            }
        }
        return result
    }
}