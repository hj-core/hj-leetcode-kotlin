package com.hj.leetcode.kotlin.problem1043

import kotlin.math.max
import kotlin.math.min

/**
 * LeetCode page: [1043. Partition Array for Maximum Sum](https://leetcode.com/problems/partition-array-for-maximum-sum/);
 */
class Solution {
    /* Complexity:
     * Time O(kN) and Space O(N) where N is the size of arr;
     */
    fun maxSumAfterPartitioning(arr: IntArray, k: Int): Int {
        // dp[i] ::= max sum for arr[i:]
        val dp = IntArray(arr.size + 1)

        for (i in arr.indices.reversed()) {
            var subResult = Int.MIN_VALUE
            var firstMax = arr[i] // max value of the first subarray;
            for (firstEnd in i..<min(i + k, arr.size)) {
                firstMax = max(firstMax, arr[firstEnd])
                subResult = max(subResult, firstMax * (firstEnd - i + 1) + dp[firstEnd + 1])
            }
            dp[i] = subResult
        }
        return dp[0]
    }
}