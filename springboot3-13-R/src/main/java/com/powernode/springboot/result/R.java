package com.powernode.springboot.result;

import com.powernode.springboot.enums.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T>{


    private int code;
    private String msg;
    private T data;

    public static <T> R<T> OK(){
        return R.<T>builder().code(CodeEnum.OK.getCode()).msg(CodeEnum.OK.getMsg()).build();
    }

    public static <T> R<T> OK(T data){
        return R.<T>builder().code(200).msg("OK").data(data).build();
    }

    public static <T> R<T> FAIL(){
        return R.<T>builder().code(400).msg("FAIL").build();
    }
    public static <T> R<T> FAIL(int code, String msg){
        return R.<T>builder().code(code).msg(msg).build();
    }


}
