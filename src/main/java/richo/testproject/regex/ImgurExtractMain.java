package richo.testproject.regex;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * Created by tjerngrr on 2014-09-01.
 */
public class ImgurExtractMain
{
  public static void main(String[] args) throws IOException {
    new ImgurExtractMain().stuff();
  }

  private void stuff() throws IOException {
    String body = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("regex/imgur.txt"));

    System.out.println(body);
  }
}
