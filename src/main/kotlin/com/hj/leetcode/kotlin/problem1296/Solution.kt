package com.hj.leetcode.kotlin.problem1296

import java.util.*

/**
 * LeetCode page: [1296. Divide Array in Sets of K Consecutive Numbers](https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of nums;
     */
    fun isPossibleDivide(nums: IntArray, k: Int): Boolean {
        val countPerSortedNum = getCountPerNumSortedByNum(nums)
        return isPossibleDivide(countPerSortedNum, k)
    }

    private fun getCountPerNumSortedByNum(nums: IntArray): SortedMap<Int, Int> {
        val counts = sortedMapOf<Int, Int>()

        for (num in nums) {
            val currCount = counts[num] ?: 0
            counts[num] = currCount + 1
        }
        return counts
    }

    private fun isPossibleDivide(countPerSortedNum: SortedMap<Int, Int>, k: Int): Boolean {
        for (start in countPerSortedNum.keys) {
            val countOfStart = checkNotNull(countPerSortedNum[start])
            if (countOfStart == 0) continue

            val end = start + k - 1

            for (num in start..end) {
                val countOfNum = countPerSortedNum[num] ?: 0
                if (countOfNum < countOfStart) return false

                countPerSortedNum[num] = countOfNum - countOfStart
            }
        }
        return true
    }
}