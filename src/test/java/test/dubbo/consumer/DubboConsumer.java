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
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Dubbo rpc " + i + " : " + versionService.getVersion(i));
        }
    }

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 10; i++) {
            new DubboConsumer("spring-comsumer.xml").run();
        }
        System.in.read();
    }

}
