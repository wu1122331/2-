package cn.jiguang.p;

import android.text.TextUtils;
import android.util.Base64;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public final class a {
    public static List<String> a = new ArrayList();
    public static List<String> b = new ArrayList();
    public static List<String> c = new ArrayList();
    public static List<String> d = new ArrayList();
    public static List<String> e = new ArrayList();
    public static List<String> f = new ArrayList();

    static {
        List<String> list;
        List asList;
        if (TextUtils.isEmpty("Z3NtLWdzbS5zaW0uc3RhdGV8Z3NtLnNpbS5zdGF0ZS4yfmltZWktcm8ucmlsLm1pdWkuaW1laTB8cm8ucmlsLm1pdWkuaW1laTF8cm8ucmlsLm1pdWkuaW1laXxyby5yaWwub2VtLmltZWkxfHJvLnJpbC5vZW0uaW1laTJ8Z3NtLm10ay5pbWVpMnxvcHBvLmRldmljZS5pbWVpY2FjaGV8cGVyc2lzdC5zeXMub3Bwby5kZXZpY2UuaW1laXxnc20uaW1laTB8Z3NtLmltZWkxfGdzbS5pbWVpMnxyaWwuZ3NtLmltZWl8cmlsLmdzbS5pbWVpMXxnc20uc2ltLmltZWl8Z3NtLnNpbS5pbWVpMXxnc20uc2ltLmltZWkyfG9lbS5kZXZpY2UuaW1laWNhY2hlfG9lbS5kZXZpY2UuaW1laWNhY2hlMHxvZW0uZGV2aWNlLmltZWljYWNoZTF8cGVyc2lzdC5zeXMudXBkYXRlci5pbWVpfHBlcnNpc3Quc3lzLmduLmltZWkwfHBlcnNpc3Quc3lzLmduLmltZWkxfHBlcnNpc3Quc3lzLmduLmltZWkwfHBlcnNpc3Quc3lzLmduLmltZWkxfHBlcnNpc3QucmFkaW8uaW1laXxwZXJzaXN0LnJhZGlvLmltZWkwfHBlcnNpc3QucmFkaW8uaW1laTF8cGVyc2lzdC5yYWRpby5pbWVpMnxnc20ucGhvbmUuaW1laXxyaWwuaW1laS4wfHJpbC5pbWVpLjF8cGVyc2lzdC5zeXMuaW1laTF8cGVyc2lzdC5zeXMuaW1laTJ8cGVyc2lzdC5zeXMuYmlyZF9pbWVpMHxwZXJzaXN0LnN5cy5iaXJkX2ltZWkxfGdzbS5yaWwuaW1laTF8Z3NtLnJpbC5pbWVpMnxnc20uZGV2aWNlLmltZWl8Z3NtLmRldmljZS5pbWVpMXxnc20uZGV2aWNlLmltZWkyfGdzbS5pbWVpLmlkfGdzbS5pbWVpLmlkMHxnc20uaW1laS5pZDF8cmlsLmltZWl8Z3NtLnl1bm9zLmltZWkwfGdzbS55dW5vcy5pbWVpMXxwZXJzaXN0LnJhZGlvLmRldmljZS5pbWVpfHBlcnNpc3QucmFkaW8uZGV2aWNlLmltZWkyfmljY2lkLXBlcnNpc3QucmFkaW8uaWNjaWR8cmlsLmljY2lkLnNpbTF8cmlsLmljY2lkLnNpbTJ8cGVyc2lzdC5yYWRpby5ia3NpbS5pY2NpZHxwZXJzaXN0LnJhZGlvLnZvbHRlMC5pY2NpZHxwZXJzaXN0LnJhZGlvLnZvbHRlMS5pY2NpZHxwZXJzaXN0LnJhZGlvLmltcy5pY2NpZHxwZXJzaXN0LnJhZGlvLmxhc3RzaW0xX2ljY2lkfHBlcnNpc3QucmFkaW8ubGFzdHNpbTJfaWNjaWR8cGVyc2lzdC5yYWRpby5sYXN0X2ljY2lkX3NpbTF8cGVyc2lzdC5yYWRpby5sYXN0X2ljY2lkX3NpbTJ8cGVyc2lzdC5yYWRpby5pY2NpZDB8cGVyc2lzdC5yYWRpby5pY2NpZDF8cGVyc2lzdC5yYWRpby5pY2NpZF8wfHBlcnNpc3QucmFkaW8uaWNjaWRfMn5pbXNpLWdzbS5zaW0ub3BlcmF0b3IuaW1zaXxnc20uc2ltLm9wZXJhdG9yLmltc2kuMnx2ZW5kb3IuY2VsbHVsYXIucmlsLmltc2l8cGVyc2lzdC5yYWRpby5sYXN0LnN1YnNjcmliZXJ8cGVyc2lzdC5yYWRpby52b2x0ZS5pbXNpLjF+bWVpZC1wZXJzaXN0LnJhZGlvLm1laWR8cmlsLm1laWQuMHxnc20ucGhvbmUubWVpZHxnc20ud2luZC5tZWlkfHJpbC5jZG1hLm1laWR8cGVyc2lzdC5zeXMuYmlyZF9tZWlkfHJvLnJpbC5vZW0ubWVpZHxjZG1hLm1laWR8Z3NtLnJpbC5jMmsubWVpZHxnc20uZGV2aWNlLnllcC5tZWlkfGdzbS5kZXZpY2UubWVpZHxjZG1hLm1laWQuaWR8cGVyc2lzdC5zeXMuZ24ubWVpZHxnc20ueXVub3MubWVpZHxwZXJzaXN0LnJhZGlvLmRldmljZS5tZWlk")) {
            return;
        }
        for (String str : new String(Base64.decode("Z3NtLWdzbS5zaW0uc3RhdGV8Z3NtLnNpbS5zdGF0ZS4yfmltZWktcm8ucmlsLm1pdWkuaW1laTB8cm8ucmlsLm1pdWkuaW1laTF8cm8ucmlsLm1pdWkuaW1laXxyby5yaWwub2VtLmltZWkxfHJvLnJpbC5vZW0uaW1laTJ8Z3NtLm10ay5pbWVpMnxvcHBvLmRldmljZS5pbWVpY2FjaGV8cGVyc2lzdC5zeXMub3Bwby5kZXZpY2UuaW1laXxnc20uaW1laTB8Z3NtLmltZWkxfGdzbS5pbWVpMnxyaWwuZ3NtLmltZWl8cmlsLmdzbS5pbWVpMXxnc20uc2ltLmltZWl8Z3NtLnNpbS5pbWVpMXxnc20uc2ltLmltZWkyfG9lbS5kZXZpY2UuaW1laWNhY2hlfG9lbS5kZXZpY2UuaW1laWNhY2hlMHxvZW0uZGV2aWNlLmltZWljYWNoZTF8cGVyc2lzdC5zeXMudXBkYXRlci5pbWVpfHBlcnNpc3Quc3lzLmduLmltZWkwfHBlcnNpc3Quc3lzLmduLmltZWkxfHBlcnNpc3Quc3lzLmduLmltZWkwfHBlcnNpc3Quc3lzLmduLmltZWkxfHBlcnNpc3QucmFkaW8uaW1laXxwZXJzaXN0LnJhZGlvLmltZWkwfHBlcnNpc3QucmFkaW8uaW1laTF8cGVyc2lzdC5yYWRpby5pbWVpMnxnc20ucGhvbmUuaW1laXxyaWwuaW1laS4wfHJpbC5pbWVpLjF8cGVyc2lzdC5zeXMuaW1laTF8cGVyc2lzdC5zeXMuaW1laTJ8cGVyc2lzdC5zeXMuYmlyZF9pbWVpMHxwZXJzaXN0LnN5cy5iaXJkX2ltZWkxfGdzbS5yaWwuaW1laTF8Z3NtLnJpbC5pbWVpMnxnc20uZGV2aWNlLmltZWl8Z3NtLmRldmljZS5pbWVpMXxnc20uZGV2aWNlLmltZWkyfGdzbS5pbWVpLmlkfGdzbS5pbWVpLmlkMHxnc20uaW1laS5pZDF8cmlsLmltZWl8Z3NtLnl1bm9zLmltZWkwfGdzbS55dW5vcy5pbWVpMXxwZXJzaXN0LnJhZGlvLmRldmljZS5pbWVpfHBlcnNpc3QucmFkaW8uZGV2aWNlLmltZWkyfmljY2lkLXBlcnNpc3QucmFkaW8uaWNjaWR8cmlsLmljY2lkLnNpbTF8cmlsLmljY2lkLnNpbTJ8cGVyc2lzdC5yYWRpby5ia3NpbS5pY2NpZHxwZXJzaXN0LnJhZGlvLnZvbHRlMC5pY2NpZHxwZXJzaXN0LnJhZGlvLnZvbHRlMS5pY2NpZHxwZXJzaXN0LnJhZGlvLmltcy5pY2NpZHxwZXJzaXN0LnJhZGlvLmxhc3RzaW0xX2ljY2lkfHBlcnNpc3QucmFkaW8ubGFzdHNpbTJfaWNjaWR8cGVyc2lzdC5yYWRpby5sYXN0X2ljY2lkX3NpbTF8cGVyc2lzdC5yYWRpby5sYXN0X2ljY2lkX3NpbTJ8cGVyc2lzdC5yYWRpby5pY2NpZDB8cGVyc2lzdC5yYWRpby5pY2NpZDF8cGVyc2lzdC5yYWRpby5pY2NpZF8wfHBlcnNpc3QucmFkaW8uaWNjaWRfMn5pbXNpLWdzbS5zaW0ub3BlcmF0b3IuaW1zaXxnc20uc2ltLm9wZXJhdG9yLmltc2kuMnx2ZW5kb3IuY2VsbHVsYXIucmlsLmltc2l8cGVyc2lzdC5yYWRpby5sYXN0LnN1YnNjcmliZXJ8cGVyc2lzdC5yYWRpby52b2x0ZS5pbXNpLjF+bWVpZC1wZXJzaXN0LnJhZGlvLm1laWR8cmlsLm1laWQuMHxnc20ucGhvbmUubWVpZHxnc20ud2luZC5tZWlkfHJpbC5jZG1hLm1laWR8cGVyc2lzdC5zeXMuYmlyZF9tZWlkfHJvLnJpbC5vZW0ubWVpZHxjZG1hLm1laWR8Z3NtLnJpbC5jMmsubWVpZHxnc20uZGV2aWNlLnllcC5tZWlkfGdzbS5kZXZpY2UubWVpZHxjZG1hLm1laWQuaWR8cGVyc2lzdC5zeXMuZ24ubWVpZHxnc20ueXVub3MubWVpZHxwZXJzaXN0LnJhZGlvLmRldmljZS5tZWlk", 2)).split("~")) {
            String[] split = str.split("-");
            if (split[0].equals("imei")) {
                list = a;
                asList = Arrays.asList(split[1].split("\\|"));
            } else if (split[0].equals("iccid")) {
                list = b;
                asList = Arrays.asList(split[1].split("\\|"));
            } else if (split[0].equals("imsi")) {
                list = c;
                asList = Arrays.asList(split[1].split("\\|"));
            } else if (split[0].equals("meid")) {
                list = d;
                asList = Arrays.asList(split[1].split("\\|"));
            } else if (split[0].equals("gsm")) {
                list = e;
                asList = Arrays.asList(split[1].split("\\|"));
            }
            list.addAll(asList);
        }
        f.addAll(e);
        f.addAll(a);
        f.addAll(c);
        f.addAll(b);
        f.addAll(d);
    }

    public static String a(int i, int i2) {
        List<String> list;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4 || i2 >= e.size()) {
                            return "";
                        }
                        list = e;
                    } else if (i2 >= b.size()) {
                        return "";
                    } else {
                        list = b;
                    }
                } else if (i2 >= d.size()) {
                    return "";
                } else {
                    list = d;
                }
            } else if (i2 >= c.size()) {
                return "";
            } else {
                list = c;
            }
        } else if (i2 >= a.size()) {
            return "";
        } else {
            list = a;
        }
        return list.get(i2);
    }
}
