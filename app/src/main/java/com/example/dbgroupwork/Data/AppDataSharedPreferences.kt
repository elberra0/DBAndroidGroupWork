package com.example.dbgroupwork.data

import android.content.Context
import android.content.SharedPreferences

class AppDataSharedPreferences {
    fun savePlan(context: Context, planId: Int) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("planId", Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt ("planId", planId).apply()
    }

    fun getPlan(context: Context): Int {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("planId", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("planId", 0)
    }
}
