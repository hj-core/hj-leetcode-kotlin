package com.hj.leetcode.kotlin.problem75

/**
 * LeetCode page: [75. Sort Colors](https://leetcode.com/problems/sort-colors/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun sortColors(nums: IntArray) {
        var nextRed = nums.indexOfFirst { color -> color != 0 }
        if (nextRed == -1) {
            return
        }

        var nextBlue = nums.indexOfLast { color -> color != 2 }
        if (nextBlue == -1) {
            return
        }

        var curr = nextRed
        while (curr <= nextBlue) {
            if (nums[curr] == 0) {
                nums.swap(curr, nextRed)
                nextRed++
                curr++
            } else if (nums[curr] == 1) {
                curr++
            } else {
                nums.swap(curr, nextBlue)
                nextBlue--
            }
        }
    }

    private fun IntArray.swap(
        i: Int,
        j: Int,
    ) {
        this[i] = this[j].also { this[j] = this[i] }
    }
}
