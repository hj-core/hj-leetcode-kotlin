package com.hj.leetcode.kotlin.problem1477

/**
 * LeetCode page: [1477. Find Two Non-overlapping Sub-arrays Each With Target Sum](https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of arr.
    fun minSumOfLengths(
        arr: IntArray,
        target: Int,
    ): Int {
        val n = arr.size
        // suffixMinLen[i] := the min length of a subarray in arr[i..] whose sum equals target
        val suffixMinLen = IntArray(n + 1)
        suffixMinLen[n] = n + 1

        var result = n + 1
        var sum = 0 // Sum(arr[start..<end])
        var end = n
        for (start in n - 1 downTo 0) {
            sum += arr[start]
            while (sum > target) {
                end--
                sum -= arr[end]
            }

            suffixMinLen[start] = suffixMinLen[start + 1]
            if (sum == target) {
                val len = end - start
                result = minOf(result, len + suffixMinLen[end])
                suffixMinLen[start] = minOf(suffixMinLen[start], len)
            }
        }

        if (result > n) {
            return -1
        }
        return result
    }
}
