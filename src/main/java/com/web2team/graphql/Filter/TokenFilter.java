package com.web2team.graphql.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter implements Filter {
  private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;

//    req.getSession().getAttribute("token")
//    restful로 login 할 때 seesion에 token을 발급해주기
//    token은 id + (암호화된pw) -> 가지고 만들기, 스프링 내에서 복호화한뒤 db에서 pw가져오고 매칭 후 ->
//    -> token을 그때그떄 만들어서 seesion에 저장. 시간 제한 걸어주기.

    if (!"123auth".equals(req.getHeader("auth"))) {
      ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "no auth");
    }
    chain.doFilter(req, response);
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void destroy() {}
}
