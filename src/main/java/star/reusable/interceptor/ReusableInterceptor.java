package star.reusable.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Luke Xie
 */
@Slf4j
@Component
public class ReusableInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 记录请求开始的时间
        request.setAttribute("startTime", System.currentTimeMillis());

        log.info(" --------Request Start --------");
        log.info(" --------Request URL: [{}]", request.getRequestURI());
        log.info(" --------Request Method: [{}]", request.getMethod());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) {
        Long startTime = (Long) request.getAttribute("startTime");
        if (startTime != null) {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            log.info(" --------Request Time: [{} ms]", duration);
        }
    }
}
