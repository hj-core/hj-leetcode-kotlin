package com.hj.leetcode.kotlin.problem1652

/**
 * LeetCode page: [1652. Defuse the Bomb](https://leetcode.com/problems/defuse-the-bomb/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Auxiliary Space O(1) where N is the length of code.
     */
    fun decrypt(
        code: IntArray,
        k: Int,
    ): IntArray {
        if (k == 0) {
            return IntArray(code.size)
        }
        val n = code.size
        val result = IntArray(n)
        var (start, end) = if (k < 0) k + n to n else 1 to k + 1

        result[0] = (start..<end).sumOf { code[it] }
        for (i in 1..<n) {
            result[i] = result[i - 1] + code[end % n] - code[start % n]
            start += 1
            end += 1
        }
        return result
    }
}
