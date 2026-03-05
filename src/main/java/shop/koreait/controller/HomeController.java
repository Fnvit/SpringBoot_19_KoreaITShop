package shop.koreait.controller;

import com.example.security.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.Map;

@Slf4j
@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home";
    }
    // 서버 사이드 일 때만 제대로 동작합니다!!
    @GetMapping("/callback")
    public void callback(@RequestParam String code, Model model){
        String url = "https://nid.naver.com/oauth2.0/token";
        String clientId = "f";
        String clientSecret = "f";
        String grantType = "authorization_code";

        URI uri = UriComponentsBuilder.fromUriString(url)
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("code", code)
                .queryParam("grant_type", grantType)
                .build().toUri();

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> responseBody = restTemplate.getForObject(uri, Map.class);
        String accessToken = responseBody.get("access_token");
        model.addAttribute("accessToken", accessToken);
    }

    @GetMapping("/client_side")
    public String client_side(){
        return "oauth2/client-side";
    }

    @GetMapping("/server_side")
    public String server_side(){
        return "oauth2/server-side";
    }

    @GetMapping("/user")
    public String user(
            Authentication authentication,
            Principal p,
            @AuthenticationPrincipal UserDTO user
            ){
        var name = authentication.getName();
        var principal = authentication.getPrincipal();
        var isAuth = authentication.isAuthenticated();
        var credentials = authentication.getCredentials();

        System.out.println("name: " + name);
        System.out.println("principal: " + principal);
        System.out.println("isAuth: " + isAuth);
        System.out.println("credentials: " + credentials);
        System.out.println("p: " + p);


        return "user";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
