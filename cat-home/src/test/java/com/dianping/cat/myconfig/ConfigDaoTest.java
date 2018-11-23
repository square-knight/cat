package com.dianping.cat.myconfig;

import com.dianping.cat.core.myconfig.WebServerConfig;
import com.dianping.cat.core.myconfig.WebServerConfigDao;
import com.dianping.cat.core.myconfig.WebServerConfigEntity;
import org.junit.Test;
import org.unidal.dal.jdbc.DalException;
import org.unidal.lookup.ComponentTestCase;

/**
 * Usage:
 * <p>
 * Description:
 * User: fuxinpeng
 * Date: 2018-11-23
 * Time: 11:12 AM
 */
public class ConfigDaoTest extends ComponentTestCase{
    @Test
    public void test(){
        WebServerConfigDao webServerConfigDao = lookup(WebServerConfigDao.class);
        try {
            WebServerConfig webServerConfig = webServerConfigDao.findOne(WebServerConfigEntity.READSET_FULL);
            System.out.println(webServerConfig);
        } catch (DalException e) {
            e.printStackTrace();
        }
    }
}
