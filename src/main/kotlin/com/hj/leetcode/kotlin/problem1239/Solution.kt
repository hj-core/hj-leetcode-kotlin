package com.hj.leetcode.kotlin.problem1239

/**
 * LeetCode page: [1239. Maximum Length of a Concatenated String with Unique Characters](https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/);
 */
class Solution {
    /* Complexity:
     * Time O(2^N) and Space O(N) where N is the size of arr;
     */
    fun maxLength(arr: List<String>): Int {
        return findMaxLength(arr)
    }

    private fun findMaxLength(
        lowercaseStrings: List<String>,
        index: Int = 0,
        existencePerLowercase: BooleanArray = BooleanArray(26)
    ): Int {
        if (index > lowercaseStrings.lastIndex) return getLength(existencePerLowercase)

        val currStr = lowercaseStrings[index]
        val existencesAfterAppend = plusButReturnNullIfNotUnique(existencePerLowercase, currStr)

        return existencesAfterAppend
            ?.let {
                maxOf(
                    findMaxLength(lowercaseStrings, index + 1, it),
                    findMaxLength(lowercaseStrings, index + 1, existencePerLowercase)
                )
            }
            ?: findMaxLength(lowercaseStrings, index + 1, existencePerLowercase)
    }

    private fun getLength(existencePerLowercase: BooleanArray) = existencePerLowercase.count { it }

    private fun plusButReturnNullIfNotUnique(existencePerLowercase: BooleanArray, newString: String): BooleanArray? {
        val existences = existencePerLowercase.clone()

        for (char in newString) {
            val index = char - 'a'
            if (existences[index]) return null
            existences[index] = true
        }

        return existences
    }
}