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
        var index = 0
        val stack = mutableListOf<Char>()

        while (remainingK > 0) {
            when {
                stack.isEmpty() -> {
                    check(index < num.length)
                    stack.add(num[index])
                    index++
                }

                index == num.length || num[index] < stack.last() -> {
                    stack.removeLast()
                    remainingK--
                }

                else -> {
                    stack.add(num[index])
                    index++
                }
            }
        }

        val result = StringBuilder()
        val firstNonZero = stack.indexOfFirst { it != '0' }
        if (firstNonZero != -1) {
            for (i in firstNonZero..<stack.size) {
                result.append(stack[i])
            }
        }

        if (result.isEmpty()) {
            while (index < num.length && num[index] == '0') {
                index++
            }
        }
        for (i in index..<num.length) {
            result.append(num[i])
        }

        if (result.isEmpty()) {
            result.append('0')
        }
        return result.toString()
    }
}