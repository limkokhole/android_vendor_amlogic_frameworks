
package com.droidlogic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.provider.Settings;
import android.content.ContentResolver;
import android.util.Log;
import android.view.IWindowManager;

import com.droidlogic.app.HdrManager;
import com.droidlogic.app.PlayBackManager;
import com.droidlogic.app.SystemControlEvent;
import com.droidlogic.app.SystemControlManager;
import com.droidlogic.app.UsbCameraManager;
import com.droidlogic.HdmiCecExtend;

public class BootComplete extends BroadcastReceiver {
    private static final String TAG             = "BootComplete";
    private static final String FIRST_RUN       = "first_run";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i(TAG, "action: " + action);

        if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            Intent gattServiceIntent = new Intent(context, DialogBluetoothService.class);
            context.startService(gattServiceIntent);
            SystemControlManager sm = new SystemControlManager(context);
            //register system control callback
            sm.setListener(new SystemControlEvent(context));

            //set default show_ime_with_hard_keyboard 1, then first boot can show the ime.
            if (SettingsPref.getFirstRun(context)) {
                Log.i(TAG, "first running: " + context.getPackageName());
                try {
                    Settings.Secure.putInt(context.getContentResolver(),
                            Settings.Secure.SHOW_IME_WITH_HARD_KEYBOARD, 1);
                    Settings.Global.putInt(context.getContentResolver(),
                            Settings.Global.CAPTIVE_PORTAL_DETECTION_ENABLED, 0);
                } catch (NumberFormatException e) {
                    Log.e(TAG, "could not find hard keyboard ", e);
                }

                SettingsPref.setFirstRun(context, false);
            }

            //use to check whether disable camera or not
            new UsbCameraManager(context).bootReady();

            new PlayBackManager(context).initHdmiSelfadaption();

            new HdmiCecExtend(context);

            new HdrManager(context).initHdrMode();

            //start optimization service
            context.startService(new Intent(context, Optimization.class));

            if (context.getPackageManager().hasSystemFeature(NetflixService.FEATURE_SOFTWARE_NETFLIX)) {
                context.startService(new Intent(context, NetflixService.class));
            }

            initDefaultAnimationSettings(context);

            context.startService(new Intent(context,NtpService.class));
            String rotProp = sm.getPropertyString("persist.sys.app.rotation", "");
            ContentResolver res = context.getContentResolver();
            int acceRotation = Settings.System.getIntForUser(res,
                Settings.System.ACCELEROMETER_ROTATION,
                0,
                UserHandle.USER_CURRENT);
            if (rotProp != null && ("middle_port".equals(rotProp) || "force_land".equals(rotProp))) {
                    if (0 != acceRotation) {
                        Settings.System.putIntForUser(res,
                            Settings.System.ACCELEROMETER_ROTATION,
                            0,
                            UserHandle.USER_CURRENT);
                    }
            }
        }
    }

    //this function fix setting database not load AnimationSettings bug
    private static final int INDEX_WINDOW_ANIMATION_SCALE = 0;
    private static final int INDEX_TRANSITION_ANIMATION_SCALE = 1;
    private void initDefaultAnimationSettings(Context context) {
        try {
            IWindowManager wm = IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
            if (wm.getAnimationScale(INDEX_WINDOW_ANIMATION_SCALE) != 0.0f) {
                wm.setAnimationScale(INDEX_WINDOW_ANIMATION_SCALE, 0);
            }
            if (wm.getAnimationScale(INDEX_TRANSITION_ANIMATION_SCALE) != 0.0f) {
                wm.setAnimationScale(INDEX_TRANSITION_ANIMATION_SCALE, 0);
            }
        } catch (RemoteException e) {
        }
    }
}

