package test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;

//서버가 가동될 때 시점을 개발자가 잡아낼 수 있을까?
public class MyTomcatListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent e) {
		System.out.println("어플리케이션 가동");
		e.getServletContext().getInitParameter("파라미터 명");
		//만약 우리가 스프링을 제작하는 개발자였다면 이 타이밍을 놓치지 않고 모든  dispatcher서블릿들이
		//공통으로 사용할 수 있도록 공통의 빈즈를 메모리에 올릴타이밍
		//빈즈.xml
	}

	@Override
	public void contextDestroyed(ServletContextEvent e) {
		System.out.println("어플리케이션 중단");
	}

}
