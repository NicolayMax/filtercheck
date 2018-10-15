package ru.hdln.filtercheck.charts;

import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LinearChart implements IChart {

    private String name;
    private String xName;
    private String yName;
    private JFreeChart chart;
    private ChartPanel chartPanel;
    private XYSeriesCollection dataset;

    public LinearChart(String name, String xName, String yName) {
        this.name = name;
        this.xName = xName;
        this.yName = yName;
        dataset = new XYSeriesCollection();
        chart = ChartFactory.createXYLineChart(name, xName, yName, dataset);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(640,300));
    }
    
    public void setValues(XYSeries series) {
        dataset.addSeries(series);
    }
    
    @Override
    public ChartPanel getChart() {
        return chartPanel;
    }
    
}
