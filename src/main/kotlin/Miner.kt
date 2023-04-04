package blockchain

class Miner(private val minerNo: Int) {
    fun mine(previousBlock: Block?): Block = previousBlock?.nextBlock(minerNo) ?: Block(minerNo)
}
