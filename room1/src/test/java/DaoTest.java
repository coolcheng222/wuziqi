import com.sealll.RoomApplication;
import com.sealll.bean.Room;
import com.sealll.config.SpringConfig2;
import com.sealll.dao.RoomDao;
import com.sealll.manager.impl.SimpleRoomManager;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.ibatis.plugin.Intercepts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/**
 * @author sealll
 * @time 2021/7/5 17:16
 */
@SpringBootTest(classes = RoomApplication.class)
public class DaoTest {
    @Autowired
    private RoomDao roomDao;
    @Test
    @Ignore
    public void test1(){
//        Map<Integer, Room> integerRoomMap =
//                roomDao.selectAll();
//        System.out.println(integerRoomMap);
        Room room1 = new Room();
        room1.setId(3);
        Room room = roomDao.selectByIdWithUser(1);
        System.out.println(room);
    }

    @Test
    @Ignore
    public void test2(){
        SimpleRoomManager simpleRoomManager = new SimpleRoomManager();
        simpleRoomManager.createRoom(1234,"123");
    }

    @Test
    public void test3(){
        Set<Integer> integers =
                roomDao.selectIds();
        System.out.println(integers);
    }
}
