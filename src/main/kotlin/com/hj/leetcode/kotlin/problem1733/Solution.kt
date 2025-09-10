package com.hj.leetcode.kotlin.problem1733

/**
 * LeetCode page: [1733. Minimum Number of People to Teach](https://leetcode.com/problems/minimum-number-of-people-to-teach/);
 */
class Solution {
    // Complexity:
    // Time O(mn+nk) and Space O(mn) where m is the length of
    // languages and k is the length of friendships.
    fun minimumTeachings(
        n: Int,
        languages: Array<IntArray>,
        friendships: Array<IntArray>,
    ): Int {
        // Find out the people who need help to communicate
        // with their friends.
        val m = languages.size
        val langBits = Array(m + 1) { LongArray(0) }
        val needHelp = BooleanArray(m + 1)
        for ((p1, p2) in friendships) {
            if (langBits[p1].isEmpty()) {
                langBits[p1] = calcLangBits(n, languages[p1 - 1])
            }
            if (langBits[p2].isEmpty()) {
                langBits[p2] = calcLangBits(n, languages[p2 - 1])
            }

            if (!canCommunicate(langBits[p1], langBits[p2])) {
                needHelp[p1] = true
                needHelp[p2] = true
            }
        }

        // Teach the language that is most widely known among
        // the people who need help.
        var cntNeedHelp = 0
        val cntKnown = IntArray(n + 1)
        var cntMostKnown = 0
        for (p in 1..m) {
            if (needHelp[p]) {
                cntNeedHelp++
                for (lang in languages[p - 1]) {
                    cntKnown[lang]++
                    if (cntKnown[lang] > cntMostKnown) {
                        cntMostKnown = cntKnown[lang]
                    }
                }
            }
        }
        return cntNeedHelp - cntMostKnown
    }

    private fun calcLangBits(
        n: Int,
        languages: IntArray,
    ): LongArray {
        val result = LongArray((n shr 6) + 1)
        for (lang in languages) {
            result[lang shr 6] = result[lang shr 6] or (1L shl (lang and 63))
        }
        return result
    }

    private fun canCommunicate(
        langBits1: LongArray,
        langBits2: LongArray,
    ): Boolean {
        for (i in langBits1.indices) {
            if (langBits1[i] and langBits2[i] != 0L) {
                return true
            }
        }
        return false
    }
}
