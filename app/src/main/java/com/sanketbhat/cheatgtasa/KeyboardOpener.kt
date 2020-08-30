package com.sanketbhat.cheatgtasa

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.inputmethod.InputMethodManager

class KeyboardOpener : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            GTASACheatService.service?.requestShowSelf(InputMethodManager.SHOW_FORCED)
        }
    }
}