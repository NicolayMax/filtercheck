package ru.hdln.filtercheck;

import ru.hdln.filtercheck.charts.LinearChart;
import ru.hdln.filtercheck.data.Filter;
import ru.hdln.filtercheck.data.SignalData;
import ru.hdln.filtercheck.gui.MainWindow;

public class Main {

    public static void main(String[] args) {
        //Create main application window
        MainWindow mainWindow = new MainWindow();
        SignalData data = new SignalData(1024, 0.005, 5);
        
        LinearChart firstChart = new LinearChart("Сигнал", "t", "u");
        firstChart.setValues(data.toXYSeries("Исходный сигнал"));
        
        Filter.MatchedFilter(data, 5);
        
        LinearChart secondChart = new LinearChart("СФ", "t", "u");
        secondChart.setValues(data.toXYSeries("Согласованный фильтр"));
        
        Filter.fft(data, true, 512);
        
        LinearChart thirdChart = new LinearChart("БПФ", "f", "y");
        thirdChart.setValues(data.toXYSeries("БПФ"));
        
        Filter.fft(data, true, 512);
        
        LinearChart fourChart = new LinearChart("ОБПФ", "f", "y");
        fourChart.setValues(data.toXYSeries("ОБПФ"));
        
        mainWindow.addChart(firstChart);
        mainWindow.addChart(secondChart);
        mainWindow.addChart(thirdChart);
        mainWindow.addChart(fourChart);
        
        mainWindow.run();
    }
    
}
