package shop.koreait.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import shop.koreait.dto.UserDTO;
import shop.koreait.mapper.UserMapper;

import java.io.IOException;

@Component
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 현재 principal이 UserDTO라면, SNS 연동 여부를 확인한 후, SNS 연동을 시킨다.
        UserDTO user = (UserDTO) authentication.getPrincipal();
        // 로그인 창에서 SNS 로그인을 시도했다면 => 홈페이지
        if(user.getSnsId() == null){
            response.sendRedirect("/");
        }
        // 마이페이지 창에서 SNS 로그인을 시도했다면 => 마이페이지로 다시 이동
        else{
            response.sendRedirect("/user/mypage/info");
        }
    }
}











