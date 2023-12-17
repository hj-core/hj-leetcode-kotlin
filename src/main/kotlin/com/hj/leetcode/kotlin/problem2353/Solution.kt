package com.hj.leetcode.kotlin.problem2353

import java.util.*

/**
 * LeetCode page: [2353. Design a Food Rating System](https://leetcode.com/problems/design-a-food-rating-system/);
 */
class FoodRatings(foods: Array<String>, cuisines: Array<String>, ratings: IntArray) {

    // entry=(food, (cuisine, rating))
    private val menu = hashMapOf<String, Pair<String, Int>>()

    // entry=(cuisine, sortedFoods)
    private val menuRatings = hashMapOf<String, SortedSet<String>>()
    private val ratingCriterion = compareBy<String>(
        { -checkNotNull(menu[it]?.second) }, { it }
    )

    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of foods;
     */
    init {
        for ((index, food) in foods.withIndex()) {
            menu[food] = cuisines[index] to ratings[index]
            menuRatings
                .computeIfAbsent(cuisines[index]) { TreeSet(ratingCriterion) }
                .add(food)
        }
    }

    /* Complexity for each call:
     * Time O(LogN) and Space O(1) where N is the size of foods;
     */
    fun changeRating(food: String, newRating: Int) {
        val cuisine = checkNotNull(menu[food]?.first)
        menuRatings[cuisine]?.remove(food)
        menu[food] = cuisine to newRating
        menuRatings[cuisine]?.add(food)
    }

    /* Complexity for each call:
     * Time O(1) and Space O(1);
     */
    fun highestRated(cuisine: String): String {
        return checkNotNull(menuRatings[cuisine]?.first())
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * var obj = FoodRatings(foods, cuisines, ratings)
 * obj.changeRating(food,newRating)
 * var param_2 = obj.highestRated(cuisine)
 */