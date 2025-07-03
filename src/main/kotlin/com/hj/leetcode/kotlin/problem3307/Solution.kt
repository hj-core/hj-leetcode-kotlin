package com.hj.leetcode.kotlin.problem3307

/**
 * LeetCode page: [3307. Find the K-th Character in String Game II](https://leetcode.com/problems/find-the-k-th-character-in-string-game-ii/);
 */
class Solution {
    // Complexity:
    // Time O(Logk) and Space O(1).
    fun kthCharacter(
        k: Long,
        operations: IntArray,
    ): Char {
        var newK = k
        var msb = newK.takeHighestOneBit()
        var shift = 0

        while (newK > msb) {
            newK -= msb
            shift += operations[msb.countTrailingZeroBits()]
            msb = newK.takeHighestOneBit()
        }

        for (i in 0..<(msb.countTrailingZeroBits())) {
            shift += operations[i]
        }
        return 'a' + (shift % 26)
    }
}
