package com.polendina.soai.models

data class Question (
    val Markdown: String,
    val Citations: List<Answer>,
    val Confidence: Double,
    val Html: String,
    val Error: Boolean,
    val CanBeAskedOnStackOverflowPage: Boolean,
    val Id: Long,
    val AIChatSessionId: Long,
    val StepNum: Int,
    val CreationDate: String,
    val Role: Int,
    val AuthorType: Int,
    val MessageType: Int,
    val IsHidden: Boolean,
    val IsAI: Boolean,
    val IsUser: Boolean,
    val IsRegenerated: Boolean,
    val User_AIChatMessageId: Long,
    val Assistant_AIChatMessageId: Long,
    val AllowLeavingFeedback: Boolean
) {
    data class Answer (
        val id: Long,
        val creationDate: String,
        val age: String,
        val score: Long,
        val url: String,
        val owner: Owner?,
        val accepted: Boolean?,
        val parent: Parent?,
        val title: String?,
        val certainty: Double?,
        val bodyMarkdown: String?,
        val postType: Long,
        val order: Long
    ) {
        data class Owner (
            val id: Long,
            val displayName: String,
            val profileImageUrl: String,
            val url: String,
            val reputation: Long,
            val isDeleted: Boolean,
            val postType: Int?,
            val order: Int?
        )
        data class Parent (
            val id: Long?,
            val creationDate: String?,
            val age: String?,
            val score: Long,
            val url: String?,
        )
    }
}