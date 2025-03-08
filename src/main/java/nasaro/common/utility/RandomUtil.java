package nasaro.common.utility;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomUtil {
    public String randomCode() {
        // 랜덤 문자열을 생성할 수 있는 문자열 풀
//        String characterPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String characterPool = "0123456789";

        // 랜덤 문자열의 길이
        int length = 8;

        // 랜덤 문자열 생성기 생성
        Random random = new Random();

        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            // 문자열 풀에서 랜덤으로 문자 선택하여 StringBuilder에 추가
            int randomIndex = random.nextInt(characterPool.length());
            char randomChar = characterPool.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        // 생성된 랜덤 문자열 출력
        String randomString = stringBuilder.toString();
        return randomString;
    }
}