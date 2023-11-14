package com.company.organization.controller;

import com.company.organization.service.BaseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseController<S extends BaseService> {
    protected final S service;
}
