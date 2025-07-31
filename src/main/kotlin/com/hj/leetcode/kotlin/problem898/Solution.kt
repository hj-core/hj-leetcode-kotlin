package com.hj.leetcode.kotlin.problem898

/**
 * LeetCode page: [898. Bitwise ORs of Subarrays](https://leetcode.com/problems/bitwise-ors-of-subarrays/);
 */
class Solution {
    // Complexity:
    // Time O(NLogM) and Space O(NLogM), where N is the
    // length of arr and M is the maximum value in arr.
    fun subarrayBitwiseORs(arr: IntArray): Int {
        val allOrs = hashSetOf<Int>()
        // Distinct OR values of the subarrays ending at the
        // previous index, strictly increasing.
        var prevOrs = mutableListOf<Int>()

        for (num in arr) {
            val currOrs = mutableListOf(num)
            allOrs.add(num)

            for (prevOr in prevOrs) {
                val currOr = prevOr or num
                if (currOr != currOrs.last()) {
                    currOrs.add(currOr)
                    allOrs.add(currOr)
                }
            }
            prevOrs = currOrs
        }
        return allOrs.size
    }
}
