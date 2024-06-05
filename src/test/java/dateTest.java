import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class dateTest {
    @Test
    public void test(){
//        Date date=new Date("2024-06-05T13:45");
        String timeString = "2024-06-05T19:30"; // ISO 8601格式的时间字符串
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        LocalDateTime localDateTime = LocalDateTime.parse(timeString, formatter);
        // 将LocalDateTime转换为java.util.Date
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("转换后的Date对象: " + date);
    }
}
