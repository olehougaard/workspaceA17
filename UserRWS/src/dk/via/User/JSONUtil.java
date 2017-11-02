package dk.via.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;

public class JSONUtil {
	public static void writeUsers(OutputStream out, Collection<User> users) throws IOException {
		try (JsonWriter writer = new JsonWriter(new OutputStreamWriter(out))) {
			writer.setIndent("  ");
			writeUsers(writer, users);
		}
	}
	
	public static void writeUser(OutputStream out, User user) throws IOException {
		try (JsonWriter writer = new JsonWriter(new OutputStreamWriter(out))) {
			writer.setIndent("  ");
			writeUser(writer, user);
		}
	}

	private static void writeUsers(JsonWriter writer, Collection<User> users) throws IOException {
		writer.beginArray();
		for(User user: users) {
			writeUser(writer, user);
		}
		writer.endArray();
	}
	
	private static void writeUser(JsonWriter writer, User user) throws IOException {
		writer.beginObject();
		writer.name("id").value(user.getId());
		writer.name("name").value(user.getName());
		writer.name("email").value(user.getEmail());
		writer.endObject();
	}
	
	public static User readUser(InputStream in) throws IOException {
		JsonParser parser = new JsonParser();
		try (InputStreamReader reader = new InputStreamReader(in)) {
			JsonObject object = parser.parse(reader).getAsJsonObject();
			JsonElement id = object.get("id");
			JsonElement name = object.get("name");
			JsonElement email = object.get("email");
			if (id != null) {
				return new User(id.getAsInt(), name.getAsString(), email.getAsString());
			} else {
				return new User(-1, name.getAsString(), email.getAsString());
			}
		}
	}
}
