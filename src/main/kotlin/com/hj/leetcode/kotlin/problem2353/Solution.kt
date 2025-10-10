package com.hj.leetcode.kotlin.problem2353

import java.util.*

/**
 * LeetCode page: [2353. Design a Food Rating System](https://leetcode.com/problems/design-a-food-rating-system/);
 */
class FoodRatings(
    foods: Array<String>,
    cuisines: Array<String>,
    ratings: IntArray,
) {
    private class Food(
        val name: String,
        val cuisine: String,
        var rating: Int,
    )

    private val foods = hashMapOf<String, Food>() // name to food
    private val cuisineFoods = hashMapOf<String, TreeSet<Food>>() // cuisine to sorted foods
    private val ratingCriteria = compareBy<Food>({ -it.rating }, { it.name })

    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the size of foods.
    init {
        for (i in foods.indices) {
            val food = Food(foods[i], cuisines[i], ratings[i])
            this.foods[food.name] = food
            this.cuisineFoods
                .computeIfAbsent(food.cuisine) { TreeSet(ratingCriteria) }
                .add(food)
        }
    }

    // Complexity:
    // Time O(LogN) and Space O(1) where N is the size of foods.
    fun changeRating(
        food: String,
        newRating: Int,
    ) {
        val food = foods[food] ?: throw IllegalArgumentException()

        cuisineFoods[food.cuisine]?.let {
            it.remove(food)
            food.rating = newRating
            it.add(food)
        } ?: throw IllegalStateException()
    }

    // Complexity:
    // Time O(1) and Space O(1).
    fun highestRated(cuisine: String): String =
        cuisineFoods[cuisine]
            ?.first()
            ?.name
            ?: throw IllegalArgumentException()
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * var obj = FoodRatings(foods, cuisines, ratings)
 * obj.changeRating(food,newRating)
 * var param_2 = obj.highestRated(cuisine)
 */
