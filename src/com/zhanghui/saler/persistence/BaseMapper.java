package com.zhanghui.saler.persistence;

import com.zhanghui.saler.common.Paginator;
import com.zhanghui.saler.domain.Id;

import java.util.List;

/**
 * ibatis�������Mapper�����ӿ�
 * @author <a href="pangkunyi@gmail.com">Calvin Pang</a>
 *
 */
public interface BaseMapper<T extends Id> {
	
    int deleteById(Integer id);
    
    int deleteByIds(int ids[]);

    int insert(T channel);

    List<T> selectAll();
    
    List<T> select(Paginator page);

    int count(Paginator page);

    T selectById(Integer id);
    
    int updateByIdSelective(T t);
    
    int updateById(T t);
}