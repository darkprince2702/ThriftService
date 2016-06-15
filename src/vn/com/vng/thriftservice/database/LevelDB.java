/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.vng.thriftservice.database;

import vn.com.vng.thriftservice.GetResult;

/**
 *
 * @author ductn
 */
public class LevelDB implements Database {

    private static LevelDB instance = new LevelDB();
    private LevelDBModel dbModel;

    private LevelDB() {
        dbModel = LevelDBModel.getInstance();
    }

    public static LevelDB getInstance() {
        return instance;
    }

    @Override
    public GetResult getData(String key) {
        return dbModel.getData(key);
    }

    @Override
    public boolean setData(String key, String value) {
        return dbModel.setData(key, value);
    }

    @Override
    public boolean removeData(String key) {
        return dbModel.removeData(key);
    }
}
