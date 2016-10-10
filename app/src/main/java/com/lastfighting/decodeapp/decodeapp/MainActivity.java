package com.lastfighting.decodeapp.decodeapp;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.zip.CRC32;

/**
 * A login screen that offers login via email/password.
 */
public class MainActivity extends AppCompatActivity {
    PortalShared localPortalShared;
    PortalCipher localPortalCipher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText user=(EditText)findViewById(R.id.editText3);
        final EditText decodedata=(EditText)findViewById(R.id.editText);
        final EditText encodedata=(EditText)findViewById(R.id.editText2);
        //user.setText("gk20151014153438");

        //String str="53917F114AC2214AEE7FF667DAE2F41CE7046E5E7E1D34E0E0AA5FF0305F30687594B3FEA939C26178CC5F262F284013AD16DE8AB848B7A6D1E8CB077FBC00E3C6AABA8A6EF5E185A49EF3D537A4A956AA50FFC97903BC406FF3C08AB21A874DDA19AE12D318E0505BB6D52295DA1384400891F99555A52393FFFE77E08AF8F733A622CFC3D0E45F334DDB7455C771EB588DAD5889C553677147267152647D23D767EDB7D4EA8D60A9FA279BE00C7E7168DF56D1DE22EF1A75FF9D6BD8A2E6D4D1E8CB077FBC00E3FECA898D2560F6400A23C97E19D82962A655F4710401BBCB98B101B3D985F0DACFDDF09DB7BC30A077EA63947A4E46921E69896D0BF61BC86BBEB5081B82129B73A754AABEF7D7D71A05EC6D83BB6EBFD8018ACF6DC5EC401367D531D0AEA263217D2B044AF30991261EE2FFB13400268ACB0E9E408E430F6A017B5AAB9AD9BB";
        //encodedata.setText(str);

        Initial(this);
        Button bt1 = (Button)findViewById(R.id.button);
        View.OnClickListener bt1listen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=encodedata.getText().toString();
                if(str != null && !str.isEmpty()) {
                    String data = decode(str, MainActivity.this, user.getText().toString());
                    decodedata.setText(data);
                }
            }
        };
        bt1.setOnClickListener(bt1listen);

        Button bt2 = (Button)findViewById(R.id.button2);
        View.OnClickListener bt2listen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    CRC32 localCRC32 = new CRC32();
                    localCRC32.update(user.getText().toString().getBytes());
                    String mUser = Long.toString(localCRC32.getValue());

                    File sdCard = Environment.getExternalStorageDirectory();
                    sdCard = new File(sdCard, "/"+mUser+".zsm");
                    try {
                        FileInputStream fin = new FileInputStream(sdCard);
                        int length = fin.available();
                        byte [] buffer = new byte[length];
                        fin.read(buffer);
                        fin.close();
                        zsmUpdate(user.getText().toString(),buffer);
                        new AlertDialog.Builder(MainActivity.this)
                                .setMessage("加载成功")
                                .setPositiveButton("确定",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialoginterface, int i) {
                                                //按钮事件
                                            }
                                        })
                                .show();
                    }catch (FileNotFoundException localException) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setMessage("文件不存在")
                                .setPositiveButton("确定",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialoginterface, int i) {
                                                //按钮事件
                                            }
                                        })
                                .show();
                    }catch (Exception localException) {
                        localException.printStackTrace();
                    }

                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        bt2.setOnClickListener(bt2listen);

        Button bt3 = (Button)findViewById(R.id.button3);
        View.OnClickListener bt3listen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String str=decodedata.getText().toString();
                    if(str != null && !str.isEmpty()) {
                        String data = encode(str, MainActivity.this, user.getText().toString());
                        encodedata.setText(data);
                    }
            }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        bt3.setOnClickListener(bt3listen);
    }
    private void Initial(Context paramContext){
        localPortalShared = PortalShared.getInstance();
        localPortalCipher = localPortalShared.getPortalCipher();
        if (localPortalCipher == null) {
            localPortalCipher = new PortalCipher();
            localPortalShared.setPortalCipher(localPortalCipher);
        }
        localPortalCipher.setContext(paramContext);
        if (!localPortalCipher.isInitial()) {
            localPortalCipher.zsmInitial();
        }
    }
    private void zsmUpdate(String user,byte[] data){
        localPortalCipher.setUser(user);
        localPortalCipher.zsmUpdate(data);
    }
    private String encode(String paramString, Context paramContext,String user){
        localPortalCipher.setUser(user);
        localPortalCipher.getZsmInfo();
        String data="加密失败";
        byte[] arrayOfByte = localPortalCipher.zsmEncrypt(paramString.getBytes());
        if(arrayOfByte!=null) {
            data = new String(arrayOfByte);
        }
        new AlertDialog.Builder(this)
                .setMessage(data)
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                //按钮事件
                            }
                        })
                .show();
        return data;
    }
    private String decode(String paramString, Context paramContext,String user) {
        localPortalCipher.setUser(user);
        localPortalCipher.getZsmInfo();
        String data="解密失败";

        byte[] array = localPortalCipher.zsmDecrypt(paramString.getBytes());
        if(array!=null) {
            data = new String(array);
        }

        new AlertDialog.Builder(this)
                .setMessage(data)
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                //按钮事件
                            }
                        })
                .show();
        return data;
    }
}
