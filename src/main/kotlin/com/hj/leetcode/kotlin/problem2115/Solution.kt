package com.hj.leetcode.kotlin.problem2115

/**
 * LeetCode page: [2115. Find All Possible Recipes from Given Supplies](https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/);
 */
class Solution {
    // Complexity:
    // Time O(N+M+S) and Space O(N+M+S)
    // where N, M, S are the flattened length of recipes, ingredients and supplies, respectively.
    fun findAllRecipes(
        recipes: Array<String>,
        ingredients: List<List<String>>,
        supplies: Array<String>,
    ): List<String> {
        val ingredientMap = mutableMapOf<String, List<String>>() // entry=(recipe, its ingredients)
        for ((i, recipe) in recipes.withIndex()) {
            ingredientMap[recipe] = ingredients[i]
        }

        val visited = mutableSetOf<String>() // visited recipes during the DFS, for cycle detection
        val memoization = mutableMapOf<String, Boolean>() // entry=(recipe, whether it can be created)
        for (ingredient in supplies) {
            memoization[ingredient] = true
        }

        val result = mutableListOf<String>()
        for (recipe in recipes) {
            if (canBeCreated(recipe, ingredientMap, visited, memoization)) {
                result.add(recipe)
            }
        }
        return result
    }

    private fun canBeCreated(
        recipe: String,
        ingredientMap: Map<String, List<String>>,
        visited: MutableSet<String>,
        memoization: MutableMap<String, Boolean>,
    ): Boolean {
        if (recipe in visited) {
            return false
        }
        if (recipe in memoization) {
            return memoization[recipe]!!
        }

        val ingredients = ingredientMap[recipe] ?: emptyList()
        if (ingredients.isEmpty()) {
            return false // A recipe depended on nothing and cannot be supplied directly
        }

        var result = true
        visited.add(recipe)
        for (ingredient in ingredients) {
            if (!canBeCreated(ingredient, ingredientMap, visited, memoization)) {
                result = false
                break
            }
        }
        visited.remove(recipe)
        memoization[recipe] = result
        return result
    }
}
