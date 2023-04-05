package blockchain

import kotlin.concurrent.thread

class Blockchain {
    @Volatile
    private var chain: Array<Block> = arrayOf()

    @Volatile
    var messages: Array<Message> = arrayOf()

    private val miners = List(10) { Miner(it + 1) }

    private fun generateBlock() {
        val messageList: List<Message>

        synchronized(this) {
            messageList = messages.toList()
            messages = arrayOf()
        }

        miners.forEach { miner ->
            thread(isDaemon = true) {
                val previousBlock = chain.lastOrNull()
                synchronized(this) {
                    val generatedBlock = miner.mine(previousBlock, messageList)
                    if ((chain.isEmpty() && generatedBlock.previousHash == "0") || chain.last().hash == generatedBlock.previousHash) {
                        chain += generatedBlock
                    }
                }
            }
        }
    }

    fun generateChain(blockCount: Int) {
        val messenger = thread(start = false) {
            // synchronized(this) {
                while (!GENERATION_COMPLETE) {
                    messages += Message(
                        namesList.random(),
                        messagesList.random()
                    )
                    // Thread.sleep(200)
                }
            // }
        }

        repeat(blockCount) {
            generateBlock()
            if (it == 0) messenger.start()
            val currentSize = chain.size
            while (true) if (chain.size != currentSize) break
        }

        GENERATION_COMPLETE = true
    }

    fun printChain() = println(chain.joinToString("\n\n", "\n"))
}
