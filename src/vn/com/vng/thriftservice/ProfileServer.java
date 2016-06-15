/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.vng.thriftservice;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Server implementation
 *
 * @author ductn
 */
public class ProfileServer {

    public static void main(String args[]) {
        try {

//            ProfileHandler handler = new ProfileHandler();
//            TServerSocket transport = new TServerSocket(7999);
//            ProfileService.Processor proccesor = new ProfileService.Processor(
//                    handler);
//            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(
//                    transport).processor(proccesor));

            ProfileHandler handler = new ProfileHandler();
            TNonblockingServerSocket transport = new TNonblockingServerSocket(7999);
            ProfileService.Processor proccesor = new ProfileService.Processor(handler);
            TServer server = new TNonblockingServer(new TNonblockingServer.Args(transport).processor(proccesor));
            // TServer server = new THsHaServer(new THsHaServer.Args(transport).processor(proccesor).maxWorkerThreads(7));

            System.out.println("Starting server on port 7999...");
            server.serve();
        } catch (TTransportException ex) {
            Logger.getLogger(ProfileServer.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
}
