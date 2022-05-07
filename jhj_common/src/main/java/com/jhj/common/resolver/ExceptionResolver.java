//package com.jhj.common.resolver;
//
//import com.alibaba.fastjson.JSONObject;
//import com.jhj.common.model.Result;
//import com.jhj.common.enums.Error;
//import com.jhj.common.exception.GlobalException;
//import feign.RetryableException;
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.ConstraintViolationException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.FieldError;
//import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
//import org.springframework.web.HttpMediaTypeNotSupportedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingPathVariableException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//
//@ControllerAdvice
//@ResponseBody
//@Slf4j
//@Component
//public class ExceptionResolver {
//
//    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor() {
//        return new MethodValidationPostProcessor();
//    }
//
//    @ExceptionHandler(Exception.class)
//    public Result handle(Exception ex) {
//        if (log.isInfoEnabled()) {
//            ex.printStackTrace();
//        }
//        return Result.getInstance().fail(log.isInfoEnabled() ? "当前节点发高烧了~" : ex.getMessage());
//    }
//
//    @ExceptionHandler(RetryableException.class)
//    public JSONObject handle(RetryableException ex) {
//        log.error("exception:{}", ex.getMessage());
//        StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw);
//        ex.printStackTrace(pw);
//        return Result.getInstance().fail("远程服务调用异常");
//    }
//
//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public JSONObject handle(HttpMediaTypeNotSupportedException ex){
//        log.error("exception:{}", ex.getMessage());
//        StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw);
//        ex.printStackTrace(pw);
//        return Result.getInstance().fail("非法请求格式");
//    }
//
//    @ExceptionHandler(MissingPathVariableException.class)
//    public Result handle(MissingPathVariableException e) {
//        if (log.isInfoEnabled()) {
//            e.printStackTrace();
//        }
//        return Result.getInstance().fail(log.isInfoEnabled() ? "请确认请求路径是否正确" : e.getMessage());
//    }
//
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    public Result handle(MissingServletRequestParameterException e) {
////        String parameterName = e.getParameterName();
//        return Result.getInstance().fail("检查请求参数是否完整");
//    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public Result handle(ConstraintViolationException e) {
//        Set <ConstraintViolation <?>> constraintViolations = e.getConstraintViolations();
//        if (constraintViolations != null && !constraintViolations.isEmpty()) {
//            Iterator <ConstraintViolation<?>> iterator = constraintViolations.iterator();
//            if (iterator.hasNext()) {
//                String messageTemplate = iterator.next().getMessageTemplate();
//                return Result.getInstance().fail(Error.PARAM_INVALID, messageTemplate);
//            }
//        }
//        return Result.getInstance().fail(Error.PARAM_INVALID);
//    }
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public Result handle(HttpMessageNotReadableException e) {
//        return Result.getInstance().fail(Error.PARAM_IS_BLANK);
//    }
//
//    @ExceptionHandler(GlobalException.class)
//    public Result handle(GlobalException e) {
//        return Result.getInstance().fail(e);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Result handle(MethodArgumentNotValidException e) {
//        //按需重新封装需要返回的错误信息
//        List <String> invalidArguments = new ArrayList <>();
//        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
//        for (FieldError error : e.getBindingResult().getFieldErrors()) {
//            invalidArguments.add(error.getDefaultMessage());
//        }
//        return Result.getInstance().fail(Error.PARAM_INVALID, invalidArguments.get(0));
//    }
//
//}
