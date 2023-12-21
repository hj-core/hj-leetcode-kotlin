package com.hj.leetcode.kotlin.problem875

/**
 * LeetCode page: [875. Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogM) and Space O(1) where N is the size of piles and M is the largest value in piles;
     */
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        require(h >= piles.size) { "The constraints of the problem are not satisfied." }
        var lowerBound = 1
        var upperBound = piles.max()!!
        while (lowerBound < upperBound) {
            val mid = lowerBound + (upperBound - lowerBound) / 2
            val canEatAll = canEatAllBananas(mid, piles, h)
            if (canEatAll) {
                upperBound = mid
            } else {
                lowerBound = mid + 1
            }
        }
        return lowerBound
    }

    private fun canEatAllBananas(speed: Int, piles: IntArray, timeLimit: Int): Boolean {
        var currTime = 0
        for (pile in piles) {
            currTime += computeEatingTime(speed, pile)
            if (currTime > timeLimit) return false
        }
        return true
    }

    private fun computeEatingTime(speed: Int, pile: Int): Int {
        val time = pile / speed
        return if (time * speed < pile) time + 1 else time
    }
}