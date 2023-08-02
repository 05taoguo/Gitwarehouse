import cn.jzyh.service.*;
import cn.jzyh.service.impl.UserDaoImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    //  构造方法
    @Test
    public void text1() {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student1Dao bookDao = (Student1Dao) app.getBean("bookDao1");

        bookDao.save();

    }

    //    静态工厂
    @Test
    public void text2() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student1Dao bookDao = (Student1Dao) app.getBean("bookDao2");

        bookDao.save2();

    }

    //    实例工厂
    @Test
    public void text3() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student1Dao bookDao = (Student1Dao) app.getBean("bookDao3");

        bookDao.save3();

    }

    //    初始化和销毁
    @Test
    public void test4() {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

        StudentDao bookDao = (StudentDao) app.getBean("bookDao4");

        bookDao.save();

        app.close();
    }


    //session注入
    @Test
    public void text5(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

        TeacherDao teacherDao = (TeacherDao) app.getBean("teacherDao");

        teacherDao.save();
    }

    //结构化注入
    @Test
    public void text6(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserDao userDao = (UserDao) app.getBean("teacherDaoB");

        userDao.save();
    }


    //自动注入
    @Test
    public void text7(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

        PersonDao personDao = (PersonDao) app.getBean("person1");

        personDao.save();
    }


//    泛型
    @Test
    public void text8(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

        TeacherDao teacherDao = (TeacherDao) app.getBean("userDao11");

        teacherDao.save2();
    }

}
