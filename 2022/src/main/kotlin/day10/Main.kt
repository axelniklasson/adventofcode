package day10

import util.FileUtils
import kotlin.math.abs

private const val inputName = "day10_input.txt"

enum class Type {
    NO_OP,
    ADD_X
}

data class Instruction(
    val type: Type,
    val value: Int? = null
)

private fun parseInstructions(): List<Instruction> {
    return FileUtils.readLines(inputName).map {
        val parts = it.split(" ")
        if (parts[0] == "noop") {
            Instruction(Type.NO_OP)
        } else {
            Instruction(Type.ADD_X, parts[1].toInt())
        }
    }
}

// https://adventofcode.com/2022/day/10
fun partOne() {
    val instructions = parseInstructions()
    var sum = 0
    var xValue = 1

    var cycles = 0
    instructions.forEach { instruction ->
        if (cycles + 1 == 20 || (cycles + 20 + 1) % 40 == 0) {
            sum += ((cycles + 1) * xValue)
        }

        if (instruction.type == Type.NO_OP) {
            cycles++
        } else {
            cycles++
            if (cycles + 1 == 20 || (cycles + 20 + 1) % 40 == 0) {
                sum += ((cycles + 1) * xValue)
            }
            cycles++
            xValue += instruction.value!!
        }
    }

    println("Sum of signal strengths: $sum")
}

// https://adventofcode.com/2022/day/10#part2
fun partTwo() {
    val instructions = parseInstructions()
    var spriteCenter = 1
    var cycles = 0

    var running = ""
    fun render() {
        running += if (abs(spriteCenter - (cycles % 40)) < 2) "#" else "."

        if ((cycles + 1) % 40 == 0) {
            println(running)
            running = ""
        }
    }

    instructions.forEach { instruction ->
        if (instruction.type == Type.NO_OP) {
            render()
            cycles++
        } else {
            render()
            cycles++

            render()
            cycles++
            spriteCenter += instruction.value!!
        }
    }
}

fun main() {
    partOne()
    partTwo()
}