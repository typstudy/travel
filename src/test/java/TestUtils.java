import com.typ.travel.util.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author typ
 * @date 2019/4/15 21:13
 * @Description: PACKAGE_NAME
 */
public class TestUtils {
    @Test
    public void test1(){
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
        System.out.println(template);
        /*String sql = "insert into tab_user(username,password,name,birthday," +
                "sex,telephone,email) values('li4','123456','外网','1997-10-25','男','1223456','1239@qq.com')";
        template.execute(sql);*/
    }
}
