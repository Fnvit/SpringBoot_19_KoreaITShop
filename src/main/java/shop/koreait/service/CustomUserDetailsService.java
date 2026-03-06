package shop.koreait.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shop.koreait.dto.UserDTO;
import shop.koreait.mapper.UserMapper;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: " + username);
//        String pw = passwordEncoder.encode("123");
//        System.out.println("인코딩된 패스워드: " + pw);
        // 1. 해당 username을 가진 유저가 있는지 검사한다. (DB에서 해당 유저를 찾아온다)
        UserDTO findUser = userMapper.selectUserById(username);
        // => 유저가 없다면 UsernameNotFoundException을 발생시켜서 /login 창으로 유도한다.
        if(findUser == null){
            System.out.println("DB에 해당 유저가 없습니다!");
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("DB의 User: " + findUser);
        // 2. 유저가 있다면 UserDetails 형태의 유저를 return 한다
        return findUser;
    }
}
