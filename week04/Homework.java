import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Homework {
    public static void main(String[] args) {
        work1();
        work2();
        work3();
        work4();
        work5();
        work6();
        work7();
        work8();
        work9();
    }

    public static String getData() {
        return "hello world -- " + Thread.currentThread().getName();
    }

    private static void work1() {
        //通过join/sleep等待子线程执行完，然后再通过中间对象取结果
        Result result = new Result();
        try {
            Thread thread = new Thread(() -> result.setData(Homework.getData()));
            thread.setName("work1");
            thread.start();
            thread.join(); //or Thread.sleep(1000);
        } catch (Exception e) {

        }
        System.out.println(result.getData());
    }

    private static void work2() {
        //相比work1，重写Thead的run方法
        Result result = new Result();
        try {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    result.setData(Homework.getData());
                }
            };
            thread.setName("work2");
            thread.start();
            thread.join();
        } catch (Exception e) {

        }
        System.out.println(result.getData());
    }

    private static void work3() {
        //循环检查是否完成，然后再通过中间对象取结果
        Result result = new Result();
        try {
            Thread thread = new Thread(() -> result.setData(Homework.getData()));
            thread.setName("work3");
            thread.start();
            while (Objects.isNull(result.getData())) {
                Thread.sleep(500);
            }
        } catch (Exception e) {

        }
        System.out.println(result.getData());
    }

    private static void work4() {
        FutureTask<String> futureTask = new FutureTask<>(() -> Homework.getData());
        Thread thread = new Thread(futureTask);
        thread.setName("work4");
        thread.start();
        try  {
            System.out.println(futureTask.get());
        } catch (Exception e) {

        }
    }

    private static void work5() {
        Result result = new Result();
        FutureTask<String> futureTask = new FutureTask<>(() -> result.setData(Homework.getData()), "success");
        Thread thread = new Thread(futureTask);
        thread.setName("work5");
        thread.start();
        try  {
            String success = futureTask.get();
            if ("success".equals(success)) {
                System.out.println(result.getData());
            }
        } catch (Exception e) {

        }
    }

    private static void work6() {
        FutureTask<String> futureTask = new FutureTask<>(() -> Homework.getData());
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(futureTask);
        executorService.shutdown();
        try {
            System.out.println(futureTask.get());
        } catch (Exception e) {

        }
    }

    private static void work7() {
        Result result = new Result();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Runnable runnable = () -> {
            try {
                result.setData(Homework.getData());
            } finally {
                countDownLatch.countDown();
            }
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(runnable);
        executorService.shutdown();
        try {
            countDownLatch.await();
        } catch (Exception e) {

        }
        System.out.println(result.getData());
    }

    private static void work8() {
        ResultRunnable resultRunnable = new ResultRunnable();
        try {
            Thread thread = new Thread(resultRunnable);
            thread.setName("work8");
            thread.start();
            thread.join();
        } catch (Exception e) {

        }
        System.out.println(resultRunnable.result);
    }

    private static void work9() {
        try {
            Result result = new Result();
            Thread thread = new Thread(() -> result.setData(Homework.getData()));
            thread.setName("work9");
            thread.start();
            while (thread.isAlive()) {

            }
            System.out.println(result.getData());
        } catch (Exception e) {

        }
    }
}

class Result {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String s) {
        data = s;
    }
}

class ResultRunnable implements Runnable {
    public String result;
    @Override
    public void run() {
        result = Homework.getData();
    }
}
