package com.redbyte.platform.byteblockchain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangwq
 */
@RestController
public class BlockWeb {

    @Autowired
    private BlockHandler blockHandler;

    @RequestMapping("/getBlockChain")
    public List<Block> getBlockChain() {
        return blockHandler.getBlockChain();
    }

    @RequestMapping("/createBlcok")
    public List<Block> createBlock(BlockData data) {
        blockHandler.createBlock(data);
        return blockHandler.getBlockChain();
    }
}
