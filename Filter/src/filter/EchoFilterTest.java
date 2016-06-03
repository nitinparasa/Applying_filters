/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

/**
 *
 * @author mahith
 */
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
//import filter.EchoFilterTest.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * An example of playing a sound with an echo filter.
 * 
 * @see EchoFilter
 * @see SimpleSoundPlayer
 */

public class EchoFilterTest {

  public static void main(String[] args) {

    NewJFrame n=new NewJFrame();
    String s1=n.get();
    
    // load the sound
    SimpleSoundPlayer sound = new SimpleSoundPlayer(s1);

    // create the sound stream
    InputStream is = new ByteArrayInputStream(sound.getSamples());

    // create an echo with a 11025-sample buffer
    // (1/4 sec for 44100Hz sound) and a 60% decay
    EchoFilter filter = new EchoFilter(11025, .6f);

    // create the filtered sound stream
    is = new FilteredSoundStream(is, filter);

    // play the sound
    sound.play(is);

    // due to bug in Java Sound, explicitly exit the VM.
    System.exit(0);
  }

    static void main() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/**
 * The SimpleSoundPlayer encapsulates a sound that can be opened from the file
 * system and later played.
 */

class SimpleSoundPlayer {

  public static void main(String[] args) {
    // load a sound
     NewJFrame n=new NewJFrame();
    String s1=n.get();
      SimpleSoundPlayer sound = new SimpleSoundPlayer(s1);

    // create the stream to play
    InputStream stream = new ByteArrayInputStream(sound.getSamples());

    // play the sound
    sound.play(stream);

    // exit
    System.exit(0);
  }

  private AudioFormat format;

  private byte[] samples;

  /**
   * Opens a sound from a file.
   */
  public SimpleSoundPlayer(String filename) {
    try {
      // open the audio input stream
      AudioInputStream stream = AudioSystem.getAudioInputStream(new File(
          filename));

      format = stream.getFormat();

      // get the audio samples
      samples = getSamples(stream);
    } catch (UnsupportedAudioFileException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Gets the samples of this sound as a byte array.
   */
  public byte[] getSamples() {
    return samples;
  }

  /**
   * Gets the samples from an AudioInputStream as an array of bytes.
   */
  private byte[] getSamples(AudioInputStream audioStream) {
    // get the number of bytes to read
    int length = (int) (audioStream.getFrameLength() * format
        .getFrameSize());

    // read the entire stream
    byte[] samples = new byte[length];
    DataInputStream is = new DataInputStream(audioStream);
    try {
      is.readFully(samples);
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    // return the samples
    return samples;
  }

  /**
   * Plays a stream. This method blocks (doesn't return) until the sound is
   * finished playing.
   */
  public void play(InputStream source) {

    // use a short, 100ms (1/10th sec) buffer for real-time
    // change to the sound stream
    int bufferSize = format.getFrameSize()
        * Math.round(format.getSampleRate() / 10);
    byte[] buffer = new byte[bufferSize];

    // create a line to play to
    SourceDataLine line;
    try {
      DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
      line = (SourceDataLine) AudioSystem.getLine(info);
      line.open(format, bufferSize);
    } catch (LineUnavailableException ex) {
      ex.printStackTrace();
      return;
    }

    // start the line
    line.start();

    // copy data to the line
    try {
      int numBytesRead = 0;
      while (numBytesRead != -1) {
        numBytesRead = source.read(buffer, 0, buffer.length);
        if (numBytesRead != -1) {
          line.write(buffer, 0, numBytesRead);
        }
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    // wait until all data is played, then close the line
    line.drain();
    line.close();

  }

}

/**
 * The EchoFilter class is a SoundFilter that emulates an echo.
 * 
 * @see FilteredSoundStream
 */

class EchoFilter extends SoundFilter {

  private short[] delayBuffer;

  private int delayBufferPos;

  private float decay;

  /**
   * Creates an EchoFilter with the specified number of delay samples and the
   * specified decay rate.
   * <p>
   * The number of delay samples specifies how long before the echo is
   * initially heard. For a 1 second echo with mono, 44100Hz sound, use 44100
   * delay samples.
   * <p>
   * The decay value is how much the echo has decayed from the source. A decay
   * value of .5 means the echo heard is half as loud as the source.
   */
  public EchoFilter(int numDelaySamples, float decay) {
    delayBuffer = new short[numDelaySamples];
    this.decay = decay;
  }

  /**
   * Gets the remaining size, in bytes, of samples that this filter can echo
   * after the sound is done playing. Ensures that the sound will have decayed
   * to below 1% of maximum volume (amplitude).
   */
  public int getRemainingSize() {
    float finalDecay = 0.01f;
    // derived from Math.pow(decay,x) <= finalDecay
    int numRemainingBuffers = (int) Math.ceil(Math.log(finalDecay)
        / Math.log(decay));
    int bufferSize = delayBuffer.length * 2;

    return bufferSize * numRemainingBuffers;
  }

  /**
   * Clears this EchoFilter's internal delay buffer.
   */
  public void reset() {
    for (int i = 0; i < delayBuffer.length; i++) {
      delayBuffer[i] = 0;
    }
    delayBufferPos = 0;
  }

  /**
   * Filters the sound samples to add an echo. The samples played are added to
   * the sound in the delay buffer multipied by the decay rate. The result is
   * then stored in the delay buffer, so multiple echoes are heard.
   */
  public void filter(byte[] samples, int offset, int length) {

    for (int i = offset; i < offset + length; i += 2) {
      // update the sample
      short oldSample = getSample(samples, i);
      short newSample = (short) (oldSample + decay
          * delayBuffer[delayBufferPos]);
      setSample(samples, i, newSample);

      // update the delay buffer
      delayBuffer[delayBufferPos] = newSample;
      delayBufferPos++;
      if (delayBufferPos == delayBuffer.length) {
        delayBufferPos = 0;
      }
    }
  }

}

/**
 * The FilteredSoundStream class is a FilterInputStream that applies a
 * SoundFilter to the underlying input stream.
 * 
 * @see SoundFilter
 */

class FilteredSoundStream extends FilterInputStream {

  private static final int REMAINING_SIZE_UNKNOWN = -1;

  private SoundFilter soundFilter;

  private int remainingSize;

  /**
   * Creates a new FilteredSoundStream object with the specified InputStream
   * and SoundFilter.
   */
  public FilteredSoundStream(InputStream in, SoundFilter soundFilter) {
    super(in);
    this.soundFilter = soundFilter;
    remainingSize = REMAINING_SIZE_UNKNOWN;
  }

  /**
   * Overrides the FilterInputStream method to apply this filter whenever
   * bytes are read
   */
  public int read(byte[] samples, int offset, int length) throws IOException {
    // read and filter the sound samples in the stream
    int bytesRead = super.read(samples, offset, length);
    if (bytesRead > 0) {
      soundFilter.filter(samples, offset, bytesRead);
      return bytesRead;
    }

    // if there are no remaining bytes in the sound stream,
    // check if the filter has any remaining bytes ("echoes").
    if (remainingSize == REMAINING_SIZE_UNKNOWN) {
      remainingSize = soundFilter.getRemainingSize();
      // round down to nearest multiple of 4
      // (typical frame size)
      remainingSize = remainingSize / 4 * 4;
    }
    if (remainingSize > 0) {
      length = Math.min(length, remainingSize);

      // clear the buffer
      for (int i = offset; i < offset + length; i++) {
        samples[i] = 0;
      }

      // filter the remaining bytes
      soundFilter.filter(samples, offset, length);
      remainingSize -= length;

      // return
      return length;
    } else {
      // end of stream
      return -1;
    }
  }

}

/**
 * A abstract class designed to filter sound samples. Since SoundFilters may use
 * internal buffering of samples, a new SoundFilter object should be created for
 * every sound played. However, SoundFilters can be reused after they are
 * finished by called the reset() method.
 * <p>
 * Assumes all samples are 16-bit, signed, little-endian format.
 * 
 * @see FilteredSoundStream
 */

abstract class SoundFilter {

  /**
   * Resets this SoundFilter. Does nothing by default.
   */
  public void reset() {
    // do nothing
  }

  /**
   * Gets the remaining size, in bytes, that this filter plays after the sound
   * is finished. An example would be an echo that plays longer than it's
   * original sound. This method returns 0 by default.
   */
  public int getRemainingSize() {
    return 0;
  }

  /**
   * Filters an array of samples. Samples should be in 16-bit, signed,
   * little-endian format.
   */
  public void filter(byte[] samples) {
    filter(samples, 0, samples.length);
  }

  /**
   * Filters an array of samples. Samples should be in 16-bit, signed,
   * little-endian format. This method should be implemented by subclasses.
   */
  public abstract void filter(byte[] samples, int offset, int length);

  /**
   * Convenience method for getting a 16-bit sample from a byte array. Samples
   * should be in 16-bit, signed, little-endian format.
   */
  public static short getSample(byte[] buffer, int position) {
    return (short) (((buffer[position + 1] & 0xff) << 8) | (buffer[position] & 0xff));
  }

  /**
   * Convenience method for setting a 16-bit sample in a byte array. Samples
   * should be in 16-bit, signed, little-endian format.
   */
  public static void setSample(byte[] buffer, int position, short sample) {
    buffer[position] = (byte) (sample & 0xff);
    buffer[position + 1] = (byte) ((sample >> 8) & 0xff);
  }
 private javax.swing.JButton jButton1;
 private javax.swing.JTextField jTextField1;
}       
           