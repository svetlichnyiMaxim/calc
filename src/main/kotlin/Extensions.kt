import kotlin.math.absoluteValue

val <Any> MutableList<Any>.simplify: Unit
    get() {
        var sum = 0.0
        this.forEachIndexed { index, value ->
            if (value is String && this[index + 1] is String) {
                val converted = value.toString()
                if (converted == "+" || converted == "-") {
                    if (!this[index + 1].toString().isVar()) {
                        sum += this[index + 1].toString().toInt()
                        this.removeAt(index.absoluteValue + 1)
                        this.removeAt(index.absoluteValue)
                    }
                }
            }
        }
        //this.add(sum)
    }

fun String.isVar(): Boolean {
    if (this.length == 1) {
        if (this.single().isLetter()) {
            return true
        }
    }
    return false
}
