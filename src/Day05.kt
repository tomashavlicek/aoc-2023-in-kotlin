import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.io.path.readText

fun main() {
    fun part1(input: List<String>): Long {
        val seeds = input.first().removePrefix("seeds: ").split(" ").map { it.toLong() }.toMutableList()

        for (i in 1..<input.size) {
            val category = input[i].split("\n").drop(1)
            seed@ for (j in seeds.indices) {
                for (k in category.indices) {
                    val (destinationStart, sourceStart, range) = category[k].split(" ").map { it.toLong() }
                    val sourceRange = LongRange(sourceStart, sourceStart + range - 1)

                    if (seeds[j] in sourceRange) {
                        seeds[j] -= sourceStart - destinationStart
                        continue@seed
                    }
                }
            }
        }

        return seeds.min()
    }

    fun part2(input: List<String>): Long {
        val seedsInput = input.first().removePrefix("seeds: ").split(" ").map { it.toLong() }.windowed(size = 2, step = 2)
        val seeds: List<LongRange> = seedsInput.map {
            LongRange(it.first(), it.first() + it.last())
        }

        val categories: List<List<Pair<LongRange, LongRange>>> = input.drop(1).reversed().map { category: String ->
            category.split("\n").drop(1).map { categories: String ->
                val (destinationStart, sourceStart, range) = categories.split(" ").map { it.toLong() }
                LongRange(destinationStart, destinationStart + range - 1) to LongRange(sourceStart, sourceStart + range - 1)
            }
        }

        for (i in 0..Int.MAX_VALUE) {
            var possibleSeed = i.toLong()
            category@ for (j in categories.indices) {
                val mapping = categories[j]
                for (k in mapping.indices) {
                    val it = mapping[k]
                    if (possibleSeed in it.first) {
                        possibleSeed -= it.first.first - it.second.first
                        continue@category
                    }
                }
            }

            seeds.forEach {
                if (possibleSeed in it) {
                    return@part2 i.toLong()
                }
            }
        }

        return 0
    }

    // test if implementation meets criteria from the description, like:
//    val testInput =  Path("src/Day05_test.txt").readText().split("\n\n")
    val input =  Path("src/Day05.txt").readText().split("\n\n")

//    check(part1(testInput) == 35L)
//    check(part2(testInput) == 46L)

//    part1(input).println()
    part2(input).println()
}