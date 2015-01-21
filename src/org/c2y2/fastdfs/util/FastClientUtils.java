package org.c2y2.fastdfs.util;

import java.io.File;
import java.io.IOException;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastClientUtils {
	static{
		String classPath = null;
		try {
			classPath = new File(FastClientUtils.class.getResource("/").getFile()).getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    String configFilePath = classPath + File.separator + "client.conf";  
	    try {
			ClientGlobal.init(configFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	
	/**
	 * 
	 * @Description 获取客户端用于上传或者下载  
	 * @return
	 * @author c2y2 2015年1月21日
	 */
	public static StorageClient getStorageClient(){
		    TrackerClient trackerClient = new TrackerClient();
			StorageClient storageClient = null;
		    TrackerServer trackerServer;
			try {
				trackerServer = trackerClient.getConnection();
				StorageServer storageServer = null;  
			    storageClient = new StorageClient(trackerServer, storageServer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		    return  storageClient;
	}
}
