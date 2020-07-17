package com.mercury.common.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercury.jpa.model.history.HistoryRequest;
import com.mercury.jpa.repository.history.HistoryRequestRepository;
import com.mercury.util.IpUtil;

/**
 * @author nkt
 *
 *
 * Create by User Date : 2020. 7. 10.
 *
 */
@WebFilter(urlPatterns = "/**")
@Component
public class LogFilter implements Filter {
	
	private static final Logger LOGGER = LogManager.getLogger(LogFilter.class);
	
	@Autowired
	private HistoryRequestRepository historyRequestRepository;
	/**
	 *
	 * 클라이언트의 요청 시 전/후 처리
	 * FilterChain을 통해 전달
	 *
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest rq = (HttpServletRequest) request;
		HttpServletResponse rs = (HttpServletResponse) response;
		
		try {
//			LOGGER.debug("Now : " + DateUtil.now());
//			LOGGER.debug("method : " + rq.getMethod());
//			LOGGER.debug("request uri : " + rq.getRequestURI());
//			LOGGER.debug("local port : " + rq.getLocalPort());
//			LOGGER.debug("server port : " + rq.getServerPort());
//			LOGGER.debug("user principal : " + rq.getUserPrincipal());
//			LOGGER.debug("servelt path : " + rq.getServletPath());
			
			HistoryRequest hq = new HistoryRequest();
			hq.setRequestDate(new Date());
			hq.setRequestUrl(rq.getRequestURI());
			hq.setIp(IpUtil.getClientIP(rq));
			hq.setBrowser(rq.getHeader("User-Agent"));
			hq.setMethod(rq.getMethod());
			hq.setMessage(rq.getRequestURL() + " : " + rq.getMethod() + "Call");
			
			historyRequestRepository.save(hq);
			chain.doFilter(rq, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
