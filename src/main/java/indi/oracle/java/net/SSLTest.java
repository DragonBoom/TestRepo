package indi.oracle.java.net;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class SSLTest {

    // 查看当前jvm支持的通信协议
    @Test
    void printlnTLSVersion() throws NoSuchAlgorithmException, KeyManagementException, IOException {
        SSLContext context = SSLContext.getInstance("TLS");  
        context.init(null, null, null);  
     
        SSLSocketFactory factory = (SSLSocketFactory) context.getSocketFactory();  
        SSLSocket socket = (SSLSocket) factory.createSocket();  
     
        String[] protocols = socket.getSupportedProtocols();  
     
        System.out.println("Supported Protocols: " + protocols.length);  
        for (int i = 0; i < protocols.length; i++) {  
            System.out.println(" " + protocols[i]);  
        }  
     
        protocols = socket.getEnabledProtocols();  
     
        System.out.println("Enabled Protocols: " + protocols.length);  
        for (int i = 0; i < protocols.length; i++) {  
            System.out.println(" " + protocols[i]);  
        }  
    }
}
