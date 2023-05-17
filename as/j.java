package cn.jiguang.as;

import android.text.TextUtils;
import com.facebook.internal.security.CertificateUtil;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Pattern;
import kotlin.UByte;

/* loaded from: classes.dex */
public final class j {
    private static Pattern a = Pattern.compile("((2[0-4]\\d|25[0-5]|[01]?\\d{1,2})\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d{1,2})");
    private static SimpleDateFormat b = new SimpleDateFormat("mm:ss:SSS", Locale.ENGLISH);

    public static String a(String str, int i) {
        int length = str.length();
        if (length >= i) {
            return str;
        }
        for (int i2 = 0; i2 < i - length; i2++) {
            str = str + " ";
        }
        return str;
    }

    public static String a(byte[] bArr) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                int i = b2 & UByte.MAX_VALUE;
                if (i < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (Throwable unused) {
            cn.jiguang.ac.d.b("StringUtils", "Get SHA1 error");
            return "";
        }
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0 || str.trim().length() == 0;
    }

    public static String b(String str) {
        return a(str) ? "" : Pattern.compile("[^\\w#$@\\-一-龥]+").matcher(str).replaceAll("");
    }

    public static String c(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[charArray.length];
            for (int i = 0; i < charArray.length; i++) {
                bArr[i] = (byte) charArray[i];
            }
            byte[] digest = messageDigest.digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                int i2 = b2 & UByte.MAX_VALUE;
                if (i2 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            return stringBuffer.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String d(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("utf-8"));
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                int i = b2 & UByte.MAX_VALUE;
                if (i < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (Throwable unused) {
            cn.jiguang.ac.d.b("StringUtils", "Get MD5 error");
            return "";
        }
    }

    public static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int indexOf = str.indexOf(CertificateUtil.DELIMITER);
        if (indexOf >= 0) {
            if (indexOf != str.lastIndexOf(CertificateUtil.DELIMITER)) {
                return false;
            }
            str = str.substring(0, indexOf);
        }
        return a.matcher(str).matches();
    }

    public static boolean f(String str) {
        int indexOf;
        return (TextUtils.isEmpty(str) || (indexOf = str.indexOf(CertificateUtil.DELIMITER)) == -1 || str.lastIndexOf(CertificateUtil.DELIMITER) == indexOf) ? false : true;
    }

    public static boolean g(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return Pattern.compile("[\\x20-\\x7E]+").matcher(str).matches();
        } catch (Throwable unused) {
            return true;
        }
    }

    public static byte[] h(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (Throwable th) {
            cn.jiguang.ac.d.i("StringUtils", "stringToUtf8Bytes error:" + th.getMessage());
            return str.getBytes();
        }
    }

    public static String i(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return a(str.getBytes("utf-8"));
        } catch (Throwable th) {
            th.printStackTrace();
            return str;
        }
    }
}
