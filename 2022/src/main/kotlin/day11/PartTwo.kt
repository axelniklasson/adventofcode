package day11

import util.FileUtils
import java.math.BigInteger

private const val inputName = "day11_input_test.txt"

data class LongMonkey(
    var items: MutableList<BigInteger>,
    val operation: String,
    val test: Test,
) {
    data class Test(
        val divisibleBy: BigInteger,
        val ifTrue: Int,
        val ifFalse: Int
    )
}

private fun parseMonkeys(): MutableList<LongMonkey> {
    val lines = FileUtils.readLines(inputName)
    val monkeys = mutableListOf<LongMonkey>()

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
                .map { num -> num.trim().toBigInteger() }.toMutableList()
            i++
            val operation = lines[i].split("= old ")[1]
            i++
            val divisibleBy = lines[i].split("divisible by ")[1].toBigInteger()
            i++
            val ifTrue = lines[i].split("monkey ")[1].toInt()
            i++
            val ifElse = lines[i].split("monkey ")[1].toInt()
            monkeys.add(LongMonkey(items, operation, LongMonkey.Test(divisibleBy, ifTrue, ifElse)))
            i++
        } else {
            i++
        }
    }

    return monkeys
}

private fun eval(value: BigInteger, operation: String): BigInteger {
    val parts = operation.split(" ")
    val right = if (parts[1] == "old") value else BigInteger.valueOf(parts[1].toLong())
    return when (parts[0]) {
        "*" -> value * right
        "/" -> value / right
        "+" -> value + right
        "-" -> value - right
        else -> error(parts)
    }
}

// https://adventofcode.com/2022/day/11#part2
fun partTwo() {
    val monkeys = parseMonkeys()
    val inspectCount = monkeys.map { 0L }.toMutableList()

    for (i in 0 until 10000) {
        for (j in monkeys.indices) {
            val monkey = monkeys[j]
            for (item in monkey.items) {
                inspectCount[j] = inspectCount[j] + 1
                val newWorryLevel = eval(item, monkey.operation)
                if ((newWorryLevel.mod(monkey.test.divisibleBy)).equals(BigInteger.ZERO)) {
                    monkeys[monkey.test.ifTrue].items.add(newWorryLevel)
                } else {
                    monkeys[monkey.test.ifFalse].items.add(newWorryLevel)
                }
            }
            monkey.items = mutableListOf()
        }
        if (listOf(1, 20, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000).contains(i+1)) {
            println("round ${i+1} done, $inspectCount")
        }
    }

    val topInspected = inspectCount.sortedDescending().subList(0, 2)
    println("Level of monkey business is: ${topInspected[0] * topInspected[1]}")
}

fun main() {
    partTwo()
}