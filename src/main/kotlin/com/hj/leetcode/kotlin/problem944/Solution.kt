package com.hj.leetcode.kotlin.problem944

/**
 * LeetCode page: [944. Delete Columns to Make Sorted](https://leetcode.com/problems/delete-columns-to-make-sorted/);
 */
class Solution {
    // Complexity:
    // Time O(NM) and Space O(M) where N is the length of strs and
    // M is the length of each string in strs.
    fun minDeletionSize(strs: Array<String>): Int {
        val n = strs.size
        val strLen = strs[0].length

        val shouldDelete = BooleanArray(strLen)
        for (i in 1..<n) {
            for (j in 0..<strLen) {
                shouldDelete[j] =
                    shouldDelete[j] || (strs[i - 1][j] > strs[i][j])
            }
        }

        return shouldDelete.count { it }
    }
}
