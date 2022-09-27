package com.hj.leetcode.kotlin.problem848

/**
 * LeetCode page: [848. Shifting Letters](https://leetcode.com/problems/shifting-letters/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of s and the size of shifts;
     */
    fun shiftingLetters(s: String, shifts: IntArray): String {
        val netShifts = getFinalNetShifts(shifts)
        val ans = StringBuilder(s.length)

        for (index in s.indices) {
            val shiftedLetter = getShifted(s[index], netShifts[index])
            ans.append(shiftedLetter)
        }
        return ans.toString()
    }

    private fun getFinalNetShifts(shifts: IntArray): IntArray {
        val netShifts = shifts.clone()
        netShifts[netShifts.lastIndex] %= 26

        for (index in shifts.lastIndex - 1 downTo 0) {
            netShifts[index] = (netShifts[index] + netShifts[index + 1]) % 26
        }
        return netShifts
    }

    private fun getShifted(lowercase: Char, netShift: Int): Char {
        require(lowercase.isLowerCase() && netShift < 26)

        val shiftFromA = (lowercase - 'a' + netShift).let {
            if (it >= 26) it - 26 else it
        }
        return 'a' + shiftFromA
    }
}