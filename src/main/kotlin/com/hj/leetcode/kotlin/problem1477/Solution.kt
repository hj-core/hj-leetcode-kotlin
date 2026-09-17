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
        var subarraySum = 0 // Sum(arr[left..<right])
        var right = n
        for (left in n - 1 downTo 0) {
            subarraySum += arr[left]
            while (subarraySum > target) {
                right--
                subarraySum -= arr[right]
            }

            suffixMinLen[left] = suffixMinLen[left + 1]
            if (subarraySum == target) {
                val len = right - left
                result = minOf(result, len + suffixMinLen[right])
                suffixMinLen[left] = minOf(suffixMinLen[left], len)
            }
        }

        if (result > n) {
            return -1
        }
        return result
    }
}
