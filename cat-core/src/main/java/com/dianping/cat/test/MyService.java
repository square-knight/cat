package com.dianping.cat.test;

import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializationException;
import org.unidal.lookup.annotation.Named;

/**
 * Usage:
 * <p>
 * Description:
 * User: fuxinpeng
 * Date: 2018-11-21
 * Time: 5:44 PM
 */
@Named
public class MyService implements Initializable {
    public void a(){
        System.out.println("hi I am MyService");
    }

    @Override
    public void initialize() throws InitializationException {
        System.out.println("hi I am initialize");
    }
}
