fun main() {
    fun part1(input: List<String>): Int {
        var totalPoints = 0
        input.forEach {
            val numbers = it.split('|')
            val winningNumbers = numbers[0].split(" ").drop(2).filter { it.isNotEmpty() }
            val myNumbers = numbers[1].split(" ").filter { it.isNotEmpty() }
            val myWinningNumbers = winningNumbers.intersect(myNumbers)
            // 1, 1, 2, 4, 8, 16
            var points = 0
            myWinningNumbers.forEachIndexed { index, s ->
                if (index == 0) {
                    totalPoints += 1
                    points += 1
                } else {
                    totalPoints += points
                    points += points
                }
            }


        }
        return totalPoints
    }


    fun part2(input: List<String>): Int {
        var totalCards = 0
        val copies = HashMap<Int, Int>()
        input.forEachIndexed { index, s ->
            repeat(1 + copies.getOrDefault(index + 1, 0)) {
                val numbers = s.split('|')
                val winningNumbers = numbers[0].split(" ").drop(2).filter { it.isNotEmpty() }
                val myNumbers = numbers[1].split(" ").filter { it.isNotEmpty() }
                val myWinningNumbers = winningNumbers.intersect(myNumbers)
                for (i in 1..myWinningNumbers.size) {
                    val key = index + 1 + i
                    if (copies[key] == null) {
                        copies[key] = 1
                    } else {
                        copies[key] = copies[key]!! + 1
                    }
                }
                totalCards += 1
            }
        }
        return totalCards
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
//    check(part1(testInput) == 13)
    check(part2(testInput) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
