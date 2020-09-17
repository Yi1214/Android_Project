package com.example.icbc.DataBase;

import android.util.Log;

import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.x;
import java.io.File;

public class XUtilsManager {

    private static XUtilsManager mInstance;

    private DbManager dbManager;

    private XUtilsManager() {
    }

    public static XUtilsManager getInstance() {
        if (mInstance == null) {
            synchronized (XUtilsManager.class) {
                if (mInstance == null) {
                    mInstance = new XUtilsManager();
                }
            }
        }
        return mInstance;
    }

    public DbManager getDbManager() {
        if (dbManager == null) {
            initDbManager();
        }
        return dbManager;
    }

    private void initDbManager() {
        File dbFile = new File("/sdcard/xutils/db");
        if (!dbFile.exists()) {
            dbFile.mkdirs();
        }
        DbManager.DaoConfig config = new DbManager.DaoConfig()
                .setDbDir(dbFile) //数据库路径
                .setDbName("ItemBean_9") //数据库名
                .setDbVersion(1) //设置数据库版本

                //设置表创建的监听

                .setTableCreateListener(new DbManager.TableCreateListener(){
                    @Override
                    public void onTableCreated(DbManager db, TableEntity<?> table) {
                        Log.i("JAVA", "onTableCreated：" + table.getName());
                    }
                })
                //设置数据库更新的监听
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                    }
                })

                //设置数据库打开的监听

                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        //开启数据库支持多线程操作，提升性能
                        db.getDatabase().enableWriteAheadLogging();
                    }
                });

        try {
            dbManager = x.getDb(config);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
