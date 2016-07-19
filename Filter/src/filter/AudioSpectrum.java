/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author mahith
 */
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class AudioSpectrum {
    
    public void fill(String s1) throws IOException{   
    
        // Generate an audio spectrum for the output audio file using SOX executable
        
        ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "cd \"C:\\Users\\mahith\\Desktop\" &&  sox "+s1+" -n spectrogram");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
        line = r.readLine();
        if (line == null) { break; }
        System.out.println(line);
        }
        
    }
    
    public void spectrum() throws IOException{
        
        // this function is never used.
     // another way to open the output audio spectrum
     
       
        
        ProcessBuilder builder1 = new ProcessBuilder(
            "cmd.exe", "/c", "cd \"C:\\Users\\mahith\\Desktop\" && spectrogram.png");
        builder1.redirectErrorStream(true);
        Process p1 = builder1.start();
        BufferedReader r1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
        String line1;
        while (true) {
        line1 = r1.readLine();
        if (line1 == null) { break; }
        System.out.println(line1);
    }
  }      

}    

class Example {

      // Display the output spectrum (for the output audio file)
    
    public void spectrum() throws IOException
    {
        BufferedImage img=ImageIO.read(new File("C:\\Users\\mahith\\Desktop\\spectrogram.png"));
        ImageIcon icon=new ImageIcon(img);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(500,500);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

  
