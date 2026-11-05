package com.hj.leetcode.kotlin.problem1385

/**
 * LeetCode page: [1385. Find the Distance Value Between Two Arrays](https://leetcode.com/problems/find-the-distance-value-between-two-arrays/);
 */
class Solution {
    // Complexity:
    // Time O(N1+N2+M) and Space O(M) where N1 is the length of arr1, N2 is the length
    // of arr2 and M is the range of allowed values.
    fun findTheDistanceValue(
        arr1: IntArray,
        arr2: IntArray,
        d: Int,
    ): Int {
        val minValue = -1000
        val maxValue = 1000

        val suffixFreq = IntArray(maxValue - minValue + 2)
        for (num in arr2) {
            suffixFreq[num - minValue]++
        }
        for (i in maxValue - minValue downTo 0) {
            suffixFreq[i] += suffixFreq[i + 1]
        }

        var count = 0
        for (num in arr1) {
            val start = maxOf(num - d, minValue) - minValue
            val end = minOf(num + d, maxValue) - minValue + 1
            if (suffixFreq[start] == suffixFreq[end]) {
                count++
            }
        }

        return count
    }
}
