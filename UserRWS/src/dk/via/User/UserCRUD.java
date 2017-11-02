package dk.via.User;

import java.util.Collection;
import java.util.HashMap;

public class UserCRUD {
	private static HashMap<Integer, User> users;
	private static int nextId;
	
	static {
		users = new HashMap<>();
		users.put(1, new User(1, "Bob", "bob@example.com"));
		users.put(2, new User(2, "Eve", "eve@megacorp.com"));
		users.put(3, new User(3, "Jill", "j.k.smith@smithanddaughter.com"));
		nextId = 4;
	}

	public static User getUser(int id) {
		if (users.containsKey(id)) {
			return users.get(id);
		} else {
			return new User(-1, "", "");
		}
	}
	
	public static Collection<User> getUsers() {
		return users.values();
	}
	
	public static User deleteUser(int id) {
		return users.remove(id);
	}
	
	public static User createUser(String name, String email) {
		int id = nextId++;
		users.put(id, new User(id, name, email));
		return users.get(id);
	}
	
	public static User createOrUpdate(User user) {
		users.put(user.getId(), user);
		return user;
	}
	
	public static void updateUser(User user) {
		if (users.containsKey(user.getId())) {
			users.put(user.getId(), user);
		}
	}
}
