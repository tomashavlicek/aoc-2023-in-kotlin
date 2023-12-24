fun main() {
    fun part1(input: List<String>): Int {
        val time = input[0].removePrefix("Time: ").split(" ").filterNot { it.isBlank() }.map { it.toInt() }
        val distance = input[1].removePrefix("Distance: ").split(" ").filterNot { it.isBlank() }.map { it.toInt() }
        val timesToDistances: List<Pair<Int, Int>> = time.zip(distance)

        var result = 1
        timesToDistances.forEach {
            var better = 0
            for (holding in 1 .. it.first) {
                val traveling = it.first - holding
                if (traveling * holding > it.second) {
                    better += 1
                }
            }
            result *= better
        }

        return result
    }

    fun part2(input: List<String>): Int {
        val time = input[0].removePrefix("Time: ").filter { it.isDigit() }.toLong()
        val distance = input[1].removePrefix("Distance: ").filter { it.isDigit() }.toLong()

        var better = 0
        for (holding in 1 .. time) {
            val traveling = time - holding
            if (traveling * holding > distance) {
                better += 1
            }
        }

        return better
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
//    check(part1(testInput) == 288)
    check(part2(testInput) == 71503)

    val input = readInput("Day06")
//    part1(input).println()
    part2(input).println()
}
