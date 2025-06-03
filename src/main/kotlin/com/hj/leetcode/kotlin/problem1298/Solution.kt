package com.hj.leetcode.kotlin.problem1298

/**
 * LeetCode page: [1298. Maximum Candies You Can Get from Boxes](https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of status.
    fun maxCandies(
        status: IntArray,
        candies: IntArray,
        keys: Array<IntArray>,
        containedBoxes: Array<IntArray>,
        initialBoxes: IntArray,
    ): Int {
        val n = status.size
        val hasKey = BooleanArray(n) { status[it] == 1 }
        val hasBox = BooleanArray(n)
        var result = 0
        for (box in initialBoxes) {
            hasBox[box] = true
            if (hasKey[box]) {
                result += dfs(box, hasKey, hasBox, candies, keys, containedBoxes)
            }
        }
        return result
    }

    private fun dfs(
        box: Int,
        hasKey: BooleanArray,
        hasBox: BooleanArray,
        candies: IntArray,
        keys: Array<IntArray>,
        containedBoxes: Array<IntArray>,
    ): Int {
        check(hasBox[box])
        check(hasKey[box])

        var result = candies[box]

        for (key in keys[box]) {
            if (hasKey[key]) {
                continue
            }
            hasKey[key] = true
            if (hasBox[key]) {
                result += dfs(key, hasKey, hasBox, candies, keys, containedBoxes)
            }
        }

        for (newBox in containedBoxes[box]) {
            hasBox[newBox] = true
            if (hasKey[newBox]) {
                result += dfs(newBox, hasKey, hasBox, candies, keys, containedBoxes)
            }
        }

        return result
    }
}
