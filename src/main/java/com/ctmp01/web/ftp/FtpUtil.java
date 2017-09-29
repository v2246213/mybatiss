package com.ctmp01.web.ftp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
* 
*  bxl   
*  create at 2017年9月6日 下午9:22:02  
*/
public class FtpUtil {

	private transient Logger logger = LoggerFactory.getLogger(FtpUtil.class);
	
	/**
	 * FTP 登录用户名
	 */
	private String userName;
	/**
	 * FTP 登录密码
	 */
	private String passWord;
	/**
	 * FTP 服务器地址IP地址
	 */
	private String host;
	/**
	 * FTP 端口
	 */
	private int port;
	
	public FtpUtil(String userName, String passWord, String host, int port) {
		this.userName = userName;
		this.passWord = passWord;
		this.host = host;
		this.port = port;
	}
	
	public FTPClient connect() throws IOException {
		FTPClient ftpClient = new FTPClient();
		try {  
			ftpClient.connect(host, port);  
        } catch (Exception ex) {  
            throw new IOException("Can't find FTP server '" + host + "'");  
        }  
		int reply = ftpClient.getReplyCode();  
        if (!FTPReply.isPositiveCompletion(reply)) {  
        	if(ftpClient.isConnected()){
        		ftpClient.disconnect();  
        	}
            throw new IOException("Can't connect to server '" + host + "'");  
        }
        if (!ftpClient.login(userName, passWord)) {  
        	if(ftpClient.isConnected()){
        		ftpClient.disconnect();  
        	}  
            throw new IOException("Can't login to server '" + host + "'");  
        } 
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE); 
        // Use passive mode to pass firewalls.
        ftpClient.enterLocalPassiveMode();  
        logger.info("ftp connect'" + host + "' successful");
		return ftpClient;
	}
	
	public void uploadFiles(String path , String fileName ,String type ,InputStream in) throws IOException{
		if(path==null){
			path = "/";
		}
		FTPClient ftpClient = connect();
		String[] dirs = path.split("/");
		String tempPath = "";
		
		for(String dir : dirs){
			if (null == dir || "".equals(dir)) {
				continue;
			}
			tempPath += "/" + dir;
			ftpClient.makeDirectory(tempPath);
			ftpClient.changeWorkingDirectory(tempPath);
		}
        ftpClient.storeFile(fileName+type, in);
        in.close();
        ftpClient.logout();
        logger.info("file: " + fileName + " is upload successful");
		}
	
	public void upload(String path, String fileName,String type , byte[] byteArr) throws IOException {
		uploadFiles(path, fileName, type , new ByteArrayInputStream(byteArr));
		}
	
	
	public byte[] downloadFile(String ftpPath , String fileName)throws IOException{
		FTPClient ftpClient = connect();
		ftpClient.changeWorkingDirectory(ftpPath);
        InputStream in = ftpClient.retrieveFileStream(fileName);
        byte[] fileData = IOUtils.toByteArray(in);
        in.close();
		ftpClient.logout();
		logger.info("file: " + fileName + " is download successful");
		return fileData;
	}
	
	}
