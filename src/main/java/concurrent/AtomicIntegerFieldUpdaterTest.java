package concurrent;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

//更新某个字段
public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) {
        User user = new User("tom", 8);
        AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
        System.out.println(updater.getAndIncrement(user));
        System.out.println(updater.get(user));
        System.out.println(user.getAge());
    }
    
    public static class User{
        private String name ;
        public volatile int age;
        
        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        
    }
}
