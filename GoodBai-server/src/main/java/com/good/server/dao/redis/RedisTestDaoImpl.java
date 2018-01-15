package com.good.server.dao.redis;

import com.good.server.base.DaoHelper;
import com.good.server.base.Namespace;
import com.good.server.entity.Student;
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
