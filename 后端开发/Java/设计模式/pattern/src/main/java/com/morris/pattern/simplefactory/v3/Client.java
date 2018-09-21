package com.morris.pattern.simplefactory.v3;

import com.morris.pattern.simplefactory.v2.Chart;
import com.morris.pattern.simplefactory.v2.ChartFactory;

public class Client {
    public static void main(String args[]) {
        String chartType = PropertiesUtil.getChartType(); // 读取配置文件config.properties
        Chart chart = ChartFactory.getChart(chartType); //通过静态工厂方法创建产品
        chart.display();
    }
}
