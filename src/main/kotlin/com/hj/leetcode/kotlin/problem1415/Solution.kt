package com.hj.leetcode.kotlin.problem1415

/**
 * LeetCode page: [1415. The k-th Lexicographical String of All Happy Strings of Length n](https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/);
 */
class Solution {
    // Complexity:
    // Time O(n) and Space O(n), treating the size of char set as constant.
    fun getHappyString(
        n: Int,
        k: Int,
    ): String {
        if (maxK(n) < k) {
            return ""
        }
        val chars = "abc"
        val result = StringBuilder()
        var cnt = 0
        var prev = chars.length
        for (i in 1..n) {
            val p = 1 shl (n - i) // The number of valid permutations by (n-i) chars
            var q = (k - cnt - 1) / p
            cnt += p * q
            if (prev <= q) {
                q++
            }
            result.append(chars[q])
            prev = q
        }
        return result.toString()
    }

    private fun maxK(n: Int): Int {
        require(n < 31) { "`n` will overflow Int" }
        return 3 * (1 shl (n - 1))
    }
}
