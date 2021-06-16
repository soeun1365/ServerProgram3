package command.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.ModelAndView;
import dao.BoardDAO;
import dto.ReplyDTO;

public class InsertReplyCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
	try {
		
		long board_no = Long.parseLong(request.getParameter("board_no"));
		String author = request.getParameter("authhor");
		String content = request.getParameter("content");
		String ip = request.getParameter("ip");
		
		ReplyDTO Re_dto = new ReplyDTO();
		Re_dto.setBoard_no(board_no);
		Re_dto.setAuthor(author);
		Re_dto.setContent(content);
		Re_dto.setIp(ip);
		
		int result = BoardDAO.getInstance().insertReply(Re_dto);
		PrintWriter out = response.getWriter();
		if(result == 0) {
			out.println("<script>");
			out.println("alert('댓글이 등록되지 않았습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else if(result > 0) {
			out.println("<script>");
			out.println("alert('댓글이 등록되었습니다.')");
			out.println("location.href='/ServerProgram3/selectListBoard.do'");
			out.println("</script>");
		}
	}catch(Exception e) {
		e.printStackTrace();
	}		
		return null;
	}

}
