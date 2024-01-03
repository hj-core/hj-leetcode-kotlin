package com.hj.leetcode.kotlin.problem2125

/**
 * LeetCode page: [2125. Number of Laser Beams in a Bank](https://leetcode.com/problems/number-of-laser-beams-in-a-bank/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the flattened length of bank;
     */
    fun numberOfBeams(bank: Array<String>): Int {
        var result = 0
        var numSenders = 0
        for (row in bank) {
            val numReceivers = row.count { it == '1' }
            if (numReceivers != 0) {
                result += numSenders * numReceivers
                numSenders = numReceivers
            }
        }
        return result
    }
}