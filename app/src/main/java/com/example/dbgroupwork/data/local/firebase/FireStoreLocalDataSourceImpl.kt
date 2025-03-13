package com.example.dbgroupwork.Data.local.firebase

import com.example.dbgroupwork.Data.toDomain
import com.google.firebase.firestore.FirebaseFirestore
import com.example.dbgroupwork.data.FireStoreLocalDataSource
import com.example.dbgroupwork.Domain.Models.Comment
import com.example.dbgroupwork.data.local.firebase.CommentsData
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FireStoreLocalDataSourceImpl() : FireStoreLocalDataSource {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    override suspend fun getComments(): Flow<List<Comment>> = callbackFlow {
        val commentsRef = firestore.collection("comments")
        val listener = commentsRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                cancel()
            }

            val commentsList = snapshot?.documents?.mapNotNull {
                it.toObject(CommentsData::class.java)
            } ?: emptyList()
            trySend(commentsList.map { it.toDomain() })
        }

        awaitClose { listener.remove() }
    }

    override suspend fun addComment(comment: Comment) {
        val commentDb = hashMapOf(
            "author" to comment.author,
            "comment" to comment.comment,
        )
        firestore.collection("comments").add(commentDb).await()
    }
}