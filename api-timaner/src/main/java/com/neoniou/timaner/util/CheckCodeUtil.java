package com.neoniou.timaner.util;

import java.util.Random;

/**
 * @author neo.zzj
 * @date 2019-11-24
 */
public class CheckCodeUtil {

    private static final int CHECK_CODE_LENGTH = 6;

    private static final String CODE_STR = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890";

    /**
     * 生成验证码
     * @return 验证码
     */
    public static String createCheckCode() {
        //生成随机角标
        Random random = new Random();
        //传递生成的验证码
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= CHECK_CODE_LENGTH; i++) {
            int index = random.nextInt(CODE_STR.length());
            char cr = CODE_STR.charAt(index);
            //放入字符
            stringBuilder.append(cr);
        }

        return stringBuilder.toString();
    }

}
