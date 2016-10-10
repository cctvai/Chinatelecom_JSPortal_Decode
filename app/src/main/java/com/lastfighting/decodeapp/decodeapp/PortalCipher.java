package com.lastfighting.decodeapp.decodeapp;
import android.content.Context;
import android.content.res.AssetManager;

import com.cndatacom.dykeylib.DyKeyCodeInfo;
import com.cndatacom.dykeylib.DyKeyDecodeInfo;
import com.cndatacom.dykeylib.DyKeyInfo;
import com.cndatacom.dykeylib.DykeyJNILib;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
/**
 * Created by cz542 on 2016/9/20.
 */

public class PortalCipher
{
    private boolean isInitialed = false;
    private Context mContext = null;
    private String mUser = null;
    private String malgoId = null;
    private String mkey = null;
    private String mkeyId = null;

    public void copyAssetFile(InputStream paramInputStream, String paramString)
    {
        try
        {
            FileOutputStream localFileOutputStream = new FileOutputStream(paramString);
            byte[] arrayOfByte = new byte['?'];
            for (;;)
            {
                int i = paramInputStream.read(arrayOfByte);
                if (i == -1)
                {
                    localFileOutputStream.close();
                    paramInputStream.close();
                    return;
                }
                localFileOutputStream.write(arrayOfByte, 0, i);
            }
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
    }

    public void copyFile(String paramString1, String paramString2)
    {
        try
        {
            if (new File(paramString1).exists())
            {
                FileInputStream localFileInputStream = new FileInputStream(paramString1);
                FileOutputStream localFileOutputStream = new FileOutputStream(paramString2);
                byte[] arrayOfByte = new byte['?'];
                for (;;)
                {
                    int i = localFileInputStream.read(arrayOfByte);
                    if (i == -1)
                    {
                        localFileOutputStream.close();
                        localFileInputStream.close();
                        return;
                    }
                    localFileOutputStream.write(arrayOfByte, 0, i);
                }
            }
            return;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
    }

    public String getAlgoId()
    {
        return this.malgoId;
    }

    public String getKey()
    {
        return this.mkey;
    }

    public String getKeyId()
    {
        return this.mkeyId;
    }

    public String getUser()
    {
        return this.mUser;
    }

    public void getZsmInfo() {
        if (this.mkey == null) {
            this.mkey = new String("testKey");
        }
        if (DykeyJNILib.dyKeySetDir(this.mContext.getFilesDir().getPath() + "/") != 0) {}
        DyKeyInfo localDyKeyInfo;
        do
        {
            localDyKeyInfo = DykeyJNILib.dyKeyInfo(this.mUser);
        } while (localDyKeyInfo.ret != 0);
        this.mkeyId = localDyKeyInfo.keyID;
        this.malgoId = localDyKeyInfo.algoID;
    }

    public boolean isFileExists(String paramString)
    {
        try
        {
            boolean bool1 = new File(paramString).exists();
            boolean bool2 = false;
            if (bool1) {
                bool2 = true;
            }
            return bool2;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return false;
    }

    public boolean isInitial()
    {
        return this.isInitialed;
    }

    public void setAlgoId(String paramString)
    {
        this.malgoId = paramString;
    }

    public void setContext(Context paramContext)
    {
        this.mContext = paramContext;
    }

    public void setKey(String paramString)
    {
        this.mkey = paramString;
    }

    public void setKeyId(String paramString)
    {
        this.mkeyId = paramString;
    }

    public void setUser(String paramString)
    {
        CRC32 localCRC32 = new CRC32();
        localCRC32.update(paramString.getBytes());
        this.mUser = Long.toString(localCRC32.getValue());
    }

    public byte[] zsmDecrypt(byte[] paramArrayOfByte)
    {
        if (this.mUser == null) {
            return null;
        }
        DyKeyDecodeInfo localDyKeyDecodeInfo;
        do
        {
            DykeyJNILib.dyKeySetDir(this.mContext.getFilesDir().getPath() + "/");
            localDyKeyDecodeInfo = DykeyJNILib.dyKeyDecode(this.mUser, new String(paramArrayOfByte));
        } while (localDyKeyDecodeInfo.ret != 0);
        return localDyKeyDecodeInfo.strOut.getBytes();
    }

    public byte[] zsmEncrypt(byte[] paramArrayOfByte)
    {
        if (this.mUser == null) {}
        DyKeyCodeInfo localDyKeyCodeInfo;
        do
        {
            String str = this.mContext.getFilesDir().getPath();
            if(DykeyJNILib.dyKeySetDir(str + "/") != 0){
                Logger.write("HNSDKCDCLogs", "DoTicket strDir : " + str);
                return null;
            }
            localDyKeyCodeInfo = DykeyJNILib.dyKeyCode(this.mUser, new String(paramArrayOfByte));
        } while (localDyKeyCodeInfo.ret != 0);
        return localDyKeyCodeInfo.strOut.getBytes();
    }

    public int zsmInitial()
    {
        String str2=null;
        AssetManager localAssetManager= mContext.getResources().getAssets();;
        if (!this.isInitialed)
        {
            String str1 = this.mContext.getFilesDir().getPath();
            File localFile = new File(str1 + "/zsm/");
            str2 = str1 + "/initial.zsm";
            if (!localFile.exists()) {
                localFile.mkdir();
            }
            if (!isFileExists(str2)) {
                localAssetManager = this.mContext.getAssets();
            }
        }
        try
        {
            copyAssetFile(localAssetManager.open("libzsm.so"), str2);
            this.isInitialed = true;
            return 0;
        }
        catch (IOException localIOException)
        {
            localIOException.printStackTrace();
        }
        return 0;
    }

    public int zsmUpdate(byte[] paramArrayOfByte)
    {
        if (this.mUser == null) {
            return -1;
        }
        if (paramArrayOfByte == null) {
            return -2;
        }
        String str = this.mContext.getFilesDir().getPath() + "/zsm/" + this.mUser + ".zsm";
        copyAssetFile(new ByteArrayInputStream(paramArrayOfByte), str);
        return 0;
    }
}