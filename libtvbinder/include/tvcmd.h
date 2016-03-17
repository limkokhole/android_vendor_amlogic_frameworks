#ifndef ANDROID_AMLOGIC_TVCMD_H
#define ANDROID_AMLOGIC_TVCMD_H

enum tvcmd_e {
    // Tv function
    OPEN_TV = 1,
    CLOSE_TV = 2,
    START_TV = 3,
    STOP_TV = 4,
    GET_TV_STATUS = 5,
    GET_LAST_SOURCE_INPUT = 6,
    GET_CURRENT_SOURCE_INPUT = 7,
    GET_CURRENT_SOURCE_INPUT_TYPE = 8,
    GET_CURRENT_SIGNAL_INFO = 9,
    IS_SOURCE_SWTICH_DONE = 10,
    SET_SOURCE_INPUT = 11,
    RUN_ADC_AUTO_CALIBRATION = 12,
    IS_DVI_SIGNAL = 13,
    IS_VGA_TIMEING_IN_HDMI = 14,
    GET_VIDEO_PATH_STATUS = 15,
    GET_VIDEO_STREAM_STATUS = 16,
    GET_FIRST_START_SWITCH_TYPE = 17,
    SET_PREVIEW_WINDOW = 18,
    GET_SOURCE_CONNECT_STATUS = 19,
    //Tv function END

    // VGA
    RUN_VGA_AUTO_ADJUST = 20,
    GET_VGA_AUTO_ADJUST_STATUS = 21,
    IS_VGA_AUTO_ADJUST_DONE = 22,
    SET_VGA_HPOS = 23,
    GET_VGA_HPOS = 24,
    SET_VGA_VPOS = 25,
    GET_VGA_VPOS = 26,
    SET_VGA_CLOCK = 27,
    GET_VGA_CLOCK = 28,
    SET_VGA_PHASE = 29,
    GET_VGA_PHASE = 30,
    SET_VGA_AJUST_PARA = 31,
    GET_VGA_AJUST_PARA = 32,
    SET_VGAPARAM_DEFAULT = 33,

    // 3D
    SET_3D_MODE = 61,
    GET_3D_MODE = 62,
    SET_3D_LR_SWITH = 63,
    GET_3D_LR_SWITH = 64,
    SET_3D_TO_2D_MODE = 65,
    GET_3D_TO_2D_MODE = 66,
    SET_3D_DEPTH = 67,
    GET_3D_DEPTH = 68,
    GET_3D_AUTO_DETECT = 69,
    SET_3D_AUTO_DETECT = 70,
    // 3D END

    //Screen related
    SET_VIDEO_DISABLE = 80,

    // PQ
    SET_SCENEMODE = 192,
    GET_SCENEMODE = 193,
    SET_BRIGHTNESS = 194,
    GET_BRIGHTNESS = 195,
    SAVE_BRIGHTNESS = 196,
    SET_CONTRAST = 197,
    GET_CONTRAST = 198,
    SAVE_CONTRAST = 199,
    SET_SATUATION = 200,
    GET_SATUATION = 201,
    SAVE_SATUATION = 202,
    SET_HUE = 203,
    GET_HUE = 204,
    SAVE_HUE = 205,
    SET_PQMODE = 206,
    GET_PQMODE = 207,
    SAVE_PQMODE = 208,
    SET_SHARPNESS = 209,
    GET_SHARPNESS = 210,
    SAVE_SHARPNESS = 211,
    SET_BACKLIGHT = 212,
    GET_BACKLIGHT = 213,
    SAVE_BACKLIGHT = 214,
    SET_COLOR_MODE = 215,
    GET_COLOR_MODE = 216,
    SAVE_COLOR_MODE = 217,
    SET_COLOR_TEMPERATURE = 218,
    GET_COLOR_TEMPERATURE = 219,
    SAVE_COLOR_TEMPERATURE = 220,
    SET_DISPLAY_MODE = 221,
    GET_DISPLAY_MODE = 222,
    SAVE_DISPLAY_MODE = 223,
    SET_NOISE_REDUCTION_MODE = 224,
    GET_NOISE_REDUCTION_MODE = 225,
    VPP_SPLIT_SCREEN_EFFECT = 226,
    SET_BACKLIGHT_SWITCH = 227,
    GET_BACKLIGHT_SWITCH = 228,
    SAVE_NOISE_REDUCTION_MODE = 229,
    //GETRGBOGO_GAIN_G = 237,
    //GETRGBOGO_GAIN_B = 238,

    // FACTORY
    FACTORY_SETPQMODE_BRIGHTNESS = 240,
    FACTORY_GETPQMODE_BRIGHTNESS = 241,
    FACTORY_SETPQMODE_CONTRAST = 242,
    FACTORY_GETPQMODE_CONTRAST = 243,
    FACTORY_SETPQMODE_SATURATION = 244,
    FACTORY_GETPQMODE_SATURATION = 245,
    FACTORY_SETPQMODE_HUE = 246,
    FACTORY_GETPQMODE_HUE = 247,
    FACTORY_SETPQMODE_SHARPNESS = 248,
    FACTORY_GETPQMODE_SHARPNESS = 249,
    FACTORY_SETTESTPATTERN = 268,
    FACTORY_GETTESTPATTERN = 269,
    FACTORY_RESETPQMODE = 270,
    FACTORY_RESETCOLORTEMP = 271,
    FACTORY_RESETPAMAMSDEFAULT = 272,
    FACTORY_SETDDRSSC = 273,
    FACTORY_GETDDRSSC = 274,
    FACTORY_SETLVDSSSC = 275,
    FACTORY_GETLVDSSSC = 276,
    FACTORY_SETNOLINEPARAMS = 277,
    FACTORY_GETNOLINEPARAMS = 278,
    FACTORY_SETOVERSCAN = 279,
    FACTORY_GETOVERSCAN = 280,
    FACTORY_SET_OUT_DEFAULT = 281,
    FACTORY_GETGLOBALOGO_RGAIN = 282,
    FACTORY_GETGLOBALOGO_GGAIN = 283,
    FACTORY_GETGLOBALOGO_BGAIN = 284,
    FACTORY_GETGLOBALOGO_ROFFSET = 285,
    FACTORY_GETGLOBALOGO_GOFFSET = 286,
    FACTORY_GETGLOBALOGO_BOFFSET = 287,
    FACTORY_SETGLOBALOGO_RGAIN = 288,
    FACTORY_SETGLOBALOGO_GGAIN = 289,
    FACTORY_SETGLOBALOGO_BGAIN = 290,
    FACTORY_SETGLOBALOGO_ROFFSET = 291,
    FACTORY_SETGLOBALOGO_GOFFSET = 292,
    FACTORY_SETGLOBALOGO_BOFFSET = 293,
    FACTORY_CLEAN_ALL_TABLE_FOR_PROGRAM = 294,

    DELETE_PARAM_PQ_DB = 295,
    REPLACE_PARAM_PQ_DB = 296,
    FACTORY_SETPATTERN_YUV = 297,

    // FACTORY END

    // AUDIO
    // AUDIO MUTE
    SET_AUDIO_MUTEKEY_STATUS = 301,
    GET_AUDIO_MUTEKEY_STATUS = 302,
    SET_AUDIO_FORCE_MUTE_STATUS = 303,
    GET_AUDIO_FORCE_MUTE_STATUS = 304,
    SET_AUDIO_AVOUT_MUTE_STATUS = 305,
    GET_AUDIO_AVOUT_MUTE_STATUS = 306,
    SET_AUDIO_SPDIF_MUTE_STATUS = 307,
    GET_AUDIO_SPDIF_MUTE_STATUS = 308,
    // AUDIO MASTER VOLUME
    SET_AUDIO_MASTER_VOLUME = 309,
    GET_AUDIO_MASTER_VOLUME = 310,
    SAVE_CUR_AUDIO_MASTER_VOLUME = 311,
    GET_CUR_AUDIO_MASTER_VOLUME = 312,
    // AUDIO BALANCE
    SET_AUDIO_BALANCE = 313,
    GET_AUDIO_BALANCE = 314,
    SAVE_CUR_AUDIO_BALANCE = 315,
    GET_CUR_AUDIO_BALANCE = 316,
    // AUDIO SUPPERBASS VOLUME
    SET_AUDIO_SUPPER_BASS_VOLUME = 317,
    GET_AUDIO_SUPPER_BASS_VOLUME = 318,
    SAVE_CUR_AUDIO_SUPPER_BASS_VOLUME = 319,
    GET_CUR_AUDIO_SUPPER_BASS_VOLUME = 320,
    // AUDIO SUPPERBASS SWITCH
    SET_AUDIO_SUPPER_BASS_SWITCH = 321,
    GET_AUDIO_SUPPER_BASS_SWITCH = 322,
    SAVE_CUR_AUDIO_SUPPER_BASS_SWITCH = 323,
    GET_CUR_AUDIO_SUPPER_BASS_SWITCH = 324,
    // AUDIO SRS SURROUND SWITCH
    SET_AUDIO_SRS_SURROUND = 325,
    GET_AUDIO_SRS_SURROUND = 326,
    SAVE_CUR_AUDIO_SRS_SURROUND = 327,
    GET_CUR_AUDIO_SRS_SURROUND = 328,
    // AUDIO SRS DIALOG CLARITY
    SET_AUDIO_SRS_DIALOG_CLARITY = 329,
    GET_AUDIO_SRS_DIALOG_CLARITY = 330,
    SAVE_CUR_AUDIO_SRS_DIALOG_CLARITY = 331,
    GET_CUR_AUDIO_SRS_DIALOG_CLARITY = 332,
    // AUDIO SRS TRUBASS
    SET_AUDIO_SRS_TRU_BASS = 333,
    GET_AUDIO_SRS_TRU_BASS = 334,
    SAVE_CUR_AUDIO_SRS_TRU_BASS = 335,
    GET_CUR_AUDIO_SRS_TRU_BASS = 336,
    // AUDIO BASS
    SET_AUDIO_BASS_VOLUME = 337,
    GET_AUDIO_BASS_VOLUME = 338,
    SAVE_CUR_AUDIO_BASS_VOLUME = 339,
    GET_CUR_AUDIO_BASS_VOLUME = 340,
    // AUDIO TREBLE
    SET_AUDIO_TREBLE_VOLUME = 341,
    GET_AUDIO_TREBLE_VOLUME = 342,
    SAVE_CUR_AUDIO_TREBLE_VOLUME = 343,
    GET_CUR_AUDIO_TREBLE_VOLUME = 344,
    // AUDIO SOUND MODE
    SET_AUDIO_SOUND_MODE = 345,
    GET_AUDIO_SOUND_MODE = 346,
    SAVE_CUR_AUDIO_SOUND_MODE = 347,
    GET_CUR_AUDIO_SOUND_MODE = 348,
    // AUDIO WALL EFFECT
    SET_AUDIO_WALL_EFFECT = 349,
    GET_AUDIO_WALL_EFFECT = 350,
    SAVE_CUR_AUDIO_WALL_EFFECT = 351,
    GET_CUR_AUDIO_WALL_EFFECT = 352,
    // AUDIO EQ MODE
    SET_AUDIO_EQ_MODE = 353,
    GET_AUDIO_EQ_MODE = 354,
    SAVE_CUR_AUDIO_EQ_MODE = 355,
    GET_CUR_AUDIO_EQ_MODE = 356,
    // AUDIO EQ GAIN
    GET_AUDIO_EQ_RANGE = 357,
    GET_AUDIO_EQ_BAND_COUNT = 358,
    SET_AUDIO_EQ_GAIN = 359,
    GET_AUDIO_EQ_GAIN = 360,
    GET_CUR_EQ_GAIN = 361,
    SAVE_CUR_AUDIO_EQ_GAIN = 362,
    SET_AUDIO_EQ_SWITCH = 363,
    // AUDIO SPDIF SWITCH
    SET_AUDIO_SPDIF_SWITCH = 364,
    GET_AUDIO_SPDIF_SWITCH = 365,
    SAVE_CUR_AUDIO_SPDIF_SWITCH = 366,
    GET_CUR_AUDIO_SPDIF_SWITCH = 367,
    // AUDIO SPDIF MODE
    SET_AUDIO_SPDIF_MODE = 368,
    GET_AUDIO_SPDIF_MODE = 369,
    SAVE_CUR_AUDIO_SPDIF_MODE = 370,
    GET_CUR_AUDIO_SPDIF_MODE = 371,
    // amAudio
    OPEN_AMAUDIO = 372,
    CLOSE_AMAUDIO = 373,
    SET_AMAUDIO_INPUT_SR = 374,
    SET_AMAUDIO_OUTPUT_MODE = 375,
    SET_AMAUDIO_MUSIC_GAIN = 376,
    SET_AMAUDIO_LEFT_GAIN = 377,
    SET_AMAUDIO_RIGHT_GAIN = 378,
    SET_AMAUDIO_REV1 = 379,
    SET_AMAUDIO_REV2 = 380,
    SET_AMAUDIO_REV3 = 381,
    SET_AMAUDIO_REV4 = 382,
    SET_AMAUDIO_REV5 = 383,
    //AUDIO INPUT LEVEL SWITCH
    SELECT_LINE_IN_CHANNEL = 384,
    SET_LINE_IN_CAPTURE_VOL = 385,
    HANDLE_AUDIO_HEADSET_PLUG_IN = 386,
    HANDLE_AUDIO_HEADSET_PULL_OUT = 387,
    SET_AUDIO_VOL_COMP = 388,
    GET_AUDIO_VOL_COMP = 389,
    SET_NOISE_GATE_THRESHOLD = 390,
    SAVE_AUDIO_VOL_COMP = 391,
    SET_KALAOK_IO_LEVEL = 392,
    AUDIO_SET_DOUBLE_OUTPUT_ENABLE = 393,
    USB_AUDIO_OUPUT_MODULE_ENABLE = 394,
    GET_USB_AUDIO_DOUBLE_OUPUT_MODULE_ENABLE = 395,
    GET_USB_AUDIO_OUPUT_MODULE_ENABLE = 396,
    SET_AUDIO_SOURCE_FOR_KARAOKE = 397,
    SET_DBX_TV_MODE = 398,
    SET_DRC_ONOFF = 399,
    GET_DRC_ONOFF = 400,
    GET_DBX_TV_MODE = 401,
    //AUDIO END

    // CALLBACK
    SEARCH_CALLBACK = 501,
    SIGLE_DETECT_CALLBACK = 502,
    VGA_CALLBACK = 503,
    DREAM_PANEL_CALLBACK = 504,
    ADC_CALIBRATION_CALLBACK = 505,
    SOURCE_SWITCH_CALLBACK = 506,
    CHANNEL_SELECT_CALLBACK = 507,
    STATUS_3D_CALLBACK = 508,
    DTV_AV_PLAYBACK_CALLBACK = 509,
    SOURCE_CONNECT_CALLBACK = 510,
    HDMIRX_CEC_CALLBACK = 511,
    SERIAL_COMMUNICATION_CALLBACK = 512,
    CLOSE_CAPTION_CALLBACK = 514,
    VCHIP_CALLBACK = 515,
    UPGRADE_FBC_CALLBACK = 516,
    HEADSET_STATUS_CALLBACK = 517,

    RELEASE_RESOURCE_CALLBACK = 530,
    RESOURCES_STATE_CHANGED_CALLBACK = 531,
    RESOURCE_STATE_CHANGED_LISTEN_NOFIFY_CALLBACK = 532,

    SUBTITLE_UPDATE_CALLBACK = 540,
    SCAN_EVENT_CALLBACK = 541,
    EPG_EVENT_CALLBACK = 542,
    VFRAME_BMP_EVENT_CALLBACK = 543,
    // CALLBACK END

    // SSM
    SSM_INIT_DEVICE = 600,
    SSM_SAVE_ONE_BYTE = 601,
    SSM_READ_ONE_BYTE = 602,
    SSM_SAVE_N_BYTES = 603,
    SSM_READ_N_BYTES = 604,
    SSM_SAVE_POWER_ON_OFF_CHANNEL = 605,
    SSM_READ_POWER_ON_OFF_CHANNEL = 606,
    SSM_SAVE_SOURCE_INPUT = 607,
    SSM_READ_SOURCE_INPUT = 608,
    SSM_SAVE_LAST_SOURCE_INPUT = 609,
    SSM_READ_LAST_SOURCE_INPUT = 610,
    SSM_SAVE_SYS_LANGUAGE = 611,
    SSM_READ_SYS_LANGUAGE = 612,
    SSM_SAVE_AGING_MODE = 613,
    SSM_READ_AGING_MODE = 614,
    SSM_SAVE_PANEL_TYPE = 615,
    SSM_READ_PANEL_TYPE = 616,
    SSM_SAVE_MAC_ADDR = 617,
    SSM_READ_MAC_ADDR = 618,
    SSM_SAVE_BAR_CODE = 619,
    SSM_READ_BAR_CODE = 620,
    SSM_SAVE_POWER_ON_MUSIC_SWITCH = 623,
    SSM_READ_POWER_ON_MUSIC_SWITCH = 624,
    SSM_SAVE_POWER_ON_MUSIC_VOL = 625,
    SSM_READ_POWER_ON_MUSIC_VOL = 626,
    SSM_SAVE_SYS_SLEEP_TIMER = 627,
    SSM_READ_SYS_SLEEP_TIMER = 628,
    SSM_SET_BUS_STATUS = 629,
    SSM_GET_BUS_STATUS = 630,
    SSM_SAVE_INPUT_SRC_PARENTAL_CTL = 631,
    SSM_READ_INPUT_SRC_PARENTAL_CTL = 632,
    SSM_SAVE_PARENTAL_CTL_SWITCH = 633,
    SSM_READ_PARENTAL_CTL_SWITCH = 634,
    SSM_SAVE_PARENTAL_CTL_PASS_WORD = 635,
    SSM_READ_PARENTAL_CTL_PASS_WORD = 636,
    SSM_SAVE_USING_DEF_HDCP_KEY_FLAG = 637,
    SSM_READ_USING_DEF_HDCP_KEY_FLAG = 638,
    SSM_GET_CUSTOMER_DATA_START = 639,
    SSM_GET_CUSTOMER_DATA_LEN = 640,
    SSM_SAVE_STANDBY_MODE = 641,
    SSM_READ_STANDBY_MODE = 642,
    SSM_SAVE_LOGO_ON_OFF_FLAG = 643,
    SSM_READ_LOGO_ON_OFF_FLAG = 644,
    SSM_SAVE_HDMIEQ_MODE = 645,
    SSM_READ_HDMIEQ_MODE = 646,
    SSM_SAVE_HDMIINTERNAL_MODE = 647,
    SSM_READ_HDMIINTERNAL_MODE = 648,
    SSM_SAVE_DISABLE_3D = 649,
    SSM_READ_DISABLE_3D = 650,
    SSM_SAVE_GLOBAL_OGOENABLE = 651,
    SSM_READ_GLOBAL_OGOENABLE = 652,
    SSM_SAVE_LOCAL_DIMING_STATUS = 653,
    SSM_READ_LOCAL_DIMING_STATUS = 654,
    SSM_SAVE_NON_STANDARD_STATUS = 655,
    SSM_READ_NON_STANDARD_STATUS = 656,
    SSM_SAVE_ADB_SWITCH_STATUS = 657,
    SSM_READ_ADB_SWITCH_STATUS = 658,
    SSM_SAVE_SERIAL_CMD_SWITCH_STATUS = 659,
    SSM_READ_SERIAL_CMD_SWITCH_STATUS = 660,
    SSM_SET_HDCP_KEY = 661,
    SSM_SAVE_CHROMA_STATUS = 662,
    SSM_SAVE_CA_BUFFER_SIZE = 663,
    SSM_READ_CA_BUFFER_SIZE = 664,
    SSM_GET_ATV_DATA_START = 665,
    SSM_GET_ATV_DATA_LEN = 666,
    SSM_GET_VPP_DATA_START = 667,
    SSM_GET_VPP_DATA_LEN = 668,
    SSM_SAVE_NOISE_GATE_THRESHOLD_STATUS = 669,
    SSM_READ_NOISE_GATE_THRESHOLD_STATUS = 670,
    SSM_SAVE_PROJECT_ID = 673,
    SSM_READ_PROJECT_ID = 674,
    SSM_SAVE_HDCPKEY = 675,
    SSM_READ_HDCPKEY = 676,
    SSM_READ_BLACKOUT_ENABLE = 677,
    SSM_REFRESH_HDCPKEY = 678,

    // Misc
    MISC_PROP_SET = 701,
    MISC_PROP_GET = 702,
    MISC_CFG_SET = 703,
    MISC_CFG_GET = 704,
    MISC_READ_ADC_VAL = 707,
    MISC_SET_WDT_USER_PET = 708,
    MISC_SET_WDT_USER_COUNTER = 709,
    MISC_SET_WDT_USER_PET_RESET_ENABLE = 710,
    MISC_GET_TV_API_VERSION = 713,
    MISC_GET_DVB_API_VERSION = 714,
    MISC_SERIAL_SWITCH = 715,
    MISC_SERIAL_SEND_DATA = 716,
    MISC_CHANNEL_EXPORT = 721,
    MISC_CHANNEL_IMPORT = 722,
    MISC_GET_PROJECT_INFO = 723,
    MISC_GET_PLATFORM_TYPE = 724,

    //Extra
    FORMAT_PARTITION = 801,
    DELETE_DIR_FILES = 802,
    REQUEST_RESOURCES = 803,
    RELEASED_RESOURCES = 804,
    SET_LISTEN_TO_RESOURCE = 805,
    QUERY_RESOURCE_STATE = 806,
    GET_DISPLAY_RESOLUTION_CONFIG = 808,
    GET_DISPLAY_RESOLUTION_INFO = 809,
    HDMIRX_CEC_SEND_CUSTOM_MESSAGE = 810,
    HDMIRX_CEC_SEND_CUSTOM_WAIT_REPLY_MESSAGE = 811,
    HDMIRX_CEC_SEND_BROADCAST_STANDBY_MESSAGE = 812,
    HDMIRX_CEC_SEND_GIVE_CEC_VERSION_MESSAGE = 813,
    HDMIRX_CEC_SEND_GIVE_DEV_VENDOR_ID_MESSAGE = 814,
    HDMIRX_CEC_SEND_GIVE_OSD_NAME_MESSAGE = 815,
    GET_HDMI_KSV_INFO = 816,
    HDMI_OUT_TOWHAT = 817,
    SET_DEBUG_SERIAL_PORT_ONOFF = 818,
    GET_DEBUG_SERIAL_PORT_ONOFF = 819,
    DO_SUSPEND = 820,
    DO_RESUME = 821,

    //Fac_FBC
    FACTORY_FBC_UPGRADE = 900,
    FACTORY_FBC_SET_BRIGHTNESS = 901,
    FACTORY_FBC_GET_BRIGHTNESS = 902,
    FACTORY_FBC_SET_CONTRAST = 903,
    FACTORY_FBC_GET_CONTRAST = 904,
    FACTORY_FBC_SET_SATURATION = 905,
    FACTORY_FBC_GET_SATURATION = 906,
    FACTORY_FBC_SET_HUE = 907,
    FACTORY_FBC_GET_HUE = 908,
    FACTORY_FBC_SET_BACKLIGHT = 909,
    FACTORY_FBC_GET_BACKLIGHT = 910,
    FACTORY_FBC_SET_PIC_MODE = 911,
    FACTORY_FBC_GET_PIC_MODE = 912,
    FACTORY_FBC_SET_GAIN_RED = 913,
    FACTORY_FBC_GET_GAIN_RED = 914,
    FACTORY_FBC_SET_GAIN_GREEN = 915,
    FACTORY_FBC_GET_GAIN_GREEN = 916,
    FACTORY_FBC_SET_GAIN_BLUE = 917,
    FACTORY_FBC_GET_GAIN_BLUE = 918,
    FACTORY_FBC_SET_OFFSET_RED = 919,
    FACTORY_FBC_GET_OFFSET_RED = 920,
    FACTORY_FBC_SET_OFFSET_GREEN = 921,
    FACTORY_FBC_GET_OFFSET_GREEN = 922,
    FACTORY_FBC_SET_OFFSET_BLUE = 923,
    FACTORY_FBC_GET_OFFSET_BLUE = 924,
    FACTORY_FBC_SET_COLORTEMP_MODE = 925,
    FACTORY_FBC_GET_COLORTEMP_MODE = 926,
    FACTORY_FBC_SET_WB_INIT = 927,
    FACTORY_FBC_GET_WB_INIT = 928,
    FACTORY_FBC_SET_TEST_PATTERN = 929,
    FACTORY_FBC_GET_TEST_PATTERN = 930,
    FACTORY_FBC_SET_BACKLIGHT_EN = 931,
    FACTORY_FBC_GET_BACKLIGHT_EN = 932,
    FACTORY_FBC_SET_LVDS_SSG = 933,
    FACTORY_FBC_SET_ELEC_MODE = 934,
    FACTORY_FBC_GET_MAINCODE_VERSION = 935,
    FACTORY_FBC_PANEL_POWER_SWITCH = 936,
    FACTORY_FBC_PANEL_GET_INFO = 937,
    FACTORY_FBC_PANEL_SUSPEND = 938,
    FACTORY_FBC_SEND_KEY_TO_FBC = 939,
    FACTORY_FBC_POWER_REBOOT = 940,
    FACTORY_FBC_GET_ELEC_MODE = 941,
    FACTORY_FBC_PANEL_USER_SETTING_DEFAULT = 942,
    FACTORY_FBC_SET_BACKLIGHT_N360 = 943,
    FACTORY_FBC_GET_BACKLIGHT_N360 = 944,
    FACTORY_FBC_SET_COLORTEMP_MODE_N360 = 945,
    FACTORY_FBC_GET_COLORTEMP_MODE_N360 = 946,
    FACTORY_FBC_SET_LOCKN_STATE_N360 = 947,
    FACTORY_FBC_TEST_PATTERN = 950,
    FACTORY_FBC_VIDEO_MUTE = 951,

    //T868 for cvt
    FACTORY_SETBACKLIGHT_PWM_FREQUENCY = 1000,
    FACTORY_GETBACKLIGHT_PWM_FREQUENCY = 1001,
    FACTORY_SETBACKLIGHT_SWITCH_STATUS = 1002,
    FACTORY_GETBACKLIGHT_SWITCH_STATUS = 1003,
    FACTORY_SETBACKLIGHT_PWM_DUTY = 1004,
    FACTORY_GETBACKLIGHT_PWM_DUTY = 1005,
    FACTORY_SETLVDS_COLOR_DEPTH = 1006,
    FACTORY_GETLVDS_COLOR_DEPTH = 1007,
    FACTORY_SETLVDS_DITHER_STATUS = 1008,
    FACTORY_GETLVDS_DITHER_STATUS = 1009,
    FACTORY_SETLVDS_MAPPING_STATUS = 1010,
    FACTORY_GETLVDS_MAPPING_STATUS = 1011,
    FACTORY_SETLVDS_PORT_SWAP_STATUS = 1012,
    FACTORY_GETLVDS_PORT_SWAP_STATUS = 1013,
    FACTORY_SET_SN = 1014,
    FACTORY_GET_SN = 1015,

    //Uniform White Balance
    FACTORY_WHITE_BALANCE_SET_GAIN_RED = 1100,
    FACTORY_WHITE_BALANCE_GET_GAIN_RED = 1101,
    FACTORY_WHITE_BALANCE_SET_GAIN_GREEN = 1102,
    FACTORY_WHITE_BALANCE_GET_GAIN_GREEN = 1103,
    FACTORY_WHITE_BALANCE_SET_GAIN_BLUE = 1104,
    FACTORY_WHITE_BALANCE_GET_GAIN_BLUE = 1105,
    FACTORY_WHITE_BALANCE_SET_OFFSET_RED = 1106,
    FACTORY_WHITE_BALANCE_GET_OFFSET_RED = 1107,
    FACTORY_WHITE_BALANCE_SET_OFFSET_GREEN = 1108,
    FACTORY_WHITE_BALANCE_GET_OFFSET_GREEN = 1109,
    FACTORY_WHITE_BALANCE_SET_OFFSET_BLUE = 1110,
    FACTORY_WHITE_BALANCE_GET_OFFSET_BLUE = 1111,
    FACTORY_WHITE_BALANCE_GET_COLOR_TMP = 1112,
    FACTORY_WHITE_BALANCE_SET_COLOR_TMP = 1113,
    FACTORY_WHITE_BALANCE_SAVE_PRAMAS = 1114,
    FACTORY_WHITE_BALANCE_SET_WB_INIT = 1115,
    FACTORY_WHITE_BALANCE_GET_WB_INIT = 1116,
    FACTORY_WHITE_BALANCE_SET_GRAY_PATTERN = 1117,
    FACTORY_WHITE_BALANCE_GET_GRAY_PATTERN = 1118,
    FACTORY_WHITE_BALANCE_OPEN_GRAY_PATTERN = 1119,
    FACTORY_WHITE_BALANCE_CLOSE_GRAY_PATTERN = 1120,
    FACTORY_WHITE_BALANCE_GET_ALL_PRAMAS = 1121,

    //dtv
    ATV_DTV_SCAN_PAUSE = 1378,
    ATV_DTV_SCAN_RESUME = 1379,
    DTV_GET_SUBTITLE_SWITCH = 1380,
    DTV_SUBTITLE_INIT = 1381,
    DTV_SUBTITLE_LOCK = 1382,
    DTV_SUBTITLE_UNLOCK = 1383,
    DTV_START_SUBTITLE = 1384,
    DTV_STOP_SUBTITLE = 1385,
    DTV_GET_SUBTITLE_INDEX = 1386,
    DTV_SET_SUBTITLE_INDEX = 1387,
    ATV_GET_CURRENT_PROGRAM_ID = 1389,
    DTV_GET_CURRENT_PROGRAM_ID = 1390,
    ATV_SAVE_PROGRAM_ID = 1391,
    DTV_SCAN_MANUAL_BETWEEN_FREQ = 1392,
    ATV_GET_MIN_MAX_FREQ = 1393,
    DTV_GET_SCAN_FREQUENCY_LIST = 1394,
    DTV_GET_CHANNEL_INFO = 1395,
    ATV_GET_CHANNEL_INFO = 1396,
    ATV_SCAN_MANUAL = 1397,
    ATV_SCAN_AUTO = 1398,
    DTV_SCAN_MANUAL = 1399,
    DTV_SCAN_AUTO = 1400,
    TV_SUBTITLE_DRAW_END = 1401,
    PLAY_PROGRAM = 1402,
    STOP_PROGRAM_PLAY = 1403,
    GET_PROGRAM_LIST = 1404,
    STOP_SCAN = 1405,
    DTV_GET_SNR = 1406,
    DTV_GET_BER = 1407,
    DTV_GET_STRENGTH = 1408,
    DTV_GET_AUDIO_TRACK_NUM = 1409,
    DTV_GET_AUDIO_TRACK_INFO = 1410,
    DTV_SWITCH_AUDIO_TRACK = 1411,
    DTV_GET_EPG_UTC_TIME = 1412,
    DTV_GET_CUR_FREQ = 1413,
    DTV_GET_EPG_INFO_POINT_IN_TIME = 1414,
    DTV_GET_EPG_INFO_DURATION = 1415,
    SET_FRONTEND_PARA = 1416,
    DTV_SET_PROGRAM_NAME = 1417,
    DTV_SET_PROGRAM_SKIPPED = 1418,
    DTV_SET_PROGRAM_FAVORITE = 1419,
    DTV_DETELE_PROGRAM  = 1420,
    DTV_SWAP_PROGRAM = 1421,
    DTV_SET_PROGRAM_LOCKED = 1422,
    DTV_SET_BOOKING_FLAG = 1423,
    DTV_GET_BOOKED_EVENT = 1424,
    DTV_GET_CURR_AUDIO_TRACK_INDEX = 1425,
    DTV_SET_AUDIO_CHANNEL_MOD = 1426,
    DTV_GET_AUDIO_CHANNEL_MOD = 1427,
    DTV_GET_FREQ_BY_PROG_ID = 1428,
    DTV_GET_VIDEO_FMT_INFO = 1429,
    DTV_START_RECORD = 1430,
    DTV_STOP_RECORD = 1431,
    DTV_SET_RECORD_ALL_TS = 1432,
    DTV_SCAN_AUTO_ATSC = 1433,
    DTV_TEST_1 = 1451,
    DTV_TEST_2 = 1452,
    DTV_TEST_3 = 1453,
    TV_CLEAR_ALL_PROGRAM = 1454,
    SET_BLACKOUT_ENABLE = 1455,
    START_AUTO_BACKLIGHT = 1456,
    STOP_AUTO_BACKLIGHT = 1457,
    IS_AUTO_BACKLIGHTING = 1458,
    HDMIAV_HOTPLUGDETECT_ONOFF = 1459,
    GET_AVERAGE_LUMA = 1480,
    GET_AUTO_BACKLIGHT_DATA = 1481,
    SET_AUTO_BACKLIGHT_DATA = 1482,
    TV_PRINT_DEBUG_INFO = 2000,

    //customer interface
    FACTROY_FBC_SET_LIGHT_SENSOR_STATUS_N310 = 3000,
    FACTROY_FBC_GET_LIGHT_SENSOR_STATUS_N310 = 3001,
    FACTROY_FBC_SET_DREAM_PANEL_STATUS_N310 = 3002,
    FACTROY_FBC_GET_DREAM_PANEL_STATUS_N310 = 3003,
    SSM_EEPROM_SAVE_ONE_BYTE_N310_N311 = 3004,
    SSM_EEPROM_READ_ONE_BYTE_N310_N311 = 3005,
    SSM_EEPROM_SAVE_N_BYTES_N310_N311 = 3006,
    SSM_EEPROM_READ_N_BYTES_N310_N311 = 3007,
    SSM_FLASH_SAVE_ONE_BYTE_N310_N311 = 3008,
    SSM_FLASH_READ_ONE_BYTE_N310_N311 = 3009,
    SSM_FLASH_SAVE_N_BYTES_N310_N311 = 3010,
    SSM_FLASH_READ_N_BYTES_N310_N311 = 3011,
    FACTROY_FBC_SET_MULT_PQ_STATUS_N310 = 3012,
    FACTROY_FBC_GET_MULT_PQ_STATUS_N310 = 3013,
    FACTROY_FBC_SET_MEMC_STATUS_N310 = 3014,
    FACTROY_FBC_GET_MEMC_STATUS_N310 = 3015,
    FACTROY_FBC_SET_BACKLIGNT_N310 = 3016,
    FACTROY_FBC_GET_BACKLIGNT_N310 = 3017,
    FACTROY_FBC_SET_COLORTEMP_MODE_N310 = 3018,
    FACTROY_FBC_GET_COLORTEMP_MODE_N310 = 3019,
    FACTROY_SET_VBYONE_SPREAD_SPECTRUM_N311 = 3020,
    FACTROY_GET_VBYONE_SPREAD_SPECTRUM_N311 = 3021,
    GET_HISTGRAM_N311 = 3022,
    FACTROY_SET_IIC_BUS_ONOFF_N311 = 3023,
    FACTROY_GET_IIC_BUS_ONOFF_N311 = 3024,
    SET_SPLIT_SCREEN_DEMO_ONOFF_N390 = 3025,
    GET_SPLIT_SCREEN_DEMO_ONOFF_N390 = 3026,
    FACTROY_FBC_SET_BLUETOOTH_IIS_N310 = 3027,
    FACTROY_FBC_GET_BLUETOOTH_IIS_N310 = 3028,
    FACTROY_FBC_SET_LED_N310 = 3029,
    MISC_SET_2K_TO_4K_SCALE_UP_MODE = 3030,
    MISC_GET_2K_TO_4K_SCALE_UP_MODE = 3031,
    MISC_SET_FBC_AP_STANDBY_N310 = 3032,
    MISC_GET_FBC_AP_STANDBY_N310 = 3033,
    MISC_SET_BLUETOOTH_VOL_N311_N310 = 3034,
    MISC_GET_BLUETOOTH_VOL_N311_N310 = 3035,

    // 2.4G headset
    START_OPEN_HEADSET_DETECT = 4000,

};

#endif  //ANDROID_AMLOGIC_TVCMD_H
