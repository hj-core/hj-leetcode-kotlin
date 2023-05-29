package com.hj.leetcode.kotlin.problem1603

/**
 * LeetCode page: [1603. Design Parking System](https://leetcode.com/problems/design-parking-system/);
 */
class ParkingSystem(big: Int, medium: Int, small: Int) {

    private val typeBig = 1
    private val typeMedium = 2
    private val typeSmall = 3

    private val bigSlot = CarSlot(limit = big)
    private val mediumSlot = CarSlot(limit = medium)
    private val smallSlot = CarSlot(limit = small)

    private class CarSlot(val limit: Int) {

        private var used: Int = 0

        fun addCar(): Boolean {
            val isFull = used >= limit
            if (isFull) {
                return false
            }

            used++
            return true
        }
    }

    /* Complexity of N calls:
     * Time O(N) and Space O(1);
     */
    fun addCar(carType: Int): Boolean {
        return when (carType) {
            typeBig -> bigSlot.addCar()
            typeMedium -> mediumSlot.addCar()
            typeSmall -> smallSlot.addCar()
            else -> throw NoWhenBranchMatchedException("Unknown car type.")
        }
    }
}