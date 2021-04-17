package com.redbyte.platform.byteblockchain;

import java.io.Serializable;

/**
 * @author wangwq
 */
public class BlockData implements Serializable {
    private static final long serialVersionUID = 4838214094257140558L;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
