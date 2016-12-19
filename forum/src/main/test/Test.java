import com.lsy.util.Config;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;


public class Test {
  @org.junit.Test
    public void sendEmail(){
      HtmlEmail htmlEmail = new HtmlEmail();
      htmlEmail.setHostName("smtp.126.com");
      htmlEmail.setSmtpPort(25);
      htmlEmail.setAuthentication("kaishengit","p@ssw@rd");
      htmlEmail.setCharset("UTF-8");
      htmlEmail.setStartTLSEnabled(true);

      try {
          htmlEmail.setFrom("kaishengit@126.com");
          htmlEmail.setSubject("主题");
          htmlEmail.setHtmlMsg("<h3>Dear <a href=\"www.baidu.com\">www.baidu.com</a> 激活账号</h3>");
          htmlEmail.addTo("1104606795@qq.com");

          htmlEmail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }
}
