package com.hj.leetcode.kotlin.problem2616

/**
 * LeetCode page: [2616. Minimize the Maximum Difference of Pairs](https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN+NLogM) and Space O(N) where N is the length of nums
    // and M is the range of nums.
    fun minimizeMax(
        nums: IntArray,
        p: Int,
    ): Int {
        if (p == 0) {
            return 0
        }

        // Binary search on the minimum maximum difference. The result
        // is in the range [left, right].
        val sortedNums = nums.sortedArray()
        var left = 0
        var right = sortedNums.last() - sortedNums.first()

        while (left < right) {
            val mid = (left + right) ushr 1
            if (canFindPairs(sortedNums, p, mid)) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }

    // Returns whether we can find p pairs such that every pair has a
    // difference not greater than allowedDiff.
    private fun canFindPairs(
        sortedNums: IntArray,
        p: Int,
        allowedDiff: Int,
    ): Boolean {
        var found = 0
        var i = 1
        while (i < sortedNums.size) {
            if (sortedNums[i] - sortedNums[i - 1] <= allowedDiff) {
                found++
                i += 2
                if (found == p) {
                    return true
                }
            } else {
                i += 1
            }
        }
        return false
    }
}
