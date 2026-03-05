package shop.koreait.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

public class CustomOAuth2FailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("등록이 안된 사용자가 OAuth2 로그인을 시도함");
        // 로그인창으로 보내면서 안내 문구 함께 전달
        String oauth2ErrorMessage = "연동된 사용자 정보가 없습니다. 회원가입 후 회원 마이페이지에서 SNS 연동을 진행해주세요.";
        request.getSession().setAttribute("oauth2ErrorMessage", oauth2ErrorMessage);
        response.sendRedirect("/user/login");
    }
}











