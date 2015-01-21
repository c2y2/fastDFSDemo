package org.c2y2.fastdfs.test;

import java.io.File;
import java.io.FileOutputStream;

import org.c2y2.fastdfs.util.FastClientUtils;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;

public class FastTestDownload {
	public static void main(String[] args)throws Exception {
        StorageClient storageClient = FastClientUtils.getStorageClient();  
          
        String group_name = "g1";  
        String remote_filename = "M00/62/C5/wKgDcVS--jWAOF9tAAF9fgUE8_k712.jpg";  
        FileInfo fi = storageClient.get_file_info(group_name, remote_filename);
        byte[] fileBuff =  storageClient.download_file(group_name, remote_filename);//获取文件字节流
        File download = new File("d:/test/download.jpg");//新命名文件
        FileOutputStream ous = new FileOutputStream(download);
        ous.write(fileBuff);//下载文件输出
        ous.flush();
        ous.close();
        String sourceIpAddr = fi.getSourceIpAddr();  
        long size = fi.getFileSize(); 
        System.out.println("ip:" + sourceIpAddr + ",size:" + size);
	}
}
