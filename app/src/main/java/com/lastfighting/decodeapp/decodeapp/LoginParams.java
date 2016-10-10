package com.lastfighting.decodeapp.decodeapp;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * Created by cz542 on 2016/9/21.
 */

public class LoginParams
{
    private static String hunan = "@xykd";
    static String mHostname;
    static String mMacaddr = null;

    static
    {
        mHostname = null;
    }

    private static String genMD5(byte[] paramArrayOfByte)
    {
        int i = 0;
        char[] arrayOfChar1 = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
        try
        {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramArrayOfByte);
            byte[] arrayOfByte = localMessageDigest.digest();
            char[] arrayOfChar2 = new char[32];
            int j = 0;
            for (;;)
            {
                if (i >= 16) {
                    return new String(arrayOfChar2);
                }
                int k = arrayOfByte[i];
                int m = j + 1;
                arrayOfChar2[j] = arrayOfChar1[(0xF & k >>> 4)];
                j = m + 1;
                arrayOfChar2[m] = arrayOfChar1[(k & 0xF)];
                i++;
            }
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return null;
    }

    public static String getClientId(Context paramContext)
    {
        return getIMEI(paramContext);
    }

    private static String getDeviceId(Context paramContext)
    {
        return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    }

    public static String getHostname()
    {
        return Build.MODEL;
    }

    private static String getIMEI(Context paramContext)
    {
        TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService(Context.TELEPHONY_SERVICE);
        String str1 = localTelephonyManager.getDeviceId();
        if ((str1 != null) && (!"".equals(str1))) {
            return str1;
        }
        String str2 = localTelephonyManager.getDeviceId();
        String str3 = localTelephonyManager.getSimSerialNumber();
        UUID localUUID = new UUID(Settings.Secure.getString(paramContext.getContentResolver(), "android_id").hashCode(), str2.hashCode() << 32 | str3.hashCode());
        if ((localUUID.toString() != null) && (!"".equals(localUUID.toString()))) {
            return localUUID.toString();
        }
        return getIMEI2(paramContext);
    }

    private static String getIMEI2(Context paramContext)
    {
        String str = ((TelephonyManager)paramContext.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        Log.i("info1", "tmDevice=" + str);
        if ((str == null) || ("".equals(str))) {
            str = getSerialNumber();
        }
        if ((str == null) || ("".equals(str))) {
            str = getDeviceId(paramContext);
        }
        Log.i("info1", "tmDevice=" + str);
        return new String(genMD5(str.getBytes()));
    }

    public static String getIpv4(Context paramContext)
    {
        WifiManager localWifiManager = (WifiManager)paramContext.getSystemService(Context.WIFI_SERVICE);
        if (localWifiManager.isWifiEnabled())
        {
            String str = intToIp(localWifiManager.getConnectionInfo().getIpAddress());
            Log.i("ppp", str);
            return str;
        }
        return "0.0.0.0";
    }
    public static String getIpv6()
    {
        return "fe80::d59c:43f8:b941:28d3%11";
    }

    private static String getMACAddr(Context paramContext)
    {
        if (mMacaddr != null) {
            return mMacaddr;
        }
        String str = ((WifiManager)paramContext.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getMacAddress();
        if (str != null) {
            mMacaddr = str;
        }
        if (mMacaddr != null) {
            return mMacaddr;
        }
        return "";
    }
    private static String getSerialNumber()
    {
        try
        {
            Class localClass = Class.forName("android.os.SystemProperties");
            String str = (String)localClass.getMethod("get", new Class[] { String.class }).invoke(localClass, new Object[] { "ro.serialno" });
            return str;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return null;
    }

    public static String getTicketUserAgent(Context paramContext)
    {
        try
        {
            paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
            String str = "CCTP/AndroidPhone/@".replace("@", String.valueOf(Constant.VersionCode));
            return str;
        }
        catch (Exception localException)
        {
            Logger.write("HNSDKCDCLogs", "LoginParams getTicketUserAgent Exception");
            Logger.write("HNSDKCDCLogs", Logger.getStackElement(localException));
            Logger.write("HNSDKCDCLogs", localException.toString());
            localException.printStackTrace();
        }
        return "CCTP/ANDROID/1";
    }

    static String intToIp(int paramInt)
    {
        return (paramInt & 0xFF) + "." + (0xFF & paramInt >> 8) + "." + (0xFF & paramInt >> 16) + "." + (0xFF & paramInt >> 24);
    }

    public static void setwifiinfo(Context paramContext)
    {
        WifiInfo localWifiInfo = ((WifiManager)paramContext.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo();
        if (localWifiInfo != null)
        {
            String str = localWifiInfo.getSSID();
            PreferencesUtils.putString(paramContext, Constant.WIFISSID, str);
        }
    }
}