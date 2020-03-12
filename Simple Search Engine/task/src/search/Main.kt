package search

fun main() {
    val data = enterData()
    println()
    loop@ while (true) {
        println("=== Menu ===")
        println("1. Find a person")
        println("2. Print all people")
        println("0. Exit")
        println()
        when (readLine()!!.toInt()) {
            1 -> findPerson(data)
            2 -> printAll(data)
            0 -> break@loop
            else -> println("Incorrect option! Try again.")
        }
    }
    println("Bye!")
}

private fun printAll(data: Array<String?>) {
    println("=== List of people ===")
    data.forEach { println(it) }
}

private fun findPerson(data: Array<String?>) {
    println("Enter a name or email to search all suitable people.")
    val search = readLine()!!
    data.forEach {
        if (it!!.contains(search, true)) {
            println(it)
        }
    }
}

private fun enterData(): Array<String?> {
    println("Enter the number of people:")
    val number = readLine()!!.toInt()
    val data = arrayOfNulls<String>(number)
    println("Enter all people:")
    for (i in 1..number) {
        data[i - 1] = readLine()!!
    }
    return data
}
