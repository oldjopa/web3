package org.oldjopa.web3.listners;

import jakarta.servlet.ServletContextListener;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.oldjopa.web3.beans.CheckResults;
import org.oldjopa.web3.jmx.MeanIntervalStatistics;
import org.oldjopa.web3.utils.CheckResult;

import java.lang.management.ManagementFactory;

public class MXBeanServer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
//            ManagementFactory.getPlatformMBeanServer().registerMBean(new CheckResults(), new ObjectName("org.oldjopa.web3.jmx:type=CheckResults,name=pointStatistics"));
            ManagementFactory.getPlatformMBeanServer().registerMBean(new MeanIntervalStatistics(), new ObjectName("org.oldjopa.web3.jmx:type=MeanIntervalStatistics,name=mean"));
        } catch (InstanceAlreadyExistsException | MalformedObjectNameException | MBeanRegistrationException | NotCompliantMBeanException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ManagementFactory.getPlatformMBeanServer().unregisterMBean(new ObjectName("org.oldjopa.web3.jmx:type=MeanIntervalStatistics,name=mean"));
//            ManagementFactory.getPlatformMBeanServer().unregisterMBean(new ObjectName("org.oldjopa.web3.jmx:type=PointStatistics,name=pointStatistics"));
        } catch (MBeanRegistrationException | InstanceNotFoundException | MalformedObjectNameException e) {
            e.printStackTrace();
        }
    }
}
