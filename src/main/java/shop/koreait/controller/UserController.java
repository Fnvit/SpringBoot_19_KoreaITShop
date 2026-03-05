package shop.koreait.controller;

import com.example.security.dto.UserDTO;
import com.example.security.service.PortoneService;
import com.example.security.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired UserService userService;
    @Autowired PortoneService portoneService;

    @GetMapping("/login")
    public void get_login(HttpSession session, Model model) {
        Object oauth2ErrorMessage = session.getAttribute("oauth2ErrorMessage");
        session.invalidate(); // 브라우저의 세션 정보를 삭제한다. 로그아웃과 같은 개념.
        if(oauth2ErrorMessage != null) {
            model.addAttribute("oauth2ErrorMessage", oauth2ErrorMessage.toString());
        }
    }

    @GetMapping("/signup")
    public void get_signup(){

    }

    /**
     *  유저 회원가입 메서드
     * @param user HTML에서 받아온 정보를 가지는 UserDTO
     */
    @PostMapping("/signup")
    public String post_signup(
            UserDTO user,
            @RequestParam("impUID") String impUID
    ){
        System.out.println("user: " + user);
        boolean isPhoneCert = portoneService.phone_certificate(impUID, user.getPhone());
        // 본인인증 실패
        if(!isPhoneCert){
            return "redirect:/user/signup"; // 다시 회원가입창으로 이동 (GET)
        }
        // 받아온 유저를 회원가입 시키기
        boolean isSigned = userService.signup_user(user);
        // 회원가입 실패
        if(!isSigned){
            return "redirect:/user/signup"; // 다시 회원가입창으로 이동 (GET)
        }
        // 회원가입 성공
        return "redirect:/user/login"; // 로그인 창으로 이동 (GET)
    }


    @GetMapping("/id/{userId}")
    @ResponseBody
    public Boolean get_check_user_exist(@PathVariable String userId){
        return !userService.user_is_exists(userId);
    }

    /*******************************************************/
    @GetMapping("/mypage/info")
    public void get_mypage_info(){}

}
