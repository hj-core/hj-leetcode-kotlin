package com.hj.leetcode.kotlin.problem907

/**
 * LeetCode page: [907. Sum of Subarray Minimums](https://leetcode.com/problems/sum-of-subarray-minimums/);
 */
class Solution {
    /* Complexity:
     * Time O(|arr|) and Space O(|arr|);
     */
    fun sumSubarrayMins(arr: IntArray): Int {
        val module = 1_000_000_007
        val indexStack = mutableListOf<Int>() // stack for searching nearest previous index with smaller value
        val prefixDp = IntArray(arr.size) // prefixDp[i] is the sum of min of all sub arrays ending at index i

        for (index in prefixDp.indices) {
            while (indexStack.isNotEmpty() && arr[index] < arr[indexStack.last()]) {
                indexStack.apply { removeAt(lastIndex) }
            }
            val indexOfPrevSmaller = indexStack.lastOrNull() ?: -1
            indexStack.add(index)

            prefixDp[index] =
                ((index - indexOfPrevSmaller) * arr[index] + prefixDp.getOrElse(indexOfPrevSmaller) { 0 }) % module
        }
        return prefixDp.reduce { acc, i -> (acc + i) % module }
    }
}