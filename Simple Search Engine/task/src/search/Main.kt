package search

fun main() {
    println("Enter the number of people:")
    var number = readLine()!!.toInt()
    val data = arrayOfNulls<String>(number)
    println("Enter all people:")
    for (i in 1..number) {
        data[i - 1] = readLine()!!
    }
    println("Enter data to search people:")
    number = readLine()!!.toInt()
    for (i in 1..number) {
        var found = false
        val search = readLine()!!
        for (j in data.indices) {
            if (data[j]!!.contains(search, true)) {
                if (!found) {
                    found = true
                    println("Found people:")
                }
                println(data[j])
            }
        }
        if (!found)
        println("No matching people found.")
    }
}
