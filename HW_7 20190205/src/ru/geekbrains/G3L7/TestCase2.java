package ru.geekbrains.G3L7;

public class TestCase2 {
    @BeforeSuite
    public void methodBefore() {
        System.out.println("TestCase2.methodBefore");
    }
    @AfterSuite
    public void methodAfter() {
        System.out.println("TestCase2.methodAfter");
    }

    @Test(priority = 3)
    public void methodTest1() {
        System.out.println("TestCase2.methodTest1 priority 3 (lowest)");
    }

    @Test(priority = 1)
    public void methodTest2() {
        System.out.println("TestCase2.methodTest2 priority 1 (highest)");
    }

    @Test(priority = 2)
    public void methodTest3() {
        System.out.println("TestCase2.methodTest3 priority 2(middle)");
    }


    public void commonMethod1() {
        System.out.println("TestCase2.commonMethod1");
    }
    public void commonMethod2() {
        System.out.println("TestCase2.commonMethod2");
    }

}
