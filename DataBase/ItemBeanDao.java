package com.example.icbc.DataBase;
import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * 数据库增删改查方法
 *
 */

public class ItemBeanDao {
    private final  DbManager mDbManager;   //final变量

    public ItemBeanDao() {
        mDbManager = XUtilsManager.getInstance().getDbManager();
    }

    /**
     * 添加数据
     *
     * @param list
     */
    public void addData(List<ItemBean> list) {
        try {
            //mDbManager.delete(UserInfo.class);
            //mDbManager.save(list);
            //mDbManager.saveBindingId(list);
            //mDbManager.saveOrUpdate(list);

            mDbManager.replace(list); //保存或更新实体类或实体类的List到数据库, 根据id和其他唯一索引判断数据是否存在.
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除全部数据
     */
    public void deleteAllData() {
        try {
            mDbManager.delete(ItemBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id删除数据
     */
    public void deleteDataById(int id) {
        try {
            mDbManager.deleteById(ItemBean.class, id); //删除指定id数据
            //根据指定条件删除
            // mDbManager.delete(UserInfo.class, WhereBuilder.b("sex", "=", "女"));
            //mDbManager.delete(UserInfo.class, WhereBuilder.b("id", ">=", "50").and("id", "<=", "100"));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据条件删除数据
     */
    public void deleteDataByCondition(WhereBuilder whereBuilder) {
        try {
            //根据指定条件删除
            // mDbManager.delete(UserInfo.class, WhereBuilder.b("sex", "=", "女"));
            //mDbManager.delete(UserInfo.class, WhereBuilder.b("id", ">=", "50").and("id", "<=", "100"));

            mDbManager.delete(ItemBean.class, whereBuilder);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新数据:修改表中的某一条数据
     */
    public void updateData(ItemBean userInfo, String... columnNames) {
        try {
            mDbManager.update(userInfo, columnNames);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    /**
     * 修改表中的某些数据
     *
     * @param whereBuilder1
     * @param whereBuilder2
     * @param keyValue
     */
    public void updateData2(WhereBuilder whereBuilder1, WhereBuilder whereBuilder2, KeyValue... keyValue) {
        try {
            mDbManager.update(ItemBean.class, whereBuilder1.or(whereBuilder2), keyValue);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查找所有数据
     */
    public List<ItemBean> findAllData(Class<ItemBean> tclass) {
        try {
            //UserInfo first = mDbManager.findFirst(tclass);查询第一条数据
            return mDbManager.findAll(tclass);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据条件查询
     */
    public List<ItemBean> findDataByCondition(Class<ItemBean> tclass, WhereBuilder whereBuilder) {
        try {
            List<ItemBean> allList = mDbManager.selector(tclass).where(whereBuilder).findAll();
            return allList;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据id查询
     * @param tclass
     * @param id
     * @return
     */
    public List<ItemBean> findDataById(Class<ItemBean> tclass, int id) {
        try {
            List<ItemBean> allList = mDbManager.selector(tclass).where("id","=",id).findAll();
            return allList;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据group_num查询
     * @param tclass
     * @param group
     * @return
     */
    public List<ItemBean> findDataByGroup(Class<ItemBean> tclass, int group) {
        try {
            List<ItemBean> allList = mDbManager.selector(tclass).where("group_num","=", group).findAll();
            return allList;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据show_flag查询
     * @param tclass
     * @param show_flag
     * @return
     */
    public List<ItemBean> findDataByShowFlag(Class<ItemBean> tclass, int show_flag) {
        try {
            List<ItemBean> allList = mDbManager.selector(tclass).where("show_flag","=", show_flag).findAll();
            return allList;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }
}
