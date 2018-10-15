package ru.hdln.filtercheck.data;

import java.util.ArrayList;
import org.jfree.data.xy.XYSeries;

public class SignalData {

    private ArrayList<Double> data;
    private double step;

    public SignalData(int size, double h, int freq) {
        data = new ArrayList<>();
        step = h;
        this.generate(size, h, freq);
    }
    
    public void setData(ArrayList<Double> data) {
        this.data = data;
    }
    
    public ArrayList<Double> getData() {
        return data;
    }
    
    public void generate(int size, double h, int freq) {
        ArrayList<Double> base = cos(size, h, 5.0, freq, 0);
        ArrayList<Double> noize = sin(size, h, 2.0, freq * 10, 0);
        ArrayList<Double> result = new ArrayList<>();
        
        for(int i = 0; i < size; i++){
            result.add(base.get(i)/* + noize.get(i)*/);
        }
        
        data.addAll(result);
    }
    
    public static ArrayList<Double> cos(int size, double h, double a, int freq, double phase){
        ArrayList<Double> result = new ArrayList<>();
        double x = 0;
        for(int i = 0; i < size; i++) {
            result.add(a * Math.cos(2 * Math.PI * freq * x + phase));
            x+=h;
        }
        
        return result;
    }
    
    public static ArrayList<Double> sin(int size, double h, double a, int freq, double phase){
        ArrayList<Double> result = new ArrayList<>();
        double x = 0;
        for(int i = 0; i < size; i++) {
            result.add(a * Math.sin(2 * Math.PI * freq * x + phase));
            x+=h;
        }
        
        return result;
    }
    
    public XYSeries toXYSeries(String name) {
        XYSeries series = new XYSeries(name);
        double x = 0;
        
        for (Double d:data) {
            series.add(x, d);
            x += step;
        }
        
        return series;
    }
    
}
