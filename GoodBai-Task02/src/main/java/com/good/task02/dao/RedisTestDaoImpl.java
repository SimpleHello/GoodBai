package com.good.task02.dao;


import com.good.task02.base.DaoHelper;
import com.good.task02.base.Namespace;
import com.good.task02.entity.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Mg on 2018/1/12.
 */
@Repository
public class RedisTestDaoImpl {

    @Resource(name = "readDaoHelper")
    private DaoHelper readDao;

    public void saveRedisTest(Student student) throws Exception {
        readDao.insert(Namespace.REDIS_TEST,"saveRedisTest",student);
    }

}
