package servlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Train;
import dao.TrainDao;
public class QueryTrains extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String start_station =request.getParameter("start_station");
		String end_station = request.getParameter("end_station");
		TrainDao trainDao = new TrainDao();
		List<Train> listTrain = trainDao.getTrainNameList(start_station,end_station);
		request.setAttribute("listTrain", listTrain);
		request.getRequestDispatcher("/train_position/train_query.jsp").forward(request, response);
	}
}