package com.lastfighting.decodeapp.decodeapp;
import android.content.Context;
/**
 * Created by cz542 on 2016/9/20.
 */
public class PortalShared
{
    private static PortalShared _instance = null;
    private PortalInfoMgr infoMgr = null;
    private Context mContext = null;
    private PortalCipher portalCipher = null;

    public static PortalShared getInstance()
    {
        if (_instance == null) {
            _instance = new PortalShared();
        }
        return _instance;
    }

    public PortalInfoMgr getInfoMgr()
    {
        return this.infoMgr;
    }

    public PortalCipher getPortalCipher()
    {
        return this.portalCipher;
    }

    public Context getmContext()
    {
        return this.mContext;
    }

    public void setInfoMgr(PortalInfoMgr paramPortalInfoMgr)
    {
        this.infoMgr = paramPortalInfoMgr;
    }

    public void setPortalCipher(PortalCipher paramPortalCipher)
    {
        this.portalCipher = paramPortalCipher;
    }

    public void setmContext(Context paramContext)
    {
        this.mContext = paramContext;
    }
}
