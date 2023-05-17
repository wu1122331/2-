package cn.jiguang.as;

import android.util.Base64;
import androidx.core.view.ViewCompat;
import java.security.KeyFactory;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public final class i {
    public static int a() {
        return Math.abs(new SecureRandom().nextInt()) & ViewCompat.MEASURED_SIZE_MASK;
    }

    public static String a(long j) {
        long j2;
        long j3;
        switch (((int) j) % 10) {
            case 1:
                j2 = 5 * j;
                j3 = 88;
                break;
            case 2:
                j2 = 23 * j;
                j3 = 15;
                break;
            case 3:
                j2 = 3 * j;
                j3 = 73;
                break;
            case 4:
                j2 = 13 * j;
                j3 = 96;
                break;
            case 5:
                j2 = 17 * j;
                j3 = 49;
                break;
            case 6:
                j2 = 7 * j;
                j3 = 68;
                break;
            case 7:
                j2 = 31 * j;
                j3 = 39;
                break;
            case 8:
                j2 = 29 * j;
                j3 = 41;
                break;
            case 9:
                j2 = 37 * j;
                j3 = 91;
                break;
            default:
                j2 = 8 * j;
                j3 = 74;
                break;
        }
        return j.c("JCKP" + (j2 + (j % j3)));
    }

    public static String a(String str) {
        try {
            byte[] c = c("DFA84B10B7ACDD25", "ASCII");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, new SecretKeySpec(c, "AES"), a(c));
            return Base64.encodeToString(cipher.doFinal(str.getBytes()), 2);
        } catch (Exception unused) {
            cn.jiguang.ac.d.f("", "Unexpected - failed to AES encrypt.");
            return "";
        }
    }

    public static String a(String str, String str2) {
        RSAPublicKey c = c(str2);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, c);
        return new String(Base64.encode(cipher.doFinal(str.getBytes()), 2), "UTF-8");
    }

    private static IvParameterSpec a(byte[] bArr) {
        try {
            return (IvParameterSpec) IvParameterSpec.class.getConstructor(byte[].class).newInstance(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] a(byte[] bArr, String str, String str2) {
        if (str == null) {
            return null;
        }
        byte[] c = c(str, "utf-8");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(1, new SecretKeySpec(c, "AES"), a(str2.getBytes("utf-8")));
        return cipher.doFinal(bArr);
    }

    public static String b(String str) {
        try {
            return b(str, "DFA84B10B7ACDD25");
        } catch (Exception unused) {
            cn.jiguang.ac.d.f("", "Unexpected - failed to AES decrypt.");
            return "";
        }
    }

    private static String b(String str, String str2) {
        try {
            if (str2.length() != 16) {
                return null;
            }
            byte[] c = c(str2, "ASCII");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, new SecretKeySpec(c, "AES"), a(c));
            return new String(cipher.doFinal(Base64.decode(str, 2)));
        } catch (Exception unused) {
            return null;
        }
    }

    private static RSAPublicKey c(String str) {
        try {
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 2)));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static byte[] c(String str, String str2) {
        byte[] bArr = new byte[str.length()];
        byte[] bytes = str.substring(0, str.length() / 2).getBytes(str2);
        byte[] bytes2 = str.substring(str.length() / 2).getBytes(str2);
        System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        System.arraycopy(bytes2, 0, bArr, bytes.length, bytes2.length);
        return bArr;
    }
}
