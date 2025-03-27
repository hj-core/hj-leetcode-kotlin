package com.hj.leetcode.kotlin.problem2780

/**
 * LeetCode page: [2780. Minimum Index of a Valid Split](https://leetcode.com/problems/minimum-index-of-a-valid-split/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun minimumIndex(nums: List<Int>): Int {
        val majority = findMajority(nums)
        var majorityCnt = 0

        for (leftEnd in nums.indices) {
            if (nums[leftEnd] != majority) {
                continue
            }

            majorityCnt++
            val leftSize = leftEnd + 1

            if (majorityCnt > leftSize - majorityCnt) {
                majorityCnt += (leftSize..<nums.size).count { nums[it] == majority }
                val cannotSplit = nums.size - majorityCnt == majorityCnt - 1

                return if (cannotSplit) -1 else leftEnd
            }
        }
        throw IllegalStateException()
    }

    // `findMajority` returns the majority number. Client must ensure a majority exists.
    private fun findMajority(nums: List<Int>): Int {
        var result = 0
        var count = 0

        for (num in nums) {
            when {
                count == 0 -> {
                    result = num
                    count = 1
                }

                num == result -> count++
                else -> count--
            }
        }
        check(count > 0)
        return result
    }
}
