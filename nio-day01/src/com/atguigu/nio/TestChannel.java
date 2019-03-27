package com.atguigu.nio;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一：通道（Channel）：用于原节点与目标节点的连接。在Java NIO中负责缓冲区中数据的传输》】。channel本身不存储数据，因此需要配合缓冲区进行传输
 *
 * 二：通道的主要实现类
 * java.nio.channels.Channel接口：
 * |--FileChannel
 * |--SocketChannel
 * |--ServerSocketChannel
 * |--DatagramChannel
 *
 * 三：获取通道
 * 1.Java 针对支持通道的类提供了getChannel()方法
 *  本地IO：
 *   FileInputStream/FileOutPutStream
 *   RandomAccessFile
 *
 *  网络IO：
 *   Socket
 *   ServerSocket
 *   DatagramSocket
 *
 *
 * 2.在JDK1.7中的NIO.2针对各个通道提供了静态方法open()
 *
 * 3.在JDK1.7中的NIO.2的Files工具类的newByteChannel()
 *
 * 四，通道之间的数据传输
 * transferFrom()
 * transferTo()
 */

public class TestChannel {

    //通道之间的数据传输(直接缓冲区的方式)
    @Test
    public void test3() throws IOException{
        FileChannel inChannel = FileChannel.open(Paths.get("/Users/kongjj/Documents/每周计划/531.docx"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/Users/kongjj/Documents/每周计划/2.docx"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);

        //inChannel.transferTo(0,inChannel.size(),outChannel);
        outChannel.transferFrom(inChannel,0,outChannel.size());


        inChannel.close();
        outChannel.close();
    }

    //2.使用直接缓冲区完成文件的复制（内存映射文件）
    @Test
    public void Test2() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("/Users/kongjj/Documents/每周计划/531.docx"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/Users/kongjj/Documents/每周计划/2.docx"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY,0,inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE,0,inChannel.size());

        //直接对缓冲去进行数据对读写操作
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);
        inChannel.close();
        outChannel.close();
    }

    //1.利用通道完成文件的复制（非直接缓冲区）
    @Test
    public void test1() {

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("/Users/kongjj/Documents/每周计划/531.docx");
            fos = new FileOutputStream("/Users/kongjj/Documents/每周计划/1.docx");

            //获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            //分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //将通道的数据存入缓冲区中
            while(inChannel.read(buf)!= -1){
                buf.flip();//切换到读取数据的模式
                //将缓冲区的数据写入通道
                outChannel.write(buf);
                buf.clear();//清空缓冲区
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(outChannel != null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inChannel != null){
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }

}
