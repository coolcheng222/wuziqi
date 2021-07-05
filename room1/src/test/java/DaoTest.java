import com.sealll.bean.Room;
import com.sealll.config.SpringConfig1;
import com.sealll.config.SpringConfig2;
import com.sealll.dao.RoomDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;

/**
 * @author sealll
 * @time 2021/7/5 17:16
 */
@ContextConfiguration(classes= SpringConfig2.class)
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
public class DaoTest {
    @Autowired
    private RoomDao roomDao;
    @Test
    public void test1(){
        Map<Integer, Room> integerRoomMap =
                roomDao.selectAll();
        System.out.println(integerRoomMap);
    }
}
