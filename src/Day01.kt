fun String.firstDigit(): String {
    for (c in this) {
        if (c.isDigit()) {
            return c.toString()
        }
    }
    return ""
}

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf {
            (it.firstDigit() + it.reversed().firstDigit()).toInt()
        }
    }

    val numbers = hashMapOf(
        "one" to '1',
        "two" to '2',
        "three" to '3',
        "four" to '4',
        "five" to '5',
        "six" to '6',
        "seven" to '7',
        "eight" to '8',
        "nine" to '9',
    )
    val spelledNumbers = numbers.keys

    fun part2(input: List<String>): Int {
        return input.map { line ->
            val l = line.toMutableList()
            val first = line.findAnyOf(spelledNumbers)
            val last = line.findLastAnyOf(spelledNumbers)
            if (first != null) {
                l.add(first.first, numbers[first.second]!!)
            }
            if (last != null) {
                l.add(last.first + 1, numbers[last.second]!!)
            }
            l.toString()
        }.sumOf {
            (it.firstDigit() + it.reversed().firstDigit()).toInt()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 142)
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
