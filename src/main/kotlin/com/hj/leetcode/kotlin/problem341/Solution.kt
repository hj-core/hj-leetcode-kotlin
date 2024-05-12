package com.hj.leetcode.kotlin.problem341

/**
 * LeetCode page: [341. Flatten Nested List Iterator](https://leetcode.com/problems/flatten-nested-list-iterator/?envType=daily-question&envId=2023-10-20);
 */
class NestedIterator(private val nestedList: List<NestedInteger>) {

    private var index = 0
    private var nestedIterator: NestedIterator? = null

    fun next(): Int {
        if (nestedIterator?.hasNext() == true) {
            return checkNotNull(nestedIterator).next()
        }

        val result = checkNotNull(nestedList[index].getInteger())
        index++
        return result
    }

    fun hasNext(): Boolean {
        if (nestedIterator?.hasNext() == true) {
            return true
        }

        if (nestedIterator?.hasNext() == false) {
            nestedIterator = null
            index++
        }

        while (index < nestedList.size && !nestedList[index].isInteger()) {
            nestedIterator = NestedIterator(checkNotNull(nestedList[index].getList()))
            if (nestedIterator?.hasNext() == true) {
                break
            }
            nestedIterator = null
            index++
        }
        return index < nestedList.size
    }
}

abstract class NestedInteger {

    abstract fun isInteger(): Boolean

    abstract fun getInteger(): Int?

    abstract fun setInteger(value: Int): Unit

    abstract fun add(ni: NestedInteger): Unit

    abstract fun getList(): List<NestedInteger>?
}