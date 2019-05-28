package enn.testone.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

//springsecurity5--写一个工具类实现PasswordEncoder这个类,配置security认证规则的passwordEncoder即可
public class PassEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
