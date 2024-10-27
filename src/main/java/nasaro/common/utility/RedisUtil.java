package nasaro.common.utility;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import nasaro.signUp.model.dto.Sms;

@RequiredArgsConstructor
@Component
public class RedisUtil {
	private final RedisTemplate<String, Object> redisTemplate;
	private final StringRedisTemplate stringRedisTemplate;

	public String getData(String memberTel) {
	    ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
	    return valueOperations.get(memberTel);
	  }

	  public void setData(String key, String value) {
	    ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
	    valueOperations.set(key, value);
	  }

	  public void setDataExpire(String key, String value, long duration) {
	    ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
	    System.out.println("setDataExpire");
	    System.out.println(value);
	    System.out.println(key);
	    Duration expireDuration = Duration.ofSeconds(duration);
	    valueOperations.set(key, value, expireDuration);
	    valueOperations.set("01012312312", value, expireDuration);
	  }
    
    
    
    
    public void deleteData(String key) {
    // 데이터 삭제
        redisTemplate.delete(key);
    }
}

