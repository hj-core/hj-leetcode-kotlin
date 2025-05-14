package com.hj.leetcode.kotlin.problem3337

/**
 * LeetCode page: [3337. Total Characters in String After Transformations II](https://leetcode.com/problems/total-characters-in-string-after-transformations-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N+(M^3)*Log(t)) and Space O(M^2) where N is the length of s and
    // M is the size of the character set, which is 26 in this problem.
    fun lengthAfterTransformations(
        s: String,
        t: Int,
        nums: List<Int>,
    ): Int {
        val module = 1_000_000_007

        // The result is the sum(freq mul (A^t)), where freq is a 1x26 vector of
        // character frequencies, and A is a 26x26 transition matrix that brings
        // the state at t = i to t = i + 1.

        val freq = IntArray(26)
        for (c in s) {
            freq[c - 'a']++
        }

        var a = buildTransitionMatrix(nums)
        a = matrixPow(a, t, module)

        var result = 0L
        for ((i, f) in freq.withIndex()) {
            for (value in a[i]) {
                result = (result + value * f) % module
            }
        }
        return result.toInt()
    }

    private fun buildTransitionMatrix(nums: List<Int>): Array<LongArray> {
        val result = Array(26) { LongArray(26) }
        for (i in 0..<26) {
            for (j in 1..nums[i]) {
                result[i][(i + j) % 26] += 1
            }
        }
        return result
    }

    private fun matrixPow(
        mat: Array<LongArray>,
        pow: Int,
        module: Int,
    ): Array<LongArray> {
        require(pow >= 1) { "unexpected pow value: $pow" }
        if (pow == 1) {
            return mat
        }

        val result = matrixPow(matrixMul(mat, mat, module), pow / 2, module)
        if (pow and 1 == 1) {
            return matrixMul(result, mat, module)
        }
        return result
    }

    private fun matrixMul(
        mat: Array<LongArray>,
        other: Array<LongArray>,
        module: Int,
    ): Array<LongArray> {
        val m = mat.size
        val n = other[0].size

        val result = Array(m) { LongArray(n) }
        for (i in 0..<m) {
            for (j in 0..<n) {
                for (k in other.indices) {
                    result[i][j] = (result[i][j] + mat[i][k] * other[k][j]) % module
                }
            }
        }
        return result
    }
}
