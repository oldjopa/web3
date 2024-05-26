package org.oldjopa.web3.jmx;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import org.oldjopa.web3.db.DBHandler;
import org.oldjopa.web3.listners.MBeanRegistry;
import org.oldjopa.web3.utils.CheckResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.IntStream;

@ManagedBean
@ApplicationScoped
public class MeanIntervalStatistics implements Serializable, MeanIntervalStatisticsMXBean {
//    @PostConstruct
    public void init(@Observes @Initialized(ApplicationScoped.class) Object unused){
        MBeanRegistry.registerBean(this, "checkResults");
    }
    public long getMeanClickInterval(){
        ArrayList<CheckResult> data = DBHandler.getResults();
        return IntStream.range(1, data.size()).mapToObj(i -> data.get(i).getTimeClick() - data.get(i-1).getTimeClick()).toList().stream().mapToLong(Long::longValue).sum()/(data.size()-1);
    }
}
