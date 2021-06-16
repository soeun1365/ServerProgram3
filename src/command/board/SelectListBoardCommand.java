package command.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.ModelAndView;
import dao.BoardDAO;

public class SelectListBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("list", BoardDAO.getInstance().selectList());
		request.setAttribute("count", BoardDAO.getInstance().countBoard());
		return new ModelAndView("/board/listBoard.jsp", false);
	}

}
