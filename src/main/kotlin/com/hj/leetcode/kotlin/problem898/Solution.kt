package com.hj.leetcode.kotlin.problem898

/**
 * LeetCode page: [898. Bitwise ORs of Subarrays](https://leetcode.com/problems/bitwise-ors-of-subarrays/);
 */
class Solution {
    // Complexity:
    // Time O(NW) and Space O(NW), where N is the length
    // of arr and W is the bits of a word.
    fun subarrayBitwiseORs(arr: IntArray): Int {
        val allOrs = hashSetOf<Int>()

        // Distinct OR values of the subarrays ending at the
        // previous index, strictly increasing.
        val prevOrs = IntArray(32)
        var size = 0

        for (num in arr) {
            var tmp = prevOrs[0] or num

            prevOrs[0] = num
            var newSize = 1
            allOrs.add(num)

            for (i in 0..<size) {
                if (tmp == prevOrs[newSize - 1]) {
                    tmp = prevOrs[i + 1] or num
                } else {
                    allOrs.add(tmp)
                    prevOrs[newSize] = tmp.also { tmp = prevOrs[i + 1] or num }
                    newSize++
                }
            }
            size = newSize
        }
        return allOrs.size
    }
}
