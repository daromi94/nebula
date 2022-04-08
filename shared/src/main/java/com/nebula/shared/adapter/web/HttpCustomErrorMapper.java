package com.nebula.shared.adapter.web;

import com.nebula.shared.domain.CustomError;

public class HttpCustomErrorMapper {

    public HttpCustomError fromDomain(CustomError error) {
        return new HttpCustomError(error.code(), error.message());
    }

}