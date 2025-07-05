package com.hj.leetcode.kotlin.problem1394

/**
 * LeetCode page: [1394. Find Lucky Integer in an Array](https://leetcode.com/problems/find-lucky-integer-in-an-array/);
 */
class Solution {
    // Complexity:
    // Time O(N+M) and Space O(M) where N is the length arr and M is
    // the allowed range of numbers.
    fun findLucky(arr: IntArray): Int {
        val freq = IntArray(501)
        for (num in arr) {
            freq[num]++
        }

        var result = -1
        for (num in arr) {
            if (freq[num] == num) {
                result = maxOf(result, num)
            }
        }
        return result
    }
}
