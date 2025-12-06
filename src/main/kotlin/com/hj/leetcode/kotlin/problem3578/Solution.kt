package com.hj.leetcode.kotlin.problem3578

/**
 * LeetCode page: [3578. Count Partitions With Max-Min Difference at Most K](https://leetcode.com/problems/count-partitions-with-max-min-difference-at-most-k/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun countPartitions(
        nums: IntArray,
        k: Int,
    ): Int {
        // To understand the implementation, first develop an O(N^2)
        // DP solution based on all valid first partitions of each
        // suffix array of nums. This implementation then improves
        // it using two monotonic deques.
        val modulo = 1_000_000_007
        val n = nums.size

        // dp[i]:= countPartitions(nums[i..], k)
        // accDp[i]:= sum(dp[i..])
        val accDp = IntArray(n + 2)
        accDp[n] = 1
        accDp[n + 1] = 0 // Require accDp[n]-accDp[n+1] = 1

        // monoMin, monoMax are indices of nums
        val monoMin = ArrayDeque<Int>()
        val monoMax = ArrayDeque<Int>()

        // nums[i..<firstEnd] is the longest first partition for
        // nums[i..].
        var firstEnd = n

        for (i in n - 1 downTo 0) {
            while (monoMin.isNotEmpty() &&
                nums[i] <= nums[monoMin.last()]
            ) {
                monoMin.removeLast()
            }
            monoMin.addLast(i)

            while (monoMax.isNotEmpty() &&
                nums[monoMax.last()] <= nums[i]
            ) {
                monoMax.removeLast()
            }
            monoMax.addLast(i)

            while (nums[monoMax.first()] - nums[monoMin.first()] >
                k
            ) {
                firstEnd =
                    if (monoMax.first() < monoMin.first()) {
                        monoMin.removeFirst()
                    } else {
                        monoMax.removeFirst()
                    }
            }

            // dp[i] = accDp[i+1] - accDp[firstEnd+1]
            accDp[i] =
                (accDp[i + 1] * 2 - accDp[firstEnd + 1]).mod(modulo)
        }
        return (accDp[0] - accDp[1]).mod(modulo)
    }
}
