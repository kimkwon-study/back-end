package com.mangoplate.mangoplate.exception;

import com.mangoplate.mangoplate.domain.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApplicationException extends RuntimeException{

    private final ErrorCode errorCode;

}
