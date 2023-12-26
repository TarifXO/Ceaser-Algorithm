fun caesarCipher(str: String, k: Int, status: String): String {
    val lowercase = "abcdefghijklmnopqrstuvwxyz".toCharArray()
    val uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
    val space = " ".toCharArray()
    val n = when {
        str.all { it.isLowerCase() } -> lowercase
        str.all { it.isUpperCase() } -> uppercase
        str.all { it.isWhitespace() } -> space
        str.all { it.isLowerCase() || it.isWhitespace() } -> space + lowercase
        str.all { it.isUpperCase() || it.isWhitespace() } -> space + uppercase
        str.all { it.isLowerCase() || it.isUpperCase() } -> lowercase + uppercase
        else -> space + lowercase + uppercase
    }
    var result = ""
    for (char in str) {
        val p = n.indexOf(char)
        val d = alphabetd.indexOf(char)
        if (p == -1) {
            result += char
            continue
        }
        if (status == "encrypt") {
            val c = (p + k) % n.size
            result += n[c]
        }
        if (status == "decrypt") {
            val c = (d - k + alphabetd.size) % alphabetd.size
            result += alphabetd[c]
        }
    }
    return result
}
private var alphabetd : CharArray = charArrayOf()

fun alphapetOfDecrypt(option : String) {
    var alphabet: CharArray = charArrayOf()
    when (option) {
        1.toString() -> alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray()
        2.toString() -> alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
        3.toString() -> alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
        4.toString() -> alphabet = " abcdefghijklmnopqrstuvwxyz".toCharArray()
        5.toString() -> alphabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
        6.toString() -> alphabet = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
        else -> println("Invalid option.")
    }
    alphabetd = alphabet
}

fun main() {
    println("Enter the text:")
    var inputText = readlnOrNull()?.takeWhile { it.isLetter() || it.isWhitespace() } ?: ""
    while (inputText.isBlank()) {
        println("Invalid input. Please enter valid text.")
        println("Enter the text:")
        inputText = readlnOrNull()?.takeWhile { it.isLetter() || it.isWhitespace() } ?: ""
    }
    var keyValue: Int? = null
    while (keyValue == null) {
        print("Enter the key value (an integer): ")
        keyValue = readlnOrNull()?.toIntOrNull()
        if (keyValue == null) {
            println("Invalid input. Please enter a valid integer.")
        }
    }
    println("Choose operation (encrypt/decrypt):")
    val operation = readlnOrNull() ?: ""
    if (operation == "decrypt") {
        println("Choose Number of the Following:")
        println("1) LowerCase ")
        println("2) UpperCase ")
        println("3) Lower + UpperCase ")
        println("4) Space + LowerCase ")
        println("5) Space + UpperCase ")
        println("6) Space + LowerCase + UpperCase ")
        val option = readlnOrNull()
        alphapetOfDecrypt(option.toString())
    }
    val result = caesarCipher(inputText, keyValue, operation)
    println("Result: $result")
}