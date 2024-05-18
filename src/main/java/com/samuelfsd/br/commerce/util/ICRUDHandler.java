package com.samuelfsd.br.commerce.util;

import java.util.List;

public interface ICRUDHandler<REQ, RES> {
    List<RES> getAll();

    RES getById(Long id);

    RES create(REQ dto);

    RES update(Long id, REQ dto);

    void delete(Long id);
}
