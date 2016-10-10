package com.cndatacom.dykeylib;

/**
 * Created by cz542 on 2016/9/20.
 */

public class DykeyJNILib
{
    static
    {
        System.loadLibrary("DyKey");
    }

    public static native DyKeyCodeInfo dyKeyCode(String paramString1, String paramString2);

    public static native DyKeyDecodeInfo dyKeyDecode(String paramString1, String paramString2);

    public static native DyKeyInfo dyKeyInfo(String paramString);

    public static native int dyKeySetDir(String paramString);
}
