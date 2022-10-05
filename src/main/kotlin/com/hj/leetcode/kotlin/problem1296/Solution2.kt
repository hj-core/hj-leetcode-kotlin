package com.hj.leetcode.kotlin.problem1296

import java.util.ArrayDeque

/**
 * LeetCode page: [1296. Divide Array in Sets of K Consecutive Numbers](https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun isPossibleDivide(nums: IntArray, k: Int): Boolean {
        val countPerNum = getCountPerNum(nums)
        val starts = findStartsOfConsecutiveNumbers(countPerNum)
        return isPossibleDivide(countPerNum, starts, k)
    }

    private fun getCountPerNum(nums: IntArray): MutableMap<Int, Int> {
        val counts = hashMapOf<Int, Int>()

        for (num in nums) {
            val currCount = counts[num] ?: 0
            counts[num] = currCount + 1
        }
        return counts
    }

    private fun findStartsOfConsecutiveNumbers(countPerNum: Map<Int, Int>): ArrayDeque<Int> {
        val starts = ArrayDeque<Int>()

        for (num in countPerNum.keys) {
            val precedingNum = num - 1
            val isStart = !countPerNum.containsKey(precedingNum)
            if (isStart) starts.add(num)
        }
        return starts
    }

    private fun isPossibleDivide(
        countPerNum: MutableMap<Int, Int>,
        startsOfConsecutiveNumbers: ArrayDeque<Int>,
        k: Int
    ): Boolean {
        val starts = startsOfConsecutiveNumbers

        while (starts.isNotEmpty()) {
            val start = starts.remove()
            val end = start + k - 1
            val countOfStart = checkNotNull(countPerNum[start])

            for (num in start..end) {
                val countOfNum = countPerNum[num] ?: 0
                val newCount = countOfNum - countOfStart

                when {
                    newCount < 0 -> return false
                    newCount == 0 -> countPerNum.remove(num)
                    else -> {
                        countPerNum[num] = newCount
                        val isNewStart = !countPerNum.containsKey(num - 1)
                        if (isNewStart) starts.add(num)
                    }
                }
            }

            val possibleNewStart = end + 1
            val isNewStart = countPerNum[end] == null && countPerNum[possibleNewStart] != null
            if (isNewStart) starts.add(possibleNewStart)
        }
        return true
    }
}