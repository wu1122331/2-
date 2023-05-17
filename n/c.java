package cn.jiguang.n;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import cn.jiguang.f.i;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class c extends cn.jiguang.f.a {
    private static volatile c c;
    private Context a;
    private JSONObject b;

    public static c d() {
        if (c == null) {
            synchronized (c.class) {
                c = new c();
            }
        }
        return c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final void c(Context context, String str) {
        try {
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (registerReceiver == null) {
                return;
            }
            int intExtra = registerReceiver.getIntExtra("level", -1);
            int intExtra2 = registerReceiver.getIntExtra("scale", -1);
            int intExtra3 = registerReceiver.getIntExtra("status", -1);
            if (intExtra3 == 1) {
                intExtra3 = 0;
            } else if (intExtra3 == 2) {
                intExtra3 = 2;
            } else if (intExtra3 == 3 || intExtra3 == 4) {
                intExtra3 = 1;
            } else if (intExtra3 == 5) {
                intExtra3 = 3;
            }
            int intExtra4 = registerReceiver.getIntExtra("voltage", -1);
            int intExtra5 = registerReceiver.getIntExtra("temperature", -1);
            if (this.b == null) {
                this.b = new JSONObject();
            }
            this.b.put("level", intExtra);
            this.b.put("scale", intExtra2);
            this.b.put("status", intExtra3);
            this.b.put("voltage", intExtra4);
            this.b.put("temperature", intExtra5);
            cn.jiguang.ai.a.c("JDeviceBattery", "collect success:" + this.b);
        } catch (JSONException e) {
            cn.jiguang.ai.a.g("JDeviceBattery", "packageJson exception: " + e.getMessage());
        }
    }

    @Override // cn.jiguang.f.a
    protected final String d(Context context) {
        this.a = context;
        return "JDeviceBattery";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final void d(Context context, String str) {
        JSONObject jSONObject = this.b;
        if (jSONObject == null) {
            cn.jiguang.ai.a.g("JDeviceBattery", "there are no data to report");
            return;
        }
        i.a(context, jSONObject, "battery");
        i.a(context, (Object) this.b);
        super.d(context, str);
        this.b = null;
    }
}
