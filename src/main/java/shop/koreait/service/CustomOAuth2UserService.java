package shop.koreait.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import shop.koreait.dto.SNSUserDTO;
import shop.koreait.dto.UserDTO;
import shop.koreait.mapper.UserMapper;

import java.util.Map;

// OAuth2 로그인을 시도하면 loadUser 메서드가 자동으로 실행됩니다!!
// OAuth2User 의 형태가 spring security oauth2가 사용하는 User의 형태입니다.
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired UserMapper userMapper;
    
    // userRequest 에는 application.properties 에 등록해놓은 내용들이 들어있음
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String clientName = userRequest.getClientRegistration().getClientName().toUpperCase(); // 로그인 한 SNS 의 종류 (NAVER, KAKAO, GOOGLE)
        System.out.println("로그인한 SNS: " +clientName);
        OAuth2User oAuth2User = super.loadUser(userRequest); // application.properties에 등록해놓은 내용을 통해 OAuth2 로그인을 실제로 진행함
        System.out.println("OAuth2User: " + oAuth2User);
        Map<String, Object> attributes = oAuth2User.getAttributes(); // 로그인 완료 후 동의항목들을 가져옴
        SNSUserDTO snsUser = get_sns_user(clientName, attributes);
        // SecurityContextHolder 는 Spring Security 관련 Bean들을 관리하는 객체
        // 기본적으로, 인증된 객체 하나를 Authentication 객체로 가지고 있는다. (인증된 객체가 없으면 null)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 1. 로그인된 유저가 존재하지 않는다면 (로그인창에서 SNS 로그인 버튼  클릭)
        if(authentication == null){
            // Database에서 현재 SNS로 연동이 되어있는지 확인한다.
            String snsId = snsUser.getSnsId();
            UserDTO user = userMapper.selectUserBySNSId(snsId); // 현재 SNS와 연동되어있는 UserDTO를 가져온다
            // 1-1. 로그인 창 혹은 회원가입 창으로 보내야 하는 경우 (마이페이지에서 연동을 안한 경우) - 로그인 실패
            if(user == null){
                throw new OAuth2AuthenticationException(OAuth2ErrorCodes.ACCESS_DENIED);
            }
            // 1-2. 유저를 로그인시켜야 하는 경우 (이미 마이페이지에서 연동을 시켰던 경우) - 로그인 성공
            return user; // UserDTO를 반환해서 어플리케이션의 인증 객체로 등록함
        }
        // 2. 이미 로그인된 유저가 존재한다면 (마이페이지에서 SNS 연동 버튼 클릭)
        UserDTO user = (UserDTO) authentication.getPrincipal(); // 현재 로그인된 유저를 가져온다
        // 2-1. 현재 유저가 연동한 SNS 목록을 받아옴 (검증을 위해)
        Map<String, SNSUserDTO> snsUsers = userMapper.selectSNSUsersByUserId(user.getId());
        // 현재 로그인하려고 하는 SNSUserDTO는 현재 로그인된 UserDTO가 누군지 모르기때문에 넣어줘야 INSERT 할때 userID를 넣어줄 수 있다.
        snsUser.setUserId(user.getId());
        // 연동된 SNS 목록이 없거나, 현재 SNS로는 연동한 적이 없다면
        if(snsUsers.isEmpty() || snsUsers.get(clientName) == null){
            // 현재 연동하려고 하는 SNS 정보로 DB에 INSERT 시켜서 유저와 연결함
            userMapper.insertSNSUser(snsUser); // 현재 SNS 로그인 정보를 담고있는 정보로 INSERT
        }
        // 이후, 성공 프로세스에서 마이페이지에서 왔다는 것을 알려주기위해 현재 UserDTO에 연동 성공한 SNS 정보를 넣어주자.
        BeanUtils.copyProperties(snsUser, user); // snsUser에 있는 변수값을 user변수값으로 복사해줌 (변수이름이 같은것만)
        return user; // UserDTO를 반환해서 어플리케이션의 인증 객체로 등록함
    }


    SNSUserDTO get_sns_user(String clientName, Map<String, Object> attributes) throws OAuth2AuthenticationException{
        // 로그인하는 SNS 종류에 따라 attribute를 받아와서 SNSUserDTO를 생성함
        switch (clientName){
            case "NAVER":
                Map<String, Object> response = (Map<String, Object>) attributes.get("response");
                String snsId = response.get("id").toString();
                return SNSUserDTO.builder().snsId(snsId).clientName(clientName).attributes(response).build();
            case "GOOGLE":
                return SNSUserDTO.builder().snsId(null).clientName(clientName).attributes(null).build();
            default:
                throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT);  // throw시, failed Handler 실행
        }
    }
}










