package com.example.dbgroupwork.Data.local.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.example.dbgroupwork.Data.FireStoreLocalDataSource
import com.example.dbgroupwork.Data.toDomain
import com.example.dbgroupwork.Domain.Models.Comment

import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FireStoreLocalDataSourceImpl(private val firestore: FirebaseFirestore) : FireStoreLocalDataSource {

    override fun getComments(monumentId: Long): Flow<List<Comment>> {
        return callbackFlow {

            val commentRef = firestore.collection("monuments")
                .document(monumentId.toString())
                .collection("reviews")


            val listener = commentRef.addSnapshotListener { snapshot, error ->

                if (error != null) {
                    // An error occurred
                    cancel()
                }


                val reviewsList = snapshot?.documents?.mapNotNull {
                    it.toObject(CommentsData::class.java)
                } ?: emptyList()
                trySend(reviewsList.map { it.toDomain() })
            }



            awaitClose { listener.remove() }
        }
    }

    override suspend fun addComment(comment: Comment) {
        val commentData = hashMapOf(
            "comment" to comment.comment,
            //"rating" to comment.rating,
            "author" to comment.author,
        )
        firestore
            //.collection("monuments")
            //.document(comment.monumentId.toString())
            .collection("comments")
            .add(commentData)
    }
}