package com.example.dbgroupwork.Data

import com.example.dbgroupwork.Data.firebase.CommentsData
import com.example.dbgroupwork.Domain.Models.Comment

fun CommentsData.toDomain(): Comment {
    return Comment(
        id = id, comment = comment, author = author)
}