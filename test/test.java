import com.zing.util.DateUtil;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

public class test {

    @Test
    public void fun1() throws Exception{
        String file="F:/stream.txt";
        String charSet="UTF-8";
        //写字符转换成字节流
        FileOutputStream fileWriter=new FileOutputStream(file);
        OutputStreamWriter writer=new OutputStreamWriter(fileWriter, charSet);
        writer.write("测试输入字符串1");
        writer.close();
    }

    @Test
    public void fun2() throws Exception{
    }
}
