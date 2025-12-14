package com.hj.leetcode.kotlin.problem3606

import kotlin.collections.flatten

/**
 * LeetCode page: [3606. Coupon Code Validator](https://leetcode.com/problems/coupon-code-validator/);
 */
class Solution {
    private val categories =
        arrayOf("electronics", "grocery", "pharmacy", "restaurant")

    private val codeValidator = Regex("""^\w+$""")

    // Complexity:
    // Time O(NMLogN) and Space O(N) where N and M are the length
    // and the flattened length of code.
    fun validateCoupons(
        code: Array<String>,
        businessLine: Array<String>,
        isActive: BooleanArray,
    ): List<String> {
        // Valid coupons for each category
        val validCoupons =
            List(categories.size) { mutableListOf<String>() }

        for (i in code.indices) {
            if (!isActive[i]) {
                continue
            }

            val categoryIdx = categories.indexOf(businessLine[i])
            if (categoryIdx == -1) {
                continue
            }

            if (isValidCode(code[i])) {
                validCoupons[categoryIdx].add(code[i])
            }
        }

        return validCoupons.onEach { it.sort() }.flatten()
    }

    private fun isValidCode(code: String): Boolean =
        codeValidator.matches(code)
}
