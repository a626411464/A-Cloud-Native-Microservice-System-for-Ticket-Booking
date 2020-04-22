package com.hao.springcloud.service.impl;
import com.hao.springcloud.dao.StorageDao;
import com.hao.springcloud.domain.Storage;
import com.hao.springcloud.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageDao storageDao;

    /**
     * 扣减库存
     */
    @Override
    public void decrease(Long ticketTypeId, Integer count) {
        LOGGER.info("------->storage-service中扣减库存开始");
        storageDao.decrease(ticketTypeId,count);
        LOGGER.info("------->storage-service中扣减库存结束");
    }

    @Override
    public List<Storage> findAll() {
        return storageDao.findAll();
    }

    @Override
    public void increase(Long ticketTypeId, Integer count) {
        LOGGER.info("------->storage-service中恢复库存开始");
        storageDao.increase(ticketTypeId,count);
        LOGGER.info("------->storage-service中恢复库存结束");
    }
}
