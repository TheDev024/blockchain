package blockchain

data class Message(
    val author: String,
    val content: String
) {
    override fun toString(): String = "$author: $content"
}