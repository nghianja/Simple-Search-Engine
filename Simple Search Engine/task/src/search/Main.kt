package search

fun main() {
    val lines = readLine()!!.split(" ")
    val line = readLine()!!
    for (i in 1..lines.size)
        if (line == lines[i - 1]) {
            println(i)
            return
        }
    println("Not found")
}
