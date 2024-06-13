package filter;

import javax.servlet.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CharacterEncoding implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 设置请求和响应的编码为UTF-8
//		request.setCharacterEncoding(StandardCharsets.UTF_8.name());
//		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
//
//		// 设置内容类型和字符编码
//		response.setContentType("text/html; charset=UTF-8");

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 确保响应的头信息也包含正确的字符编码
		//response.setContentType("text/html; charset=UTF-8");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
