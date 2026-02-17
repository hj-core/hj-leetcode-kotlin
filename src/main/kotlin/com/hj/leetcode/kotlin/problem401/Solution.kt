package com.hj.leetcode.kotlin.problem401

/**
 * LeetCode page: [401. Binary Watch](https://leetcode.com/problems/binary-watch/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1)
    fun readBinaryWatch(turnedOn: Int): List<String> {
        val mms =
            Array(minOf(6, turnedOn + 1)) {
                mutableListOf<Int>()
            }
        for (mm in 0..<60) {
            val mmBitCnt = mm.countOneBits()
            if (mmBitCnt <= turnedOn) {
                mms[mmBitCnt].add(mm)
            }
        }

        val result = mutableListOf<String>()
        for (hh in 0..<12) {
            val hhBitCnt = hh.countOneBits()
            val mmBitCnt = turnedOn - hhBitCnt
            if (mmBitCnt !in mms.indices) {
                continue
            }

            for (mm in mms[mmBitCnt]) {
                result.add(String.format("%d:%02d", hh, mm))
            }
        }

        return result
    }
}
