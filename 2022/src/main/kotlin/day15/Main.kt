package day15

import util.FileUtils
import kotlin.math.abs

private const val inputName = "day15_input.txt"

data class Point(
    var x: Int,
    var y: Int
)

fun Point.manhattanDistance(other: Point): Int {
    return abs(this.x - other.x) + abs(this.y - other.y)
}

private fun getPointsWithinManhattanDistanceOnRow(point: Point, distance: Int, row: Int): List<Point> {
    val res = mutableListOf<Point>()
    var width = 0
    for (i in point.y-distance..point.y+distance) {
        if (i == row) {
            for (j in point.x - width..point.x + width) {
                res.add(Point(j, i))
            }
        }

        width += if (i < point.y) 1 else -1
    }
    return res
}

private fun getPointsWithinManhattanDistance(point: Point, distance: Int, topLeft: Point, bottomRight: Point): List<Point> {
    val res = mutableListOf<Point>()
    var width = 0

    if (point.y-distance < topLeft.y) return emptyList()
    if (point.y+distance > bottomRight.y) return emptyList()

    for (i in point.y-distance..point.y+distance) {
        if (i < topLeft.y) continue
        if (i > bottomRight.y) break

        if (point.x + width < topLeft.x) continue
        if (point.x - width > bottomRight.x) continue

        for (j in point.x - width..point.x + width) {
            if (j < topLeft.x) continue
            if (j > bottomRight.x) break
            res.add(Point(j, i))
        }

        width += if (i < point.y) 1 else -1
    }
    return res
}


private fun parse(): Pair<List<Point>, List<Point>> {
    val lines = FileUtils.readLines(inputName)
    val sensors = mutableListOf<Point>()
    val beacons = mutableListOf<Point>()

    lines.forEach {
        val parts = it.split(":")
        val sensorParts = parts[0].replace("Sensor at ", "").split(", ").map { p -> p.split("=") }
        val sensor = Point(sensorParts[0][1].toInt(), sensorParts[1][1].toInt())
        sensors.add(sensor)

        val beaconParts = parts[1].split("is at ")[1].split(", ").map { p -> p.split("=") }
        val beacon = Point(beaconParts[0][1].toInt(), beaconParts[1][1].toInt())
        beacons.add(beacon)
    }

    return Pair(sensors, beacons)
}

// https://adventofcode.com/2022/day/15
fun partOne() {
    val (sensors, beacons) = parse()
    val invalidPoints = mutableSetOf<Point>()
    val row = 2000000

    for (i in sensors.indices) {
        val sensor = sensors[i]
        val beacon = beacons[i]

        val points = getPointsWithinManhattanDistanceOnRow(sensor, sensor.manhattanDistance(beacon), row)
        for (point in points) {
            invalidPoints.add(point)
        }
    }

    println("Number of positions that cannot contain a beacon: ${invalidPoints.size - beacons.toSet().filter { it.y == row }.size}")
}

// https://adventofcode.com/2022/day/15#part2
fun partTwo() {
    var frequency = 0
    val (sensors, beacons) = parse()
    val invalidPoints = mutableSetOf<Point>()

    for (i in sensors.indices) {
        val sensor = sensors[i]
        val beacon = beacons[i]

        val distance = sensor.manhattanDistance(beacon)
        val points = getPointsWithinManhattanDistance(sensor, distance, Point(0, 0), Point(4000000, 4000000))
        for (point in points) {
            invalidPoints.add(point)
        }
    }

    println("Tuning frequency of distress beacon is: $frequency")
}

fun main() {
    partOne()
    partTwo()
}