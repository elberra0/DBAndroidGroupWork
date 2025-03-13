package com.example.dbgroupwork.Data

import com.example.dbgroupwork.data.local.room.PlanLocal
import com.example.dbgroupwork.Domain.Models.Plan

import com.example.dbgroupwork.Domain.Models.Comment
import com.example.dbgroupwork.data.local.firebase.CommentsData

fun CommentsData.toDomain(): Comment {
    return Comment(
        id = id, comment = comment, author = author)
}


fun Plan.toPlanLocal():PlanLocal {
    return  PlanLocal(
        id = id,
        clasificacionid = clasificacionid,
        clasificacion = clasificacion
    )
}

fun PlanLocal.toPlan():Plan {
    return  Plan(
        id = id,
        clasificacionid = clasificacionid,
        clasificacion = clasificacion
    )
}