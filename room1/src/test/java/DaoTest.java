import com.sealll.bean.Room;
import com.sealll.config.SpringConfig2;
import com.sealll.dao.RoomDao;
import com.sealll.manager.impl.SimpleRoomManager;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    public void test2(){
        SimpleRoomManager simpleRoomManager = new SimpleRoomManager();
        simpleRoomManager.createRoom(1234,"123");
    }
}
