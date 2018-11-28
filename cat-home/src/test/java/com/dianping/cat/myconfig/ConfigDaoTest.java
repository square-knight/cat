package com.dianping.cat.myconfig;

import com.dianping.cat.consumer.transaction.TransactionAnalyzer;
import com.dianping.cat.consumer.transaction.model.entity.TransactionReport;
import com.dianping.cat.core.myconfig.WebServerConfig;
import com.dianping.cat.core.myconfig.WebServerConfigDao;
import com.dianping.cat.core.myconfig.WebServerConfigEntity;
import com.dianping.cat.report.alert.transaction.TransactionRuleConfigManager;
import com.dianping.cat.report.service.ModelService;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializationException;
import org.junit.Test;
import org.unidal.dal.jdbc.DalException;
import org.unidal.lookup.ComponentTestCase;
import org.unidal.lookup.annotation.Inject;

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
    @Test
    public void test1() throws InitializationException {
        TransactionRuleConfigManager transactionRuleConfigManager = lookup(TransactionRuleConfigManager.class);
        ModelService<TransactionReport> m_service = lookup(ModelService.class,TransactionAnalyzer.ID);
        transactionRuleConfigManager.initialize();
        String name = m_service.getName();
        System.out.println();

    }
}
