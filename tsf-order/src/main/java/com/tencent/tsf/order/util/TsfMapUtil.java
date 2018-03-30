package com.tencent.tsf.order.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * TSF Map工具类
 *
 * @author hongweizhu
 */
public class TsfMapUtil {

    /**
     * 将N个对象转换为一个Map<br>
     * <ul>
     * <li>标注了@JsonIgnore的get方法会被忽略</li>
     * <li>总是使用get方法取值，没有get方法则忽略该值</li>
     * <li>多个obj合并时，同名变量会被覆盖（后传入的覆盖先传入的）</li>
     * <li><b>注：这是一个非深度的方法</b></li>
     * </ul>
     * 
     * @param ignoreNullValue 是否忽略null值<br>
     *            true：值为null的字段不输出<br>
     *            false：值为null的字段输出
     * @param objs 需要转换的对象实例数组
     * @return 转换后的Map实例；如果objs为null或者长度为0时，返回null
     */
    public static Map<String, Object> getMapValue(Boolean ignoreNullValue, Object... objs) {
        if (null == objs || objs.length == 0) {
            return null;
        } else {
            Map<String, Object> map = new HashMap<>();
            for (Object obj : objs) {
                for (Method m : obj.getClass().getMethods()) {
                    // 屏蔽getClass()方法
                    if ("getClass".equals(m.getName())) {
                        continue;
                    }
                    // 对get方法进行处理，屏蔽掉方法名只有get三个字符的方法
                    if (m.getName().startsWith("get") && m.getName().length() > 3) {
                        try {
                            // 标注了@JsonIgnore的属性会被忽略
                            if (null == m.getAnnotation(JsonIgnore.class)) {
                                Object value = m.invoke(obj);
                                // 如果忽略null值，则跳过值为null的字段
                                if (ignoreNullValue && null == value) {
                                    continue;
                                } else {
                                    map.put(StringUtils.uncapitalize(m.getName().substring(3)), value);
                                }
                            }
                        } catch (Exception e) {
                            // doing nothing
                        }
                    }
                }
            }
            return map;
        }
    }

}
