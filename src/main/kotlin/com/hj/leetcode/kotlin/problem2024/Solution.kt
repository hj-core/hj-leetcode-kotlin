package com.hj.leetcode.kotlin.problem2024

/**
 * LeetCode page: [2024. Maximize the Confusion of an Exam](https://leetcode.com/problems/maximize-the-confusion-of-an-exam/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of answerKey;
     */
    fun maxConsecutiveAnswers(answerKey: String, k: Int): Int {

        /* For each index, find the earliest starting index of the sub-array ending at that index
         * that can form a consecutive answer after performing the operation at most k times.
         * The result will be the length of the longest sub-array.
         */
        var result = 0
        var start = 0
        var numAnswerT = 0
        var numAnswerF = 0

        for ((end, answer) in answerKey.withIndex()) {
            when (answer) {
                'T' -> numAnswerT++
                'F' -> numAnswerF++
            }
            while (numAnswerF > k && numAnswerT > k) {
                when (answerKey[start]) {
                    'T' -> numAnswerT--
                    'F' -> numAnswerF--
                }
                start++
            }
            result = maxOf(result, end - start + 1)
        }
        return result
    }
}