package com.hj.leetcode.kotlin.problem2125

/**
 * LeetCode page: [2125. Number of Laser Beams in a Bank](https://leetcode.com/problems/number-of-laser-beams-in-a-bank/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(1) where M and N are the number of
    // rows and columns in bank, respectively.
    fun numberOfBeams(bank: Array<String>): Int =
        bank
            .asSequence()
            .map { it.count { c -> c == '1' } }
            .filter { it > 0 }
            .zipWithNext()
            .sumOf { (sendCnt, recvCnt) -> sendCnt * recvCnt }
}
