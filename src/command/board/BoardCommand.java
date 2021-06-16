package command.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.ModelAndView;

public interface BoardCommand {
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response);
}
