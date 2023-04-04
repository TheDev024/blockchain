package blockchain

import kotlin.concurrent.thread

class Blockchain {
    @Volatile
    private var chain: Array<Block> = arrayOf()

    private val miners = List(10) { Miner(it + 1) }

    private fun generateBlock() {
        miners.forEach { miner ->
            thread(isDaemon = true) {
                val previousBlock = chain.lastOrNull()
                synchronized(this) {
                    val generatedBlock = miner.mine(previousBlock)
                    if ((chain.isEmpty() && generatedBlock.previousHash == "0") || chain.last().hash == generatedBlock.previousHash) {
                        chain += generatedBlock
                    }
                }
            }
        }

        val currentSize = chain.size

        while (true) if (chain.size != currentSize) break
    }

    fun generateChain(blockCount: Int) {
        repeat(blockCount) {
            generateBlock()
        }
    }

    fun printChain() = println(chain.joinToString("\n\n", "\n"))
}
