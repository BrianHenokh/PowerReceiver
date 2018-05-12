package com.android.example.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
  private static final String ACTION_CUSTOM_BROADCAST = "com.example.android.powerreceiver.ACTION_CUSTOM_BROADCAST";

  @Override
  public void onReceive(Context context, Intent intent) {
    String intentAction = intent.getAction(), toastMessage = null;

    switch (intentAction){
      case Intent.ACTION_POWER_CONNECTED:
        toastMessage = context.getString(R.string.connected_toast);
      break;
      case Intent.ACTION_POWER_DISCONNECTED:
        toastMessage = context.getString(R.string.disconnected_toast);
      break;
      case ACTION_CUSTOM_BROADCAST:
        toastMessage = context.getString(R.string.custom_broadcast_toast);
      break;
    }
    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
  }
}

