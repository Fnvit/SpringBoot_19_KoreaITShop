package shop.koreait.service;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class PortoneService {
    private final String IMP_KEY = "3787546272824711";
    private final String IMP_SECRET = "ㅅ";
    private final String ACCESS_TOKEN_URL = "https://api.iamport.kr/users/getToken";
    private final String CERTIFICATIONS_URL = "https://api.iamport.kr/certifications/{impUID}";
    private RestTemplate restTemplate = new RestTemplate();

    // Access Token을 발급받는 기능
    private String get_access_token(){
        System.out.println("ACCESS TOKEN 발급 중...");
        Map<String, String> body = Map.of(
            "imp_key", IMP_KEY,
            "imp_secret", IMP_SECRET
        );
        try{
            Map<String, Object> responseBody = restTemplate.postForObject(ACCESS_TOKEN_URL, body, Map.class);
            System.out.println("responseBody: " + responseBody);
            Map<String, Object> response = (Map)responseBody.get("response");
            String accessToken = response.get("access_token").toString();
            System.out.println("accessToken: " + accessToken);
            return accessToken;
        }catch (Exception e){
            System.out.println("에러가 발생했습니다: " + e);
        }
        return null;
    }

    // 휴대폰 본인인증 결과를 받아오는 기능
    private boolean is_certificated(String accessToken, String impUID, String phoneNumber){
        System.out.println("휴대폰 인증 검사 중...");
        RequestEntity<Void> requestEntity = RequestEntity.get(CERTIFICATIONS_URL, impUID)
                .header("Authorization", accessToken)
                .build();
        try{
            ResponseEntity<Map> responseEntity = restTemplate.exchange(requestEntity, Map.class);
            Map<String, Object> responseBody = responseEntity.getBody();
            Map<String, Object> response = (Map)responseBody.get("response");
            Boolean result = (Boolean)response.get("certified");
            System.out.println("result: " + result);
            String phone = response.get("phone").toString();
            System.out.println("phone: " + phone);
            if(!result){
                System.out.println("인증에 실패한 요청입니다");
                return false;
            }
            if(!phoneNumber.equals(phone)){
                System.out.println("인증한 휴대폰과 실제 입력된 휴대폰 번호가 다릅니다");
                return false;
            }
            return true;
        }
        catch (Exception e){
            System.out.println("에러가 발생했습니다: " + e);
        }
        return false;
    }

    // 휴대폰 본인인증 메서드
    public boolean phone_certificate(String impUID, String phoneNumber){
        String accessToken = get_access_token();
        if(accessToken == null){
            return false;
        }
        phoneNumber = phoneNumber.replaceAll("-", "");
        return is_certificated(accessToken, impUID, phoneNumber);
    }



}
