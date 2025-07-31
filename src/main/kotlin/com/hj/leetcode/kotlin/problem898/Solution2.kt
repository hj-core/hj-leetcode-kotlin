package com.hj.leetcode.kotlin.problem898

/**
 * LeetCode page: [898. Bitwise ORs of Subarrays](https://leetcode.com/problems/bitwise-ors-of-subarrays/);
 */
class Solution2 {
    // Complexity:
    // Time O(NLogM)? and Space O(NLogM), where N is the
    // length of arr and M is the maximum value in arr.
    fun subarrayBitwiseORs(arr: IntArray): Int {
        val allOrs = hashSetOf<Int>()
        for (i in arr.indices) {
            var prevOr = 0 // OR value of arr[j..<i]
            var currOr = arr[i]
            allOrs.add(currOr)

            for (j in (i - 1) downTo 0) {
                prevOr = prevOr or arr[j]
                currOr = currOr or arr[j]
                if (prevOr == currOr) {
                    break
                } else {
                    allOrs.add(currOr)
                }
            }
        }
        return allOrs.size
    }
}
