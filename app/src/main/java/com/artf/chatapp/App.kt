package com.artf.chatapp

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(), LifecycleObserver {

    /*
    * MD5: 52:E8:A1:D6:D2:42:41:E2:8B:DB:D7:AF:0E:57:BF:CE
    * SHA1: F3:67:56:60:18:49:2B:50:A0:DE:36:0A:9E:90:3E:F2:CC:EF:19:E9
    * SHA-256: CB:58:70:BD:4E:65:3E:CB:95:AE:41:8D:62:F6:37:F1:E7:4C:9B:58:DC:99:17:1C:32:F3:00:A5:B8:81:AF:DB
    * */

    companion object {
        var tempReceiverId: String? = null
        var receiverId: String? = null
    }

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onMoveToForeground() {
        receiverId = tempReceiverId
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onMoveToBackground() {
        tempReceiverId = receiverId
        receiverId = null
    }
}