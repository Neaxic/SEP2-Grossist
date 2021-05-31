package shared.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Frederik Bergmann

public class md5
{
  public static String encode(String input)
  {
    String hash = null;
    if (input == null) return null;

    try
    {
      MessageDigest digest = MessageDigest.getInstance("MD5");
      digest.update(input.getBytes(), 0, input.length());
      hash = new BigInteger(1, digest.digest()).toString(16);
    }
    catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
    return hash;
  }

  public static boolean compare(String input, String md5Hash)
  {
    String inputHash = encode(input);

    return inputHash.equals(md5Hash);
  }
}
