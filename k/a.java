package cn.jiguang.k;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import cn.jiguang.ap.h;
import cn.jiguang.f.f;
import cn.jiguang.f.i;
import com.aliyun.security.yunceng.android.sdk.traceroute.d;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a extends cn.jiguang.f.a {
    private static volatile a c;
    private Context a;
    private JSONObject b;
    private String d;
    private boolean e;

    private void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, List<cn.jiguang.l.a> list) {
        try {
            if (this.b == null) {
                this.b = new JSONObject();
            }
            this.b.put("ssid", str);
            this.b.put("bssid", str2);
            this.b.put("local_ip", str3);
            this.b.put("local_mac", str4);
            this.b.put("netmask", str5);
            this.b.put("gateway", str8);
            this.b.put("dhcp", str9);
            JSONArray jSONArray = new JSONArray();
            if (!TextUtils.isEmpty(str6)) {
                jSONArray.put(str6);
            }
            if (!TextUtils.isEmpty(str7)) {
                jSONArray.put(str7);
            }
            this.b.put("dns", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            for (cn.jiguang.l.a aVar : list) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ip", aVar.a);
                jSONObject.put("mac", aVar.d);
                jSONArray2.put(jSONObject);
            }
            this.b.put("data", jSONArray2);
        } catch (JSONException e) {
            cn.jiguang.ai.a.g("JArp", "packageJson exception: " + e.getMessage());
        }
    }

    public static a d() {
        if (c == null) {
            synchronized (a.class) {
                c = new a();
            }
        }
        return c;
    }

    @Override // cn.jiguang.f.a
    protected final void a(JSONObject jSONObject) {
        this.e = true;
        JSONObject optJSONObject = jSONObject.optJSONObject("content");
        boolean z = !optJSONObject.optBoolean("disable");
        f.a(this.a, "JArp", z);
        if (z) {
            long optLong = optJSONObject.optLong("frequency", 0L) * 1000;
            if (optLong == 0) {
                f.a(this.a, true);
                return;
            }
            f.a(this.a, false);
            f.b(this.a, "JArp", optLong);
        }
    }

    @Override // cn.jiguang.f.a
    protected final boolean a(Context context, String str) {
        return true;
    }

    @Override // cn.jiguang.f.a
    protected final boolean b() {
        return f.h(this.a, "JArp");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final boolean b(Context context, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        if (TextUtils.isEmpty(this.d)) {
            this.d = "";
        }
        return currentTimeMillis - f.f(context, new StringBuilder("JArp").append(this.d).toString()) > f.g(context, "JArp");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final void c(Context context, String str) {
        String str2;
        String str3;
        if (!cn.jiguang.ap.a.a(context, "android.permission.ACCESS_WIFI_STATE")) {
            cn.jiguang.ai.a.g("JArp", "collect arp failed because has no permission Manifest.permission.ACCESS_WIFI_STATE");
        } else if (!cn.jiguang.ap.a.i(context).toUpperCase().startsWith(d.c)) {
            cn.jiguang.ai.a.g("JArp", "collect arp failed because networkType is not wifi");
        } else if (TextUtils.isEmpty(cn.jiguang.sdk.impl.b.g(context))) {
            cn.jiguang.ai.a.g("JArp", "collect arp failed because can't get registerId");
        } else {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            if (wifiManager == null) {
                cn.jiguang.ai.a.c("JArp", "collect arp failed because get wifiManager failed");
                return;
            }
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null) {
                str3 = h.b(connectionInfo.getSSID());
                str2 = connectionInfo.getBSSID();
            } else {
                str2 = "";
                str3 = str2;
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            if (TextUtils.isEmpty(str3)) {
                str3 = "";
            }
            this.d = TextUtils.isEmpty(str2) ? str3 : str2;
            if (f.c(context) && f.l(context, this.d)) {
                cn.jiguang.ai.a.g("JArp", "collect arp failed because this wifi 【" + this.d + "】 can't report twice");
                return;
            }
            boolean b = b(context, "JArp");
            if (!this.e && !b) {
                cn.jiguang.ai.a.g("JArp", "collect arp failed because this wifi 【" + this.d + "】 is not in report time");
                return;
            }
            this.e = false;
            DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
            if (dhcpInfo == null) {
                return;
            }
            long j = dhcpInfo.ipAddress;
            byte[] bArr = {(byte) (j & 255), (byte) ((j >> 8) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 24) & 255)};
            String a = cn.jiguang.m.a.a(dhcpInfo.ipAddress);
            if (TextUtils.equals(a, "0.0.0.0")) {
                a = "";
            }
            String d = cn.jiguang.ap.a.d(context, "");
            String a2 = cn.jiguang.m.a.a(dhcpInfo.netmask);
            String str4 = TextUtils.equals(a2, "0.0.0.0") ? "" : a2;
            String a3 = cn.jiguang.m.a.a(dhcpInfo.dns1);
            String str5 = TextUtils.equals(a3, "0.0.0.0") ? "" : a3;
            String a4 = cn.jiguang.m.a.a(dhcpInfo.dns2);
            String str6 = TextUtils.equals(a4, "0.0.0.0") ? "" : a4;
            String a5 = cn.jiguang.m.a.a(dhcpInfo.gateway);
            String str7 = TextUtils.equals(a5, "0.0.0.0") ? "" : a5;
            String a6 = cn.jiguang.m.a.a(dhcpInfo.serverAddress);
            String str8 = TextUtils.equals(a6, "0.0.0.0") ? "" : a6;
            cn.jiguang.m.a.a(str8, bArr);
            List<cn.jiguang.l.a> a7 = cn.jiguang.m.a.a(str8);
            if (a7 == null || a7.isEmpty()) {
                cn.jiguang.ai.a.g("JArp", "collect arp failed because can't get arp info");
                return;
            }
            cn.jiguang.ai.a.c("JArp", "collect arp success");
            a(str3, str2, a, d, str4, str5, str6, str7, str8, a7);
        }
    }

    @Override // cn.jiguang.f.a
    protected final String d(Context context) {
        this.a = context;
        return "JArp";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final void d(Context context, String str) {
        JSONObject jSONObject = this.b;
        if (jSONObject == null) {
            cn.jiguang.ai.a.c("JArp", "there are no data to report");
            return;
        }
        i.a(context, jSONObject, "mac_list");
        i.a(context, this.b, new b(this, context, str));
        this.b = null;
    }
}
