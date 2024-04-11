package com.hj.leetcode.kotlin.problem402

/**
 * LeetCode page: [402. Remove K Digits](https://leetcode.com/problems/remove-k-digits/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of num;
     */
    fun removeKdigits(num: String, k: Int): String {
        var remainingK = k
        var i = 0
        val stack = mutableListOf<Char>()

        while (remainingK > 0) {
            when {
                stack.isEmpty() -> {
                    check(i < num.length)
                    stack.add(num[i])
                    i++
                }

                i == num.length -> {
                    stack.removeLast()
                    remainingK--
                }

                num[i] < stack.last() -> {
                    stack.removeLast()
                    remainingK--
                }

                else -> {
                    stack.add(num[i])
                    i++
                }
            }
        }

        val result = StringBuilder()
        val firstNonZero = stack.indexOfFirst { it != '0' }
        if (firstNonZero != -1) {
            for (j in firstNonZero..<stack.size) {
                result.append(stack[j])
            }
        }

        if (result.isEmpty()) {
            while (i < num.length && num[i] == '0') {
                i++
            }
        }
        for (j in i..<num.length) {
            result.append(num[j])
        }

        if (result.isEmpty()) {
            result.append('0')
        }
        return result.toString()
    }
}