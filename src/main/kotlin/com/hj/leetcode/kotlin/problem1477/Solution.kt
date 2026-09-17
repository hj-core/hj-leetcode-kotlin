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
        var minLenSum = n + 1
        // suffixMinLen[i] := the min length of a subarray in arr[i..] whose sum equals target
        val suffixMinLen = IntArray(n + 1)
        suffixMinLen[n] = n + 1
        var windowSum = 0 // Sum(arr[left..<right]
        var right = n
        for (left in n - 1 downTo 0) {
            windowSum += arr[left]
            while (windowSum > target) {
                right--
                windowSum -= arr[right]
            }

            suffixMinLen[left] = suffixMinLen[left + 1]
            if (windowSum == target) {
                val len = right - left
                minLenSum = minOf(minLenSum, len + suffixMinLen[right])
                suffixMinLen[left] = minOf(suffixMinLen[left], len)
            }
        }

        if (minLenSum > n) {
            return -1
        }
        return minLenSum
    }
}
