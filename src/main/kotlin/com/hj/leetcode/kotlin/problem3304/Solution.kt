package com.hj.leetcode.kotlin.problem3304

/**
 * LeetCode page: [3304. Find the K-th Character in String Game I](https://leetcode.com/problems/find-the-k-th-character-in-string-game-i/);
 */
class Solution {
    // Complexity:
    // Time O(Logk) and Space O(1).
    fun kthCharacter(k: Int): Char = 'a' + ((k - 1).countOneBits() % 26)

    // Complexity:
    // Time O(Logk) and Space O(1).
    fun kthCharacter2(k: Int): Char {
        var newK = k
        var msb = newK.takeHighestOneBit()
        var shift = 0

        while (newK > msb) {
            newK -= msb
            shift++
            msb = newK.takeHighestOneBit()
        }
        shift = (shift + newK.countTrailingZeroBits()) % 26
        return 'a' + shift
    }
}
