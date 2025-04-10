package com.hj.leetcode.kotlin.problem2999

/**
 * LeetCode page: [2999. Count the Number of Powerful Integers](https://leetcode.com/problems/count-the-number-of-powerful-integers/);
 */
class Solution {
    // Complexity:
    // Time O(Log(finish)^2) and Space O(Log(finish)).
    //
    // Note:
    // You may reduce the time complexity to O(Log(finish)) by precomputing the transposeHelper.
    fun numberOfPowerfulInt(
        start: Long,
        finish: Long,
        limit: Int,
        s: String,
    ): Long {
        require(s.length <= 16) {
            "Problem constraints have changed, which may cause Long overflow"
        }

        val suffix = s.toLong()
        if (finish < suffix) {
            return 0
        }

        val suffixMsdUnit = msdUnit(suffix)
        val nextUnit = suffixMsdUnit * 10

        val newStart = (start / nextUnit) + if (suffix < start % nextUnit) 1 else 0
        val newFinish = (finish / nextUnit) - if (finish % nextUnit < suffix) 1 else 0

        return numberOfPowerfulIntHelper(newStart, newFinish, limit.toLong(), msdUnit(newFinish), mutableMapOf())
    }

    // msdUnit returns the unit of x's most significant digit.
    private fun msdUnit(x: Long): Long {
        var result = 1L
        var y = x
        while (y > 9) {
            y /= 10
            result *= 10
        }
        return result
    }

    // numberOfPowerfulIntHelper computes the number of longs in [start,finish] that have all digits
    // not greater than limit.
    private fun numberOfPowerfulIntHelper(
        start: Long,
        finish: Long,
        limit: Long,
        msdUnit: Long, // msdUnit of finish
        memoization: MutableMap<Pair<Long, Long>, Long>, // entry=(Pair(start,finish),result)
    ): Long {
        if (start > finish) {
            return 0
        }

        if (msdUnit == 1L) {
            return if (limit < start) 0 else minOf(limit, finish) - start + 1
        }

        val key = Pair(start, finish)
        if (key in memoization) {
            return memoization[key]!!
        }

        val startMsd = start / msdUnit
        val finishMsd = finish / msdUnit

        if (limit < startMsd) {
            return 0
        }

        val result =
            when {
                startMsd <= limit && startMsd == finishMsd -> {
                    numberOfPowerfulIntHelper(start % msdUnit, finish % msdUnit, limit, msdUnit / 10, memoization)
                }

                startMsd <= limit && limit < finishMsd -> {
                    numberOfPowerfulIntHelper(start % msdUnit, msdUnit - 1, limit, msdUnit, memoization) +
                        (limit - startMsd) * transposeHelper(limit + 1, msdUnit)
                }

                startMsd < finishMsd && finishMsd <= limit -> {
                    numberOfPowerfulIntHelper(start % msdUnit, msdUnit - 1, limit, msdUnit, memoization) +
                        (finishMsd - startMsd - 1) * transposeHelper(limit + 1, msdUnit) +
                        numberOfPowerfulIntHelper(0, finish % msdUnit, limit, msdUnit / 10, memoization)
                }

                else -> throw IllegalStateException()
            }

        memoization[key] = result
        return result
    }

    // transposeHelper computes the base^k where msdUnit equals 10^k.
    private fun transposeHelper(
        base: Long,
        msdUnit: Long,
    ): Long = if (msdUnit == 1L) 1L else base * transposeHelper(base, msdUnit / 10)
}
