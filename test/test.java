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
        StringBuilder sb = new StringBuilder(" ");
        if(sb == null){
            System.err.println("sb是null");
        }else{
            System.err.println(1);
        }
        if(sb.toString() == ""){
            System.err.println("空串");
        }else{
            System.err.println(2);
        }
        if(sb.toString() == null){
            System.err.println("字符串为空");
        }else {
            System.err.println(3);
        }
        System.err.println(sb.toString());
    }
}
