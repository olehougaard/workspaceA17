package dk.via.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile(".*/users/(\\d+)");
		Matcher matcher = pattern.matcher("blah/users/3");
		System.out.println(matcher.matches());
		System.out.println(matcher.group(1));
		matcher = pattern.matcher("blah/users");
		System.out.println(matcher.matches());
	}
}
