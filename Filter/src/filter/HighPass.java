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
 class HighPass {
    
    public void high(String s2) throws IOException{   
      
      // applying high pass filter using razor lame executable (Developed on c)
      // specfying path of the audio file as input to the executabe using command line
        
      ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "cd \"C:\\Users\\mahith\\Desktop\\razorlamepack\" && lame "+s2+" audio3.mp3 --highpass 19.7");
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
    
}