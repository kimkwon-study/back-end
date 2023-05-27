package com.mangoplate.mangoplate.domain.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {

    private String result_code;
    private T result;

    public static <T> Response<T> error(String errorcode, T errorCode){
        return new Response<>(errorcode,errorCode);
    }

    public static <T> Response<T> success(T result){
        return new Response<>("Success",result);
    }

}
