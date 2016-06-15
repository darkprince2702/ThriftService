/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.vng.thriftservice.buffer;

/**
 *
 * @author ductn
 */
public class BufferFactory {

    public Buffer getCache(String bufferType) {
        if (bufferType.isEmpty()) {
            return null;
        } else if (bufferType.contains("HashMap")) {
            return HashMap.getInstance();
        } else if (bufferType.contains("HashTable")) {
            return HashTable.getInstance();
        } else {
            return null;
        }
    }
}
