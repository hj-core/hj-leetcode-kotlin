package com.hj.leetcode.kotlin.problem3886

/**
 * LeetCode page: [3886. Sum of Sortable Integers](https://leetcode.com/problems/sum-of-sortable-integers/);
 */
class Solution {
    // Complexity:
    // Time O(N * sqrt(N)) and Space O(N) where N is the length of nums.
    fun sortableIntegers(nums: IntArray): Int {
        val n = nums.size

        // prefixMax[i]:= maxOf(nums[..<i])
        val prefixMax = IntArray(n + 1)
        prefixMax[0] = Int.MIN_VALUE
        for (i in 1..<n) {
            prefixMax[i] = maxOf(nums[i - 1], prefixMax[i - 1])
        }

        // suffixMin[i]:= minOf(nums[i..])
        val suffixMin = IntArray(n + 1)
        suffixMin[n] = Int.MAX_VALUE
        for (i in n - 1 downTo 0) {
            suffixMin[i] = minOf(nums[i], suffixMin[i + 1])
        }

        var sum = 0
        var k = 1
        while (k * k < n) {
            if (n % k == 0) {
                if (isSortable(nums, k, prefixMax, suffixMin)) {
                    sum += k
                }

                val k2 = n / k
                if (isSortable(nums, k2, prefixMax, suffixMin)) {
                    sum += k2
                }
            }

            k++
        }

        if (k * k == n) {
            if (isSortable(nums, k, prefixMax, suffixMin)) {
                sum += k
            }
        }

        return sum
    }

    private fun isSortable(
        nums: IntArray,
        k: Int,
        prefixMax: IntArray,
        suffixMin: IntArray,
    ): Boolean {
        for (i in nums.indices step k) {
            val pivot = findPivot(nums, i, i + k)
            if (pivot < 0) {
                return false
            }

            if (nums[pivot] < prefixMax[i]) {
                return false
            }

            val currMax = if (pivot == i) nums[i + k - 1] else nums[pivot - 1]
            if (currMax > suffixMin[i + k]) {
                return false
            }
        }
        return true
    }

    private fun findPivot(
        nums: IntArray,
        start: Int,
        end: Int,
    ): Int {
        var pivot = start
        var turned = false
        for (i in start + 1..<end) {
            if (nums[i] < nums[i - 1]) {
                if (turned || nums[start] < nums[end - 1]) {
                    return -1
                }
                pivot = i
                turned = true
            }
        }
        return pivot
    }
}
