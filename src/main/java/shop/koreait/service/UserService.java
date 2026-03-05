package shop.koreait.service;

import com.example.security.dto.UserDTO;
import com.example.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired UserMapper userMapper;

    /**
     * DB에 id와 pw를 전달해서 일치하는 유저가 존재하는지 검사하는 메서드
     * @param id 유저의 id
     * @return 유저가 존재하면 true / 존재하지 않으면 false
     */
    public boolean user_is_exists(String id){
        UserDTO user = userMapper.selectUserById(id);
        return user != null;
    }

    /**
     * 회원가입 가능한 유저인지 판단 후, DB에 저장하는 메서드
     * @param user 회원가입 대상 유저
     * @return 회원가입 성공시: true / 실패시: false
     */
    public boolean signup_user(UserDTO user){
        // 1. 해당 유저가 이미 존재하는가?
        boolean isExist = user_is_exists(user.getId());
        if(isExist){
            System.out.println("[" + user.getId() + "] 유저가 이미 존재합니다!");
            return false;
        }
        // 2. 정보가 확실한가? (각 요소가 존재하는가?) ....
        if(user.getId() == null || user.getPassword() == null){
            System.out.println("유저 정보가 부족합니다!");
            return false;
        }
        // 3. DB에 INSERT
        /// DB에 INSERT 하기 전에, 패스워드를 인코딩해서 설정한다
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insertUser(user);
        return true;
    }

}
