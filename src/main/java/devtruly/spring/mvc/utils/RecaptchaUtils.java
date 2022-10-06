package devtruly.spring.mvc.utils;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("grcp")
public class RecaptchaUtils {

    @Value("${grecaptcha.secretkey}")
    private String secretkey;
    public boolean checkCaptcha(String grcp) throws IOException, ParseException {
        String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
        String params = String.format("?secret=%s&response=%s", secretkey, grcp);
        String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36";

        Logger log = LoggerFactory.getLogger(getClass());

        // httpclient 객체 생성
        CloseableHttpClient req = HttpClients.createDefault();
        // 요청할 URL을 get 메서드로 정의
        HttpGet get = new HttpGet(VERIFY_URL + params);

        // 요청 header 정의
        get.addHeader("User-Agent", USER_AGENT);
        get.addHeader("Content-type", "application/json");


        // 설정 된 정보로 실제 URL 요청
        CloseableHttpResponse res = req.execute(get);
        // log.info(res.getCode());
        // 실행 여부 확인(응답코드 : 200-정상실행, 404, 505-실행실패)
        String result = EntityUtils.toString(res.getEntity());
        // log.info(result);
        // 결과 문자열을 JSON 객체로 반환하고
        // success 키의 값을 받아옴
        JSONObject json = new JSONObject(result);

        return json.getBoolean("success");
    }
}
