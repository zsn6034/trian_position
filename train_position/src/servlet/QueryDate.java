package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import dao.TrainDao;
public class QueryDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		this.getDates(out);
		out.close();
	}
	private void getDates(PrintWriter out) {
		TrainDao trainDao = new TrainDao();
		List<Map<String,String>> listDate = trainDao.getDates();
		Gson gson = new Gson();
		String json = gson.toJson(listDate);
		System.out.println("json==== " + json);
		out.print(json);
		out.flush();
		out.close();
	}

}