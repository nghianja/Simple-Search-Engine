package search

import java.io.File

fun main(args: Array<String>) {
    val data =
        if (args.size == 2 && args[0] == "--data") importData(args[1])
        else enterData()
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

private fun printAll(data: List<String>) {
    println("=== List of people ===")
    data.forEach { println(it) }
}

private fun findPerson(data: List<String>) {
    println("Enter a name or email to search all suitable people.")
    val search = readLine()!!
    data.forEach {
        if (it!!.contains(search, true)) {
            println(it)
        }
    }
}

private fun enterData(): List<String> {
    println("Enter the number of people:")
    val number = readLine()!!.toInt()
    val data = mutableListOf<String>()
    println("Enter all people:")
    for (i in 1..number) {
        data.add(readLine()!!)
    }
    return data
}

private fun importData(filename: String): List<String> {
    val data = mutableListOf<String>()
    File(filename).forEachLine {
        data.add(it)
    }
    return data
}
