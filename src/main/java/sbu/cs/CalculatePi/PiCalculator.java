package sbu.cs.CalculatePi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class PiCalculator {

    /**
     * Calculate pi and represent it as a BigDecimal object with the given floating point number (digits after . )
     * There are several algorithms designed for calculating pi, it's up to you to decide which one to implement.
     * Experiment with different algorithms to find accurate results.
     * <p>
     * You must design a multithreaded program to calculate pi. Creating a thread pool is recommended.
     * Create as many classes and threads as you need.
     * Your code must pass all of the test cases provided in the test folder.
     *
     * @param floatingPoint the exact number of digits after the floating point
     * @return pi in string format (the string representation of the BigDecimal object)
     */

    public static String calculate(int floatingPoint) {
        AtomicReference<BigDecimal> result = new AtomicReference<>(new BigDecimal("0"));
        ExecutorService myFThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i <= 20; ++i) {
            final int index = i;

            myFThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    BigDecimal partialResult = BBPCalculation(index * 100);
                    BigDecimal current = result.get();
                    BigDecimal updated = current.add(partialResult);
                    result.set(updated);
                }
            });

        }

        myFThreadPool.shutdown();

        try {
            myFThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result.get().toString().substring(0,floatingPoint+2);
    }

    private static BigDecimal BBPCalculation(int start) {
        MathContext mc = new MathContext(1001);
        BigDecimal bigDecimal = new BigDecimal("0");
        for (int i = start; i < start + 100; ++i) {
            // 0.0625 = 1/16
            BigDecimal a = new BigDecimal("0.0625").pow(i);
            BigDecimal b = new BigDecimal("4").divide(new BigDecimal((8*i+1)), mc);
            BigDecimal c = new BigDecimal("-2").divide(new BigDecimal((8*i+4)), mc);
            BigDecimal d = new BigDecimal("-1").divide(new BigDecimal((8*i+5)), mc);
            BigDecimal e = new BigDecimal("-1").divide(new BigDecimal((8*i+6)), mc);
            BigDecimal sum = a.multiply(b.add(c, mc).add(d).add(e), mc);
            bigDecimal = bigDecimal.add(sum);
        }
        return bigDecimal;
    }

    public static void main(String[] args) {
        System.out.println(calculate(5));
    }

}