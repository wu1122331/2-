package cn.jiguang.ap;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public final class d {
    public static void a(File file) {
        if (file != null) {
            try {
                if (file.exists()) {
                    file.delete();
                    cn.jiguang.ai.a.c("FileUtils", "delete File:" + file.getPath());
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static boolean a(File file, String str) {
        byte[] bytes;
        if (str != null) {
            try {
                bytes = str.getBytes("UTF-8");
            } catch (Throwable th) {
                cn.jiguang.ai.a.g("FileUtils", "getBytes exception:" + th);
                return false;
            }
        } else {
            bytes = null;
        }
        return a(file, bytes);
    }

    private static boolean a(File file, byte[] bArr) {
        Object th;
        if (file == null || file.isDirectory()) {
            cn.jiguang.ai.a.g("FileUtils", "file should not be null or a directory");
            return false;
        }
        FileOutputStream fileOutputStream = null;
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                th = th2;
                try {
                    cn.jiguang.ai.a.g("FileUtils", "save to file exception:" + th + " path = " + file.getAbsolutePath());
                    return false;
                } finally {
                    k.a(fileOutputStream);
                }
            }
        }
        if (file != null && !file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists()) {
                parentFile.mkdirs();
            }
            try {
                file.createNewFile();
            } catch (Throwable unused) {
            }
        }
        FileOutputStream fileOutputStream2 = new FileOutputStream(file);
        try {
            fileOutputStream2.write(bArr);
            k.a(fileOutputStream2);
            return true;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = fileOutputStream2;
            cn.jiguang.ai.a.g("FileUtils", "save to file exception:" + th + " path = " + file.getAbsolutePath());
            return false;
        }
    }

    public static String b(File file) {
        byte[] c = c(file);
        if (c == null) {
            return null;
        }
        try {
            return new String(c, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            cn.jiguang.ai.a.c("FileUtils", "can't encoding, give up read :" + e);
            return null;
        }
    }

    private static byte[] c(File file) {
        FileInputStream fileInputStream;
        if (file == null || !file.exists() || file.isDirectory()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
        }
        try {
            return k.a((InputStream) fileInputStream);
        } catch (Throwable th2) {
            th = th2;
            try {
                cn.jiguang.ai.a.c("FileUtils", "can't read, give up read. e:" + th);
                return null;
            } finally {
                k.a((Closeable) fileInputStream);
            }
        }
    }
}
