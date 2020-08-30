package com.sanketbhat.cheatgtasa

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.buttonCreateNotification).setOnClickListener { showNotification() }
        val recyclerView: RecyclerView = findViewById(R.id.mainList)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ListAdapter(GTASACheatService.getCheats())
        }
    }

    class ListAdapter(val list: List<Cheat>) : RecyclerView.Adapter<ListViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            return ListViewHolder(view)
        }

        override fun getItemCount() = list.size

        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            holder.bind(list[position])
        }

    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameView: TextView = view.findViewById(R.id.name)
        private val codeView: TextView = view.findViewById(R.id.code)
        private val typeView: TextView = view.findViewById(R.id.type)

        fun bind(cheat: Cheat) {
            nameView.text = cheat.name
            codeView.text = cheat.code
            typeView.text = when (cheat.type) {
                Cheat.Type.Player -> "Player"
                Cheat.Type.Vehicle -> "Vehicle"
                Cheat.Type.Weapon -> "Weapon"
                Cheat.Type.Game -> "Game"
                Cheat.Type.Weather -> "Weather"
                Cheat.Type.Other -> "Other"
            }
        }
    }

    private fun showNotification() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "GTASACheat"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    channelId,
                    "GTASA Cheat",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
        }
        val notification = NotificationCompat.Builder(this, channelId)
            .setNotificationSilent()
            .setContentIntent(
                PendingIntent.getBroadcast(
                    this,
                    0,
                    Intent(this, KeyboardOpener::class.java).apply { action = "OPEN_KEYBOARD" },
                    0
                )
            )
            .setContentTitle("Tap to open keyboard")
            .setContentText("GTASA Cheat keyboard")
            .setSmallIcon(R.mipmap.ic_launcher_round)
        notificationManager.notify(777, notification.build())
    }
}