package com.hj.leetcode.kotlin.problem955

import kotlin.experimental.and
import kotlin.experimental.or

/**
 * LeetCode page: [955. Delete Columns to Make Sorted II](https://leetcode.com/problems/delete-columns-to-make-sorted-ii/);
 */
class Solution {
    // Complexity:
    // Time O(NM) and Space O(N) where N is the length of strs and M
    // is the length of each string.
    fun minDeletionSize(strs: Array<String>): Int {
        val n = strs.size
        val m = strs[0].length

        // isSplitPoint is a merge of two boolean arrays
        val isSplitPoint = ByteArray(n)
        var oldMask: Byte = 1
        var newMask: Byte = 2

        isSplitPoint[0] = oldMask or newMask
        var minDeletions = 0

        for (col in 0..<m) {
            var shouldDelete = false
            for (row in 1..<n) {
                isSplitPoint[row] = isSplitPoint[row] and oldMask

                if (isSplitPoint[row] and oldMask == oldMask ||
                    strs[row - 1][col] < strs[row][col]
                ) {
                    isSplitPoint[row] = isSplitPoint[row] or newMask
                } else if (strs[row - 1][col] > strs[row][col]) {
                    shouldDelete = true
                    break
                }
            }

            if (shouldDelete) {
                minDeletions++
            } else {
                oldMask = newMask.also { newMask = oldMask }
            }
        }

        return minDeletions
    }
}
