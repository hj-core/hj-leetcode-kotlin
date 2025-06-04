package com.hj.leetcode.kotlin.problem3403

/**
 * LeetCode page: [3403. Find the Lexicographically Largest String From the Box I](https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i/);
 */
class Solution {
    // Complexity:
    // Time O(NM) and Space O(M) where N is the length of word and M is the difference
    // between N and numFriends.
    //
    // TODO: There is an O(n) solution.
    fun answerString(
        word: String,
        numFriends: Int,
    ): String {
        if (numFriends == 1) {
            return word
        }
        return word.windowedSequence(word.length - numFriends + 1, 1, true).max()
    }
}
