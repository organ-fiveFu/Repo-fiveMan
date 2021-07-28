package com.vblessings.nhs.exception;


import cn.hutool.core.map.MapUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * <p>枚举类异常断言，提供简便的方式判断条件，并在条件满足时抛出异常</p>
 * <p>错误码和错误信息定义在枚举类中，在本断言方法中，传递错误信息需要的参数</p>
 *
 */
public interface BaseAssert {

    /**
     * 创建异常
     *
     * @return
     */
    BaseException newException();

    /**
     * 创建异常
     * @param message
     * @return
     */
    BaseException newException(String message);

    /**
     * 创建异常
     * @param args
     * @return
     */
    BaseException newException(Object... args);

    /**
     * 创建异常
     * @param message
     * @param args
     * @return
     */
    BaseException newException(String message, Object... args);

    BaseException newException(String message, String... args);

    /**
     * 创建异常
     * @param t
     * @param args
     * @return
     */
    BaseException newException(Throwable t, Object... args);

    /**
     *  创建异常
     * @author zhangguoqing
     * @date 2021/3/1 14:01
     * @param message:
     * @return com.ichoice.hsp.common.exception.BaseException
     */
    BaseException newExceptionFormat(String message);

    /**
     * <p>断言对象。
     * 如果对象obj为空，则抛出异常
     *
     * @param obj 待判断对象
     */
    default void assertNotNull(Object obj) {
        if (Objects.isNull(obj)) {
            throw newException();
        }
    }

    /**
     * <p>断言对象。
     * 如果对象obj为空，则抛出异常
     * <p>异常信息message支持传递参数方式，避免在判断之前进行字符串拼接操作
     *
     * @param obj 待判断对象
     * @param args message占位符对应的参数列表
     */
    default void assertNotNull(Object obj, Object... args) {
        if (Objects.isNull(obj)) {
            throw newException(args);
        }
    }

    /**
     * <p>断言字符串。
     * 如果字符串str为空串，则抛出异常
     *
     * @param str 待判断字符串
     */
    default void assertNotEmpty(String str) {
        if (StringUtils.isBlank(str)) {
            throw newException();
        }
    }

    /**
     * <p>断言字符串。
     * 如果字符串str为空串，则抛出异常
     *
     * @param str 待判断字符串
     * @param args message占位符对应的参数列表
     */
    default void assertNotEmpty(String str, Object... args) {
        if (StringUtils.isBlank(str)) {
            throw newException(args);
        }
    }

    /**
     * <p>断言数组
     * 如果数组arrays为空，则抛出异常
     *
     * @param arrays 待判断数组
     */
    default void assertNotEmpty(Object[] arrays) {
        if (arrays == null || arrays.length == 0) {
            throw newException();
        }
    }

    /**
     * <p>断言数组
     * 如果数组arrays为空，则抛出异常
     * <p>异常信息message支持传递参数方式，避免在判断之前进行字符串拼接操作
     *
     * @param arrays 待判断数组
     * @param args message占位符对应的参数列表
     */
    default void assertNotEmpty(Object[] arrays, Object... args) {
        if (arrays == null || arrays.length == 0) {
            throw newException(args);
        }
    }

    /**
     * <p>断言集合。
     * 如果集合c为空，则抛出异常
     *
     * @param c 待判断数组
     */
    default void assertNotEmpty(Collection<?> c) {
        if (CollectionUtils.isEmpty(c)) {
            throw newException();
        }
    }

    /**
     * <p>断言集合。
     * 如果集合c为空，则抛出异常
     *
     * @param c 待判断数组
     * @param args message占位符对应的参数列表
     */
    default void assertNotEmpty(Collection<?> c, Object... args) {
        if (CollectionUtils.isEmpty(c)) {
            throw newException(args);
        }
    }

    /**
     * <p>断言Map不为空。
     * 如果Map为空，则抛出异常
     *
     * @param map 待判断Map
     */
    default void assertNotEmpty(Map<?, ?> map) {
        if (MapUtil.isEmpty(map)) {
            throw newException();
        }
    }

    /**
     * <p>断言Map不为空。
     * 如果Map为空，则抛出异常
     *
     * @param map 待判断Map
     * @param args message占位符对应的参数列表
     */
    default void assertNotEmpty(Map<?, ?> map, Object... args) {
        if (MapUtil.isEmpty(map)) {
            throw newException(args);
        }
    }

    /**
     * <p>断言布尔值。
     * 如果布尔值expression为true，则抛出异常
     *
     * @param expression 待判断布尔变量
     */
    default void assertIsFalse(boolean expression) {
        if (expression) {
            throw newException();
        }
    }

    /**
     * <p>断言布尔值。
     * 如果布尔值expression为true，则抛出异常
     *
     * @param expression 待判断布尔变量
     * @param args message占位符对应的参数列表
     */
    default void assertIsFalse(boolean expression, Object... args) {
        if (expression) {
            throw newException(args);
        }
    }

    /**
     * <p>断言布尔值。
     * 如果布尔值expression为false，则抛出异常
     *
     * @param expression 待判断布尔变量
     */
    default void assertIsTrue(boolean expression) {
        if (!expression) {
            throw newException();
        }
    }

    /**
     * <p>断言布尔值。
     * 如果布尔值expression为false，则抛出异常
     *
     * @param expression 待判断布尔变量
     * @param args message占位符对应的参数列表
     */
    default void assertIsTrue(boolean expression, Object... args) {
        if (!expression) {
            throw newException(args);
        }
    }

    /**
     * <p>断言对象
     * 如果对象obj不为null，则抛出异常
     *
     * @param obj 待判断对象
     */
    default void assertIsNull(Object obj) {
        if (obj != null) {
            throw newException();
        }
    }

    /**
     * <p>断言对象
     * 如果对象obj不为null，则抛出异常
     *
     * @param obj 待判断布尔变量
     * @param args message占位符对应的参数列表
     */
    default void assertIsNull(Object obj, Object... args) {
        if (obj != null) {
            throw newException(args);
        }
    }

    /**
     * <p>直接抛出异常
     *
     */
    default void assertFail() {
        throw newException();
    }

    /**
     * <p>直接抛出异常
     *
     * @param args message占位符对应的参数列表
     */
    default void assertFail(Object... args) {
        throw newException(args);
    }

    /**
     * <p>直接抛出异常，并包含原异常信息
     * <p>当捕获非运行时异常（非继承{@link RuntimeException}）时，并该异常进行业务描述时，
     * 必须传递原始异常，作为新异常的cause
     *
     * @param t 原始异常
     */
    default void assertFail(Throwable t) {
        throw newException(t);
    }

    /**
     * <p>直接抛出异常，并包含原异常信息
     * <p>当捕获非运行时异常（非继承{@link RuntimeException}）时，并该异常进行业务描述时，
     * 必须传递原始异常，作为新异常的cause
     *
     * @param t 原始异常
     * @param args message占位符对应的参数列表
     */
    default void assertFail(Throwable t, Object... args) {
        throw newException(t, args);
    }

    /**
     * <p>断言对象相等，
     * 此处的相等指（o1.equals(o2)为true）。
     * 如果两对象不相等，则抛出异常
     *
     * @param o1 待判断对象，若<code>o1</code>为null，也当作不相等处理
     * @param o2  待判断对象
     */
    default void assertEquals(Object o1, Object o2) {
        if (o1 == o2) {
            return;
        }
        if (o1 == null) {
            throw newException();
        }
        if (!o1.equals(o2)) {
            throw newException();
        }
    }

    /**
     * <p>断言对象相等，
     * 此处的相等指（o1.equals(o2)为true）。
     * 如果两对象不相等，则抛出异常
     *
     * @param o1 待判断对象，若<code>o1</code>为null，也当作不相等处理
     * @param o2  待判断对象
     * @param args message占位符对应的参数列表
     */
    default void assertEquals(Object o1, Object o2, Object... args) {
        if (o1 == o2) {
            return;
        }
        if (o1 == null) {
            throw newException(args);
        }
        if (!o1.equals(o2)) {
            throw newException(args);
        }
    }
}
