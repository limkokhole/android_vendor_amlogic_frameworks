package com.droidlogic.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;

public class OutputModeManager {
    private static final String TAG                         = "OutputModeManager";
    private static final boolean DEBUG                      = false;

    /**
     * The saved value for Outputmode auto-detection.
     * One integer
     * @hide
     */
    public static final String DISPLAY_OUTPUTMODE_AUTO      = "display_outputmode_auto";

    /**
     *  broadcast of the current HDMI output mode changed.
     */
    public static final String ACTION_HDMI_MODE_CHANGED     = "android.intent.action.HDMI_MODE_CHANGED";

    /**
     * Extra in {@link #ACTION_HDMI_MODE_CHANGED} indicating the mode:
     */
    public static final String EXTRA_HDMI_MODE              = "mode";

    public static final String SYS_DIGITAL_RAW              = "/sys/class/audiodsp/digital_raw";
    public static final String SYS_AUDIO_CAP                = "/sys/class/amhdmitx/amhdmitx0/aud_cap";
    public static final String SYS_AUIDO_HDMI               = "/sys/class/amhdmitx/amhdmitx0/config";
    public static final String SYS_AUIDO_SPDIF              = "/sys/devices/platform/spdif-dit.0/spdif_mute";

    public static final String AUIDO_DSP_AC3_DRC            = "/sys/class/audiodsp/ac3_drc_control";
    public static final String AUIDO_DSP_DTS_DEC            = "/sys/class/audiodsp/dts_dec_control";

    public static final String SYS_PPSCALER                 = "/sys/class/ppmgr/ppscaler";
    public static final String SYS_PPSCALER_RECT            = "/sys/class/ppmgr/ppscaler_rect";

    public static final String HDMI_STATE                   = "/sys/class/amhdmitx/amhdmitx0/hpd_state";
    public static final String HDMI_VDAC_PLUGGED            = "/sys/class/aml_mod/mod_off";
    public static final String HDMI_VDAC_UNPLUGGED          = "/sys/class/aml_mod/mod_on";
    public static final String HDMI_SUPPORT_LIST            = "/sys/class/amhdmitx/amhdmitx0/disp_cap";

    public static final String DISPLAY_MODE                 = "/sys/class/display/mode";
    public static final String DISPLAY_AXIS                 = "/sys/class/display/axis";

    public static final String VIDEO_AXIS                   = "/sys/class/video/axis";

    public static final String FB0_FREE_SCALE_UPDATE        = "/sys/class/graphics/fb0/update_freescale";
    public static final String FB0_FREE_SCALE_AXIS          = "/sys/class/graphics/fb0/free_scale_axis";
    public static final String FB0_FREE_SCALE_MODE          = "/sys/class/graphics/fb0/freescale_mode";
    public static final String FB0_FREE_SCALE               = "/sys/class/graphics/fb0/free_scale";
    public static final String FB1_FREE_SCALE               = "/sys/class/graphics/fb1/free_scale";

    public static final String FB0_WINDOW_AXIS              = "/sys/class/graphics/fb0/window_axis";
    public static final String FB0_BLANK                    = "/sys/class/graphics/fb0/blank";

    public static final String ENV_CVBS_MODE                = "ubootenv.var.cvbsmode";
    public static final String ENV_HDMI_MODE                = "ubootenv.var.hdmimode";
    public static final String ENV_OUTPUT_MODE              = "ubootenv.var.outputmode";
    public static final String ENV_DIGIT_AUDIO              = "ubootenv.var.digitaudiooutput";
    public static final String ENV_IS_BEST_MODE             = "ubootenv.var.is.bestmode";

    public static final String PROP_BEST_OUTPUT_MODE        = "ro.platform.best_outputmode";
    public static final String PROP_HDMI_ONLY               = "ro.platform.hdmionly";

    public static final String FULL_WIDTH_480               = "720";
    public static final String FULL_HEIGHT_480              = "480";
    public static final String FULL_WIDTH_576               = "720";
    public static final String FULL_HEIGHT_576              = "576";
    public static final String FULL_WIDTH_720               = "1280";
    public static final String FULL_HEIGHT_720              = "720";
    public static final String FULL_WIDTH_1080              = "1920";
    public static final String FULL_HEIGHT_1080             = "1080";
    public static final String FULL_WIDTH_4K2K              = "3840";
    public static final String FULL_HEIGHT_4K2K             = "2160";
    public static final String FULL_WIDTH_4K2KSMPTE         = "4096";
    public static final String FULL_HEIGHT_4K2KSMPTE        = "2160";

    private static final String DISPLAY_AXIS_1080           = " 1920 1080 ";
    private static final String DISPLAY_AXIS_720            = " 1280 720 ";
    private static final String DISPLAY_AXIS_576            = " 720 576 ";
    private static final String DISPLAY_AXIS_480            = " 720 480 ";

    private static final String FREQ_DEFAULT                = "60hz";
    private static final String FREQ_SETTING                = "50hz";

    public static final String PCM                          = "PCM";
    public static final String RAW                          = "RAW";
    public static final String HDMI                         = "HDMI";
    public static final String SPDIF                        = "SPDIF";
    public static final String HDMI_RAW                     = "HDMI passthrough";
    public static final String SPDIF_RAW                    = "SPDIF passthrough";
    public static final int IS_AUTO                         = 0x10;
    public static final int IS_PCM                          = 0x01;
    public static final int IS_HDMI                         = 0x02;
    public static final int IS_SPDIF                        = 0x04;

    private String DEFAULT_OUTPUT_MODE                      = "1080p60hz";
    private static String currentOutputmode = null;
    private String mSupportModes = null;
    private boolean ifModeSetting = false;
    private final Context mContext;
    final Object mLock = new Object[0];

    private SystemControlManager mSystenControl;
    SystemControlManager.DisplayInfo mDisplayInfo;

    public OutputModeManager(Context context) {
        mContext = context;

        mSystenControl = new SystemControlManager(context);
        mDisplayInfo = mSystenControl.getDisplayInfo();
        if (mDisplayInfo.defaultUI != null) {
            DEFAULT_OUTPUT_MODE = mDisplayInfo.defaultUI;

            if (DEBUG)
                Log.d(TAG, "output mode, display type [1:tablet 2:MBOX 3:TV]: "
                    + mDisplayInfo.type + ", default output:" + mDisplayInfo.defaultUI);
        }

        currentOutputmode = readSysfs(DISPLAY_MODE);
    }

    public void setOutputMode(final String mode) {
        setOutputModeNowLocked(mode);
    }

    public void setBestMode(String mode) {
        if (mode == null) {
            if (!isBestOutputmode()) {
                mSystenControl.setBootenv(ENV_IS_BEST_MODE, "true");
                setOutputMode(getBestMatchResolution());
            } else {
                mSystenControl.setBootenv(ENV_IS_BEST_MODE, "false");
            }
        } else {
            mSystenControl.setBootenv(ENV_IS_BEST_MODE, "false");
            setOutputModeNowLocked(mode);
        }
    }

    public void setOutputModeNowLocked(final String newMode){
        synchronized (mLock) {
            String oldMode = currentOutputmode;


            if (oldMode == null || oldMode.length() < 4) {
                Log.e(TAG, "get display mode error, oldMode:" + oldMode + " set to default " + DEFAULT_OUTPUT_MODE);
                oldMode = DEFAULT_OUTPUT_MODE;
            }

            if (DEBUG)
                Log.d(TAG, "change mode from " + oldMode + " -> " + newMode);

            if (newMode.equals(oldMode)) {
                if (DEBUG)
                    Log.d(TAG,"The same mode as current , do nothing !");
                return ;
            }

            shadowScreen(oldMode);

            if (newMode.contains("cvbs")) {
                 openVdac(newMode);
            } else {
                 closeVdac(newMode);
            }

            writeSysfs(DISPLAY_MODE, newMode);
            currentOutputmode = newMode;

            int[] curPosition = getPosition(newMode);
            int[] oldPosition = getPosition(oldMode);

            String winAxis = curPosition[0] + " " + curPosition[1] + " " +
                (curPosition[0] + curPosition[2] - 1) + " " + (curPosition[1] + curPosition[3] - 1);

            if (mDisplayInfo.socType.contains("meson8")) {
                writeSysfs(FB0_FREE_SCALE_MODE,"1");

                if (mDisplayInfo.defaultUI.contains("720")) {
                    writeSysfs(FB0_FREE_SCALE_AXIS,"0 0 1279 719");
                } else if (mDisplayInfo.defaultUI.contains("4k2k")) {
                    writeSysfs(FB0_FREE_SCALE_AXIS,"0 0 3839 2159");
                } else {
                    writeSysfs(FB0_FREE_SCALE_AXIS,"0 0 1919 1079");
                }

                writeSysfs(FB0_WINDOW_AXIS,winAxis);
                writeSysfs(VIDEO_AXIS,winAxis);
                writeSysfs(FB0_FREE_SCALE,"0x10001");
            } else {
                String value = curPosition[0] + " " + curPosition[1]
                    + " " + (curPosition[2] + curPosition[0])
                    + " " + (curPosition[3] + curPosition[1]) + " " + 0;
                setM6FreeScaleAxis(newMode);
                writeSysfs(SYS_PPSCALER_RECT, value);
                writeSysfs(FB0_FREE_SCALE_UPDATE, "1");
            }

            setBootenv(ENV_OUTPUT_MODE, newMode);
            saveNewMode2Prop(newMode);
            setOsdMouse(newMode);

            Intent intent = new Intent(ACTION_HDMI_MODE_CHANGED);
            //intent.addFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY_BEFORE_BOOT);
            intent.putExtra(EXTRA_HDMI_MODE, newMode);
            mContext.sendStickyBroadcast(intent);
        }
    }

    public void setOsdMouse(String curMode) {
        if (DEBUG)
            Log.d(TAG, "set osd mouse curMode " + curMode);
        mSystenControl.setOsdMouseMode(curMode);
    }

    public void setOsdMouse(int x, int y, int w, int h) {
        mSystenControl.setOsdMousePara(x, y, w, h);
    }

    public void setOutputWithoutFreeScaleLocked(String newMode){
        int[] curPosition = { 0, 0, 0, 0 };
        int[] oldPosition = { 0, 0, 0, 0 };
        int axis[] = {0, 0, 0, 0};

        String oldMode = currentOutputmode;
        if (DEBUG)
            Log.d(TAG, "setOutputWithoutFreeScale change mode from " +
                oldMode + " -> " + newMode + " WithoutFreeScale");

        if (newMode.equals(oldMode)) {
            if (DEBUG)
                Log.d(TAG, "The same mode as current , do nothing !");
            return;
        }

        synchronized (mLock) {
            if (newMode.contains("cvbs")) {
                 openVdac(newMode);
            } else {
                 closeVdac(newMode);
            }
            shadowScreen(oldMode);
            writeSysfs(SYS_PPSCALER, "0");
            writeSysfs(FB0_FREE_SCALE, "0");
            writeSysfs(FB1_FREE_SCALE, "0");
            writeSysfs(DISPLAY_MODE, newMode);
            currentOutputmode = newMode;
            setBootenv(ENV_OUTPUT_MODE, newMode);
            saveNewMode2Prop(newMode);

            Intent intent = new Intent(ACTION_HDMI_MODE_CHANGED);
            //intent.addFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY_BEFORE_BOOT);
            intent.putExtra(EXTRA_HDMI_MODE, newMode);
            mContext.sendStickyBroadcast(intent);

            curPosition = getPosition(newMode);
            oldPosition = getPosition(oldMode);
            String axisStr = readSysfs(VIDEO_AXIS);
            String[] axisArray = axisStr.split(" ");

            for (int i=0; i<axisArray.length; i++) {
                if (i == axis.length) {
                    break;
                }
                try {
                    axis[i] = Integer.parseInt(axisArray[i]);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (mDisplayInfo.socType.contains("meson8")) {
               /* String display_value = curPosition[0] + " "+ curPosition[1] + " "
                        + 1920+ " "+ 1080+ " "
                        + curPosition[0]+ " " + curPosition[1]+ " " + 18+ " " + 18;
                writeSysfs(DISPLAY_AXIS, display_value);
                if (DEBUG)
                    Log.d("OutputSettings", "outputmode change:curPosition[2]:"+curPosition[2]+" curPosition[3]:"+curPosition[3]+"\n");*/
            } else {
                int oldX = oldPosition[0];
                int oldY = oldPosition[1];
                int oldWidth = oldPosition[2];
                int oldHeight = oldPosition[3];
                int curX = curPosition[0];
                int curY = curPosition[1];
                int curWidth = curPosition[2];
                int curHeight = curPosition[3];
                int temp1 = curX;
                int temp2 = curY;
                int temp3 = curWidth;
                int temp4 = curHeight;

                if (DEBUG) {
                    Log.d(TAG, "setOutputWithoutFreeScale, old is: "
                        + oldX + " " + oldY + " " + oldWidth + " " + oldHeight);
                    Log.d(TAG, "setOutputWithoutFreeScale, new is: "
                        + curX + " " + curY + " " + curWidth + " " + curHeight);
                    Log.d(TAG, "setOutputWithoutFreeScale, axis is: "
                        + axis[0] + " " + axis[1] + " " + axis[2] + " " + axis[3]);
                }

                if (!((axis[0] == 0) && (axis[1] == 0) && (axis[2] == -1) && (axis[3] == -1))
                        && !((axis[0] == 0) && (axis[1] == 0) && (axis[2] == 0) && (axis[3] == 0))) {
                    temp1 = (axis[0] - oldX) * curWidth / oldWidth + curX;
                    temp2 = (axis[1] - oldY) * curHeight / oldHeight + curY;
                    temp3 = (axis[2] - axis[0] + 1) * curWidth / oldWidth;
                    temp4 = (axis[3] - axis[1] + 1) * curHeight / oldHeight;
                }

                if (DEBUG)
                    Log.d(TAG, "setOutputWithoutFreeScale, changed axis is: "
                        + temp1 + " " + temp2 + " " + (temp3 + temp1 - 1) + " " + (temp4 + temp2 - 1));

                writeSysfs(VIDEO_AXIS, temp1 + " " + temp2 + " "
                    + (temp3 + temp1 - 1) + " " + (temp4 + temp2 - 1));
            }
            setOsdMouse(newMode);
        }
    }

    private void saveNewMode2Prop(String newMode) {
        if ((newMode != null) && newMode.contains("cvbs")) {
            setBootenv(ENV_CVBS_MODE, newMode);
        }
        else {
            setBootenv(ENV_HDMI_MODE, newMode);
        }
    }

    private void closeVdac(String outputmode) {
       if (getPropertyBoolean(PROP_HDMI_ONLY, false)) {
           if (!outputmode.contains("cvbs")) {
               writeSysfs(HDMI_VDAC_PLUGGED,"vdac");
           }
       }
    }
    private void openVdac(String outputmode) {
        if (getPropertyBoolean(PROP_HDMI_ONLY, false)) {
            if (outputmode.contains("cvbs")) {
                writeSysfs(HDMI_VDAC_UNPLUGGED,"vdac");
            }
        }
    }

    public String getCurrentOutputMode(){
        return currentOutputmode;
    }

    public int[] getPosition(String mode) {
        return mSystenControl.getPosition(mode);
    }

    public void savePosition(int left, int top, int width, int height) {
        mSystenControl.setPosition(left, top, width, height);
    }

    private void setM6FreeScaleAxis(String mode) {
        writeSysfs(FB0_FREE_SCALE_AXIS, "0 0 1279 719");
        writeSysfs(FB0_FREE_SCALE, "1");
    }

    public String getHdmiSupportList() {
        String list = readSupportList(HDMI_SUPPORT_LIST).replaceAll("[*]", "");

        if (DEBUG)
            Log.d(TAG, "getHdmiSupportList :" + list);
        return list;
    }

    public String getBestMatchResolution() {
        if (DEBUG)
            Log.d(TAG, "get best mode, if support mode contains *, that is best mode, otherwise use:" + PROP_BEST_OUTPUT_MODE);

        String[] supportList = null;
        String value = readSupportList(HDMI_SUPPORT_LIST);
        if (value.indexOf("480") >= 0 || value.indexOf("576") >= 0
            ||value.indexOf("720") >= 0||value.indexOf("1080") >= 0 || value.indexOf("4k2k") >= 0) {
            supportList = (value.substring(0, value.length()-1)).split(",");
        }

        if (supportList != null) {
            for (int i = 0; i < supportList.length; i++) {
                if (supportList[i].contains("*")) {
                    return supportList[i].substring(0, supportList[i].length()-1);
                }
            }
        }

        return getPropertyString(PROP_BEST_OUTPUT_MODE, DEFAULT_OUTPUT_MODE);
    }

    public String getSupportedResolution() {
        String curMode = getBootenv(ENV_HDMI_MODE, DEFAULT_OUTPUT_MODE);

        if (DEBUG)
            Log.d(TAG, "get supported resolution curMode:" + curMode);

        String value = readSupportList(HDMI_SUPPORT_LIST);
        String[] supportList = null;

        if (value.indexOf("480") >= 0 || value.indexOf("576") >= 0
            ||value.indexOf("720") >= 0||value.indexOf("1080") >= 0 || value.indexOf("4k2k") >= 0) {
            supportList = (value.substring(0, value.length()-1)).split(",");
        }

        if (supportList == null) {
            return curMode;
        }

        for (int i = 0; i < supportList.length; i++) {
            if (supportList[i].equals(curMode)) {
                return curMode;
            }
        }

        return getBestMatchResolution();
    }

    private String readSupportList(String path) {
        if (null != mSupportModes)
            return mSupportModes;

        String str = null;
        String value = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((str = br.readLine()) != null) {
                if (str != null)
                    value += str + ",";
            }
            br.close();

            Log.d(TAG, "TV support list is :" + value);
            mSupportModes = value;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    private String getDisplayAxisByMode(String mode){
        if (mode.indexOf("1080") >= 0)
            return DISPLAY_AXIS_1080;
        else if (mode.indexOf("720") >= 0)
            return DISPLAY_AXIS_720;
        else if (mode.indexOf("576") >= 0)
            return DISPLAY_AXIS_576;
        else
            return DISPLAY_AXIS_480;
    }

    public void initOutputMode(){
        if (isHDMIPlugged()) {
            setHdmiPlugged();
        } else {
            if (!currentOutputmode.contains("cvbs"))
                setHdmiUnPlugged();
        }

        //there can not set osd mouse parameter, otherwise bootanimation logo will shake
        //because set osd1 scaler will shake
    }

    public void setHdmiUnPlugged(){
        Log.d(TAG, "setHdmiUnPlugged");

        if (mDisplayInfo.socType.contains("meson8")) {
            if (getPropertyBoolean(PROP_HDMI_ONLY, true)) {
                String cvbsmode = getBootenv(ENV_CVBS_MODE, "576cvbs");
                setOutputMode(cvbsmode);
                synchronized (mLock) {
                    writeSysfs(HDMI_VDAC_UNPLUGGED, "vdac");//open vdac
                }
            }
        } else {
            if (getPropertyBoolean(PROP_HDMI_ONLY, true)) {
                String cvbsmode = getBootenv(ENV_CVBS_MODE, "576cvbs");
                if (isFreeScaleClosed()) {
                    setOutputWithoutFreeScaleLocked(cvbsmode);
                } else {
                    setOutputMode(cvbsmode);
                }
                synchronized (mLock) {
                    writeSysfs(HDMI_VDAC_UNPLUGGED, "vdac");//open vdac
                }
            }
        }
    }

    public void setHdmiPlugged() {
        boolean isAutoMode = isBestOutputmode() || readSupportList(HDMI_SUPPORT_LIST).contains("null edid");

        Log.d(TAG, "setHdmiPlugged auto mode: " + isAutoMode);
        if (mDisplayInfo.socType.contains("meson8")) {
            if (getPropertyBoolean(PROP_HDMI_ONLY, true)) {
                writeSysfs(HDMI_VDAC_PLUGGED, "vdac");
                if (isAutoMode) {
                    setOutputMode(filterResolution(getBestMatchResolution()));
                } else {
                    String mode = getSupportedResolution();
                    setOutputMode(mode);
                }
            }
            switchHdmiPassthough();
        } else {
            if (getPropertyBoolean(PROP_HDMI_ONLY, true)) {
                writeSysfs(HDMI_VDAC_PLUGGED, "vdac");
                if (isAutoMode) {
                    if (isFreeScaleClosed()) {
                        setOutputWithoutFreeScaleLocked(filterResolution(getBestMatchResolution()));
                    } else {
                        setOutputMode(filterResolution(getBestMatchResolution()));
                    }

                } else {
                    String mode = getSupportedResolution();
                    if (isFreeScaleClosed())
                        setOutputWithoutFreeScaleLocked(mode);
                    else
                        setOutputMode(mode);
                }
                switchHdmiPassthough();
                writeSysfs(FB0_BLANK, "0");
            }
        }
    }

    public boolean isFreeScaleClosed() {
        String freeScaleStatus = readSysfs(FB0_FREE_SCALE);
        if (freeScaleStatus.contains("0x0")) {
            Log.d(TAG, "freescale is closed");
            return true;
        } else {
            Log.d(TAG, "freescale is open");
            return false;
        }
    }

    public boolean isBestOutputmode() {
        return Boolean.parseBoolean(mSystenControl.getBootenv(ENV_IS_BEST_MODE, "true"));
    }

    public String filterResolution(String resolution) {
        if (resolution.contains("480i")) {
            resolution = "480i60hz";
        } else if (resolution.contains("480cvbs")) {
            resolution = "480cvbs";
        } else if (resolution.contains("480p")) {
            resolution = "480p60hz";
        } else if (resolution.contains("576i")) {
            resolution = "576i50hz";
        } else if (resolution.contains("576cvbs")) {
            resolution = "576cvbs";
        } else if (resolution.contains("576p")) {
            resolution = "576p50hz";
        } else if (resolution.contains("720p")) {
            if (resolution.contains(FREQ_SETTING)) {
                resolution = "720p" + FREQ_SETTING;
            } else {
                resolution ="720p" + FREQ_DEFAULT;
            }
        } else if (resolution.contains("1080i")) {
            if (resolution.contains(FREQ_SETTING)) {
                resolution = "1080i" + FREQ_SETTING;
            } else {
                resolution = "1080i" + FREQ_DEFAULT;
            }
        } else if (resolution.contains("1080p")) {
            if (resolution.contains(FREQ_SETTING)) {
                resolution = "1080p" + FREQ_SETTING;
            } else {
                resolution = "1080p" + FREQ_DEFAULT;
            }
        }

        return resolution;
    }

    public boolean isHDMIPlugged() {
        String status = readSysfs(HDMI_STATE);
        if ("1".equals(status))
            return true;
        else
            return false;
    }

    public boolean ifModeIsSetting() {
        return ifModeSetting;
    }

    private void shadowScreen(final String mode) {
        writeSysfs(FB0_BLANK, "1");
        Thread task = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ifModeSetting = true;
                    Thread.sleep(1000);
                    writeSysfs(FB0_BLANK, "0");
                    ifModeSetting = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        task.start();
    }

    private void switchHdmiPassthough() {
        String value = getBootenv(ENV_DIGIT_AUDIO, PCM);

        if (value.contains(":auto")) {
            autoSwitchHdmiPassthough();
        } else {
            setDigitalVoiceValue(value);
        }
    }

    public int getDigitalVoiceMode(){
        int ret = 0;

        String value = getBootenv(ENV_DIGIT_AUDIO, PCM);
        if (value.contains(":auto")) {
            ret = ret | IS_AUTO;
        }
        if (value.contains(PCM)) {
            ret = ret | IS_PCM;
        }else if (value.contains(HDMI)) {
            ret = ret | IS_HDMI;
        }else if (value.contains(SPDIF)) {
            ret = ret | IS_SPDIF;
        }
        return ret;
    }

    public int autoSwitchHdmiPassthough () {
        String mAudioCapInfo = readSysfsTotal(SYS_AUDIO_CAP);
        if (mAudioCapInfo.contains("Dobly_Digital+")) {
            writeSysfs(SYS_DIGITAL_RAW, "2");
            writeSysfs(SYS_AUIDO_SPDIF, "spdif_mute");
            writeSysfs(SYS_AUIDO_HDMI, "audio_on");
            setBootenv(ENV_DIGIT_AUDIO, "HDMI passthrough:auto");
            return 2;
        } else if (mAudioCapInfo.contains("AC-3")) {
            writeSysfs(SYS_DIGITAL_RAW, "1");
            writeSysfs(SYS_AUIDO_HDMI, "audio_on");
            writeSysfs(SYS_AUIDO_SPDIF, "spdif_unmute");
            setBootenv(ENV_DIGIT_AUDIO, "SPDIF passthrough:auto");
            return 1;
        } else {
            writeSysfs(SYS_DIGITAL_RAW, "0");
            writeSysfs(SYS_AUIDO_SPDIF, "spdif_mute");
            writeSysfs(SYS_AUIDO_HDMI, "audio_on");
            setBootenv(ENV_DIGIT_AUDIO, "PCM:auto");
            return 0;
        }
    }

    public void setDigitalVoiceValue(String value) {
        // value : "PCM" ,"RAW","SPDIF passthrough","HDMI passthrough"
        setBootenv(ENV_DIGIT_AUDIO, value);

        if (PCM.equals(value)) {
            writeSysfs(SYS_DIGITAL_RAW, "0");
            writeSysfs(SYS_AUIDO_SPDIF, "spdif_mute");
            writeSysfs(SYS_AUIDO_HDMI, "audio_on");
        } else if (RAW.equals(value)) {
            writeSysfs(SYS_DIGITAL_RAW, "1");
            writeSysfs(SYS_AUIDO_HDMI, "audio_off");
            writeSysfs(SYS_AUIDO_SPDIF, "spdif_unmute");
        } else if (SPDIF_RAW.equals(value)) {
            writeSysfs(SYS_DIGITAL_RAW, "1");
            writeSysfs(SYS_AUIDO_HDMI, "audio_off");
            writeSysfs(SYS_AUIDO_SPDIF, "spdif_unmute");
        } else if (HDMI_RAW.equals(value)) {
            writeSysfs(SYS_DIGITAL_RAW, "2");
            writeSysfs(SYS_AUIDO_SPDIF, "spdif_mute");
            writeSysfs(SYS_AUIDO_HDMI, "audio_on");
        }
    }

    public void enableDobly_DRC (boolean enable) {
        if (enable) {       //open DRC
            writeSysfs(AUIDO_DSP_AC3_DRC, "drchighcutscale 0x64");
            writeSysfs(AUIDO_DSP_AC3_DRC, "drclowboostscale 0x64");
        } else {           //close DRC
            writeSysfs(AUIDO_DSP_AC3_DRC, "drchighcutscale 0");
            writeSysfs(AUIDO_DSP_AC3_DRC, "drclowboostscale 0");
        }
    }

    public void setDoblyMode (String mode) {
        //"CUSTOM_0","CUSTOM_1","LINE","RF"; default use "LINE"
        int i = Integer.parseInt(mode);
        if (i >= 0 && i <= 3) {
            writeSysfs(AUIDO_DSP_AC3_DRC, "drcmode" + " " + mode);
        } else {
            writeSysfs(AUIDO_DSP_AC3_DRC, "drcmode" + " " + "2");
        }
    }

    public void setDTS_DownmixMode(String mode) {
        // 0: Lo/Ro;   1: Lt/Rt;  default 0
        int i = Integer.parseInt(mode);
        if (i >= 0 && i <= 1) {
            writeSysfs(AUIDO_DSP_DTS_DEC, "dtsdmxmode" + " " + mode);
        } else {
            writeSysfs(AUIDO_DSP_DTS_DEC, "dtsdmxmode" + " " + "0");
        }
    }

    public void enableDTS_DRC_scale_control (boolean enable) {
        if (enable) {
            writeSysfs(AUIDO_DSP_DTS_DEC, "dtsdrcscale 0x64");
        } else {
            writeSysfs(AUIDO_DSP_DTS_DEC, "dtsdrcscale 0");
        }
    }

    public void enableDTS_Dial_Norm_control (boolean enable) {
        if (enable) {
            writeSysfs(AUIDO_DSP_DTS_DEC, "dtsdialnorm 1");
        } else {
            writeSysfs(AUIDO_DSP_DTS_DEC, "dtsdialnorm 0");
        }
    }

    private String getProperty(String key) {
        if (DEBUG)
            Log.i(TAG, "getProperty key:" + key);
        return mSystenControl.getProperty(key);
    }

    private String getPropertyString(String key, String def) {
        if (DEBUG)
            Log.i(TAG, "getPropertyString key:" + key + " def:" + def);
        return mSystenControl.getPropertyString(key, def);
    }

    private int getPropertyInt(String key,int def) {
        if (DEBUG)
            Log.i(TAG, "getPropertyInt key:" + key + " def:" + def);
        return mSystenControl.getPropertyInt(key, def);
    }

    private long getPropertyLong(String key,long def) {
        if (DEBUG)
            Log.i(TAG, "getPropertyLong key:" + key + " def:" + def);
        return mSystenControl.getPropertyLong(key, def);
    }

    private boolean getPropertyBoolean(String key,boolean def) {
        if (DEBUG)
            Log.i(TAG, "getPropertyBoolean key:" + key + " def:" + def);
        return mSystenControl.getPropertyBoolean(key, def);
    }

    private void setProperty(String key, String value) {
        if (DEBUG)
            Log.i(TAG, "setProperty key:" + key + " value:" + value);
        mSystenControl.setProperty(key, value);
    }

    private String getBootenv(String key, String value) {
        if (DEBUG)
            Log.i(TAG, "getBootenv key:" + key + " def value:" + value);
        return mSystenControl.getBootenv(key, value);
    }

    private int getBootenvInt(String key, String value) {
        if (DEBUG)
            Log.i(TAG, "getBootenvInt key:" + key + " def value:" + value);
        return Integer.parseInt(mSystenControl.getBootenv(key, value));
    }

    private void setBootenv(String key, String value) {
        if (DEBUG)
            Log.i(TAG, "setBootenv key:" + key + " value:" + value);
        mSystenControl.setBootenv(key, value);
    }

    private String readSysfsTotal(String path) {
        return mSystenControl.readSysFs(path).replaceAll("\n", "");
    }

    private String readSysfs(String path) {

        return mSystenControl.readSysFs(path).replaceAll("\n", "");
        /*
        if (!new File(path).exists()) {
            Log.e(TAG, "File not found: " + path);
            return null;
        }

        String str = null;
        StringBuilder value = new StringBuilder();

        if (DEBUG)
            Log.i(TAG, "readSysfs path:" + path);

        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            try {
                while ((str = br.readLine()) != null) {
                    if (str != null)
                        value.append(str);
                };
                fr.close();
                br.close();
                if (value != null)
                    return value.toString();
                else
                    return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        */
    }

    private boolean writeSysfs(String path, String value) {
        if (DEBUG)
            Log.i(TAG, "writeSysfs path:" + path + " value:" + value);

        return mSystenControl.writeSysFs(path, value);
        /*
        if (!new File(path).exists()) {
            Log.e(TAG, "File not found: " + path);
            return false;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path), 64);
            try {
                writer.write(value);
            } finally {
                writer.close();
            }
            return true;

        } catch (IOException e) {
            Log.e(TAG, "IO Exception when write: " + path, e);
            return false;
        }
        */
    }
}

