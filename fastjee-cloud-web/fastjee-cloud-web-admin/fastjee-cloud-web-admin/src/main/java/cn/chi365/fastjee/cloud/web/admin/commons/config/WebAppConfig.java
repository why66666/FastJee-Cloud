package cn.chi365.fastjee.cloud.web.admin.commons.config;

import cn.chi365.fastjee.cloud.web.admin.commons.utils.StringToDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;



/**
 * WebAppConfig TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/29 16:03
 */
@Configuration
public class WebAppConfig {
    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;


    /**
     * 此方法解决前台提交的日期参数绑定不正确问题,将自己实现的StringToDateConverter交给spring
     */
    @PostConstruct
    public void initEditableValidation() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        if (initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
            genericConversionService.addConverter(new StringToDateConverter());
        }
    }
}
