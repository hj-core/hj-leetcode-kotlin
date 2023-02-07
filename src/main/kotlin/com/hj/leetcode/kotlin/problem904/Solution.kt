package com.hj.leetcode.kotlin.problem904

/**
 * LeetCode page: [904. Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/);
 */
class Solution {
    /* Complexity:
     * Time O(|fruits|) and Space O(1);
     */
    fun totalFruit(fruits: IntArray): Int {
        // an emptyBasket that match algorithm logic
        val basket1 = SingleTypeBasket(-1, 0, -1)
        // a basket with one fruits[0]
        val basket2 = SingleTypeBasket(fruits[0], 1, 0)
        var maxFruits = 0

        for (index in 1 until fruits.size) {
            when (val type = fruits[index]) {
                basket1.fruitType -> basket1.putOne(type, index)
                basket2.fruitType -> basket2.putOne(type, index)
                else -> {
                    maxFruits = maxOf(maxFruits, basket1.numFruits + basket2.numFruits)

                    val isBasket1MoreRecent = basket1.lastPickIndex > basket2.lastPickIndex
                    val (moreRecent, lessRecent) =
                        if (isBasket1MoreRecent) basket1 to basket2 else basket2 to basket1

                    // require emptyBasket set lastPickIndex to -1
                    moreRecent.numFruits = moreRecent.lastPickIndex - lessRecent.lastPickIndex
                    lessRecent.putOne(type, index)
                }
            }
        }
        maxFruits = maxOf(maxFruits, basket1.numFruits + basket2.numFruits)
        return maxFruits
    }

    private class SingleTypeBasket(var fruitType: Int, var numFruits: Int, var lastPickIndex: Int) {

        fun putOne(type: Int, pickIndex: Int) {
            val isExistingType = type == fruitType

            if (isExistingType) {
                numFruits++
            } else {
                fruitType = type
                numFruits = 1
            }
            lastPickIndex = pickIndex
        }
    }
}