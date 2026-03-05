package shop.koreait.mapper;

import com.example.security.dto.SNSUserDTO;
import com.example.security.dto.UserDTO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {
    UserDTO selectUserById(String id);
    void insertUser(UserDTO userDTO); // 유저 회원가입
    /************ SNS USER **********************/
    UserDTO selectUserBySNSId(String id);
    @MapKey("clientName")
    Map<String, SNSUserDTO> selectSNSUsersByUserId(String id); // 특정 유저가 연동한 SNS 목록 받아오기
    void insertSNSUser(SNSUserDTO snsUser); // SNS 연동 정보를 추가하기

}
