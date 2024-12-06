package com.hj.leetcode.kotlin.problem2337

/**
 * LeetCode page: [2337. Move Pieces to Obtain a String](https://leetcode.com/problems/move-pieces-to-obtain-a-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of start and target.
     */
    fun canChange(
        start: String,
        target: String,
    ): Boolean {
        require(start.length == target.length)
        var i = -1 // index of start
        var j = -1 // index of target

        while (true) {
            i = indexOfNextPiece(start, i)
            j = indexOfNextPiece(target, j)

            when {
                i == start.length || j == target.length -> return i == j
                start[i] != target[j] -> return false
                start[i] == 'L' && i < j -> return false
                start[i] == 'R' && j < i -> return false
            }
        }
    }

    private fun indexOfNextPiece(
        s: String,
        after: Int,
    ): Int {
        require(after < s.length)
        var result = after + 1
        while (result < s.length && s[result] == '_') {
            result++
        }
        return result
    }
}
