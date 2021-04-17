package com.redbyte.platform.byteblockchain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangwq
 */
@Service
public class BlockHandler {

    @Autowired
    private BlockChain blockChain;

    public List<Block> getBlockChain() {
        return blockChain.blockChain;
    }

    public void createBlock(Object data) {
        blockChain.createBlock(blockChain.getLastBlock(), data);
    }
}
