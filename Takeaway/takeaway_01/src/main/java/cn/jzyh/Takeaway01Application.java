package cn.jzyh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@ServletComponentScan
//开启事务
@EnableTransactionManagement
public class Takeaway01Application {

    public static void main(String[] args) {
        log.info("启动成功。。。");
        SpringApplication.run(Takeaway01Application.class, args);
        System.out.println("启动");
    }

}
