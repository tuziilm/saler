package com.zhanghui.saler.statistics.analyzer;

import com.zhanghui.saler.statistics.common.ChartPvUvData;

import java.util.List;

public interface Analyzer {
    List<ChartPvUvData> analyze() throws Exception;
}
