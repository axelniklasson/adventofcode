package day9

import util.FileUtils
import kotlin.math.abs

private const val inputName = "day9_input.txt"

data class Coordinate(
    var x: Int,
    var y: Int
) {
    fun move(direction: Motion.Direction) {
        when (direction) {
            Motion.Direction.LEFT -> this.x -= 1
            Motion.Direction.RIGHT -> this.x += 1
            Motion.Direction.UP -> this.y -= 1
            Motion.Direction.DOWN -> this.y += 1
            Motion.Direction.UP_RIGHT -> {
                this.y -= 1
                this.x += 1
            }
            Motion.Direction.DOWN_RIGHT -> {
                this.y += 1
                this.x += 1
            }
            Motion.Direction.DOWN_LEFT -> {
                this.y += 1
                this.x -= 1
            }
            Motion.Direction.UP_LEFT -> {
                this.y -= 1
                this.x -= 1
            }
        }
    }

    override fun toString(): String {
        return "($x, $y)"
    }
}

data class Motion(
    val direction: Direction,
    val count: Int,
) {
    enum class Direction {
        // grid directions
        LEFT,
        RIGHT,
        UP,
        DOWN,
        // diagonal directions
        UP_RIGHT,
        DOWN_RIGHT,
        DOWN_LEFT,
        UP_LEFT
    }
}

private fun parseMotions(lines: List<String>): List<Motion> {
    return lines.map {
        val parts = it.split(" ")
        Motion(
            when (parts[0]) {
                "L" -> Motion.Direction.LEFT
                "R" -> Motion.Direction.RIGHT
                "U" -> Motion.Direction.UP
                "D" -> Motion.Direction.DOWN
                else -> error("invalid direction")
            },
            parts[1].toInt(),
        )
    }
}

private fun isAdjacent(head: Coordinate, tail: Coordinate): Boolean {
    return abs(head.x - tail.x) < 2 && abs(head.y - tail.y) < 2
}

// https://adventofcode.com/2022/day/9
fun partOne() {

    val lines = FileUtils.readLines(inputName)
    val motions = parseMotions(lines)

    var head = Coordinate(0, 0)
    var tail = Coordinate(0, 0)
    val visitedCoordinates = HashSet<String>()
    visitedCoordinates.add(tail.toString())

    motions.forEach {
        for (i in 0 until it.count) {
            head.move(it.direction)
            if (!isAdjacent(head, tail)) {
                // need to move tail
                if (head.y == tail.y) {
                    // head and tail are in same row
                    tail.move(if (head.x > tail.x) Motion.Direction.RIGHT else Motion.Direction.LEFT)
                } else if (head.x == tail.x) {
                    // head and tail are in same column
                    tail.move(if (head.y > tail.y) Motion.Direction.DOWN else Motion.Direction.UP)
                } else {
                    // need to move tail diagonally
                    if (head.y < tail.y) {
                        // head is above
                        tail.move(if (head.x > tail.x) Motion.Direction.UP_RIGHT else Motion.Direction.UP_LEFT)
                    } else {
                        // head is below
                        tail.move(if (head.x > tail.x) Motion.Direction.DOWN_RIGHT else Motion.Direction.DOWN_LEFT)
                    }
                }
                visitedCoordinates.add(tail.toString())
            }
        }
    }

    println("${visitedCoordinates.size} positions visited at least once")
}

// https://adventofcode.com/2022/day/9#part2
fun partTwo() {
}

fun main() {
    partOne()
    partTwo()
}