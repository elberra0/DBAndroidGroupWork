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

    //override suspend fun addComment(comment: Comment) {
    //    val commentData = hashMapOf(
    //        "comment" to comment.comment,
    //        //"rating" to comment.rating,
    //        "author" to comment.author,
    //    )
    //    firestore
    //        //.collection("monuments")
    //        //.document(comment.monumentId.toString())
    //        .collection("comments")
    //        .add(commentData)
    //}


}