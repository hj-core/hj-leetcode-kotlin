package com.hj.leetcode.kotlin.problem1488

import java.util.*

/**
 * LeetCode page: [1488. Avoid Flood in The City](https://leetcode.com/problems/avoid-flood-in-the-city/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the size of rains.
    fun avoidFlood(rains: IntArray): IntArray {
        // lastFullDay[lake]:= the previous day that lake became full
        val lastFullDay = hashMapOf<Int, Int>()
        val dryDays = TreeSet<Int>()
        val result = IntArray(rains.size) { 1 }

        for ((i, rain) in rains.withIndex()) {
            when (rain) {
                0 -> dryDays.add(i)

                !in lastFullDay -> {
                    result[i] = -1
                    lastFullDay[rain] = i
                }

                else -> {
                    val fullDay = lastFullDay[rain]!!
                    val dryDay = dryDays.ceiling(fullDay) ?: return intArrayOf()
                    result[dryDay] = rain
                    dryDays.remove(dryDay)

                    result[i] = -1
                    lastFullDay[rain] = i
                }
            }
        }
        return result
    }
}
