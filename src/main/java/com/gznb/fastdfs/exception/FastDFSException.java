package com.gznb.fastdfs.exception;

import com.gznb.fastdfs.client.FastDFSClient;

/**
 * Base class for exceptions thrown by {@link FastDFSClient} whenever it encounters
 *
 * @author jiangjun
 * @create 2017/9/30
 */
public class FastDFSException extends RuntimeException{

    public FastDFSException(String message) {
        super(message);
    }

    public FastDFSException(String message, Throwable cause) {
        super(message, cause);
    }
}
