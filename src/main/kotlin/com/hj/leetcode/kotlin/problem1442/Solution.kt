package com.hj.leetcode.kotlin.problem1442

/**
 * LeetCode page: [1442. Count Triplets That Can Form Two Arrays of Equal XOR](https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of arr;
     */
    fun countTriplets(arr: IntArray): Int {
        /* Entry = prefix xor value to
         * Pair(count, sum) of indices having this prefix xor value.
         */
        val prefixXORSummary = hashMapOf<Int, Pair<Int, Int>>().apply {
            this[0] = Pair(1, -1)
        }
        var result = 0
        var prefixXOR = 0

        for (k in arr.indices) {
            prefixXOR = prefixXOR xor arr[k]
            if (prefixXOR in prefixXORSummary) {
                val (count, sum) = checkNotNull(prefixXORSummary[prefixXOR])
                result += count * k - sum - count
                prefixXORSummary[prefixXOR] = Pair(count + 1, sum + k)
            } else {
                prefixXORSummary[prefixXOR] = Pair(1, k)
            }
        }
        return result
    }
}