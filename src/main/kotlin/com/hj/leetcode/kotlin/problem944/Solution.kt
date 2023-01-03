package com.hj.leetcode.kotlin.problem944

/**
 * LeetCode page: [944. Delete Columns to Make Sorted](https://leetcode.com/problems/delete-columns-to-make-sorted/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the flat length of strs;
     */
    fun minDeletionSize(strs: Array<String>): Int {
        val wordLength = strs[0].length
        var minDeletion = 0
        for (charIndex in 0 until wordLength) {
            for (wordIndex in 1 until strs.size) {
                if (strs[wordIndex][charIndex] < strs[wordIndex - 1][charIndex]) {
                    minDeletion++
                    break
                }
            }
        }
        return minDeletion
    }
}