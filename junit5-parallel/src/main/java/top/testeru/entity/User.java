package top.testeru.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname: User
 * @Description:
 * @Date: 2022/6/15 11:17
 * @Created by top.testeru
 */
public class User {
    private static Map<Integer, String> GLOBAL_USERS = new HashMap<>();

    public static String get(int id) {
        return GLOBAL_USERS.get(id);
    }

    public static void add(int id, String user) {
        GLOBAL_USERS.put(id, user);
    }

    public static void update(int id, String user) {
        GLOBAL_USERS.put(id, user);
    }
    public static void remove(int id) {
        GLOBAL_USERS.remove(id);
    }

    public static void clear() {
        GLOBAL_USERS.clear();
    }

    public static Collection<String> getUsers() {
        return GLOBAL_USERS.values();
    }

    public static void setUsers(Map<Integer, String> users) {
        GLOBAL_USERS = users;
    }
}
