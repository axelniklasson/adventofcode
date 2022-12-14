package day11

import util.FileUtils
import kotlin.math.floor

private const val inputName = "day11_input.txt"

data class Monkey(
    var items: MutableList<Int>,
    val operation: String,
    val test: Test,
) {
    data class Test(
        val divisibleBy: Int,
        val ifTrue: Int,
        val ifFalse: Int
    )
}

private fun parseMonkeys(): MutableList<Monkey> {
    val lines = FileUtils.readLines(inputName)
    val monkeys = mutableListOf<Monkey>()

    var i = 0
    while (i < lines.size) {
        if (lines[i].isBlank()) {
            i++
            continue
        }
        if (lines[i].trim().startsWith("Monkey")) {
            i++
            val items = lines[i]
                .split("Starting items: ").first { it.isNotBlank() }
                .split(',')
                .map { num -> num.trim().toInt() }.toMutableList()
            i++
            val operation = lines[i].split("= old ")[1]
            i++
            val divisibleBy = lines[i].split("divisible by ")[1].toInt()
            i++
            val ifTrue = lines[i].split("monkey ")[1].toInt()
            i++
            val ifElse = lines[i].split("monkey ")[1].toInt()
            monkeys.add(Monkey(items, operation, Monkey.Test(divisibleBy, ifTrue, ifElse)))
            i++
        } else {
            i++
        }
    }

    return monkeys
}

private fun eval(value: Double, operation: String): Double {
    val parts = operation.split(" ")
    val right = if (parts[1] == "old") value else parts[1].toDouble()
    return when (parts[0]) {
        "*" -> value * right
        "/" -> value / right
        "+" -> value + right
        "-" -> value - right
        else -> error(parts)
    }
}

// https://adventofcode.com/2022/day/11
fun partOne() {
    val monkeys = parseMonkeys()
    val inspectCount = monkeys.map { 0 }.toMutableList()

    for (i in 0 until 20) {
        for (j in monkeys.indices) {
            val monkey = monkeys[j]
            for (item in monkey.items) {
                inspectCount[j]++
                val newWorryLevel = floor(eval(item.toDouble(), monkey.operation) / 3).toInt()
                if (newWorryLevel % monkey.test.divisibleBy == 0) {
                    monkeys[monkey.test.ifTrue].items.add(newWorryLevel)
                } else {
                    monkeys[monkey.test.ifFalse].items.add(newWorryLevel)
                }
            }
            monkey.items = mutableListOf()
        }
    }

    val topInspected = inspectCount.sortedDescending().subList(0, 2)
    println("Level of monkey business is: ${topInspected[0] * topInspected[1]}")
}

fun main() {
    partOne()
    partTwo()
}