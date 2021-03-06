package file;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class FileUtils {

  private static String suffixTest = ".test";
  
  private FileUtils(){
    
  }

  public static void setSuffixTest(String suffixTest) {
    FileUtils.suffixTest = suffixTest;
  }

  public static void cleanDir(File dir, String endPattern) {
    for (String fileName : dir.getParentFile().list()) {
      if (fileName.endsWith(endPattern)) {
        final File fileToDelete = new File(dir.getParentFile() + File.separator + fileName);
        fileToDelete.renameTo(new File(fileToDelete.getAbsolutePath().replaceAll(endPattern, "-" + suffixTest)));
      }
    }
  }

  public static String sha1sum(InputStream file) {
    final MessageDigest digest = calculateSHA1(file);
    return digestToString(digest);
  }
  
  private static String digestToString(MessageDigest digest) {
    final byte[] mdbytes = digest.digest();
    final StringBuilder builder = new StringBuilder();
    for (int i = 0; i < mdbytes.length; i++) {
      builder.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
    }
    return builder.toString();
  }

  private static MessageDigest calculateSHA1(InputStream inputStream) {
    if(inputStream == null){
      fail("File not found"); 
    }
    final MessageDigest digest = getSHA1();
    final byte[] dataBytes = new byte[1024];
    try {
      int nread = 0;
      while ((nread = inputStream.read(dataBytes)) != -1) {
        digest.update(dataBytes, 0, nread);
      }
    } catch (IOException e) {
      fail(e.getMessage());
    }
    return digest;
  }

  private static MessageDigest getSHA1() {
    MessageDigest digest = null;
    try {
      digest = MessageDigest.getInstance("SHA1");
    } catch (NoSuchAlgorithmException e) {
      fail("No such algorithmException thrown " + e.getMessage());
    }
    return digest;
  }

}
