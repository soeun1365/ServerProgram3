package controller;

import command.board.BoardCommand;
import command.board.DeleteBoardCommand;
import command.board.InsertBoardCommand;
import command.board.InsertBoardPageCommand;
import command.board.InsertReplyCommand;
import command.board.SelectBoardByNoCommand;
import command.board.SelectListBoardCommand;

public class BoardCommandMapper {

	private static BoardCommandMapper instance = new BoardCommandMapper();
	private BoardCommandMapper() {}
	public static BoardCommandMapper getInstance() {
		if (instance == null) {
			instance = new BoardCommandMapper();
		}
		return instance;
	}
	
	public BoardCommand getCommand(String cmd) {
		BoardCommand command = null;
		switch (cmd) {
		case "selectListBoard.do":
			command = new SelectListBoardCommand();
			break;
		case "insertBoardPage.do":
			command = new InsertBoardPageCommand();
			break;
		case "insertBoard.do":
			command = new InsertBoardCommand();
			break;
		case "selectBoardByNo.do":
			command = new SelectBoardByNoCommand();
			break;
		case "insertReply.do":
			command = new InsertReplyCommand();
			break;
		case "deleteBoard.do":
			command = new DeleteBoardCommand();
			break;
		}
		return command;
	}
	
}
