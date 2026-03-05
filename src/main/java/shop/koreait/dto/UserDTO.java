package shop.koreait.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends SNSUserDTO implements UserDetails {
    private String id; // varchar(15) pk
    private String password; // char 60 not null
    private String nickname; // varchar(10) null
    private String phone; // 010-1111-1111 char(13) not null
    private String email; // varchar(100) not null
    private LocalDate registerDate; // date

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getUsername() {
        return this.id;
    }
}
