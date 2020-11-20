package com.bernatasel.firebaseinapp


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.inappmessaging.FirebaseInAppMessaging
import com.google.firebase.inappmessaging.FirebaseInAppMessagingClickListener
import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplay
import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplayCallbacks
import com.google.firebase.inappmessaging.model.Action
import com.google.firebase.inappmessaging.model.InAppMessage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //notification allowed
        var isNotificationAllowed = NotificationManagerCompat.from(this).areNotificationsEnabled()
        tv_notification_state.setText("Notification Permission State:" + isNotificationAllowed.toString())


        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        buton.setOnClickListener {
            //firebaseAnalytics.logEvent("button_clicked",null)
            //FirebaseInAppMessaging.getInstance().triggerEvent("button_clicked")
            val intent = Intent()
            intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
            intent.putExtra("android.provider.extra.APP_PACKAGE", packageName)
            startActivity(intent)
        }


        if(!isNotificationAllowed){
            firebaseAnalytics.logEvent("not_allowed_notification",null)
            FirebaseInAppMessaging.getInstance().triggerEvent("not_allowed_notification")

        }

/*
        FirebaseInAppMessaging.getInstance().setMessageDisplayComponent { inAppMessage, callbacks ->
            var campaignMetadata = inAppMessage.campaignMetadata
            var messageType = inAppMessage.messageType
            callbacks.messageDismissed(FirebaseInAppMessagingDisplayCallbacks.InAppMessagingDismissType.CLICK)
        }

        FirebaseInAppMessaging.getInstance().addClickListener { inAppMessage, _ ->
            inAppMessage.data?.let { metadata ->
                val testData = metadata["test_key"]
                if(testData == "1234"){
                    Toast.makeText(this, "SELAM TEST DATA 1234", Toast.LENGTH_SHORT).show()
                }
            }
        }

        FirebaseInAppMessaging.getInstance().addImpressionListener { inAppMessage ->
            var messageType = inAppMessage.messageType
        }
*/
/*
        FirebaseInAppMessaging.getInstance().addClickListener { inAppMessage, action ->
            Toast.makeText(this, "SELAM TEST DATA 1234", Toast.LENGTH_SHORT).show()
        }
*/

        //FirebaseInAppMessaging.getInstance().addClickListener()
    }


}

