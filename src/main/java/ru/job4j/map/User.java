import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {

        Calendar date = Calendar.getInstance();
        date.set(1990, Calendar.JANUARY, 1, 10, 20, 30);
        date.set(Calendar.MILLISECOND, 123); // полное совпадение до миллисекунд

        User user1 = new User("Ivan", 2, date);
        User user2 = new User("Ivan", 2, date);

        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

        System.out.println(map);
    }
}
