package net.liuchenfei.ssitest.services.impl;

import net.liuchenfei.ssitest.daos.PartitionsMapper;
import net.liuchenfei.ssitest.entitys.Partitions;
import net.liuchenfei.ssitest.entitys.PartitionsExample;
import net.liuchenfei.ssitest.handler.FileHandler;
import net.liuchenfei.ssitest.services.SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * Created by liuchenfei on 2017/11/10.
 */
@Service
public class SaveServiceImpl implements SaveService {
    private String[] address = {"master", "worker1"};
    private Integer blockSize = 1024 * 1024;

    @Autowired
    private PartitionsMapper partitionMapper;

    private FileHandler fileHandler = new FileHandler();

    @Transactional
    @Override
    public void saveFile(File file, String fileId) throws Exception {
        byte[] b = new byte[blockSize];
        int index = 0;
        InputStream is = new FileInputStream(file);
        while (is.available() != 0) {
            int addressIndex = index % address.length;
            is.read(b);
            Partitions p = new Partitions(UUID.randomUUID().toString(), index++, address[addressIndex], fileId);
            fileHandler.saveFile(address[addressIndex], p.getPartitionId(), b);
            partitionMapper.insert(p);
        }
        is.close();
        file.delete();
    }

    @Transactional
    @Override
    public byte[] fetchFile(String fileId) throws Exception {
        byte[] result = new byte[10 * 1024 * 1024];
        int index = 0;
        PartitionsExample example = new PartitionsExample();
        PartitionsExample.Criteria criteria = example.createCriteria();
        criteria.andFileIdEqualTo(fileId);
        example.setOrderByClause("partition_index asc");
        List<Partitions> partitionses = partitionMapper.selectByExample(example);
        for (Partitions p : partitionses) {
            byte[] bytes = fileHandler.getFile(p.getPartitionLoc(), p.getPartitionId());
            for (byte b : bytes) {
                result[index++] = b;
            }
        }
        return result;
    }
}


