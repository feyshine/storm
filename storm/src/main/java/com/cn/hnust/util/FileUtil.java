package com.cn.hnust.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.cn.hnust.controller.BaseController;

public class FileUtil {
	public static Logger logger = LogManager.getLogger(FileUtil.class.getName());
	public static final String PATH = "c:/accessToken.text";

	public static String read(String pathName) {
		final File logFile = new File(pathName);
		final StringBuffer sb = new StringBuffer();
		try {
			final RandomAccessFile randomAccessFile = new RandomAccessFile(
					logFile, "rw");
			try {
				String tmp = "";
				while ((tmp = randomAccessFile.readLine()) != null) {
					sb.append(tmp);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					randomAccessFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	public static void writerLog(String pathName, final String msg) {
		final File logFile = new File(pathName);
		if (!logFile.exists()) {
			creat(logFile);
		} else {
			logFile.delete();
			creat(logFile);
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				Writer txtWriter = null;
				try {
					txtWriter = new FileWriter(logFile, true);
					txtWriter.write(msg);
					txtWriter.flush();
				} catch (IOException e) {
					throw new RuntimeException(e);
				} finally {
					try {
						txtWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}

		}).start();
	}

	private static void creat(final File logFile) {
		try {
			logFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param request
	 * @return
	 */
	public static String getFilePath(HttpServletRequest request) {
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
		return realPath;
	}
	
	/**
	 * @param file
	 * @param kservice
	 * @param request
	 * @param map
	 */
	public static boolean saveFile(MultipartFile file, String path) {
		boolean flag = false;
		if (file.isEmpty()) {
			logger.info("文件未上传");
			flag = false;
		} else {
			logger.info("文件长度: " + file.getSize());
			logger.info("文件类型: " + file.getContentType());
			logger.info("文件名称: " + file.getName());
			logger.info("文件原名: " + file.getOriginalFilename());
			logger.info("realPath = " + path);
			logger.info("========================================");
			try {
				// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
				FileUtils.copyInputStreamToFile(file.getInputStream(),
						new File(path, file.getOriginalFilename()));
				flag = true;
			} catch (IOException e) {
				e.printStackTrace();
				logger.info("io异常  =" + e);
			}
		}

		return flag;
	}
	
	
	/**
	 * @param files
	 * @param request
	 */
	public static boolean saveFiles(MultipartFile[] files, HttpServletRequest request) {
		boolean flag = false;
		for(MultipartFile file : files){
			if(file.isEmpty()){  
				flag = false;
				logger.info("文件未上传");
			}else{  
				logger.info("文件长度: " + file.getSize());  
				logger.info("文件类型: " + file.getContentType());  
				logger.info("文件名称: " + file.getName());  
				logger.info("文件原名: " + file.getOriginalFilename());  
				logger.info("========================================"); 
				String realPath = FileUtil.getFilePath(request);
                try {
                	//这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
					FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, file.getOriginalFilename()));
					flag = true;
				} catch (IOException e) {
					e.printStackTrace();
				}  
             }
		}
		return flag;
	}

//	public static void main(String[] args) {
//		FileUtil.writerLog(PATH, UUID.randomUUID().toString());
//		L.i("value:", FileUtil.read(PATH));
//		;
//	}
}
