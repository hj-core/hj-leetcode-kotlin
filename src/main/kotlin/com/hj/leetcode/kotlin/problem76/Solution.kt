package com.hj.leetcode.kotlin.problem76

/**
 * LeetCode page: [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/);
 */
class Solution {
    /* Complexity:
     * Time O(M+N) and Space O(N) where M and N are the length of s and t respectively;
     */
    fun minWindow(s: String, t: String): String {
        val missingCount = t.groupingBy { it }.eachCountTo(hashMapOf())
        val missingChars = missingCount.keys.toHashSet()
        var minWindow = 0..s.length
        var latestStart = 0

        for (end in s.indices) {
            if (s[end] !in missingCount) {
                continue
            }

            missingCount[s[end]] = checkNotNull(missingCount[s[end]]) - 1
            if (missingCount[s[end]] == 0) {
                missingChars.remove(s[end])
            }

            while (latestStart <= end && (missingCount[s[latestStart]]?.let { it < 0 } != false)) {
                if (s[latestStart] in missingCount) {
                    missingCount[s[latestStart]] = checkNotNull(missingCount[s[latestStart]]) + 1
                }
                latestStart += 1
            }
            if (missingChars.isEmpty() && end - latestStart < minWindow.last - minWindow.first) {
                minWindow = latestStart..end
            }
        }

        val windowNotFound = minWindow.last == s.length
        return if (windowNotFound) "" else s.slice(minWindow)
    }
}