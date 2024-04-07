package com.hj.leetcode.kotlin.problem678

class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     *
     * TODO("How to prove the correctness?)
     */
    fun checkValidString(s: String): Boolean {
        return forwardChecking(s) && backwardChecking(s)
    }

    private fun forwardChecking(s: String): Boolean {
        var needClose = 0
        var asterisks = 0
        for (char in s) {
            when (char) {
                '(' -> needClose++
                '*' -> asterisks++
                ')' -> when {
                    needClose > 0 -> needClose--
                    asterisks > 0 -> asterisks--
                    else -> return false
                }
            }
        }
        return true
    }

    private fun backwardChecking(s: String): Boolean {
        var needOpen = 0
        var asterisks = 0
        for (i in s.indices.reversed()) {
            when (s[i]) {
                ')' -> needOpen++
                '*' -> asterisks++
                '(' -> when {
                    needOpen > 0 -> needOpen--
                    asterisks > 0 -> asterisks--
                    else -> return false
                }
            }
        }
        return true
    }
}