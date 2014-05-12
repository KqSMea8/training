package seker.grammer.date;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TimeCount extends JFrame implements Runnable
{
    private static final long serialVersionUID = 397056308850345883L;

    private JLabel lbNow, lbNowTitle, lbLeftSecTitle, lbLeftSec, lbLeftMinTitle, lbLeftMin;

    private Thread clocker;

    public TimeCount()
    {
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        this.setSize(200, 150);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(830, 580);
        initUI();
        clocker = new Thread(this);
        clocker.start();
    }

    private void initUI()
    {
        lbNowTitle = new JLabel("Current Time: ");
        lbNow = new JLabel();
        lbLeftSecTitle = new JLabel("Left Time: ");
        lbLeftSec = new JLabel();
        lbLeftMinTitle = new JLabel("Left Time: ");
        lbLeftMin = new JLabel("");
        
        this.add(lbNowTitle);
        this.add(lbNow);
        this.add(lbLeftSecTitle);
        this.add(lbLeftSec);
        this.add(lbLeftMinTitle);
        this.add(lbLeftMin);
    }

    public void run()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        Calendar startCalendar = Calendar.getInstance();
        long startTime = startCalendar.getTime().getTime(); //Get current time.
        long endTime = startTime + 2 * 60 * 60 * 1000; // Add two hours
        long nowTime, leftTime, leftSec, leftMin;
        Calendar now;
        
        while(true)
        {
            now = Calendar.getInstance();
            nowTime = now.getTime().getTime();
            leftTime = endTime - nowTime;
            leftSec = leftTime / 1000;
            leftMin = leftTime / (60 * 1000);
            
            lbNow.setText(dateFormat.format(now.getTime()));
            lbLeftSec.setText(leftSec + " seconds");    
            lbLeftMin.setText(leftMin + " minutes");
            
            if(leftSec == 0)
            {
                JOptionPane.showMessageDialog(this, "Sorry, time out.", "Tips", JOptionPane.OK_OPTION);
                break;
            }
            
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        new TimeCount();
    }
}