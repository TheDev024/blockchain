class Block(
    private val id: Int = 1,
    private val previousHash: String = "0",
    private val timeStamp: Long = System.currentTimeMillis(),
) {
    private val magicNumber: Int
    private val hash: String
    private val generatingSeconds: Long

    init {
        var testNumber: Int
        var testHash: String
        val startToGenerate = System.currentTimeMillis()
        do {
            testNumber = random.nextInt()
            testHash = applySha256("$id$previousHash$timeStamp$testNumber")
        } while (testHash.substring(0 until proofNumber).any { it != '0' })
        val generated = System.currentTimeMillis()
        this.magicNumber = testNumber
        this.hash = testHash
        this.generatingSeconds = generated - startToGenerate
    }

    fun nextBlock(): Block = Block(
        this.id + 1,
        this.hash
    )

    @Suppress("Unused")
    fun validateChain(): Boolean {
        TODO("Validate the current chain")
    }

    override fun toString(): String = "Block:\n" +
            "Id: $id\n" +
            "Timestamp: $timeStamp\n" +
            "Magic number: $magicNumber\n" +
            "Hash of the previous block:\n$previousHash\n" +
            "Hash of the block:\n$hash\n" +
            "Block was generating for $generatingSeconds seconds"
}
