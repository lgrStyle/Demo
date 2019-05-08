package concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

//更新多个字段
public class AtomicReferenceTest {
    
    public static void main(String[] args) {
        User user = new User("tom", 8);
        AtomicReference<User> ref = new AtomicReference<User>();
        ref.set(user);
        System.out.println(ref.getAndSet(new User("Jerry", 11)));
        System.out.println(ref.get());
        
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
        
        @Override
        public String toString() {
            return "User [name=" + name + ", age=" + age + "]";
        }
        
    }
}
