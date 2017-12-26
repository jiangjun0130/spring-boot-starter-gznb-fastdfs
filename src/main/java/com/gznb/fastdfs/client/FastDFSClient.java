package com.gznb.fastdfs.client;

import com.gznb.fastdfs.constants.FDFSConstants;
import com.gznb.fastdfs.exception.FastDFSException;
import com.gznb.fastdfs.utils.FDFSUriUtils;
import org.apache.commons.io.FilenameUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient1;

import java.util.HashMap;
import java.util.Map;

/**
 * Fast DFS 客户端
 *
 * @author jiangjun
 * @create 2017/9/30
 */
public class FastDFSClient implements FastDFSOperations {

    private StorageClient1 storageClient;

    public FastDFSClient(StorageClient1 storageClient) {
        this.storageClient = storageClient;
    }

    @Override
    public String uploadFile(byte[] file, String fileName, Long fileSize) throws FastDFSException {
        String extName = FilenameUtils.getExtension(fileName);
        NameValuePair[] meta_list = new NameValuePair[3];
        meta_list[0] = new NameValuePair(FDFSConstants.META_DATA_FILE_NAME, fileName);
        meta_list[1] = new NameValuePair(FDFSConstants.META_DATA_FILE_EXT, extName);
        meta_list[2] = new NameValuePair(FDFSConstants.META_DATA_FILE_SIZE, String.valueOf(fileSize));
        try {
            String filePath = storageClient.upload_file1(file, extName, meta_list);
            return filePath;
        } catch (Exception e) {
            throw new FastDFSException("fast dfs client upload file failed!", e);
        }
    }

    @Override
    public Map<String,String> getFileMetaData(String fileUri) throws FastDFSException {
        String[] resolveUri = FDFSUriUtils.resolveUri(fileUri);
        if (resolveUri == null) {
            return null;
        }
        try {
            NameValuePair[] metadata = storageClient.get_metadata(resolveUri[0], resolveUri[1]);
            Map<String,String> map = new HashMap<>();
            for (NameValuePair valuePair : metadata) {
                map.put(valuePair.getName(),valuePair.getValue());
            }
            return map;
        } catch (Exception e) {
            throw new FastDFSException("fast dfs client get file meta data failed!", e);
        }
    }

    @Override
    public byte[] downloadFile(String fileUri) throws FastDFSException {
        String[] resolveUri = FDFSUriUtils.resolveUri(fileUri);
        if (resolveUri == null) {
            return null;
        }
        try {
            byte[] bytes = storageClient.download_file(resolveUri[0], resolveUri[1]);
            return bytes;
        } catch (Exception e) {
            throw new FastDFSException("fast dfs client download file failed!", e);
        }
    }

    @Override
    public boolean deleteFile(String fileUri) throws FastDFSException {
        String[] resolveUri = FDFSUriUtils.resolveUri(fileUri);
        if (resolveUri == null) {
            return false;
        }
        try {
            int result = storageClient.delete_file(resolveUri[0], resolveUri[1]);
            return result == 0 ? true : false;
        } catch (Exception e) {
            throw new FastDFSException("fast dfs client delete file failed!", e);
        }
    }
}
