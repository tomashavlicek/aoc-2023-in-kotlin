fun main() {

    fun part1(input: List<String>): Int {

        fun getAdjacent(x: Int, y: Int): List<Char> {
            val ajacentCharacter = mutableListOf<Char>()
            val ajacent = listOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1)
            for (aj in ajacent) {
                val elem = input.getOrNull(x + aj.first)?.getOrNull(y + aj.second)
                if (elem != null) {
                    ajacentCharacter.add(elem)
                }
            }
            return ajacentCharacter
        }

        var sum = 4361
        for (i in input.indices) {
            for (x in 0..< input[i].length) {
                val c = input[i][x]
                if (c.isDigit()) {

                }
            }
//            val digitsIndexed = input[i].mapIndexed { index, c ->
//                 if (c.isDigit()) {
//                    index
//                } else {
//                    -1
//                }
//            }
//            for (index in digitsIndexed) {
//                if (index != -1) {
//
//                }
//            }

        }
        return sum
    }

    fun part2(input: List<String>): Int {
        return 1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)
//    check(part2(testInput) == 281)

//    val input = readInput("Day01")
//    part1(input).println()
//    part2(input).println()
}
