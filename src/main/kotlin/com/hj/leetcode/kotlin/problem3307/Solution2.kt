package com.hj.leetcode.kotlin.problem3307

class Solution2 {
    // Complexity:
    // Time O(Logk) and Space O(1).
    fun kthCharacter(
        k: Long,
        operations: IntArray,
    ): Char {
        var shift = 0
        var newK = k - 1
        var i = 0

        while (newK > 0) {
            shift += newK.toInt() and operations[i]
            i++
            newK = newK shr 1
        }
        return 'a' + (shift % 26)
    }
}
