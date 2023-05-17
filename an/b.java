package cn.jiguang.an;

import cn.jiguang.ah.f;
import cn.jiguang.ap.k;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class b extends a {
    private final int g;
    private ByteBuffer h = ByteBuffer.allocate(8192);

    public b(int i, int i2) {
        this.g = i;
        this.f = i2;
    }

    private boolean b(byte[] bArr) {
        try {
            if (!a()) {
                cn.jiguang.ai.a.c("NioSocketClient", "send error - connect was invalid");
                return false;
            }
            if (bArr != null && bArr.length > 0) {
                int write = this.b.write(ByteBuffer.wrap(bArr));
                if (write > 0) {
                    cn.jiguang.ai.a.a("NioSocketClient", "isWritable has send len:" + write);
                    return true;
                } else if (write < 0) {
                    cn.jiguang.ai.a.a("NioSocketClient", "isWritable error:" + write);
                    return false;
                } else {
                    return true;
                }
            }
            cn.jiguang.ai.a.c("NioSocketClient", "send error - invalide buffer");
            return false;
        } catch (Exception e) {
            cn.jiguang.ai.a.h("NioSocketClient", "send data error:" + e);
            close();
            return false;
        }
    }

    @Override // cn.jiguang.an.a
    public final synchronized int a(String str, int i) {
        super.a(str, i);
        this.b = SocketChannel.open();
        this.d = Selector.open();
        this.b.configureBlocking(false);
        this.b.connect(new InetSocketAddress(str, i));
        cn.jiguang.ai.a.c("NioSocketClient", "tcp connecting...");
        long currentTimeMillis = System.currentTimeMillis();
        while (!this.b.finishConnect()) {
            if (!this.e) {
                cn.jiguang.ai.a.c("NioSocketClient", "has close channel when connect...");
                return -991;
            }
            Thread.sleep(10L);
            if (System.currentTimeMillis() - currentTimeMillis > 3000) {
                close();
                return -994;
            }
        }
        if (!this.e) {
            cn.jiguang.ai.a.c("NioSocketClient", "has close channel when connected...");
            return -991;
        }
        cn.jiguang.ai.a.c("NioSocketClient", "tcp connected");
        this.b.register(this.d, 1);
        return 0;
    }

    @Override // cn.jiguang.an.a
    public final int a(byte[] bArr) {
        if (bArr == null) {
            cn.jiguang.ai.a.c("NioSocketClient", "sendData failed, send data was null");
            return 103;
        }
        cn.jiguang.ai.a.c("NioSocketClient", "send data length:" + bArr.length);
        if (bArr.length < this.g) {
            return b(bArr) ? 0 : 103;
        }
        cn.jiguang.ai.a.c("NioSocketClient", "sendData failed, data length must less than " + this.g);
        return 6026;
    }

    @Override // cn.jiguang.an.a
    public final ByteBuffer a(int i) {
        ByteBuffer b;
        try {
            if (a()) {
                int b2 = b();
                if (b2 <= 0 || (b = b(b2)) == null) {
                    int i2 = 0;
                    int i3 = 1048576;
                    while (a() && this.c < i3) {
                        int select = i > 0 ? this.d.select(i) : this.d.select();
                        if (select == 0) {
                            cn.jiguang.ai.a.c("NioSocketClient", "readSelect:" + select + ",time out:" + i);
                            if (i > 0) {
                                throw new f(-994, "recv time out");
                            }
                        } else {
                            Iterator<SelectionKey> it = this.d.selectedKeys().iterator();
                            while (it.hasNext()) {
                                SelectionKey next = it.next();
                                SocketChannel socketChannel = (SocketChannel) next.channel();
                                if (next.isReadable()) {
                                    int read = socketChannel.read(this.h);
                                    if (read < 0) {
                                        throw new f(-996, "read len < 0:" + read);
                                    }
                                    this.h.flip();
                                    int limit = this.h.limit();
                                    if (this.a.remaining() < limit) {
                                        throw new f(-996, "the total buf remaining less than readLen,remaining:" + this.a.remaining() + ",readLen:" + limit);
                                    }
                                    this.a.put(this.h);
                                    this.c += limit;
                                    this.h.compact();
                                    if (this.c < this.f) {
                                        cn.jiguang.ai.a.c("NioSocketClient", "totalbuf can not parse head:" + this.c + ",peerNetData len:" + limit + ",read:" + read);
                                    } else {
                                        i3 = b();
                                    }
                                    i2 = limit;
                                } else {
                                    next.isWritable();
                                }
                                it.remove();
                            }
                            continue;
                        }
                    }
                    if (i3 != 1048576) {
                        cn.jiguang.ai.a.c("NioSocketClient", "read len:" + i2 + ",recvTotalLen:" + this.c + ",shouldLen:" + i3);
                        ByteBuffer b3 = b(i3);
                        if (b3 != null) {
                            return b3;
                        }
                        throw new f(-1001, "parse error");
                    }
                    throw new f(-997, "recv empty data or tcp has close");
                }
                return b;
            }
            throw new f(-991, "recv error,the connect is invalid");
        } catch (Throwable th) {
            if (th instanceof SocketTimeoutException) {
                throw new f(-994, th.getMessage());
            }
            if (th instanceof f) {
                throw th;
            }
            throw new f(-997, th.getMessage());
        }
    }

    @Override // cn.jiguang.an.a, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        cn.jiguang.ai.a.c("NioSocketClient", "close this connect");
        super.close();
        if (this.d != null) {
            try {
                this.d.close();
            } catch (IOException unused) {
            }
        }
        k.a(this.b);
        this.b = null;
    }
}
