package com.hj.leetcode.kotlin.problem67

/**
 * LeetCode page: [67. Add Binary](https://leetcode.com/problems/add-binary/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the longer length between a
    // and b.
    fun addBinary(
        a: String,
        b: String,
    ): String {
        if (a.length > b.length) {
            return addBinary(b, a)
        }

        val builder = StringBuilder(b.length + 1)
        var carry = 0

        for (i in 0..<a.length) {
            carry =
                3 and (
                    carry +
                        a[a.length - 1 - i].code +
                        b[b.length - 1 - i].code
                )
            builder.append(carry and 1)
            carry = carry shr 1
        }
        for (i in a.length..<b.length) {
            carry = 3 and (carry + b[b.length - 1 - i].code)
            builder.append(carry and 1)
            carry = carry shr 1
        }

        if (carry > 0) {
            builder.append(carry)
        }
        return builder.reverse().toString()
    }
}
