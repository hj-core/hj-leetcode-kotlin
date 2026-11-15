package com.hj.leetcode.kotlin.problem3364

import java.util.TreeMap

/**
 * LeetCode page: [3364. Minimum Positive Sum Subarray ](https://leetcode.com/problems/minimum-positive-sum-subarray/);
 */
class Solution {
    // Complexity:
    // Time O(NLogM) and Space O(M) where N is the length of nums and M is r - l.
    fun minimumSumSubarray(
        nums: List<Int>,
        l: Int,
        r: Int,
    ): Int {
        // Each subarray starting index has a range of valid ending indices.
        // We compute the sums of these valid subarrays by subtracting the prefix
        // sum before the start index from the prefix sums in the valid range.
        val n = nums.size
        var prefixSumL = (0..<l).sumOf { nums[it] }
        var prefixSumR = prefixSumL
        val sumFreq = TreeMap<Int, Int>()

        join(sumFreq, prefixSumL)
        for (j in l..<r) {
            prefixSumR += nums[j]
            join(sumFreq, prefixSumR)
        }

        var subtraction = 0
        var minSum = sumFreq.higherKey(subtraction) ?: Int.MAX_VALUE
        for (i in 0..<(n - l)) {
            subtraction += nums[i]
            leave(sumFreq, prefixSumL)
            prefixSumL += nums[i + l]
            if (i + r < n) {
                prefixSumR += nums[i + r]
                join(sumFreq, prefixSumR)
            }

            sumFreq.higherKey(subtraction)?.let {
                minSum = minOf(minSum, it - subtraction)
            }
        }

        return if (minSum == Int.MAX_VALUE) -1 else minSum
    }

    private fun join(
        freq: TreeMap<Int, Int>,
        key: Int,
    ) {
        freq.compute(key) { _, v -> (v ?: 0) + 1 }
    }

    private fun leave(
        freq: TreeMap<Int, Int>,
        key: Int,
    ) {
        freq.compute(key) { _, v ->
            checkNotNull(v)
            if (v == 1) null else v - 1
        }
    }
}
