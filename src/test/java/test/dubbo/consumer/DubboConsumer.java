package test.dubbo.consumer;

import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.dubbo.service.VersionService;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class DubboConsumer implements Runnable {

    private final int index;
    private final String config;

    public DubboConsumer(int index, String config) {
        this.index = index;
        this.config = config;
    }

    @Override
    public void run() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        context.start();

        final VersionService versionService = (VersionService) context.getBean("versionService" + index);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            try {
                Thread.sleep(1000);

                invoke(new Callable() {

                    @Override
                    public Object call() throws Exception {
                        String result = null;
                        int time = new Random().nextInt(1000);
                        if (index == 1) {
                            result = versionService.getVersion(time);
                        } else {
                            versionService.getVersion(time);
                            Future<String> future = RpcContext.getContext().getFuture();
                            result = future.get();
                        }
                        System.out.println("Dubbo rpc " + index + " : " + result);
                        return null;
                    }

                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized static void invoke(Callable callable) throws Exception {
        callable.call();
    }

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 1; i++) {
            new Thread(new DubboConsumer(i, "spring-comsumer-" + i + ".xml")).start();
        }
        System.in.read();
    }

}
