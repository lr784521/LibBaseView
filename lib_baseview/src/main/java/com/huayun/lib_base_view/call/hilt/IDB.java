package com.huayun.lib_base_view.call.hilt;

import com.huayun.lib_db.sql.BaseDao;
import com.huayun.lib_db.sql.IBaseDao;
import com.huayun.lib_db.sql.bean.TbUser;
import com.huayun.lib_db.sql.update.UpdateManager;

/**
 * 该接口用于访问数据操作对象
 */
public interface IDB {

    /**
     * 提供数据库管理权限-主要用于数据初始化+版本升级
     * @return 数据库管理
     */
    UpdateManager updateManager();

    /**
     * 提供用户个体操作能力(针对某个用户数据的增、删、改、查)
     * @return 某个用户数据库操作能力
     */
    IBaseDao<TbUser> getUserDao();

    /**
     * 提供个人数据库操作能力
     * @param t  个人数据库的某张表Class对象
     * @param <T> 表的操作对象
     * @return
     */
    <T> BaseDao<T> getAppDao(Class<T> t);

}
