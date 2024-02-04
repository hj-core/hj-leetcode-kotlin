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
        var missingLength = t.length
        var minWindow = 0..s.length
        var latestStart = 0

        for (end in s.indices) {
            if (s[end] !in missingCount) {
                continue
            }

            missingCount[s[end]] = checkNotNull(missingCount[s[end]]) - 1
            if (checkNotNull(missingCount[s[end]]) >= 0) {
                missingLength -= 1
            }

            while (latestStart <= end && (missingCount[s[latestStart]]?.let { it < 0 } != false)) {
                if (s[latestStart] in missingCount) {
                    missingCount[s[latestStart]] = checkNotNull(missingCount[s[latestStart]]) + 1
                }
                latestStart += 1
            }
            if (missingLength == 0 && end - latestStart < minWindow.last - minWindow.first) {
                minWindow = latestStart..end
            }
        }

        return if (missingLength > 0) "" else s.slice(minWindow)
    }
}