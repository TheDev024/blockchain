package blockchain

class Block(
    private val id: Int = 1,
    private val minerNo: Int = 1,
    private val proofNumber: Int = 0,
    val previousHash: String = "0",
    private val messages: List<Message> = emptyList(),
    private val timeStamp: Long = System.currentTimeMillis()
) {
    private val magicNumber: Int
    val hash: String
    private val generatingSeconds: Long
    private val proofNoChange: Int

    init {
        var testNumber: Int
        var testHash: String
        val startToGenerate = System.currentTimeMillis()
        do {
            testNumber = random.nextInt()
            testHash = applySha256("$minerNo$proofNumber$id$previousHash$timeStamp$testNumber")
        } while (testHash.substring(0 until proofNumber).any { it != '0' })
        val generated = System.currentTimeMillis()
        this.magicNumber = testNumber
        this.hash = testHash
        this.generatingSeconds = (generated - startToGenerate) / 1000
        this.proofNoChange = when {
            generatingSeconds < 5 -> 1

            generatingSeconds > 15 -> -1

            else -> 0
        }
    }

    fun nextBlock(minerNo: Int, messages: List<Message>): Block = Block(
        this.id + 1,
        minerNo,
        proofNumber + proofNoChange,
        this.hash,
        messages
    )

    override fun toString(): String = "Block:\n" +
            "Created by miner # $minerNo\n" +
            "Id: $id\n" +
            "Timestamp: $timeStamp\n" +
            "Magic number: $magicNumber\n" +
            "Hash of the previous block:\n$previousHash\n" +
            "Hash of the block:\n$hash\n" +
            "Block data: ${
                if (messages.isEmpty()) "no messages"
                else messages.joinToString("\n", "\n")
            }\n" +
            "Block was generating for $generatingSeconds seconds\n" +
            "N ${
                when (proofNoChange) {
                    -1 -> "was decreased by 1"

                    1 -> "was increased to ${proofNumber + 1}"

                    else -> "stays the same"
                }
            }"
}
