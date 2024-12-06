package com.hj.leetcode.kotlin.problem2554

/**
 * LeetCode page: [2554. Maximum Number of Integers to Choose From a Range I](https://leetcode.com/problems/maximum-number-of-integers-to-choose-from-a-range-i/);
 */
class Solution {
    /* Complexity:
     * Time O(n+M) and Space O(min(n, M)) where M is the size of banned.
     */
    fun maxCount(
        banned: IntArray,
        n: Int,
        maxSum: Int,
    ): Int {
        val blackList = if (banned.size * 2 < n) SetBlackList(banned) else ArrayBlackList(n, banned)
        var result = 0
        var sum = 0
        for (num in 1..n) {
            if (num in blackList) {
                continue
            }
            if (maxSum < sum + num) {
                break
            }
            result++
            sum += num
        }
        return result
    }

    private interface BlackList {
        operator fun contains(num: Int): Boolean
    }

    private class SetBlackList(
        banned: IntArray,
    ) : BlackList {
        private val banned = banned.toSet()

        override fun contains(num: Int): Boolean = num in banned
    }

    private class ArrayBlackList(
        n: Int,
        banned: IntArray,
    ) : BlackList {
        private val isBanned = BooleanArray(n + 1)

        init {
            isBanned[0] = true
            for (num in banned) {
                if (num in isBanned.indices) {
                    isBanned[num] = true
                }
            }
        }

        override fun contains(num: Int): Boolean = isBanned[num]
    }
}
