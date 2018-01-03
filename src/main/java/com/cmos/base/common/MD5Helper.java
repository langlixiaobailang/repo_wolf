package com.cmos.base.common;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5帮助类
 * @author hy 2016年2月23日16:59:29
 *
 */
public class MD5Helper
{
  private static char[] hexDigits = { 
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

  protected static MessageDigest messagedigest = null;

  static {
    try { messagedigest = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException nsaex) {
      System.err.println(MD5Helper.class.getName() + 
        "初始化失败，MessageDigest不支持MD5Util。");
      nsaex.printStackTrace();
    }
  }

  public static String getMD5(String str)
  {
    byte[] bs = str.getBytes();

    return getMD5ToByte(bs);
  }

  private static String getMD5ToByte(byte[] source)
  {
    String s = null;
    try
    {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(source);
      byte[] tmp = md.digest();
      char[] str = new char[32];
      int k = 0;
      for (int i = 0; i < 16; i++)
      {
        byte byte0 = tmp[i];
        str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
        str[(k++)] = hexDigits[(byte0 & 0xF)];
      }
      s = new String(str);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return s;
  }

  private static String byteToHexString(byte[] b)
  {
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < b.length; i++) {
      String hex = Integer.toHexString(b[i] & 0xFF);
      if (hex.length() == 1) {
        hex = '0' + hex;
      }
      hexString.append(hex.toUpperCase());
    }
    return hexString.toString();
  }

  public static String getMD5String(String s)
  {
    return getMD5String(s.getBytes());
  }

  public static boolean checkPassword(String password, String md5PwdStr)
  {
    String s = getMD5String(password);
    return s.equals(md5PwdStr);
  }

  public static String getFileMD5String(File file)
    throws IOException
  {
    InputStream fis = new FileInputStream(file);
    byte[] buffer = new byte[1024];
    int numRead = 0;
    while ((numRead = fis.read(buffer)) > 0) {
      messagedigest.update(buffer, 0, numRead);
    }
    fis.close();
    return bufferToHex(messagedigest.digest());
  }

  public static String getFileMD5String(String filePath) throws IOException
  {
    File file = new File(filePath);

    InputStream fis = new FileInputStream(file);
    byte[] buffer = new byte[1024];
    int numRead = 0;
    while ((numRead = fis.read(buffer)) > 0)
    {
      messagedigest.update(buffer, 0, numRead);
    }
    fis.close();
    return bufferToHex(messagedigest.digest());
  }

  public static String getFileMD5StringByURL(String urlString) throws IOException
  {
    URL url = new URL(urlString);

    HttpURLConnection connection = (HttpURLConnection)url.openConnection();

    BufferedInputStream fis = null;

    fis = new BufferedInputStream(connection.getInputStream());

    byte[] buffer = new byte[1024];
    int numRead = 0;
    while ((numRead = fis.read(buffer)) > 0)
    {
      messagedigest.update(buffer, 0, numRead);
    }
    fis.close();
    return bufferToHex(messagedigest.digest());
  }

  public static String getStrMD5String(String value)
    throws IOException
  {
    byte[] vbyte = value.getBytes();

    messagedigest.update(vbyte, 0, vbyte.length);

    return bufferToHex(messagedigest.digest());
  }

  public static String getbyteArrayMD5String(byte[] vbyte)
    throws IOException
  {
    messagedigest.update(vbyte, 0, vbyte.length);

    return bufferToHex(messagedigest.digest());
  }

  public static String getFileMD5String_old(File file)
    throws IOException
  {
    FileInputStream in = new FileInputStream(file);
    FileChannel ch = in.getChannel();
    MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0L, 
      file.length());
    messagedigest.update(byteBuffer);
    return bufferToHex(messagedigest.digest());
  }

  public static String getMD5String(byte[] bytes) {
    messagedigest.update(bytes);
    return bufferToHex(messagedigest.digest());
  }

  private static String bufferToHex(byte[] bytes) {
    return bufferToHex(bytes, 0, bytes.length);
  }

  private static String bufferToHex(byte[] bytes, int m, int n) {
    StringBuffer stringbuffer = new StringBuffer(2 * n);
    int k = m + n;
    for (int l = m; l < k; l++) {
      appendHexPair(bytes[l], stringbuffer);
    }
    return stringbuffer.toString();
  }

  private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
    char c0 = hexDigits[((bt & 0xF0) >> 4)];
    char c1 = hexDigits[(bt & 0xF)];
    stringbuffer.append(c0);
    stringbuffer.append(c1);
  }
  
  public static void main(String args[]){
	  String aaa=getMD5("123456");
	  System.out.print(aaa);
  } 
}