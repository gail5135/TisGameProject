package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//인터페이스: 추상메소드와 상수만 멤버로 갖는다.
public interface Command {
	
	//public abstract가 자동으로 붙는다.
	void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception;

}
