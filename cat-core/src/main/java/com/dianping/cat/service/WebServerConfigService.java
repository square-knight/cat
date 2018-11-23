package com.dianping.cat.service;

import com.dianping.cat.Cat;
import com.dianping.cat.core.myconfig.WebServerConfig;
import com.dianping.cat.core.myconfig.WebServerConfigDao;
import com.dianping.cat.core.myconfig.WebServerConfigEntity;
import org.unidal.lookup.annotation.Inject;
import org.unidal.lookup.annotation.Named;

/**
 * Usage:
 * <p>
 * Description:
 * User: fuxinpeng
 * Date: 2018-11-23
 * Time: 1:47 PM
 */
@Named
public class WebServerConfigService {
    private String host;
    @Inject
    private WebServerConfigDao webServerConfigDao;

    public String getHost(){
        if(null == host){
            host = getHostFromDB();
        }
        return host;
    }

    private String getHostFromDB(){
        String host = "cat-web-server";
        try {
            WebServerConfig webServerConfig = webServerConfigDao.findOne(WebServerConfigEntity.READSET_FULL);
            host = webServerConfig.getDomain();
        } catch (Exception e) {
            Cat.logError(e);
        }
        return host;
    }
}
