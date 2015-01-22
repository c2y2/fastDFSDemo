package org.c2y2.fastdfs.test;

import java.io.File;
import java.io.FileInputStream;

import org.c2y2.fastdfs.util.FastClientUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient;

public class FastTestUpload {
	/**
	 * 
	 * 1,获取配置 String configFilePath = classPath + File.separator + "fdfs_client.conf"; 
	 * 2,全局初始化配置 ClientGlobal.init(configFilePath);  
	 * 3,获取一个跟踪节点实例 
	 * 		TrackerClient trackerClient = new TrackerClient();  
	 * 		TrackerServer trackerServer = trackerClient.getConnection();
	 * 4,根据跟踪节点获取存储节点实例
	 * 		StorageServer storageServer = null;
	 * 		StorageClient storageClient = new StorageClient(trackerServer, storageServer); 
	 * 		说明：
	 *		如果需要获取所有存储节点：
	 *			 String group_name = null;  
	 *   		 StorageServer[] storageServers = trackerClient.getStoreStorages(trackerServer, group_name);
	 * 5,上传文件
	 * 		NameValuePair[] meta_list = new NameValuePair[3];  //添加文件属性
	 *      meta_list[0] = new NameValuePair("width", "120");  
	 *      meta_list[1] = new NameValuePair("heigth", "120");  
	 *      meta_list[2] = new NameValuePair("author", "gary");
	 *      
	 * 		File file = new File("D:/mzl/工作资料/fastDFS/testFile/500fd9f9d72a6059496335a02b34349b033bba1d.jpg");  
	 *	    FileInputStream fis = new FileInputStream(file);  
	 *      byte[] file_buff = null;  
	 *	    if(fis != null){  
	 *	        int len = fis.available();  
	 *	        file_buff = new byte[len];  
	 *	        fis.read(file_buff);  
	 *	    }
	 *     String[] results = storageClient.upload_file(file_buff, "jpg", meta_list);
	 *     results 结果中包含有文件名所在组名，及文件保存路径名
	 *     
	 *     格式：upload_file(字节流,拓展名,文件属性);
	 * 6,获取上传文件相关信息
	 * 		storageClient.get_file_info(group_name, remote_filename)
	 */
	public static void main(String[] args) throws Exception{
	    NameValuePair[] meta_list = new NameValuePair[1];  
	    meta_list[0] = new NameValuePair("author", "mzl");  
	    File file = new File("D:/mzl/工作资料/fastDFS/testFile/500fd9f9d72a6059496335a02b34349b033bba1d.jpg");  
	    FileInputStream ins = new FileInputStream(file);  
	    byte[] file_buff = null;  
	    if(ins != null){  
	        int len = ins.available();  
	        file_buff = new byte[len];  
	        ins.read(file_buff);  
	    }
	    System.out.println("file length: " + file_buff.length);  
	    String group_name = null;  
	    long startTime = System.currentTimeMillis();  
	    StorageClient storageClient = FastClientUtils.getStorageClient();
	    String[] results = storageClient.upload_file(file_buff, "jpg", null);
	    System.out.println("upload_file time used: " + (System.currentTimeMillis() - startTime) + " ms");  
	    if (results == null){  
	        System.err.println("upload file fail, error code: " + storageClient.getErrorCode());  
	        return;  
	    }  
	    group_name = results[0];  
	    String remote_filename = results[1];  
	    System.err.println("group_name: " + group_name + ", remote_filename: " + remote_filename);  
	    System.err.println(storageClient.get_file_info(group_name, remote_filename));  
	 }  
}
