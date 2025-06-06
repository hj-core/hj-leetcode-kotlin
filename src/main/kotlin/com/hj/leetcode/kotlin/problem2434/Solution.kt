package com.hj.leetcode.kotlin.problem2434

/**
 * LeetCode page: [2434. Using a Robot to Print the Lexicographically Smallest String](https://leetcode.com/problems/using-a-robot-to-print-the-lexicographically-smallest-string/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of s.
    fun robotWithString(s: String): String {
        val result = StringBuilder()
        var i = 0
        val suffixMin = computeSuffixMin(s)
        val stack = StringBuilder()

        while (i < s.length) {
            val minIndex = suffixMin[i]
            if (stack.isNotEmpty() && stack.last() <= s[minIndex]) {
                result.append(stack.last())
                stack.deleteAt(stack.lastIndex)
            } else {
                for (j in i..<minIndex) {
                    stack.append(s[j])
                }
                result.append(s[minIndex])
                i = minIndex + 1
            }
        }

        for (j in stack.indices.reversed()) {
            result.append(stack[j])
        }
        return result.toString()
    }

    // computeSuffixMin returns the index of the first smallest char in s[i:]
    // for each index i.
    private fun computeSuffixMin(s: String): IntArray {
        val result = IntArray(s.length)

        result[s.lastIndex] = s.lastIndex
        for (i in s.lastIndex - 1 downTo 0) {
            if (s[i] <= s[result[i + 1]]) {
                result[i] = i
            } else {
                result[i] = result[i + 1]
            }
        }
        return result
    }
}
