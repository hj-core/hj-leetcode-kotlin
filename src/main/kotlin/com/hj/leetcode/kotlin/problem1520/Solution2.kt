package com.hj.leetcode.kotlin.problem1520

class Solution2 {
    // Complexity:
    // Time O(MN) and Space O(M+N) where N is the length of s and M is the
    // size of char set (i.e., 26).
    fun maxNumOfSubstrings(s: String): List<String> {
        val firstIndex = IntArray(26) { s.length }
        val lastIndex = IntArray(26) { -1 }
        for (i in s.indices) {
            val c = s[i] - 'a'
            firstIndex[c] = minOf(firstIndex[c], i)
            lastIndex[c] = maxOf(lastIndex[c], i)
        }

        val intervals = mutableListOf<IntArray>()
        for (i in s.indices) {
            val c = s[i] - 'a'
            if (i != firstIndex[c]) {
                continue
            }

            var end = lastIndex[c]
            var j = i + 1
            var isAuthority = true // whether s[i] controls the length of the substring
            while (isAuthority && j <= end) {
                isAuthority = firstIndex[s[j] - 'a'] >= i
                end = maxOf(end, lastIndex[s[j] - 'a'])
                j++
            }

            if (!isAuthority) {
                continue
            }

            if (intervals.isNotEmpty() && i < intervals.last()[1]) {
                intervals.removeLast()
            }
            intervals.add(intArrayOf(i, end))
        }

        return intervals.map { (start, end) -> s.substring(start..end) }
    }
}
