package dk.via.User;

import java.io.IOException;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserRWS
 */
@WebServlet({"/users", "/users/*"})
public class UserRWS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int getRequestId(HttpServletRequest request) {
		Pattern pattern = Pattern.compile(".*/users/(\\d+)");
		Matcher matcher = pattern.matcher(request.getRequestURL());
		if (matcher.matches()) {
			return Integer.parseInt(matcher.group(1));
		} else {
			return -1;
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = getRequestId(request);
		ServletOutputStream out = response.getOutputStream();
		if (id == -1) {
			Collection<User> users = UserCRUD.getUsers();
			JSONUtil.writeUsers(out, users);
		} else {
			User user = UserCRUD.getUser(id);
			JSONUtil.writeUser(out, user);
		}
		out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = getRequestId(request);
		User user = JSONUtil.readUser(request.getInputStream());
		ServletOutputStream out = response.getOutputStream();
		if (id == -1) {
			User newUser = UserCRUD.createUser(user.getName(), user.getEmail());
			JSONUtil.writeUser(out, newUser);
		} else {
			user.setId(id);
			UserCRUD.updateUser(user);
			JSONUtil.writeUser(out, user);
		}
		out.flush();
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = getRequestId(request);
		User user = JSONUtil.readUser(request.getInputStream());
		ServletOutputStream out = response.getOutputStream();
		if (id != -1) {
			user.setId(id);
			user = UserCRUD.createOrUpdate(user);
			JSONUtil.writeUser(out, user);
		}
		out.flush();
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = getRequestId(request);
		ServletOutputStream out = response.getOutputStream();
		if (id != -1) {
			User user = UserCRUD.deleteUser(id);
			JSONUtil.writeUser(out, user);
		}
		out.flush();
	}
	
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = getRequestId(request);
		ServletOutputStream out = response.getOutputStream();
		if (id == -1) {
			out.println("Allowed: GET, POST");
		} else {
			out.println("Allowed: GET, POST, PUT, DELETE");
		}
		out.flush();
	}
}
