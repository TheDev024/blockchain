package blockchain

import java.util.*

const val CHAIN_LENGTH = 5
var GENERATION_COMPLETE = false
val random = Random()

fun main() {
    val blockchain = Blockchain()
    blockchain.generateChain(CHAIN_LENGTH)
    blockchain.printChain()
}
