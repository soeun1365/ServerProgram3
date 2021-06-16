package command.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class InsertBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		try{
			String author = request.getParameter("author");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String ip = request.getParameter("ip");
			
			BoardDTO dto = new BoardDTO();
			dto.setAuthor(author);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setIp(ip);
			
			int result = BoardDAO.getInstance().insertBoard(dto);
			PrintWriter out = response.getWriter();
			if(result == 0) {
				out.println("<script>");
				out.println("alert('게시글이 저장되지 않았습니다.')");
				out.println("history.back()");
				out.println("</script>");
			}else if(result > 0) {
				out.println("<script>");
				out.println("alert('게시글이 저장되었습니다.')");
				out.println("location.href='/ServerProgram3/selectListBoard.do'");
				out.println("</script>");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
