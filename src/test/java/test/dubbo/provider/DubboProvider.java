package test.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DubboProvider implements Runnable {

    private String config;

    public DubboProvider(String config) {
        this.config = config;
    }

    @Override
    public void run() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        context.start();
    }

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 3; i++) {
            new Thread(new DubboProvider("spring-provider-cluster-" + i + ".xml")).start();
        }
        System.in.read();
    }

}
