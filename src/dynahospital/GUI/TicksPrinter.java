/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynahospital.GUI;

import dynahospital.PatientDriver;
import dynahospital.DynaHospital;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class TicksPrinter {

    private JFrame frame;
    private JLabel currentTime;

    public TicksPrinter() {
        createFrameOptions();

        Timer timer = new Timer(1000/DynaHospital.howMuchFasterFromRealTimeInSecond, new TimerListener());
        timer.start();
    }

    class EventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Code added here 
        }
    }

    private void createFrameOptions() {
        frame = new JFrame("Simulation Time");
        frame.getContentPane().add(createMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.pack();
        frame.setLocationRelativeTo(null); //Centers frame. Must follow pack()
        frame.setVisible(true);
    }

    private JPanel createTimePanel() {
        JPanel timePanel = new JPanel(new GridBagLayout());

        currentTime = new JLabel(("<html><center>" + "00/00/0000" + "<br/>" + "WednesdayW 00:00" + "</html>"), SwingConstants.CENTER);
        currentTime.setFont(currentTime.getFont().deriveFont(48.0f));
        currentTime.setSize(currentTime.getMaximumSize().width+1024,currentTime.getMaximumSize().height);
        currentTime.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;

        timePanel.add(currentTime, c);
        return timePanel;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(createTimePanel());
        return mainPanel;
    }

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Date date3 = new Date(PatientDriver.getTicks() * 1000);
            SimpleDateFormat sdf3 = new SimpleDateFormat("EEEE HH:mm", Locale.ENGLISH);
            SimpleDateFormat sdf4 = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            sdf3.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            String formattedDate3 = sdf3.format(date3);
            sdf4.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            String formattedDate4 = sdf4.format(date3);
            currentTime.setText("<html><center>" + formattedDate4 + "<br/>      " + formattedDate3 + "      </html>");
        }
    }
}
