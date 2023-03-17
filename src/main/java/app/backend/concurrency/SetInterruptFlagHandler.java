package app.backend.concurrency;

public class SetInterruptFlagHandler implements ExceptionHandler<InterruptedException> {
    @Override
    public void handle(InterruptedException exception) {
        Thread.currentThread().interrupt();
    }

    public static void main(String[] args) {
        ExceptionHandler<InterruptedException> handlerWithPrinting = new SetInterruptFlagHandler() {
            @Override
            public void handle(InterruptedException exception) {
                System.out.println("Using handler");
                super.handle(exception);
            }
        };

        long time = 0;
        int waitTime = 50;
        long startTime = System.currentTimeMillis();
        long simulationTime = 4_000;
        Thread threadWithLoop = Thread.currentThread();
        Thread interruptor = new Thread(() -> {
            int waitForInterruption = 1300;
            try {
                Thread.sleep(waitForInterruption);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadWithLoop.interrupt();
        });
        interruptor.start();
        while (time < simulationTime) {
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                handlerWithPrinting.handle(e);
            }
            time = System.currentTimeMillis() - startTime;
        }
    }


}
