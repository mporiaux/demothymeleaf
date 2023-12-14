package be.condorcet.demo11.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InterfService<T> {
        public T create(T t) throws Exception;
        public T read(Integer id) throws Exception;
        public T update(T t) throws Exception;
        public T delete(T t) throws Exception;
        public List<T> all() throws Exception;
        public Page<T> allp(Pageable pageable) throws Exception;
    }

