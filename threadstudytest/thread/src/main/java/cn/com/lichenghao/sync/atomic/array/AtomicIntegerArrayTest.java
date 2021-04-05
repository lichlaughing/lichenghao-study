package cn.com.lichenghao.sync.atomic.array;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author chenghao.li
 * 演示 AtomicIntegerArray 的一些基础操作
 */
public class AtomicIntegerArrayTest {
    public static void main(String[] args) {
        // 1.声明数组，长度为 10
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
        System.out.println("1." + atomicIntegerArray);
        // 2.返回指定位置的元素
        int i = atomicIntegerArray.get(2);
        System.out.println("2." + i);
        // 设置指定位置元素的值 atomicIntegerArray.set(0,10);
        // 3.设置指定位置的元素，并且返回旧值
        int andSet = atomicIntegerArray.getAndSet(3, 10);
        System.out.println("3." + andSet);
        // 4.把数组元素的值，加上某个值。返回这个元素的新值
        int i1 = atomicIntegerArray.addAndGet(4, 10);
        System.out.println("4." + i1);
        // 5.获取某个元素的值，然后再给这个元素加上某个值
        int andAdd = atomicIntegerArray.getAndAdd(4, 10);
        System.out.println("5." + andAdd);
        // 6.CAS 操作，数组下标为6的位置的元素的值为10就替换成20
        boolean b = atomicIntegerArray.compareAndSet(6, 10, 20);
        System.out.println("6." + b);
        // 自增/自减
        int i2 = atomicIntegerArray.incrementAndGet(7);
        int i3 = atomicIntegerArray.decrementAndGet(8);
        System.out.println("7." + i2 + "," + i3);
        System.out.println(atomicIntegerArray);
    }
}
