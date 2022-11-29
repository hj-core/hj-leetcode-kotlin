package com.hj.leetcode.kotlin.problem380

/**
 * LeetCode page: [380. Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/);
 */
class RandomizedSet() {
    private val values = mutableListOf<Int>()
    private val indexPerValue = hashMapOf<Int, Int>()

    /* Complexity of N calls:
     * Time O(N) and Space O(N);
     */
    fun insert(`val`: Int): Boolean {
        val isExist = indexPerValue.containsKey(`val`)
        if (isExist) return false

        values.add(`val`)
        indexPerValue[`val`] = values.lastIndex
        return true
    }

    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun remove(`val`: Int): Boolean {
        val index = indexPerValue[`val`] ?: return false
        val lastValue = values.last()

        values[index] = lastValue
        indexPerValue[lastValue] = index

        values.apply { removeAt(lastIndex) }
        indexPerValue.remove(`val`)
        return true
    }

    /* Complexity:
     * TIme O(1) and Space O(1);
     */
    fun getRandom(): Int {
        return values.random()
    }
}