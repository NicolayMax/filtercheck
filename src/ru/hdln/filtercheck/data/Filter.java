package ru.hdln.filtercheck.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Filter {
    
//    public static void MachedFilter(SignalData signal, int len) {
//        ArrayList<Double> newSeq = new ArrayList<>();
//        ArrayList<Double> seq = signal.getData();
//        
//        int size = seq.size();
//        
//        for(int i = 0; i < size - len; i++) {
//            newSeq.add(calcMachedFilter(seq.subList(i, i + len)));
//        }
//        
//        signal.setData(newSeq);
//    }
    
    public static void MatchedFilter(SignalData signal, int freq){
        ArrayList<Double> H = SignalData.cos(512, 0.005, 1, freq, 0);
        ArrayList<Double> ip = signal.getData();
        ArrayList op = new ArrayList();
        
        double sum = 0;
        for (int j=0; j < H.size(); j++)
        {
          sum = 0;
          for (int i = 0; i < H.size(); i++)
          {    
            sum += H.get(i) * ip.get(j+i);
          }
          op.add(sum);      
        }
        
        signal.setData(op);
    }
    
    static private double calcMachedFilter(List<Double> list){
        double sum = 0;
        
        for (int i = 0; i < list.size(); i++){
            sum += list.get(i) * list.get(list.size() - 1 - i);
        }
        
        return sum;
    }
    
    public static void fft(SignalData signal, boolean direct, int size) {
        ArrayList<Double> spectrum = new ArrayList<>();
        ArrayList<Double> seq = signal.getData();
        
        double sumRe, sumIm, re, im, arg;
        
        for(int i = size/2; i < size; i++){
            sumRe = 0;
            sumIm = 0;
            for (int j = 0; j < seq.size(); j++){
                if (direct) {
                    arg = 2 * Math.PI * j * i / seq.size();
                } else {
                    arg = 2 * Math.PI * i * j / seq.size();
                }
                re = Math.cos(arg) * seq.get(j);
                im = Math.sin(arg) * seq.get(j);
                sumRe += re;
                sumIm += im;
            }
            spectrum.add(Math.sqrt(sumRe * sumRe + sumIm * sumIm));
        }
        
        signal.setData(spectrum);
    }
    
}
