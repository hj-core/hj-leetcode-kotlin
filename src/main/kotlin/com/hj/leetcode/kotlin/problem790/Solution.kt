package com.hj.leetcode.kotlin.problem790

/**
 * LeetCode page: [790. Domino and Tromino Tiling](https://leetcode.com/problems/domino-and-tromino-tiling/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(1);
     */
    fun numTilings(n: Int): Int {
        val module = 1_000_000_007 // from the problem requirements;

        /* A valid tiling's start tile must be one of the following:
         *   1. a vertical domino;
         *   2. a stack of two horizontal dominoes;
         *   3. a tromino like 'L';
         *   4. a tromino like upside down 'L';
         * We can apply Dynamic Programming to solve the problem, the sub problems are the number of
         * tilings in each case for a segment start from index i. The original problem is the sum of
         * 4 cases with i equals 0.
         */
        var case1 = 1L // initial value with i equals n - 1;
        var prevCase1 = 0L // initial value with i equals n;
        var case2 = 0L // initial value with i equals n - 1;
        var case3 = 0L // initial value with i equals n - 1;
        var case4 = 0L // initial value with i equals n - 1;

        for (i in n - 2 downTo 0) {
            val newCase1 = (case1 + case2 + case3 + case4) % module
            val newCase2 = case1
            val newCase3 = (case4 + prevCase1) % module
            val newCase4 = (case3 + prevCase1) % module

            prevCase1 = case1
            case1 = newCase1
            case2 = newCase2
            case3 = newCase3
            case4 = newCase4
        }

        return ((case1 + case2 + case3 + case4) % module).toInt()
    }
}