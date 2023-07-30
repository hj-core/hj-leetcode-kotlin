package com.hj.leetcode.kotlin.problem808

/**
 * LeetCode page: [808. Soup Servings](https://leetcode.com/problems/soup-servings/);
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1) because the number of recursions is bounded by O(n0^2),
     * where n0 is a constant much smaller than the upper bound of n, and for n is not
     * less than n0, the probability is not less than 1-tolerance;
     */
    fun soupServings(n: Int): Double {
        val memoization = hashMapOf<RemainingSoup, Double>()
        val tolerance = 1e-5
        val unitServeMl = 25
        for (amount in 0..n step unitServeMl) {
            if (probabilityAsked(RemainingSoup(amount, amount), memoization) > 1 - tolerance) {
                return 1.0
            }
        }
        return probabilityAsked(RemainingSoup(n, n), memoization)
    }

    private fun probabilityAsked(
        remainingSoup: RemainingSoup,
        memoization: MutableMap<RemainingSoup, Double> = hashMapOf()
    ): Double = with(remainingSoup) {
        if (this in memoization) {
            return checkNotNull(memoization[this])
        }
        if (typeA > 0 && typeB <= 0) {
            return 0.0
        }
        if (typeA <= 0) {
            return if (typeB <= 0) 0.5 else 1.0
        }

        val result = 0.25 * (probabilityAsked(remainingSoup.afterOperation1(), memoization)
                + probabilityAsked(remainingSoup.afterOperation2(), memoization)
                + probabilityAsked(remainingSoup.afterOperation3(), memoization)
                + probabilityAsked(remainingSoup.afterOperation4(), memoization))
        memoization[remainingSoup] = result
        return result
    }

    private data class RemainingSoup(val typeA: Int, val typeB: Int) {

        fun afterOperation1(): RemainingSoup = RemainingSoup(typeA - 100, typeB)
        fun afterOperation2(): RemainingSoup = RemainingSoup(typeA - 75, typeB - 25)
        fun afterOperation3(): RemainingSoup = RemainingSoup(typeA - 50, typeB - 50)
        fun afterOperation4(): RemainingSoup = RemainingSoup(typeA - 25, typeB - 75)
    }
}