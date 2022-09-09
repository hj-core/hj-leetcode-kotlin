package com.hj.leetcode.kotlin.problem2287

/**
 * LeetCode page: [2287. Rearrange Characters to Make Target String](https://leetcode.com/problems/rearrange-characters-to-make-target-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(1) where N and M are the length of s and target;
     */
    fun rearrangeCharacters(s: String, target: String): Int {
        val sCharFreq = getCharFrequency(s)
        val targetCharFreq = getCharFrequency(target)

        var maxCopies = s.length
        for (index in targetCharFreq.indices) {
            val currTargetCharFreq = targetCharFreq[index]
            if (currTargetCharFreq != 0) {
                val currSCharFreq = sCharFreq[index]
                if (currSCharFreq < currTargetCharFreq) {
                    maxCopies = 0
                    break
                } else {
                    maxCopies = minOf(maxCopies, currSCharFreq / currTargetCharFreq)
                }
            }
        }

        return maxCopies
    }

    private fun getCharFrequency(lowercaseString: String): IntArray {
        val charFrequency = IntArray(26)
        for (char in lowercaseString) {
            charFrequency[char - 'a']++
        }
        return charFrequency
    }
}