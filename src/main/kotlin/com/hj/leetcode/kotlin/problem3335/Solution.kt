package com.hj.leetcode.kotlin.problem3335

/**
 * LeetCode page: [3335. Total Characters in String After Transformations I](https://leetcode.com/problems/total-characters-in-string-after-transformations-i/);
 */
class Solution {
    // Complexity:
    // Time O(t+len(s)) and Space O(1).
    fun lengthAfterTransformations(
        s: String,
        t: Int,
    ): Int {
        val module = 1_000_000_007

        val freq = IntArray(26)
        for (c in s) {
            freq[c - 'a']++
        }

        var head = 0 // head points to the index that corresponds to a
        repeat(t) {
            val newHead = if (head == 0) 25 else head - 1
            freq[head] = (freq[head] + freq[newHead]) % module
            head = newHead
        }

        return freq.fold(0) { acc, f -> (acc + f) % module }
    }
}
