package com.hj.leetcode.kotlin.problem904

/**
 * LeetCode page: [904. Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of fruits.
    fun totalFruit(fruits: IntArray): Int {
        var result = 0
        var fruit1 = fruits[0]
        var fruit2 = fruits[0]
        var amount = 1
        var lastAlter = 0 // The most recent index where type changed

        for (i in 1..<fruits.size) {
            if (fruits[i] == fruit1 || fruits[i] == fruit2) {
                amount++
            } else if (fruit1 == fruit2) {
                fruit2 = fruits[i]
                amount++
            } else {
                result = maxOf(result, amount)
                fruit1 = fruits[i - 1]
                fruit2 = fruits[i]
                amount = i - lastAlter + 1
            }

            if (fruits[i] != fruits[i - 1]) {
                lastAlter = i
            }
        }
        result = maxOf(result, amount)
        return result
    }
}
