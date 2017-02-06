package com.emmairving.bob.server.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by irving on 17/2/3.
 */
public interface BaseDao<T, PK> {
    void insert(T t);

    void update(T t);

    void deleteById(PK id);

    T getById(PK id);

    List<T> getList(Map<String,Object> paramMap);

    int getCount(Map<String,Object> paramMap);
}
