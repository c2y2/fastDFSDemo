package org.c2y2.fastdfs.test;

import org.c2y2.fastdfs.util.FastClientUtils;
import org.csource.fastdfs.StorageClient;

public class FastTestDelete {
	public static void main(String[] args) throws Exception{
        StorageClient storageClient = FastClientUtils.getStorageClient();  
        String group_name = "g1";  
        String remote_filename = "M00/97/F9/wKgDcFS_J3CAARH-AAF9fgUE8_k114.jpg";  
        int flag = storageClient.delete_file(group_name, remote_filename);  
        System.out.println(flag);
	}
}
