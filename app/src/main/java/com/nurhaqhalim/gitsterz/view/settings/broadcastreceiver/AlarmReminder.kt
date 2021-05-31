package com.nurhaqhalim.gitsterz.view.settings.broadcastreceiver

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.nurhaqhalim.gitsterz.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class AlarmReminder : BroadcastReceiver() {

    companion object{
        private const val idNotif = 11
        private const val idChannel = "channel_id"
        private const val channelName = "Gitsterz Alarm Reminder"
        private const val timeFormat = "HH:mm"
        private const val idAlarm = 101
        const val extraMessage = "message_extra"
        const val extraType = "type_extra"
    }

    override fun onReceive(context: Context, intent: Intent) {
        forwardNotif(context)
    }

    private fun forwardNotif(context: Context) {
        val intent = context.packageManager.getLaunchIntentForPackage("com.nurhaqhalim.gitsterz")
        val suspendIntent = PendingIntent.getActivity(context, 0, intent,0)
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(context, idChannel)
            .setContentIntent(suspendIntent)
            .setSmallIcon(R.drawable.ic_notif)
            .setContentTitle(context.resources.getString(R.string.app_name))
            .setContentText("Start search your favourite Users now!")
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(idChannel, channelName,NotificationManager.IMPORTANCE_DEFAULT)
            builder.setChannelId(idChannel)
            manager.createNotificationChannel(channel)
        }
        val notification = builder.build()
        manager.notify(idNotif, notification)
    }

    fun setRepeat(context: Context, type: String, time: String, message: String){
        if (isDateInvalid(time, timeFormat)) return

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, AlarmReminder::class.java)
        intent.putExtra(extraMessage, message)
        intent.putExtra(extraType, type)
        val timeArray = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)
        val suspendIntent = PendingIntent.getBroadcast(context, idAlarm, intent, 0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY,suspendIntent)
        Toast.makeText(context, context.getString(R.string.set_alarm), Toast.LENGTH_LONG).show()
    }

    private fun isDateInvalid(time: String, format: String): Boolean =
        try {
            val dateFormat = SimpleDateFormat(format, Locale.getDefault())
                dateFormat.isLenient = false
                dateFormat.parse(time)
            false
        }catch (e: ParseException){
            true
        }


    fun alarmCancellation(context: Context){
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReminder::class.java)
        val request = idAlarm
        val suspendIntent = PendingIntent.getBroadcast(context, request, intent, 0)
        suspendIntent.cancel()
        alarmManager.cancel(suspendIntent)
        Toast.makeText(context, context.getString(R.string.cancel_alarm), Toast.LENGTH_LONG).show()
    }
}