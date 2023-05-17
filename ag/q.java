package cn.jiguang.ag;

import java.io.EOFException;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import kotlin.UByte;

/* loaded from: classes.dex */
public final class q {
    protected long a;
    protected SelectionKey b;

    private q(long j) {
        Selector selector;
        SocketChannel open = SocketChannel.open();
        this.a = j;
        try {
            selector = Selector.open();
        } catch (Throwable th) {
            th = th;
            selector = null;
        }
        try {
            open.configureBlocking(false);
            this.b = open.register(selector, 1);
        } catch (Throwable th2) {
            th = th2;
            if (selector != null) {
                selector.close();
            }
            open.close();
            throw th;
        }
    }

    private void a() {
        this.b.selector().close();
        this.b.channel().close();
    }

    private static void a(SelectionKey selectionKey, long j) {
        long currentTimeMillis = j - System.currentTimeMillis();
        int i = (currentTimeMillis > 0L ? 1 : (currentTimeMillis == 0L ? 0 : -1));
        if ((i > 0 ? selectionKey.selector().select(currentTimeMillis) : i == 0 ? selectionKey.selector().selectNow() : 0) == 0) {
            throw new SocketTimeoutException();
        }
    }

    private byte[] a(int i) {
        SocketChannel socketChannel = (SocketChannel) this.b.channel();
        byte[] bArr = new byte[i];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        this.b.interestOps(1);
        int i2 = 0;
        while (i2 < i) {
            try {
                if (this.b.isReadable()) {
                    long read = socketChannel.read(wrap);
                    if (read < 0) {
                        throw new EOFException();
                    }
                    i2 += (int) read;
                    if (i2 < i && System.currentTimeMillis() > this.a) {
                        throw new SocketTimeoutException();
                    }
                } else {
                    a(this.b, this.a);
                }
            } finally {
                if (this.b.isValid()) {
                    this.b.interestOps(0);
                }
            }
        }
        return bArr;
    }

    public static byte[] a(SocketAddress socketAddress, SocketAddress socketAddress2, byte[] bArr, long j) {
        q qVar = new q(j);
        try {
            SocketChannel socketChannel = (SocketChannel) qVar.b.channel();
            if (!socketChannel.connect(socketAddress2)) {
                qVar.b.interestOps(8);
                while (!socketChannel.finishConnect()) {
                    if (!qVar.b.isConnectable()) {
                        a(qVar.b, qVar.a);
                    }
                }
                if (qVar.b.isValid()) {
                    qVar.b.interestOps(0);
                }
            }
            SocketChannel socketChannel2 = (SocketChannel) qVar.b.channel();
            ByteBuffer[] byteBufferArr = {ByteBuffer.wrap(new byte[]{(byte) (bArr.length >>> 8), (byte) (bArr.length & 255)}), ByteBuffer.wrap(bArr)};
            qVar.b.interestOps(4);
            int i = 0;
            while (i < bArr.length + 2) {
                if (qVar.b.isWritable()) {
                    long write = socketChannel2.write(byteBufferArr);
                    if (write < 0) {
                        throw new EOFException();
                    }
                    i += (int) write;
                    if (i < bArr.length + 2 && System.currentTimeMillis() > qVar.a) {
                        throw new SocketTimeoutException();
                    }
                } else {
                    a(qVar.b, qVar.a);
                }
            }
            if (qVar.b.isValid()) {
                qVar.b.interestOps(0);
            }
            byte[] a = qVar.a(2);
            byte[] a2 = qVar.a(((a[0] & UByte.MAX_VALUE) << 8) + (a[1] & UByte.MAX_VALUE));
            qVar.b.channel();
            return a2;
        } finally {
            qVar.a();
        }
    }
}
