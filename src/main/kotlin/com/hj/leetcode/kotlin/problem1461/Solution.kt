package com.hj.leetcode.kotlin.problem1461

/**
 * LeetCode page: [1461. Check If a String Contains All Binary Codes of Size K](https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of s.
    fun hasAllCodes(
        s: String,
        k: Int,
    ): Boolean {
        if (s.length - k + 1 < 1 shl k) {
            return false
        }

        val seen = hashSetOf<Int>()
        var value =
            (0..<k).fold(0) { acc, i -> (acc shl 1) or (s[i] - '0') }
        seen.add(value)

        val mask = (1 shl k) - 1
        for (i in k..<s.length) {
            value = (value shl 1 and mask) or (s[i] - '0')
            seen.add(value)
        }

        return seen.size == 1 shl k
    }
}
