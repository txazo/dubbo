package test.dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.dubbo.service.VersionService;

import java.io.IOException;

public class DubboConsumer implements Runnable {

    private String config;

    public DubboConsumer(String config) {
        this.config = config;
    }

    @Override
    public void run() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        context.start();

        VersionService versionService = (VersionService) context.getBean("versionService");
        for (int i = 0; i < 1; i++) {
            System.out.println("Dubbo rpc " + i + " : " + versionService.getVersion());
        }
    }

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 1; i++) {
            new DubboConsumer("spring-comsumer.xml").run();
        }
        System.in.read();
    }

}
