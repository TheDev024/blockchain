import java.security.MessageDigest
import java.util.Random

val random = Random()
var proofNumber = 2

fun main() {
    proofNumber = getInput("Enter how many zeros the hash must start with: ").toInt()
    val blockchain = Blockchain()
    blockchain.generateChain()
    blockchain.printChain()
}

fun getInput(message: String): String {
    print(message)
    return readln()
}

fun applySha256(input: String): String {
    return try {
        val digest = MessageDigest.getInstance("SHA-256")
        /* Applies sha256 to our input */
        val hash = digest.digest(input.toByteArray(charset("UTF-8")))
        val hexString = StringBuilder()
        for (elem in hash) {
            val hex = Integer.toHexString(0xff and elem.toInt())
            if (hex.length == 1) hexString.append('0')
            hexString.append(hex)
        }
        hexString.toString()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}
