package blockchain

import java.security.MessageDigest
import java.util.*

val random = Random()

fun main() {
    val blockchain = Blockchain()
    blockchain.generateChain(5)
    blockchain.printChain()
}

fun applySha256(input: String): String {
    return try {
        val digest = MessageDigest.getInstance("SHA-256")
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
