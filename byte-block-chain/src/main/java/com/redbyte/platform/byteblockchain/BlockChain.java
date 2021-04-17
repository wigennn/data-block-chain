package com.redbyte.platform.byteblockchain;

import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangwq
 */
@Service
public class BlockChain implements InitializingBean {

    public List<Block> blockChain;

    public String calcHash(Block block) {
        String key = block.getIndex() + block.getPreHash() + block.getTimestamp() + block.getData();
        return Md5Crypt.md5Crypt(key.getBytes());
    }

    public Block getLastBlock() {
        return blockChain.get(blockChain.size() - 1);
    }

    public Block createBlock(Block oldBlock, Object data) {
        Block block = new Block();
        block.setIndex(oldBlock.getIndex() + 1);
        block.setHash(calcHash(oldBlock));
        block.setPreHash(oldBlock.getPreHash());
        block.setTimestamp(block.createTimeStamp());
        block.setData(data);

        addBlockChain(oldBlock, block);
        return block;
    }

    public boolean validBlock(Block oldBlock, Block newBlock) {
        if (oldBlock.getIndex() + 1 != newBlock.getIndex()
                || oldBlock.getHash().equals(newBlock.getPreHash())
                || calcHash(oldBlock).equals(newBlock.getHash())) {
            return false;
        }
        return true;
    }

    public void syncBlockChain(List<Block> newBlockChain) {
        if (newBlockChain.size() > blockChain.size()) {
            blockChain = newBlockChain;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        blockChain = new ArrayList<>();
        blockChain.add(initMetaBlock());
    }

    public Block initMetaBlock() {
        Block metaBlock = new Block();
        metaBlock.setIndex(0);
        metaBlock.setTimestamp(metaBlock.createTimeStamp());
        metaBlock.setPreHash("");
        metaBlock.setData(null);
        metaBlock.setHash(calcHash(metaBlock));
        return metaBlock;
    }

    public void addBlockChain(Block oldBlock, Block newBlock) {
        synchronized (this) {
            if (validBlock(oldBlock, newBlock)) {
                blockChain.add(newBlock);
            }
        }
    }
}
