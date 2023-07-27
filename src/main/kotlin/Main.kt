const val version: String = "1.0"

val signs: Array<Char> = arrayOf('+', '-', '*', '/')
fun main() {
    println("hello and welcome to the calculator, version $version")
    val line = readln() + "="
    println("Starting solving")
    val data = validate(line)
    solve(data.first, data.second)
}

fun validate(line: String): Pair<MutableList<Any>, MutableList<Char>> {
    // TODO: add support for custom variables as x1 and other
    // What variables we need to find
    val amount = line.count { it == '=' }
    println("amount is $amount")
    val listOfAl = mutableListOf<MutableList<Any>>()
    val listOfVariables = mutableListOf<Char>()
    val transformed = mutableListOf<Any>()
    // Used for digits
    var currentStr = ""
    var currentChar = ' '
    // Remove all spaces and iterate on every char
    line.filter { it != ' ' }.forEach {
        // Check if it is digit
        if (it.isDigit()) {
            if (currentChar != ' ')
                fail()
            currentStr += it
            return@forEach
        }
        // Checks if it is a sign
        if (signs.contains(it)) {
            if (currentStr == "" && currentChar == ' ')
                fail()
            if (currentStr == "")
                transformed.add(currentChar)
            else
                transformed.add(currentStr)
            currentStr = ""
            currentChar = ' '
            transformed.add(it.toString())
            return@forEach
        }
        // Checks if it is a letter
        if (it.isLetter()) {
            if (currentStr != "" && currentChar != ' ')
                fail()
            currentChar = it
            listOfVariables.add(it)
            currentStr = it.toString()
            return@forEach
        }

        if (it == '=') {
            // it means, that last element was a sign
            if (currentStr == "" && currentChar == ' ')
                fail()
            if (currentStr == "")
                transformed.add(currentChar)
            else
                transformed.add(currentStr)
            currentStr = ""
            currentChar = ' '
            /*
            `.toMutableList()` is used for static reference to objects of `transformed`, so they don't change,
            when `transformed` does
             */
            listOfAl.add(transformed.toMutableList())
            transformed.clear()
            return@forEach
        }
        // smth wierd
        fail()
    }
    // it means, that last element was a sign
    println("ended validating, result:")
    listOfAl.forEach {
        it.forEach {
            println("$it with ${it::class.simpleName}")
        }
        println("end")
    }
    println("${listOfAl}")
    if (listOfVariables.isEmpty())
        nothingToSolve()
    return Pair(transformed, listOfVariables)
}

fun solve(transformed: MutableList<Any>, listOfVariables: MutableList<Char>) {
}

// Вы допустили потерю дорогостоящего оборудования, его стоимость будет вычета из вашей зарплаты
fun fail() {
    throw IllegalArgumentException("warning, you have made a mistake in inputs")
}

fun nothingToSolve() {
    throw IllegalArgumentException("Nothing to solve")
}