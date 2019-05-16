package lang;

public class IntegerTest {

    public static void main(String[] args) {
        //Integer缓存 -127 ~ 128,适用于自动装拆箱，新建对象不适用
        //自动装箱，相当于 Integer a = Integer.valueOf(100);
        //自动拆箱，相当于 int i = a.intValue();
        Integer a = 100;
        Integer b = 100;
        Integer c = 200;
        Integer d = 200;
        Integer e = new Integer(100);
        System.out.println("a == b : " + (a == b ));
        System.out.println("c == d : " + (c == d ));
        System.out.println("a == e : " + (a == e ));
    }
}
