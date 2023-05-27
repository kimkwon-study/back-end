package com.mangoplate.mangoplate.exception;

import com.mangoplate.mangoplate.domain.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicationException extends RuntimeException{

    private final ErrorCode errorCode;


}
