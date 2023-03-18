class Block(
    private val id: Int = 1,
    private val previousHash: String = "0",
    private val timeStamp: Long = System.currentTimeMillis(),
) {
    private val hash: String = applySha256("$id$timeStamp$previousHash")

    fun nextBlock(): Block = Block(
        this.id + 1,
        this.hash
    )

    fun validateChain(): Boolean {
        TODO("Validate the current chain")
    }

    override fun toString(): String = "Block:\n" +
            "Id: $id\n" +
            "Timestamp: $timeStamp\n" +
            "Hash of the previous block:\n$previousHash\n" +
            "Hash of the block:\n$hash"
}
