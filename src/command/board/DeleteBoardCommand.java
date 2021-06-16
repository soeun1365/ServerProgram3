package command.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.ModelAndView;
import dao.BoardDAO;

public class DeleteBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			long no = Long.parseLong(request.getParameter("no"));
			int result = BoardDAO.getInstance().deleteBoard(no);
			PrintWriter out = response.getWriter();
			if(result > 0) {
				out.println("<scrip>");
				out.println("alert('정말 삭제하시겠습니까?')");
				out.println("<scrip>");
				out.println("</scrip>");
			}else {
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
