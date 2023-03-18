class Blockchain {
    private val chain: MutableList<Block> = mutableListOf()

    fun generateChain() {
        val firstBlock = Block()
        chain.add(firstBlock)

        repeat(4) { chain.add(chain.last().nextBlock()) }
    }

    fun printChain() = println(chain.joinToString("\n\n"))
}
