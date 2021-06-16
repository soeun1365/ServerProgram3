package command.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;
import dto.ReplyDTO;

public class SelectBoardByNoCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		long no = Long.parseLong(request.getParameter("no"));
		BoardDAO.getInstance().updateHit(no);
		BoardDTO dto = BoardDAO.getInstance().selectBoardByNo(no);
		ReplyDTO Re_dto = BoardDAO.getInstance().selectReplyByNo(no);
		
		request.setAttribute("dto", dto);
		request.setAttribute("Re_dto", Re_dto);
		ModelAndView mav = new ModelAndView("board/view.jsp", false);
		return mav;
	}

}
