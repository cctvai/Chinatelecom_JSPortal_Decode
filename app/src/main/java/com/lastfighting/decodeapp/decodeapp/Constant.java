package com.lastfighting.decodeapp.decodeapp;

/**
 * Created by cz542 on 2016/9/20.
 */
public class Constant
{
    public static String AREA;
    public static String AUTHAUTHURL;
    public static String AUTHDEFAULT;
    public static String AUTHPOSTFIX;
    public static String AUTHSTATEURL;
    public static String AUTHTYPE;
    public static String Action_NetworkChanged;
    public static String Action_Selled;
    public static String Action_Termed;
    public static String Action_login_Usb;
    public static String CLIENT_ID;
    public static final String DEFAULT_MAC_ADDRESS = "02:00:00:00:00:00";
    public static String DOMAIN;
    public static String EXPIRE;
    public static String GetPhoneMarketingData;
    public static String HOSTNAME;
    public static int Handler_NetworkChanged = 0;
    public static int Handler_PostData = 0;
    public static int Handler_ShowMessage = 0;
    public static int Handler_UpdateTimes = 0;
    public static String INTERVAL = "interval";
    public static String IPV4;
    public static String IPV6;
    public static String KEEP_RETRY;
    public static String KEEP_URL;
    public static String LEVEL = "level";
    public static boolean LogWrite = false;
    public static String MACADDR;
    public static int NoticeID_Icon = 0;
    public static String OFFLINE;
    public static String ONLINE;
    public static final String PostErrorLog;
    public static String QueryURL;
    public static String SCHOOLID;
    public static boolean SeeLog = false;
    public static String TERM_URL;
    public static String TICKET;
    public static String TICKETURL;
    public static final String Tags = "HNSDKCDCLogs";
    public static String USERID;
    public static String USER_AGENT;
    public static boolean Version;
    public static int VersionCode = 2101;
    public static String VersionName = "2.2.2101.0825";
    public static String WIFISSID;
    public static String WLANACIP;
    public static String WLANUSERIP;
    public static String ipip1;
    public static String secret;

    static
    {
        LogWrite = true;
        SeeLog = false;
        Version = true;
        ONLINE = "1";
        OFFLINE = "0";
        USER_AGENT = "user-agent";
        CLIENT_ID = "client-id";
        IPV4 = "ipv4";
        IPV6 = "ipv6";
        MACADDR = "macaddr";
        HOSTNAME = "hostName";
        secret = "Eshore!@#";
        ipip1 = "http://218.77.121.111";
        GetPhoneMarketingData = ipip1 + "/EsurfingClient/Other/GetPhoneMarketingData.ashx";
        PostErrorLog = ipip1 + "/EsurfingClient/Other/PostErrorLog.ashx";
        NoticeID_Icon = 1;
        Handler_UpdateTimes = 1;
        Handler_NetworkChanged = 2;
        Handler_ShowMessage = 4;
        Handler_PostData = 5;
        Action_NetworkChanged = "com.cndatacom.campus.cdccportalgd.ActionReceiver_NetworkChanged";
        Action_Termed = "com.cndatacom.campus.cdccportalgd.ActionReceiver_Term";
        Action_Selled = "com.cndatacom.campus.cdccportalgd.ActionReceiver_Selled";
        Action_login_Usb = "com.cndatacom.campus.cdccportalgd.ActionReceiver_login_Usb";
        WLANUSERIP = "wlanuserip";
        WLANACIP = "wlanacip";
        SCHOOLID = "schoolid";
        DOMAIN = "domain";
        AREA = "area";
        QueryURL = "queryURL";
        AUTHTYPE = "authType";
        AUTHDEFAULT = "authDefault";
        AUTHAUTHURL = "authAuthURL";
        AUTHSTATEURL = "authStateURL";
        AUTHPOSTFIX = "authpostfix";
        TICKET = "ticket";
        TICKETURL = "ticketURL";
        EXPIRE = "expire";
        USERID = "userid";
        KEEP_RETRY = "keep-retry";
        KEEP_URL = "keep-url";
        TERM_URL = "term-url";
        WIFISSID = "WifiInfo";
    }
}