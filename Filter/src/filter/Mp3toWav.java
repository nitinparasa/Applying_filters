/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;

/**
 *
 * @author mahith
 */
 public class Mp3toWav extends NewJFrame{

  String x;
  
     public String convert(String s) {
     
        //String x=""; 
          if(s.endsWith(".mp3")){
        
            File source = new File(s);
            s=s.replace(".mp3", ".wav");
            File target = new File(s);
             jTextField1.setText(s); 
            x=s; 
            System.out.println(s);
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("pcm_s16le");
            audio.setBitRate(128000);
            audio.setChannels(2);
            audio.setSamplingRate(44100);
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("wav");
            attrs.setAudioAttributes(audio);
            Encoder en = new Encoder();
            
         try {   
               en.encode(source,target,attrs);
           } catch (IllegalArgumentException ex) {
               Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
           } catch (EncoderException ex) {
               Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
           }
        } 
          else{
          
             System.out.println("wav format");
          }
           return x;
     }    
          
      public void play(String s){  
           
           s=s.replace(".mp3", ".wav");
          AudioSpectrum as=new AudioSpectrum(); 
        try {
            as.fill(x);
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            File f2=new File(x);
            //System.out.print(f2);
            
            // check wav file
           try {
               InputStream in1 = new FileInputStream(f2);
               AudioPlayer.player.start(in1);
                
        } catch (FileNotFoundException ex) {
               Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
           }  
           
          Example ex1=new Example();
             try {
                 ex1.spectrum();
             } catch (IOException ex) {
                 Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
       }
           
   
        private javax.swing.JTextField jTextField1;
}
