package com.nurhaqhalim.gitsterz.view.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.nurhaqhalim.gitsterz.R
import com.nurhaqhalim.gitsterz.view.settings.broadcastreceiver.AlarmReminder
import com.nurhaqhalim.gitsterz.databinding.ActivitySettingsBinding
import com.nurhaqhalim.gitsterz.core.data.source.remote.response.AlarmModel
import com.nurhaqhalim.gitsterz.view.settings.preference.Reminder

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var model: AlarmModel
    private lateinit var broadcast: AlarmReminder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar?.title = this.getString(R.string.settings)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reminderPreference = Reminder(this)

        binding.switch1.isChecked = reminderPreference.getAlarm().isRemind
        
        broadcast = AlarmReminder()
        
        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                saveReminder(true)
                broadcast.setRepeat(this, "RepeatingAlarm","09:00", this.resources.getString(R.string.alarm_message))
            }else{
                saveReminder(false)
                broadcast.alarmCancellation(this)
            }
        }

        binding.lsButton.setOnClickListener{
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
    }

    private fun saveReminder(save: Boolean) {
        val reminderPreference = Reminder(this)
        model = AlarmModel()
        model.isRemind = save
        reminderPreference.setAlarm(model)
    }
}