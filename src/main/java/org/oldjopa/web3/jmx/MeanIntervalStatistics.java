package org.oldjopa.web3.jmx;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.ApplicationScoped;
import org.oldjopa.web3.db.DBHandler;
import org.oldjopa.web3.utils.CheckResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.IntStream;

@ManagedBean
@ApplicationScoped
public class MeanIntervalStatistics implements Serializable, MeanIntervalStatisticsMXBean {
    public long getMeanClickInterval(){
        ArrayList<CheckResult> data = DBHandler.getResults();
        return IntStream.range(1, data.size()).mapToObj(i -> data.get(i).getTimeClick() - data.get(i-1).getTimeClick()).toList().stream().mapToLong(Long::longValue).sum()/(data.size()-1);
    }
}
