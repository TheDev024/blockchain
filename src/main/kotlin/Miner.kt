package blockchain

class Miner(private val minerNo: Int) {
    fun mine(previousBlock: Block?, messages: List<Message>): Block = previousBlock?.nextBlock(minerNo, messages = messages) ?: Block(minerNo)
}
