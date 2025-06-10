package com.hj.leetcode.kotlin.problem3442

/**
 * LeetCode page: [3442. Maximum Difference Between Even and Odd Frequency I](https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun maxDifference(s: String): Int {
        val freq = IntArray(26)
        for (c in s) {
            freq[c - 'a']++
        }

        var maxOdd = -1
        var minEven = s.length + 1
        for (f in freq) {
            if (f and 1 == 1) {
                maxOdd = maxOf(maxOdd, f)
            } else if (f != 0) {
                minEven = minOf(minEven, f)
            }
        }

        check(maxOdd != -1) { "odd freq not found" }
        check(minEven != s.length + 1) { "even freq not found" }
        return maxOdd - minEven
    }
}
