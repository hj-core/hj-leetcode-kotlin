package com.hj.leetcode.kotlin.problem401

/**
 * LeetCode page: [401. Binary Watch](https://leetcode.com/problems/binary-watch/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1)
    fun readBinaryWatch(turnedOn: Int): List<String> {
        val hrs =
            Array(minOf(4, turnedOn + 1)) {
                mutableListOf<Int>()
            }
        for (hr in 0..<12) {
            val hrBitCnt = hr.countOneBits()
            if (hrBitCnt <= turnedOn) {
                hrs[hrBitCnt].add(hr)
            }
        }

        val result = mutableListOf<String>()
        for (min in 0..<60) {
            val minBitCnt = min.countOneBits()
            val hrBitCnt = turnedOn - minBitCnt
            if (hrBitCnt !in hrs.indices) {
                continue
            }

            for (hr in hrs[hrBitCnt]) {
                result.add(String.format("%d:%02d", hr, min))
            }
        }

        return result
    }
}
