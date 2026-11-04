package com.hj.leetcode.kotlin.problem3505

import java.util.PriorityQueue

/**
 * LeetCode page: [3505. Minimum Operations to Make Elements Within K Subarrays Equal](https://leetcode.com/problems/minimum-operations-to-make-elements-within-k-subarrays-equal/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN + kN) and Space O(N) where N is the length of nums.
    fun minOperations(
        nums: IntArray,
        x: Int,
        k: Int,
    ): Long {
        val cost = computeSubarrayMinOps(nums, x)
        val n = nums.size
        val dp = LongArray(n + 1)
        for (cnt in 1..k) {
            for (group in (n - cnt * x) downTo 0 step x) {
                var start = group
                var tmp = n - (cnt - 1) * x
                dp[start] = (cost[start] + dp[tmp]).also { dp[tmp] = dp[start] }
                if (start + x < tmp) {
                    dp[start] = minOf(dp[start], dp[start + 1])
                }

                repeat(minOf(x - 1, group)) {
                    start--
                    tmp--
                    dp[start] = (cost[start] + dp[tmp]).also { dp[tmp] = dp[start] }
                    dp[start] = minOf(dp[start], dp[start + 1])
                }
            }
        }
        return dp[0]
    }

    private fun computeSubarrayMinOps(
        nums: IntArray,
        x: Int,
    ): LongArray {
        val left = PriorityQueue<Int>(compareBy({ -nums[it] }, { -it }))
        var leftSum = 0L

        val right = PriorityQueue<Int>(compareBy({ nums[it] }, { it }))
        var rightSum = 0L
        for (i in 0..<x) {
            right.add(i)
            rightSum += nums[i]
        }

        var sizeDiff = x // right.effectiveSize - left.effectiveSize
        while (sizeDiff > 1) {
            val i: Int = right.poll()
            rightSum -= nums[i]
            left.add(i)
            leftSum += nums[i]
            sizeDiff -= 2
        }

        val n = nums.size
        val subarrayCost = LongArray(n - x + 1)
        subarrayCost[0] = computeMinOps(leftSum, rightSum, nums[right.peek()], x)
        for (i in 1..(n - x)) {
            val m = right.peek()
            val leave = i - 1
            if (nums[m] < nums[leave] || (nums[m] == nums[leave] && m <= leave)) {
                rightSum -= nums[leave]
                sizeDiff -= 1
            } else {
                leftSum -= nums[leave]
                sizeDiff += 1
            }

            val enter = i + x - 1
            if (left.isNotEmpty() && nums[enter] < nums[left.peek()]) {
                left.add(enter)
                leftSum += nums[enter]
                sizeDiff -= 1
            } else {
                right.add(enter)
                rightSum += nums[enter]
                sizeDiff += 1
            }

            while (sizeDiff < 0) {
                val pop = left.poll()
                if (pop < i) {
                    continue
                }
                leftSum -= nums[pop]
                right.add(pop)
                rightSum += nums[pop]
                sizeDiff += 2
            }
            while (sizeDiff > 1) {
                val pop = right.poll()
                if (pop < i) {
                    continue
                }
                rightSum -= nums[pop]
                left.add(pop)
                leftSum += nums[pop]
                sizeDiff -= 2
            }
            while (right.peek() < i) {
                right.poll()
            }

            subarrayCost[i] = computeMinOps(leftSum, rightSum, nums[right.peek()], x)
        }

        return subarrayCost
    }

    private fun computeMinOps(
        leftSum: Long,
        rightSum: Long,
        median: Int,
        x: Int,
    ): Long = if (x and 1 == 0) rightSum - leftSum else rightSum - leftSum - median
}
