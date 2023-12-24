data class Game(val id: Int, val sets: List<Set>)
data class Set(val cubes: List<Cube>)
data class Cube(val count: Int, val color: Color)
enum class Color { blue, green, red }

fun main() {

    fun List<String>.toGame() : List<Game> {
        return this.map {
            val gameSetStrings = it.split(":")
            val gameId = gameSetStrings[0].split(' ')[1].toInt()
            val sets = gameSetStrings[1].split(';').map {
                val cubesList = it.split(',').map {
                    val cubeString = it.split(' ')
                    Cube(count = cubeString[1].toInt(), color = Color.valueOf(cubeString[2]))
                }
                Set(cubes = cubesList)
            }
            Game(id = gameId, sets = sets)
        }
    }

    fun part1(input: List<Game>): Int {
        return input.filter {
            it.sets.all {
                it.cubes.all {
                    when(it.color) {
                        Color.blue -> it.count <= 14
                        Color.green -> it.count <= 13
                        Color.red -> it.count <= 12
                    }
                }
            }
        }.sumOf { it.id }
    }

    fun part2(input: List<Game>): Int {
        return input.map {
            val allCubes = mutableListOf<Cube>()
            it.sets.forEach { allCubes.addAll( it.cubes) }
            val red = allCubes.filter { it.color == Color.red }.maxBy { it.count }
            val green = allCubes.filter { it.color == Color.green }.maxBy { it.count }
            val blue = allCubes.filter { it.color == Color.blue }.maxBy { it.count }
            red.count * green.count * blue.count
        }.sumOf { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test").toGame()
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02").toGame()
    part1(input).println()
    part2(input).println()
}