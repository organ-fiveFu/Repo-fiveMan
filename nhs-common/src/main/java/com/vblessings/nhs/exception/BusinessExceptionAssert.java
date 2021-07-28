package com.vblessings.nhs.exception;


import cn.hutool.core.util.StrUtil;

/**
 * <p>业务异常断言</p>
 *
 */
public interface BusinessExceptionAssert extends IResponseEnum, BaseAssert {

    @Override
    default BaseException newException() {
        return new BusinessException(this);
    }

    @Override
    default BaseException newException(Object... args) {
        String msg = StrUtil.format(this.getMessage(), args);
        return new BusinessException(this, args, msg);
    }

    @Override
    default BaseException newException(String message) {
        return new BusinessException(this, null,message);
    }

    @Override
    default BaseException newExceptionFormat(String args){
        String msg = StrUtil.format(this.getMessage(), args);
        return new BusinessException(this, null,msg);
    }

    @Override
    default BaseException newException(String message, Object... args) {
        String msg = StrUtil.format(message, args);
        return new BusinessException(this, args, msg);
    }

    @Override
    default BaseException newException(String message, String... args) {
        String msg = StrUtil.format(message, args);
        return new BusinessException(this, null, msg);
    }


    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = StrUtil.format(this.getMessage(), args);
        return new BusinessException(this, args, msg, t);
    }

}
