import com.alibaba.fastjson.JSON;
import gp.security.oauth2.AuthApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 码农界的小学生
 * @description:
 * @title: TestRedis
 * @projectName xc-edu
 * @description: TODO
 * @date 2020/3/8 17:47
 */
@SpringBootTest(classes = AuthApplication.class)
@RunWith(SpringRunner.class)
public class TestJwt {
    //生成一个jwt令牌
    @Test
    public void testCreateJwt() {
        //证书文件
        String key_location = "gp.keystore";
        //密钥库密码
        String keystore_password = "handsomelykeystore";
        //访问证书路径
        ClassPathResource resource = new ClassPathResource(key_location);
        //密钥工厂
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(resource, keystore_password.toCharArray());
        //密钥的密码，此密码和别名要匹配
        String keypassword = "handsomely";
        //密钥别名
        String alias = "gpkey";
        //密钥对（密钥和公钥）
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias,keypassword.toCharArray());
        //私钥
        RSAPrivateKey aPrivate = (RSAPrivateKey) keyPair.getPrivate();
        //定义payload信息
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("id", "123");
        tokenMap.put("name", "mrt");
        tokenMap.put("roles", "r01,r02");
        tokenMap.put("ext", "1");
        //生成jwt令牌
        Jwt jwt = JwtHelper.encode(JSON.toJSONString(tokenMap), new RsaSigner(aPrivate));
        //取出jwt令牌
        String token = jwt.getEncoded();
        System.out.println("token="+token);
    }
    //资源服务使用公钥验证jwt的合法性，并对jwt解码
    @Test public void testVerify(){
        //jwt令牌
        String token ="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlX25hbWUiOiLnrqHnkIblkZgiLCJ1c2VycGljIjoiL3Vzci9sb2NhbC9waWMvO2xhamRmIiwidXNlcl9uYW1lIjoiMTIzNDU2Iiwic2NvcGUiOlsiYXBwIl0sImlkIjoiNDAyOGI4ODE3MGU2ZWI4NjAxNzBlNmVmNDk2MDAwMDAiLCJleHAiOjE1ODQ0ODczODMsImF1dGhvcml0aWVzIjpbImdwX2VxdWlwbWVudF9hZGQiLCJncF9lcXVpcG1lbnRfdXBkYXRlIl0sImp0aSI6IjhkM2VkZjVhLWUwMjctNGEyNC04NmFlLTFhYWJhYzNjZmNmZiIsImNsaWVudF9pZCI6IlhjV2ViQXBwIiwidXNlcm5hbWUiOiIxMjM0NTYifQ.uFYZgaUIIXfFTIgq6q_cPw3f2GbgJIHzm0mnYX9qs67dY93Dp52fuBs9JR9wSRBmeqW9_wHFce1uZjNlVChP2HZMirmcz9oGa-AOWTRl5Rl6-AyQh5X5Hjota3w_R5sPGITzd-ywmAbzDXl_BJrZlaW33BxmCfgQVOZEdOovy5EnNdLeBc9_PdBHSjivWDBSCJympxMo6dyLGPCtt0bAZMRJJIxp80oUYiQbkA_K0a6CYWPDnf6oSkeGHS3RwF1kj5P_JfAFUPTJu0uw-O5y8Xgk1hSu2RRjrvw27zx06CUOy06YonxDhmI-4aYrwiGDuAlufjLAaQddsXVwmpejTQ";
        //公钥
        String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAy8E2vY/P5DvLFEr+KN9vidaAufZZquG9ik7rHIcPuZb/arb91oxNfc3lFBPuiW2sX9i6GKCzK2DP/1yz4yl90VlYx7x2yHXUNQRqjAApc0II820Mt5c/u01eh+NGsNOuplE5cAxNaGpAGwJk17qlfpT8e2P0neds4t91TE+y/u9UyK8vnXtYNsjqAfVWANJ8jdrG9vErITGtS9kpgdFMe+KuJrpaW42woVq8FczHRy28hHPgSeRKyekRYBfSSh0SI2Wgk6TvJtxSw36YnPAUp+HtsI39/TIQrCBGv9Cb7SEAOz9EUcWm60KfpObsi316QOvH7PbTj52gwIEJ8NRuLQIDAQAB-----END PUBLIC KEY-----";
        //校验jwt
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));
        //获取jwt原始内容
        String claims = jwt.getClaims();
        //jwt令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }
}
