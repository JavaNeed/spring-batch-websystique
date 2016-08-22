package org.krams.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;

/**
 * Extends {@link CustomizableTraceInterceptor} to provide custom logging levels
 */
public class TraceInterceptor extends CustomizableTraceInterceptor {

	private static final long serialVersionUID = 287162721460370957L;
	protected static Logger LOGGER = LoggerFactory.getLogger(TraceInterceptor.class);

	@Override
	protected void writeToLog(Log logger, String message, Throwable ex) {
		if (ex != null) {
			LOGGER.debug(message, ex);
		} else {
			LOGGER.debug(message);
		}
	}

	@Override
	protected boolean isInterceptorEnabled(MethodInvocation invocation, Log logger) {
		return true;
	}
}