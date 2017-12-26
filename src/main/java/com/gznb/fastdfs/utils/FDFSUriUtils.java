package com.gznb.fastdfs.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 解析FastDFS上传成功后返回的URI工具类
 *
 * @author jiangjun
 * @create 2017/10/10
 */
public class FDFSUriUtils {

    /**
     * 返回解析uri中的groupName和文件相对路径path
     *
     * @param uri：[0]:groupName [1]:path
     * @return
     */
    public static String[] resolveUri(String uri) {
        if (StringUtils.isBlank(uri) || !uri.contains("group")) {
            return null;
        }
        int index = uri.indexOf("/");
        if (index < 0) {
            return null;
        }
        String groupName = uri.substring(0, index);
        String path = uri.substring(index + 1);
        return new String[]{groupName, path};
    }
}
