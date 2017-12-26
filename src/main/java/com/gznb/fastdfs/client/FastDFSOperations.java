package com.gznb.fastdfs.client;

import com.gznb.fastdfs.constants.FDFSConstants;
import com.gznb.fastdfs.exception.FastDFSException;
import java.util.Map;

/**
 * fast dfs operation methods
 *
 * @author jiangjun
 * @create 2017/9/30
 */
public interface FastDFSOperations {

    /**
     * 上传文件
     *
     * @param file:     上传文件二进制数组
     * @param fileName: 文件名称
     * @param fileSize: 文件大小
     * @throws FastDFSException
     * @return: 返回上传之后的uri路径
     */
    String uploadFile(byte[] file, String fileName, Long fileSize) throws FastDFSException;

    /**
     * 获取文件上传的meta信息。文件原始名称、文件扩展名、文件大小
     * @param fileUri
     * @return: 返回的key值请查看 {@link FDFSConstants}
     * @throws FastDFSException
     */
    Map<String,String> getFileMetaData(String fileUri) throws FastDFSException;

    /**
     * 下载文件
     *
     * @param fileUri：上传文件成功后返回的uri
     * @return
     * @throws FastDFSException
     */
    byte[] downloadFile(String fileUri) throws FastDFSException;

    /**
     * 删除文件
     * @param fileUri：上传文件成功后返回的uri
     * @return：true删除成功；false删除失败
     * @throws FastDFSException
     */
    boolean deleteFile(String fileUri) throws FastDFSException;
}
