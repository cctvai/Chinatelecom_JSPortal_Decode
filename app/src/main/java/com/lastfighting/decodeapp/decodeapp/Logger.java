package com.lastfighting.decodeapp.decodeapp;
import android.annotation.SuppressLint;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cz542 on 2016/9/20.
 */

@SuppressLint({"SimpleDateFormat"})
public class Logger
{
    public static String getStackElement(Throwable paramThrowable)
    {
        try
        {
            StringWriter localStringWriter = new StringWriter();
            paramThrowable.printStackTrace(new PrintWriter(localStringWriter));
            String str = localStringWriter.toString();
            return str;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return "";
    }

    public static void write(String paramString1, String paramString2)
    {
        if (Constant.SeeLog) {
            Log.i(paramString1, paramString2);
        }
        if (!Constant.LogWrite) {}
        while (!"mounted".equals(Environment.getExternalStorageState())) {
            return;
        }
        String str1 = Environment.getExternalStorageDirectory().getPath() + "/" + paramString1 + "/";
        try
        {
            Date localDate = new Date();
            SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("yyyyMMdd");
            String str2 = localSimpleDateFormat1.format(localDate);
            String str3 = localSimpleDateFormat2.format(localDate);
            File localFile1 = new File(str1);
            if (!localFile1.exists()) {
                localFile1.mkdirs();
            }
            File localFile2 = new File(str1 + str3 + ".txt");
            if (!localFile2.exists()) {
                localFile2.createNewFile();
            }
            FileWriter localFileWriter = new FileWriter(localFile2, true);
            localFileWriter.append("\r\n=============" + str2 + "=====================\r\n");
            localFileWriter.append(paramString2);
            localFileWriter.flush();
            localFileWriter.close();
            return;
        }
        catch (IOException localIOException) {}
    }

    public static void xjhui_write(String paramString1, String paramString2)
    {
        if (Constant.SeeLog) {
            Log.i(paramString1, paramString2);
        }
    }
}
