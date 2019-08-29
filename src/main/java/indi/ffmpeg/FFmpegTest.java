package indi.ffmpeg;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class FFmpegTest {
    
    @Test
    @Disabled
    void normalTest() throws IOException, InterruptedException {
        String command = new StringBuilder()
                .append("e:/ffmpeg.exe -i e:/b.mp4 -c:v h264 -c:a copy e:/h264.mp4 -y -nostdin")
                .toString();
        start(command);
    }

    @Test
    void limitBitTest() throws IOException {
        String command = new StringBuilder()
                .append("e:/ffmpeg.exe -i e:/b.mp4 -c:v h264 -c:a copy -maxrate 3000K -bufsize 3000K e:/h2642.mp4 -y -nostdin")
                .toString();
        start(command);
    }
    
    /**
     * 这应该是最高效的执行process的代码了
     * 
     * @param command
     * @throws IOException
     */
    private void start(String command) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        processBuilder.redirectErrorStream(true);
        
        long currentTimeMillis = System.currentTimeMillis();
        
        Process process = processBuilder.start();
        
        byte[] buff = new byte[1024 * 1024 * 50];// 50m
        try (InputStream inputStream = process.getInputStream()) {
            System.out.println(inputStream);// BufferedInputStream (8192) wrap FileInputStream
            int len = -1;
            while ((len = inputStream.read(buff)) != -1) {
                System.out.write(buff, 0, len);
            }
        }
        System.out.println(System.currentTimeMillis() - currentTimeMillis);
    }

}
