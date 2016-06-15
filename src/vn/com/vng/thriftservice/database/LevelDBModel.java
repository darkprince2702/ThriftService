/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.vng.thriftservice.database;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import static org.fusesource.leveldbjni.JniDBFactory.*;
import org.iq80.leveldb.*;
import vn.com.vng.thriftservice.GetResult;

/**
 *
 * @author ductn
 */
public class LevelDBModel {

    private static LevelDBModel instance = new LevelDBModel();

    private Options options;
    private DB db;
    private ReadWriteLock lock;

    private LevelDBModel() {
        options = new Options();
        // If database is missing, create it instead
        options.createIfMissing(true);    
        try {
            db = factory.open(new File("database"), options);
        } catch (IOException ex) {
            System.out.println("Cannot open database from disk: " 
                    + ex.toString());
        }
        lock = new ReentrantReadWriteLock();
    }

    public static LevelDBModel getInstance() {
        return instance;
    }

    /**
     * Get data from database
     *
     * @param key
     * @return Value of @key or "NOT_FOUND" if key not exist
     */
    public GetResult getData(String key) {
        String result;
        lock.readLock().lock();
        try {
            result = asString(db.get(bytes(key)));
        } finally {
            lock.readLock().unlock();
        }
        return (result.isEmpty() || result == null) ? 
                new GetResult(true) : (new GetResult(false)).setValue(result) ;
    }

    /**
     * Set data in database
     *
     * @param key
     * @param value
     * @return true if successful, otherwise false
     */
    boolean setData(String key, String value) {
        lock.writeLock().lock();
        try {
            db.put(bytes(key), bytes(value));
            return true;
        } catch (DBException ex) {
            return false;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Delete data in database
     *
     * @param key
     * @return true if successful, otherwise false
     */
    boolean removeData(String key) {
        lock.writeLock().lock();
        try {
            db.delete(bytes(key));
            return true;
        } catch (DBException ex) {
            return false;
        } finally {
            lock.writeLock().unlock();
        }
    }
}
