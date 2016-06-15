/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.vng.thriftservice.database;

/**
 *
 * @author ductn
 */
public class DatabaseFactory {

    public Database getDatabase(String databaseType) {
        if (databaseType.isEmpty()) {
            return null;
        } else if (databaseType.contains("LevelDB")) {
            return LevelDB.getInstance();
        } else {
            return null;
        }
    }
}
