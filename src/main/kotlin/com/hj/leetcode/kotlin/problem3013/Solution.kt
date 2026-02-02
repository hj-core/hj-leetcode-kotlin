package com.hj.leetcode.kotlin.problem3013

import java.util.*

/**
 * LeetCode page: [3013. Divide an Array Into Subarrays With Minimum Cost II](https://leetcode.com/problems/divide-an-array-into-subarrays-with-minimum-cost-ii/);
 */
class Solution {
    // Complexity:
    // Time O(NLogD) and Space O(D) where N is the length of nums and D
    // is dist.
    fun minimumCost(
        nums: IntArray,
        k: Int,
        dist: Int,
    ): Long {
        // We slide a window of size dist, tracking the sum of the k-1
        // smallest numbers within it. We use a TreeSet to store the k-1
        // smallest indexed values in the window, while using a
        // PriorityQueue for the rest.
        val small = TreeSet<IntArray>(compareBy({ -it[1] }, { it[0] }))
        val big =
            PriorityQueue<IntArray>(compareBy({ it[1] }, { it[0] }))

        var smallSum = 0L
        for (i in 1..dist + 1) {
            small.add(intArrayOf(i, nums[i]))
            smallSum += nums[i]
        }
        while (small.size >= k) {
            val popped = checkNotNull(small.pollFirst())
            smallSum -= popped[1]
            big.offer(popped)
        }

        var minSmallSum = smallSum
        for (i in dist + 2..<nums.size) {
            val popIndex = i - dist - 1
            if (nums[popIndex] <= small.first()[1] &&
                small.remove(intArrayOf(popIndex, nums[popIndex]))
            ) {
                smallSum -= nums[popIndex]
            }

            big.offer(intArrayOf(i, nums[i]))
            while (big.isNotEmpty() && big.first()[0] <= popIndex) {
                big.poll()
            }

            if (small.size < k - 1) {
                val added = checkNotNull(big.poll())
                smallSum += added[1]
                small.add(added)
            } else if (small.first()[1] > big.first()[1]) {
                smallSum += big.first()[1] - small.first()[1]
                val firstSmall = checkNotNull(small.pollFirst())
                val firstBig = big.poll()
                small.add(firstBig)
                big.add(firstSmall)
            }

            minSmallSum = minOf(minSmallSum, smallSum)
        }

        return nums[0] + minSmallSum
    }
}
