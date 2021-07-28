package com.vblessings.nhs.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.vblessings.nhs.Interceptor.TokenInterceptor;
import com.vblessings.nhs.exception.GlobalExceptionHandler;
import com.vblessings.nhs.holder.CurrentUserMethodArgumentResolver;
import com.vblessings.nhs.holder.SpringContextHolder;
import com.vblessings.nhs.request.filter.RequestDetailFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@Import({SpringContextHolder.class, GlobalExceptionHandler.class, RequestDetailFilter.class})
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;

    @Resource
    TokenInterceptor tokenInterceptor;


    /**
     * 配置自己的拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/doc.html/**")
                .excludePathPatterns("/swagger-ui.html/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        //Long类型转String类型
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        // ToStringSerializer 是这个包 com.alibaba.fastjson.serializer.ToStringSerializer
        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        serializeConfig.put(Long.class, ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
        serializeConfig.put(BigDecimal.class, ToStringSerializer.instance);
        serializeConfig.put(Time.class, ToStringSerializer.instance);
        config.setSerializeConfig(serializeConfig);
        //如果date类型值为null，则返回空串
        ValueFilter valueFilter = (Object var1, String var2, Object var3) -> {
            try {
                if (var3 == null && var1 != null && Date.class.isAssignableFrom(var1.getClass().getDeclaredField(var2).getType())){
                    return "";
                }
                if (var3 == null && var1 != null && BigDecimal.class.isAssignableFrom(var1.getClass().getDeclaredField(var2).getType())){
                    return "";
                }
                if (var3 == null && var1 != null && Time.class.isAssignableFrom(var1.getClass().getDeclaredField(var2).getType())){
                    return "";
                }
            } catch (Exception e) {}
            return var3;
        };
        config.setSerializeFilters(valueFilter);
        config.setSerializerFeatures(
                // 保留map空的字段
                SerializerFeature.WriteMapNullValue,
                // 将String类型的null转成""
                SerializerFeature.WriteNullStringAsEmpty,
                // 将List类型的null转成[]
                SerializerFeature.WriteNullListAsEmpty,
                //日期格式转换
                SerializerFeature.WriteDateUseDateFormat,
                // 避免循环引用
                SerializerFeature.DisableCircularReferenceDetect
        );
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        // 解决中文乱码问题，相当于在Controller上的@RequestMapping中加了个属性produces = "application/json"
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(mediaTypeList);
        converters.add(0, converter);
    }

}
