package com.hj.leetcode.kotlin.problem1603

/**
 * LeetCode page: [1603. Design Parking System](https://leetcode.com/problems/design-parking-system/);
 */
class ParkingSystem(big: Int, medium: Int, small: Int) {

    private val typeBig = 1
    private val typeMedium = 2
    private val typeSmall = 3

    private val bigSlots = CarSlots(limit = big)
    private val mediumSlots = CarSlots(limit = medium)
    private val smallSlots = CarSlots(limit = small)

    private class CarSlots(val limit: Int) {

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
            typeBig -> bigSlots.addCar()
            typeMedium -> mediumSlots.addCar()
            typeSmall -> smallSlots.addCar()
            else -> throw NoWhenBranchMatchedException("Unknown car type.")
        }
    }
}