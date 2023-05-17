package cn.jiguang.api.utils;

import cn.jiguang.api.JResponse;
import cn.jiguang.aq.d;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class ProtocolUtil {
    private static final String ENCODING_UTF_8 = "UTF-8";
    private static final String TAG = "ProtocolUtil";

    public static void fillIntData(byte[] bArr, int i, int i2) {
        System.arraycopy(int2ByteArray(i), 0, bArr, i2, 4);
    }

    public static void fillStringData(byte[] bArr, String str, int i) {
        byte[] bytes = str.getBytes();
        System.arraycopy(bytes, 0, bArr, i, bytes.length);
    }

    public static byte[] fixedStringToBytes(String str, int i) {
        if (str == null || "".equals(str)) {
            return new byte[]{0, 0, 0, 0};
        }
        byte[] bArr = null;
        try {
            bArr = str.getBytes(ENCODING_UTF_8);
        } catch (UnsupportedEncodingException unused) {
        }
        if (bArr == null) {
            return new byte[]{0, 0, 0, 0};
        }
        byte[] defaultByte = getDefaultByte(i);
        if (bArr.length <= i) {
            i = bArr.length;
        }
        System.arraycopy(bArr, 0, defaultByte, 0, i);
        return defaultByte;
    }

    public static byte[] getBytes(ByteBuffer byteBuffer) {
        try {
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.asReadOnlyBuffer().flip();
            byteBuffer.get(bArr);
            return bArr;
        } catch (NegativeArraySizeException unused) {
            d.c(TAG, "[getBytes] - ByteBuffer error.");
            return null;
        } catch (Exception unused2) {
            return null;
        }
    }

    public static byte[] getBytesConsumed(ByteBuffer byteBuffer) {
        try {
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            return bArr;
        } catch (NegativeArraySizeException unused) {
            d.c(TAG, "[getBytesConsumed] - ByteBuffer error.");
            return null;
        } catch (Exception unused2) {
            return null;
        }
    }

    public static byte[] getDefaultByte(int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[0] = 0;
        }
        return bArr;
    }

    public static String getString(ByteBuffer byteBuffer, int i) {
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        try {
            return new String(bArr, ENCODING_UTF_8);
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public static String getTlv2(ByteBuffer byteBuffer) {
        try {
            byte[] bArr = new byte[byteBuffer.getShort()];
            byteBuffer.get(bArr);
            return new String(bArr, ENCODING_UTF_8);
        } catch (UnsupportedEncodingException | Exception unused) {
            return null;
        }
    }

    public static String getTlv2(ByteBuffer byteBuffer, JResponse jResponse) {
        int i = ByteBufferUtils.getShort(byteBuffer, jResponse);
        if (i < 0) {
            d.c(TAG, "[getTlv2] - ByteBuffer error.");
            return null;
        }
        byte[] bArr = new byte[i];
        ByteBufferUtils.get(byteBuffer, bArr, jResponse);
        try {
            return new String(bArr, ENCODING_UTF_8);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static byte[] int2ByteArray(int i) {
        return new byte[]{(byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i};
    }

    public static byte[] long2ByteArray(long j) {
        return new byte[]{(byte) (j >>> 56), (byte) (j >>> 48), (byte) (j >>> 40), (byte) (j >>> 32), (byte) (j >>> 24), (byte) (j >>> 16), (byte) (j >>> 8), (byte) j};
    }

    public static byte[] short2ByteArray(short s) {
        return new byte[]{(byte) (s >>> 8), (byte) s};
    }

    public static byte[] tlv2ToByteArray(String str) {
        if (str != null && !"".equals(str)) {
            byte[] bArr = null;
            try {
                bArr = str.getBytes(ENCODING_UTF_8);
            } catch (UnsupportedEncodingException unused) {
            }
            if (bArr != null) {
                short length = (short) bArr.length;
                byte[] bArr2 = new byte[length + 2];
                System.arraycopy(short2ByteArray(length), 0, bArr2, 0, 2);
                System.arraycopy(bArr, 0, bArr2, 2, length);
                return bArr2;
            }
        }
        return new byte[]{0, 0};
    }
}
