package com.hj.leetcode.kotlin.problem380

class RandomizedSet2() {

    private val values = hashSetOf<Int>()
    private var laggedValues = mutableListOf<Int>()
    private val maxFalseRate = 0.5

    /* Complexity:
     * Time O(N) and Space O(N) For N Calls;
     */
    fun insert(`val`: Int): Boolean {
        if (`val` in values) {
            return false
        }

        values.add(`val`)
        laggedValues.add(`val`)
        return true
    }

    /* Complexity:
     * Time amortized O(1) and Space O(1);
     */
    fun remove(`val`: Int): Boolean {
        if (`val` !in values) {
            return false
        }

        values.remove(`val`)
        if (values.size < laggedValues.size * maxFalseRate) {
            garbageCollection()
        }
        return true
    }

    private fun garbageCollection() {
        laggedValues = laggedValues.filterTo(mutableListOf()) { it in values }
    }

    /* Complexity:
     * Time expected O(1) and Space O(1);
     */
    fun getRandom(): Int {
        var result = laggedValues.random()
        while (result !in values) {
            result = laggedValues.random()
        }
        return result
    }
}