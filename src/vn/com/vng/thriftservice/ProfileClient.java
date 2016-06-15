/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.vng.thriftservice;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.*;
import org.apache.thrift.transport.TTransportException;

/**
 * Client implementation
 *
 * @author ductn
 */
public class ProfileClient {

    public static void main(String args[]) {
        try {
//            TTransport socket = new TSocket("127.0.0.1", 7999);
//            TTransport transport = new TFramedTransport(socket);
            TSocket transport = new TSocket("localhost", 7999);
            transport.open();
//            TProtocol protocol = new TBinaryProtocol(transport);
            TProtocol protocol = new TBinaryProtocol(transport);
            ProfileService.Client client = new ProfileService.Client(protocol);

            Random rand = new Random();
            
            int testSize = 10000;
            
            // Test set data
            long start = System.currentTimeMillis();
            for (int i = 1; i <= testSize; i++) {
                client.setData("ID" + i, "John");
            }
            long eslapsedTime = System.currentTimeMillis() - start;
            System.out.println("Time to set 1 million key: " + eslapsedTime);
            
            // Test get data
            start = System.currentTimeMillis();
            for (int i = 1; i <= testSize; i++) {
                client.getData("ID" + i);
            }
            eslapsedTime = System.currentTimeMillis() - start;
            System.out.println("Time to get 1 million key: " + eslapsedTime);
            
            // Test remove data
            start = System.currentTimeMillis();
            for (int i = 1; i <= testSize; i++) {
                client.removeData("ID" + i);
            }
            eslapsedTime = System.currentTimeMillis() - start;
            System.out.println("Time to remove 1 million key: " + eslapsedTime);
            
            transport.close();
        } catch (TTransportException ex) {
            Logger.getLogger(ProfileClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TException ex) {
            Logger.getLogger(ProfileClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public static void main(String args[]) throws InterruptedException {
//        int threadCount = 10000;
//        Thread threads[] = new Thread[threadCount];
//        long start = System.currentTimeMillis();
//
//        for (int i = 0; i < threadCount; i++) {
//            final int x = i;
//            threads[i] = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        TTransport socket = new TSocket("127.0.0.1", 7999);
//                        TTransport transport = new TFramedTransport(socket);
////                        TTransport transport = new TSocket("localhost", 7999);
//                        transport.open();
//                        TProtocol protocol = new TBinaryProtocol(transport);
//                        ProfileService.Client client = new ProfileService.Client(protocol);
//
//                        // Test set data
//                        client.setData("ID" + x, "John");
//                        if (x == 0)
//                            System.out.println("Set OK");
//                        // Test get data
//                        String result = client.getData("ID" + x);
//                        if (x == 0)
//                            System.out.println("Get OK");
//                        // Test remove data
//                        client.removeData("ID" + x);
//                        if (x == 0)
//                            System.out.println("Remove OK");
//                        
//                        transport.close();
//                    } catch (TTransportException ex) {
//                        Logger.getLogger(ProfileClient.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (TException ex) {
//                        Logger.getLogger(ProfileClient.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            });
//
//            threads[i].start();
//        }
//
//        for (int i = 0; i < threadCount; i++) {
//            threads[i].join();
//        }
//
//        long eslapsedTime = System.currentTimeMillis() - start;
//        System.out.println("Time to complete tasks: " + eslapsedTime);
//    }
}
