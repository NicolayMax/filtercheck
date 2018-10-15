package ru.hdln.filtercheck.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ru.hdln.filtercheck.charts.IChart;

public class MainWindow implements Runnable {

    private JFrame frame;
    private JPanel buttonsPanel;
    private JPanel chartPanel;
    
    private JButton exitButton;
    
    private List<IChart> charts;
    
    public MainWindow() {
        charts = new ArrayList<>();
        
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        
        chartPanel = new JPanel();
                
        buttonsPanel = new JPanel();
        
        exitButton = new JButton("Закрыть");
        exitButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        
        
        chartPanel.setLayout(new GridLayout(2,1));
        buttonsPanel.setBackground(Color.white);
        buttonsPanel.add(exitButton);
        
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);
        frame.pack();
        //center of monitor
        frame.setLocationRelativeTo(null);
    }
    
    public void addChart(IChart chart) {
        charts.add(chart);
        chartPanel.add(charts.get(charts.size() - 1).getChart());
        frame.pack();
    }
    
    @Override
    public void run() {
        frame.setVisible(true);
    }
    
}
