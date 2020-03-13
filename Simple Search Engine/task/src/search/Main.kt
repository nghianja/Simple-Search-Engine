package search

import java.io.File

fun main(args: Array<String>) {
    val data =
        if (args.size == 2 && args[0] == "--data") importData(args[1])
        else enterData()
    val index = createInvertedIndex(data)
    loop@ while (true) {
        println("=== Menu ===")
        println("1. Find a person")
        println("2. Print all people")
        println("0. Exit")
        when (readLine()!!.toInt()) {
            1 -> findPerson(data, index)
            2 -> printAll(data)
            0 -> break@loop
            else -> println("Incorrect option! Try again.")
        }
        println()
    }
    println("Bye!")
}

private fun printAll(data: List<String>) {
    println()
    println("=== List of people ===")
    data.forEach { println(it) }
}

private fun printFound(data: List<String>, indices: List<Int>) {
    if (indices.isEmpty()) {
        println("No matching people found.")
    } else {
        println("${indices.size} persons found:")
        indices.forEach {
            println(data[it])
        }
    }
}

private fun findPerson(data: List<String>, index: Map<String, MutableList<Int>>) {
    println()
    println("Select a matching strategy: ALL, ANY, NONE")
    val strategy = readLine()!!
    println()
    println("Enter a name or email to search all suitable people.")
    val search = readLine()!!
    println()
    val terms = search.split(" ")
    val list = MutableList(data.size) { it }
    when (strategy) {
        "ALL" -> {
            terms.forEach {
                index[it.toLowerCase()]?.let { it1 -> list.retainAll(it1) }
            }
        }
        "ANY" -> {
            list.clear()
            terms.forEach {
                index[it.toLowerCase()]?.let { it1 -> list.addAll(it1) }
            }
            println(list)
        }
        "NONE" -> {
            terms.forEach {
                index[it.toLowerCase()]?.let { it1 -> list.removeAll(it1) }
            }
        }
    }
    printFound(data, list)
}

private fun createInvertedIndex(data: List<String>): Map<String, MutableList<Int>> {
    val invertedIndex = mutableMapOf<String, MutableList<Int>>()
    data.forEachIndexed {
        index, line -> run {
            val tokens = line.split(" ")
            tokens.forEach {
                val list = invertedIndex.getOrDefault(it.toLowerCase(), mutableListOf())
                list.add(index)
                invertedIndex[it.toLowerCase()] = list
            }
        }
    }
    return invertedIndex
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
