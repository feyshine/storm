package com.cn.hnust.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.util.UUID;

public class FileUtls {
	public static final String PATH = "d:/accessToken.text";

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

	public static void main(String[] args) {
		FileUtls.writerLog(PATH, UUID.randomUUID().toString());
		L.i("value:", FileUtls.read(PATH));
		;
	}
}
