package com.hj.leetcode.kotlin.problem1200

/**
 * LeetCode page: [1200. Minimum Absolute Difference](https://leetcode.com/problems/minimum-absolute-difference/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of arr.
    fun minimumAbsDifference(arr: IntArray): List<List<Int>> {
        val arr = arr.sortedArray()
        val (minDiff, countMinDiff) = countMinDiff(arr)

        return buildList(countMinDiff) {
            for (i in 1..<arr.size) {
                if (arr[i] - arr[i - 1] == minDiff) {
                    add(listOf(arr[i - 1], arr[i]))
                }
            }
        }
    }

    // Returns the difference and the number of minimum different pairs.
    private fun countMinDiff(sortedArr: IntArray): Pair<Int, Int> {
        require(sortedArr.size > 1)

        var minDiff = sortedArr[1] - sortedArr[0]
        var countMinDiff = 1
        for (i in 2..<sortedArr.size) {
            val diff = sortedArr[i] - sortedArr[i - 1]
            if (diff < minDiff) {
                minDiff = diff
                countMinDiff = 1
            } else if (diff == minDiff) {
                countMinDiff++
            }
        }

        return Pair(minDiff, countMinDiff)
    }
}
