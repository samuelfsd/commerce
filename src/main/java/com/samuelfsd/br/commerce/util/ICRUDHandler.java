package com.samuelfsd.br.commerce.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ICRUDHandler<REQ, RES> {
    Page<RES> getAll(Pageable pageable);

    RES getById(Long id);

    RES create(REQ dto);

    RES update(Long id, REQ dto);

    void delete(Long id);
}
