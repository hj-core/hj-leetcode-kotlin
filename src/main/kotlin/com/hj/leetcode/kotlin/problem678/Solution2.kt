package com.hj.leetcode.kotlin.problem678

class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     *
     * TODO("How to prove the correctness?
     *  One plausible way is that:
     *  1. First, if the left "(" in "(...*....*...*.."
     *  require a "*" to close, then all three stars can do the job
     *  because all of they can increase needOpen by one.
     *  2. Greedily use the available left most "*" whenever a "(" is needed
     *  and use the available right most "*" whenever a ")" is needed.
     *  3. If the "*" that change to "(" and "*" that change to ")" don't overlap,
     *  then passing the checking in both directions implies valid string.
     *  4. Prove by contradiction that the above mentioned change cannot overlay.
     *  )
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