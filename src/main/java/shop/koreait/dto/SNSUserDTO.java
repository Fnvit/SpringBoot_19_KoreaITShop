package shop.koreait.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SNSUserDTO implements OAuth2User {
    private String snsId; // 각 SNS 사이트에서 고유하게 부여해주는 id값
    private String userId; // 실제 UserDTO의 id
    private String token; // SNS에서 받아온 Access Token
    private String clientName; // 로그인된 SNS의 이름 (NAVER, KAKAO, GOOGLE)
    private LocalDateTime linkedDate; // 연동날짜
    /// SNS 로그인 관련 정보
    private Map<String, Object> attributes; // SNS 에서 가져온 데이터들 (id, email, name, ..)

    @Override
    public String getName() {
        return clientName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("SNS_USER"));
    }
}
