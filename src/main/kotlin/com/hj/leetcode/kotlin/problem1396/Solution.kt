package com.hj.leetcode.kotlin.problem1396

/**
 * LeetCode page: [1396. Design Underground System](https://leetcode.com/problems/design-underground-system/);
 */
class UndergroundSystem() {

    private val activeCheckIn = hashMapOf<CustomerID, CheckInRecord>()
    private val tripTimeSummary = hashMapOf<Trip, TimeSummary>()

    private data class CustomerID(val value: Int)

    private data class CheckInRecord(val stationName: String, val timeStamp: Int)

    private data class Trip(val fromStation: String, val toStation: String)

    private data class TimeSummary(val totalTimes: Int, val numTrips: Int) {

        fun averageTravelTime(): Double {
            return totalTimes.toDouble() / numTrips
        }
    }

    /* Complexity of N calls:
     * Time O(N) and Space O(N);
     */
    fun checkIn(id: Int, stationName: String, t: Int) {
        check(!activeCheckIn.contains(CustomerID(id))) {
            "The customer has already checked in but has not yet checked out."
        }
        activeCheckIn[CustomerID(id)] = CheckInRecord(stationName, t)
    }

    /* Complexity of N calls:
     * Time O(N) and Space O(N);
     */
    fun checkOut(id: Int, stationName: String, t: Int) {
        val checkInRecord = activeCheckIn[CustomerID(id)] ?: throw IllegalStateException()
        activeCheckIn.remove(CustomerID(id))

        val trip = Trip(checkInRecord.stationName, stationName)
        val tripDuration = t - checkInRecord.timeStamp
        val oldSummary = tripTimeSummary[trip] ?: TimeSummary(0, 0)
        val newSummary = TimeSummary(oldSummary.totalTimes + tripDuration, oldSummary.numTrips + 1)
        tripTimeSummary[trip] = newSummary
    }

    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun getAverageTime(startStation: String, endStation: String): Double {
        val trip = Trip(startStation, endStation)
        val summary = tripTimeSummary[trip] ?: TimeSummary(0, 0)
        return summary.averageTravelTime()
    }
}