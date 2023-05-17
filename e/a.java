package cn.jiguang.e;

import com.facebook.gamingservices.cloudgaming.internal.SDKAnalyticsEvents;

/* loaded from: classes.dex */
public final class a<T> {
    public static String e = "cn.jiguang.sdk.share.profile";
    public static String f = "cn.jpush.preferences.v2";
    String a;
    String b;
    T c;
    boolean d;

    private a(String str, String str2, T t) {
        this.a = str;
        this.b = str2;
        if (t == null) {
            throw new IllegalArgumentException("default value can not be null");
        }
        this.c = t;
    }

    public static a<String> a() {
        return new a<>("cn.jpush.android.user.profile", "devcie_id_generated", "");
    }

    public static a<String> a(boolean z) {
        a<String> aVar = new a<>("cn.jiguang.sdk.address", "default_https_report", "");
        aVar.d = true;
        return aVar;
    }

    public static a<Boolean> b() {
        return new a<>("cn.jpush.android.user.profile", "upload_crash", true);
    }

    public static a<Long> c() {
        a<Long> aVar = new a<>("cn.jiguang.sdk.user.profile", "key_uid", 0L);
        aVar.d = true;
        return aVar;
    }

    public static a<String> d() {
        a<String> aVar = new a<>("cn.jiguang.sdk.user.profile", "key_rid", "");
        aVar.d = true;
        return aVar;
    }

    public static a<String> e() {
        a<String> aVar = new a<>("cn.jiguang.sdk.user.profile", "key_pwd", "");
        aVar.d = true;
        return aVar;
    }

    public static a<Integer> f() {
        a<Integer> aVar = new a<>("cn.jiguang.sdk.user.profile", "idc", -1);
        aVar.d = true;
        return aVar;
    }

    public static a<Long> g() {
        return new a<>("cn.jiguang.sdk.user.profile", "login_local_time", -1L);
    }

    public static a<Long> h() {
        return new a<>("cn.jiguang.sdk.user.profile", "login_server_time", -1L);
    }

    public static a<String> i() {
        a<String> aVar = new a<>(e, "key_share_process_uuid", "");
        aVar.d = true;
        return aVar;
    }

    public static a<Long> j() {
        a<Long> aVar = new a<>(e, "key_share_process_uuid_creattime", -1L);
        aVar.d = true;
        return aVar;
    }

    public static a<Integer> k() {
        a<Integer> aVar = new a<>(e, "sp_state", -1);
        aVar.d = true;
        return aVar;
    }

    public static a<String> l() {
        return new a<>("cn.jiguang.sdk.user.set.profile", "option_channel", "");
    }

    public static a<String> m() {
        return new a<>("cn.jiguang.sdk.user.set.profile", "analytics_account_id", "");
    }

    public static a<Long> n() {
        return new a<>("Push_Page_Config", "css", 0L);
    }

    public static a<Long> o() {
        return new a<>("Push_Page_Config", "cse", 0L);
    }

    public static a<Long> p() {
        return new a<>("Push_Page_Config", "last_pause", -1L);
    }

    public static a<String> q() {
        return new a<>("Push_Page_Config", SDKAnalyticsEvents.PARAMETER_SESSION_ID, "");
    }

    public static a<String> r() {
        return new a<>("cn.jiguang.sdk.report", "report_urls", "");
    }

    public static a<String> s() {
        return new a<>("cn.jiguang.sdk.report", "report_sis_urls", "");
    }

    public static a<Long> t() {
        return new a<>("cn.jiguang.sdk.report", "last_update_report_urls", 0L);
    }

    public static a<Long> u() {
        return new a<>("cn.jiguang.sdk.report", "report_urls_ttl_millis", 3600000L);
    }

    public static a<String> v() {
        a<String> aVar = new a<>(f, "sdk_version", "");
        aVar.d = true;
        return aVar;
    }

    public static a<Long> w() {
        return new a<>(f, "first_init", 0L);
    }

    public static a<Long> x() {
        return new a<>(f, "lbs_delay", 0L);
    }

    public final a<T> a(T t) {
        this.c = t;
        return this;
    }
}
