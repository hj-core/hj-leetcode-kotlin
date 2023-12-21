package com.hj.leetcode.kotlin.problem1071

/**
 * LeetCode page: [1071. Greatest Common Divisor of Strings](https://leetcode.com/problems/greatest-common-divisor-of-strings/);
 */
class Solution {
    /* Complexity:
     * Time O(|str1|+|str2|) and Space O(min(|str1|, |str2|));
     */
    fun gcdOfStrings(str1: String, str2: String): String {
        val potentialGcd = getPotentialGcd(str1, str2)
        val isTrueGcd = str1.isRepeating(potentialGcd) && str2.isRepeating(potentialGcd)
        return if (isTrueGcd) potentialGcd else ""
    }

    private fun getPotentialGcd(str1: String, str2: String): String {
        /* If gcd exists, one can prove that the length of gcd is the gcd of str1.length
         * and str2.length.
         */
        val length = gcd(str1.length, str2.length)
        return str1.slice(0 until length)
    }

    private tailrec fun gcd(a: Int, b: Int): Int {
        require(a >= 0 && b >= 0)
        val (large, small) = if (a >= b) a to b else b to a
        return if (small == 0) large else gcd(small, large % small)
    }

    private fun String.isRepeating(baseStr: String): Boolean {
        if (this.length % baseStr.length != 0) return false
        if (this.length == baseStr.length) return this == baseStr

        for (i in baseStr.length until this.length step baseStr.length) {
            for (j in baseStr.indices) {
                if (this[i + j] != this[i + j - baseStr.length]) {
                    return false
                }
            }
        }
        return true
    }
}