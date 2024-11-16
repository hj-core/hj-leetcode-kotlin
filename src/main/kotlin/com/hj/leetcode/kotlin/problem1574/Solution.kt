package com.hj.leetcode.kotlin.problem1574

import kotlin.math.min

/**
 * LeetCode page: [1574. Shortest Subarray to be Removed to Make Array Sorted](https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and SpaceO(1) where N is the length of arr.
     */
    fun findLengthOfShortestSubarray(arr: IntArray): Int {
        // arr = L + removed + R
        // L and R are sorted or empty, but they cannot both be empty.
        // L.last() <= R.first()
        val maxLeftLength =
            (1..<arr.size)
                .firstOrNull { arr[it] < arr[it - 1] }
                ?: return 0

        var result = arr.size - maxLeftLength
        var left = maxLeftLength - 1

        for (right in arr.indices.reversed()) {
            while (0 <= left && arr[right] < arr[left]) {
                left -= 1
            }
            result = min(result, right - left - 1)

            if (arr[right] < arr[right - 1]) {
                break
            }
        }
        return result
    }
}
