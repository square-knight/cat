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
    private String domain;
    @Inject
    private WebServerConfigDao webServerConfigDao;

    public String getDomain(){
        if(null == domain){
            domain = getDomainFromDB();
        }
        return domain;
    }

    private String getDomainFromDB(){
        String domain = "cat-web-server";
        try {
            WebServerConfig webServerConfig = webServerConfigDao.findOne(WebServerConfigEntity.READSET_FULL);
            domain = webServerConfig.getDomain();
        } catch (Exception e) {
            Cat.logError(e);
        }
        return domain;
    }
}
