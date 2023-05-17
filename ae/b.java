package cn.jiguang.ae;

import com.facebook.gamingservices.cloudgaming.internal.SDKConstants;

/* loaded from: classes.dex */
public final class b<T> {
    public static String e = "cn.jpush.preferences.v2";
    String a;
    String b;
    T c;
    boolean d;

    public b(String str, String str2, T t) {
        this.a = str;
        this.b = str2;
        if (t == null) {
            throw new IllegalArgumentException("default value can not be null");
        }
        this.c = t;
    }

    public static b<Boolean> a() {
        return new b<>("cn.jpush.android.user.profile", "is_tcp_close", false);
    }

    public static b<String> a(String str) {
        return new b<>("cn.jpush.android.user.profile", "sdk_version_" + str, "");
    }

    public static b<String> a(boolean z) {
        return new b<>("cn.jiguang.sdk.address", "last_good_sis_address" + (z ? "_V4" : "_V6"), "");
    }

    public static b<Integer> b() {
        b<Integer> bVar = new b<>("cn.jpush.android.user.profile", "jpush_register_code", -1);
        bVar.d = true;
        return bVar;
    }

    public static b<String> b(String str) {
        return new b<>("cn.jiguang.sdk.address", "dns_" + str, "");
    }

    public static b<String> b(boolean z) {
        return new b<>("cn.jiguang.sdk.address", "last_good_conn" + (z ? "_V4" : "_V6"), "");
    }

    public static b<Integer> c() {
        b<Integer> bVar = new b<>("cn.jiguang.sdk.user.profile", "idc", -1);
        bVar.d = true;
        return bVar;
    }

    public static b<Long> c(String str) {
        return new b<>("cn.jiguang.sdk.address", "dns_last_update_" + str, 0L);
    }

    public static b<String> c(boolean z) {
        b<String> bVar = new b<>("cn.jiguang.sdk.address", z ? "default_https_report" : "default_http_report", "");
        bVar.d = true;
        return bVar;
    }

    public static b<Long> d() {
        b<Long> bVar = new b<>("cn.jiguang.sdk.user.profile", "key_uid", 0L);
        bVar.d = true;
        return bVar;
    }

    public static b<String> d(String str) {
        return new b<>("cn.jiguang.sdk.address", "srv_" + str, "");
    }

    public static b<String> e() {
        b<String> bVar = new b<>("cn.jiguang.sdk.user.profile", "key_rid", "");
        bVar.d = true;
        return bVar;
    }

    public static b<Long> e(String str) {
        return new b<>("cn.jiguang.sdk.address", "srv_last_update_" + str, 0L);
    }

    public static b<String> f() {
        b<String> bVar = new b<>("cn.jiguang.sdk.user.profile", "key_pwd", "");
        bVar.d = true;
        return bVar;
    }

    public static b<String> f(String str) {
        return new b<>("IpInfos", str, "");
    }

    public static b<String> g() {
        b<String> bVar = new b<>(e, "sdk_version", "");
        bVar.d = true;
        return bVar;
    }

    public static b<Integer> g(String str) {
        return new b<>("netinfo", str, 0);
    }

    public static b<String> h() {
        return new b<>(e, "device_config_appkey", "");
    }

    public static b<String> i() {
        return new b<>(e, "i_new", "");
    }

    public static b<String> j() {
        return new b<>(e, "push_udid", "");
    }

    public static b<String> k() {
        return new b<>(e, "last_connection_type", "");
    }

    public static b<String> l() {
        return new b<>(e, "sis_report_history", "");
    }

    public static b<Long> m() {
        return new b<>(e, "lbs_delay", 0L);
    }

    public static b<Long> n() {
        return new b<>("cn.jpush.preferences.v2.rid", "next_rid", -1L);
    }

    public static b<Integer> o() {
        return new b<>("cn.jpush.preferences.v2.rid", "seq_id", -1);
    }

    public static b<String> p() {
        return new b<>("cn.jiguang.sdk.address", "ips_in_last_good_sis", "");
    }

    public static b<String> q() {
        return new b<>("cn.jiguang.sdk.address", "ssl_ips_in_last_good_sis", "");
    }

    public static b<Boolean> r() {
        return new b<>("cn.jiguang.sdk.address", "udp_data_report", false);
    }

    public static b<Long> s() {
        return new b<>("cn.jiguang.sdk.address", "sis_last_update", 0L);
    }

    public static b<Long> t() {
        return new b<>("cn.jiguang.sdk.address", "last_sis_report_time", 0L);
    }

    public static b<String> u() {
        return new b<>("cn.jiguang.sdk.address", "default_sis_ips", "");
    }

    public static b<String> v() {
        return new b<>("cn.jiguang.sdk.address", "default_conn", "");
    }

    public static b<String> w() {
        return new b<>("cn.jiguang.sdk.address", "default_conn_srv", "");
    }

    public static b<String> x() {
        b<String> bVar = new b<>("cn.jiguang.sdk.address", "tcp_report", "");
        bVar.d = true;
        return bVar;
    }

    public static b<String> y() {
        return new b<>("PrefsFile", SDKConstants.PARAM_KEY, "");
    }

    public final b<T> a(T t) {
        this.c = t;
        return this;
    }
}
