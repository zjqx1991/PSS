import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;


public class App {

    @Test
    public void dbPassword() throws Exception {
        String pd =  ConfigTools.encrypt("admin");
        System.out.println("App.dbPassword:==" + pd);
    }
}
