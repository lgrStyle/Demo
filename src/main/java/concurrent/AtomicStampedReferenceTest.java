package concurrent;

import java.util.concurrent.atomic.AtomicStampedReference;

//带上版本号，避免ABA问题
public class AtomicStampedReferenceTest {

    public static void main(String[] args) {
        User user = new User("Tom", 9);
        AtomicStampedReference<User> ref = new AtomicStampedReference<User>(user, 1);
        System.out.println(ref.getReference());
        System.out.println(ref.compareAndSet(user, new User("Je", 11),  1, 2));
        System.out.println(ref.getReference());
        System.out.println(ref.getStamp());
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
