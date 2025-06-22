package com.hj.leetcode.kotlin.problem2138

/**
 * LeetCode page: [2138. Divide a String Into Groups of Size k](https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/);
 */
class Solution {
    // Complexity:
    // Time O(N+k) and Space O(N+k) where N is the length of s.
    fun divideString(
        s: String,
        k: Int,
        fill: Char,
    ): Array<String> {
        val result =
            Array((s.length + k - 1) / k) {
                val startIndex = it * k
                val endIndex = startIndex + k
                if (endIndex > s.length) "" else s.substring(startIndex, endIndex)
            }

        if (result.last().isEmpty()) {
            val rem = s.length % k
            val last = s.substring(s.length - rem) + "$fill".repeat(k - rem)
            result[result.lastIndex] = last
        }
        return result
    }
}
