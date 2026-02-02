package com.hj.leetcode.kotlin.problem3013

import java.util.*

class Solution2 {
    // Complexity:
    // Time O(NLogD) and Space O(D) where N is the length of nums and D
    // is dist.
    fun minimumCost(
        nums: IntArray,
        k: Int,
        dist: Int,
    ): Long {
        // Pqs of (i, nums[i]), sorted by different orders. They may
        // contain numbers that are out of the current window.
        val small =
            PriorityQueue<IntArray>(compareBy({ -it[1] }, { it[0] }))
        val big =
            PriorityQueue<IntArray>(compareBy({ it[1] }, { -it[0] }))

        var smallSum = 0L // The sum of valid numbers in small
        for (i in 1..dist + 1) {
            small.offer(intArrayOf(i, nums[i]))
            smallSum += nums[i]
        }
        while (small.size > k - 1) {
            val firstSmall = checkNotNull(small.poll())
            smallSum -= firstSmall[1]
            big.offer(firstSmall)
        }

        // The number of valid numbers in small
        var smallSize = k - 1
        // The largest number in the last size k-1 smallSum
        var threshold = small.first()[1]
        var minSmallSum = smallSum

        for (i in dist + 2..<nums.size) {
            val popIndex = i - dist - 1
            if (nums[popIndex] < threshold) {
                smallSum -= nums[popIndex]
                smallSize--
            } else if (small.isNotEmpty() &&
                small.first()[0] == popIndex
            ) {
                smallSum -= small.first()[1]
                smallSize--
                small.poll()
            }

            // Only a nums[i] > threshold may improve the last size k-1
            // smallSum.
            if (nums[i] >= threshold) {
                big.offer(intArrayOf(i, nums[i]))
            } else {
                smallSum += nums[i]
                smallSize++
                small.offer(intArrayOf(i, nums[i]))
                while (smallSize > k - 1) {
                    val firstSmall = checkNotNull(small.poll())
                    if (firstSmall[0] > popIndex) {
                        smallSum -= firstSmall[1]
                        smallSize--
                        big.offer(firstSmall)
                    }
                }
                while (smallSize < k - 1) {
                    val firstBig = checkNotNull(big.poll())
                    if (firstBig[0] > popIndex) {
                        smallSum += firstBig[1]
                        smallSize++
                        small.offer(firstBig)
                    }
                }

                while (small.first()[0] <= popIndex) {
                    small.poll()
                }
                threshold = small.first()[1]

                if (smallSum < minSmallSum) {
                    minSmallSum = smallSum
                }
            }
        }

        return nums[0] + minSmallSum
    }
}
