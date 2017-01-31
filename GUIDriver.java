import javax.imageio.*;
import java.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUIDriver
{
    public static void main(String[] arg)
    {
        JFrame workHour = new JFrame ("Shell");

        //ImageIcon img = new ImageIcon("Pictures\\icon.png");
        String icon = "icon.png";
        ImageIcon img = new ImageIcon(icon);
        workHour.setIconImage(img.getImage());
        
        //workHour.setPreferredSize(new Dimension(855,655));  
        workHour.setResizable(false);
        
        workHour.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);      
        workHour.addWindowListener(new java.awt.event.WindowAdapter() 
        {
           @Override
           public void windowClosing(java.awt.event.WindowEvent we)
           { 
            int PromptResult = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Exit?", JOptionPane.YES_NO_OPTION);
             if(PromptResult==JOptionPane.YES_OPTION)
             {
               System.exit(0);
             }
           }
        });

        WorkHourGUI gui = new WorkHourGUI();
        
        workHour.getContentPane().add(gui);
        
        workHour.pack();
        workHour.setVisible(true);
    }
}