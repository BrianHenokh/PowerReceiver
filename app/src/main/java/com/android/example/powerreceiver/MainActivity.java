package com.android.example.powerreceiver;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
  ComponentName mReceiveComponentName;
  PackageManager mPackageManager;
  private static final String ACTION_CUSTOM_BROADCAST = "com.example.android.powerreceiver.ACTION_CUSTOM_BROADCAST";
  private CustomReceiver mReceiver = new CustomReceiver();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mReceiveComponentName = new ComponentName(this, CustomReceiver.class);
    mPackageManager = getPackageManager();

    LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));

  }

  @Override
  protected void onStart() {
    super.onStart();
    mPackageManager.setComponentEnabledSetting(mReceiveComponentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
  }

  @Override
  protected void onStop() {
    super.onStop();
    mPackageManager.setComponentEnabledSetting(mReceiveComponentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
  }

  @Override
  protected void onDestroy() {
    LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    super.onDestroy();
  }

  public void sendCustomBroadcast(View view) {
    Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
    LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
  }
}
