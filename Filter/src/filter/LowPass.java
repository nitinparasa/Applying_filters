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
public class LowPass {
    
    public void fil(String s1) throws IOException{   
      
      ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "cd \"C:\\Users\\mahith\\Desktop\\razorlamepack\" && lame "+s1+" audio2.mp3 --lowpass 19.7");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
        line = r.readLine();
        if (line == null) { break; }
        System.out.println(line);}
    
  }
    
    public static void main(String[] args) throws IOException{
    
       
    }
    
}