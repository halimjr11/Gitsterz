package com.nurhaqhalim.gitsterz.view.settings.preference

import android.content.Context
import androidx.core.content.edit
import com.nurhaqhalim.gitsterz.core.data.source.remote.response.AlarmModel

class Reminder(context: Context) {
    companion object{
        const val preferenceName = "name_preference"
        private const val Reminder = "isRemind"
    }
    private val remindPreference = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)

    fun setAlarm(value: AlarmModel){
        remindPreference.edit {
            putBoolean(Reminder, value.isRemind)
        }

    }

    fun getAlarm(): AlarmModel {
        val remind = AlarmModel()
        remind.isRemind = remindPreference.getBoolean(Reminder, false)
        return remind
    }
}