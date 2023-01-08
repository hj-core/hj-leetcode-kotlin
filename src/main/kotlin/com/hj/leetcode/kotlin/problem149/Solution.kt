package com.hj.leetcode.kotlin.problem149

/**
 * LeetCode page: [149. Max Points on a Line](https://leetcode.com/problems/max-points-on-a-line/);
 */
class Solution {
    fun maxPoints(points: Array<IntArray>): Int {
        if (points.size == 1) return 1

        val allSlopes = findAllSlopesToPoints(points)
        var maxPoints = 2
        for ((slope, pointSet) in allSlopes) {
            maxPoints = maxOf(maxPoints, countMaxPoints(slope, pointSet))
        }
        return maxPoints
    }

    private fun findAllSlopesToPoints(points: Array<IntArray>): Map<Rational, Set<Point>> {
        require(points.size > 1)
        val allSlopes = hashMapOf<Rational, MutableSet<Point>>()
        for (i in 0 until points.lastIndex) {
            for (j in i + 1 until points.size) {
                val point1 = Point.of(points[i])
                val point2 = Point.of(points[j])
                val slope = calculateSlope(point1, point2)
                allSlopes
                    .computeIfAbsent(slope) { hashSetOf() }
                    .apply {
                        add(point1)
                        add(point2)
                    }
            }
        }
        return allSlopes
    }

    private data class Point(val x: Int, val y: Int) {
        companion object {
            fun of(intArray: IntArray) = Point(intArray[0], intArray[1])
        }

    }

    private fun calculateSlope(point1: Point, point2: Point): Rational {
        val dx = point1.x - point2.x
        val dy = point1.y - point2.y

        return when {
            dx == 0 -> Rational.INF
            dy == 0 -> Rational.ZERO
            else -> {
                val gcd = gcd(dx, dy)
                val numerator = Math.abs(dy / gcd)
                val denominator = Math.abs(dx / gcd)
                val isNegative = (dx < 0 && dy > 0) || (dx > 0 && dy < 0)
                RationalNum(numerator, denominator, isNegative)
            }
        }
    }

    private interface Rational {
        object ZERO : Rational
        object INF : Rational
    }

    private data class RationalNum(val numerator: Int, val denominator: Int, val isNegative: Boolean) : Rational

    private fun gcd(num1: Int, num2: Int): Int {
        val abs1 = Math.abs(num1)
        val abs2 = Math.abs(num2)
        val (smaller, larger) = if (abs1 <= abs2) abs1 to abs2 else abs2 to abs1
        return if (smaller == 0) larger else gcd(smaller, larger % smaller)
    }

    private fun countMaxPoints(slope: Rational, points: Set<Point>): Int {
        if (slope == Rational.ZERO) return countMaxPointsOfZeroSlope(points)
        if (slope == Rational.INF) return countMaxPointsOfInfSlope(points)

        val pointList = points.toList()
        var maxPoint = 0

        for (i in 0 until pointList.lastIndex) {
            var currMaxPoints = 1
            for (j in i + 1 until pointList.size) {
                val point1 = pointList[i]
                val point2 = pointList[j]
                val currSlope = calculateSlope(point1, point2)
                if (currSlope == slope) currMaxPoints++
            }
            maxPoint = maxOf(maxPoint, currMaxPoints)
        }
        return maxPoint
    }

    private fun countMaxPointsOfZeroSlope(points: Set<Point>): Int {
        return points
            .groupingBy { it.y }
            .eachCount()
            .values
            .max()!!
    }

    private fun countMaxPointsOfInfSlope(points: Set<Point>): Int {
        return points
            .groupingBy { it.x }
            .eachCount()
            .values
            .max()!!
    }
}
