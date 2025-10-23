package com.hj.leetcode.kotlin.problem3461

/**
 * LeetCode page: [3461. Check If Digits Are Equal in String After Operations I](https://leetcode.com/problems/check-if-digits-are-equal-in-string-after-operations-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun hasSameDigits(s: String): Boolean {
        var digitDiff = 0
        for ((i, f) in combMod10Iter(s.length - 2).withIndex()) {
            digitDiff = (digitDiff + f * (s[i] - s[i + 1])) % 10
        }
        return digitDiff == 0
    }

    // Returns an iterator of Comb(n,i)%10 for i in 0..=n.
    private fun combMod10Iter(n: Int): Iterator<Int> =
        object : Iterator<Int> {
            private var i = -1
            private val combMod5Iter = combMod5Iter(n)

            override fun next(): Int {
                i++
                val combMod2 = calcCombMod2(n, i)
                val combMod5 = combMod5Iter.next()
                return calcXMod10(combMod2, combMod5)
            }

            override fun hasNext(): Boolean = i < n
        }

    // Returns an iterator of Comb(n,i)%5 for i in 0..=n.
    private fun combMod5Iter(n: Int): Iterator<Int> =
        object : Iterator<Int> {
            private var i = -1

            // combMod5 := a * invMod5(b) * pow(5,exp5) % 5
            private var a = 1
            private var b = 1
            private var exp5 = 0

            override fun next(): Int {
                i++
                if (i == 0) {
                    return 1
                }

                a *= n - i + 1
                while (a % 5 == 0) {
                    a /= 5
                    exp5++
                }
                a %= 5

                b *= i
                while (b % 5 == 0) {
                    b /= 5
                    exp5--
                }
                b %= 5

                // Fermat's Little Theorem for invMod5(b)
                return if (exp5 > 0) 0 else a * b * b * b % 5
            }

            override fun hasNext(): Boolean = i < n
        }

    // Computes Comb(n,i)%2.
    private fun calcCombMod2(
        n: Int,
        i: Int,
    ): Int = if (i and n == i) 1 else 0 // Lucas's Theorem

    // Computes x%10 based on x%2 and x%5.
    private fun calcXMod10(
        xMod2: Int,
        xMod5: Int,
    ): Int = (xMod2 * 5 + xMod5 * 6) % 10 // Chinese Remainder Theorem
}
