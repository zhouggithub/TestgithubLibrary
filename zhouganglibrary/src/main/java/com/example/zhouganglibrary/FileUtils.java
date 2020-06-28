package com.example.zhouganglibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * @author zhouchangshi
 * @Create 2013-8-30
 * @Module utils
 * @Description 文件和目录操作的封装
 */
public final class FileUtils {

	private static final String TAG = "FileUtils";

	/**
	 * 工具类禁止实例化
	 */
	public FileUtils() {
	}

	/**
	 * @Description 获取sdcard的绝对路径
	 * @return 返回sdcard的绝对路径, 如: /mnt/sdcard
	 */
	public static String getSDCardPath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}

	/**
	 * @Description 获取一个UUID字符串
	 * @return 返回UUID字符串
	 */
	public static String getUUIDString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * @Description 计算文件或文件夹的容量大小
	 * @param path
	 *            输入文件或文件夹的路径
	 * @return 返回文件或文件夹的大小 (以字节为单位), -1表示文件或文件夹不存在或者发生错误
	 */
	public static long calculateCapacity(final String path, long initCapacity) {
		File file = new File(path);
		if (!file.exists())
			return -1; // 文件或文件夹不存在
		if (file.isFile())
			return file.length(); // 文件大小

		File[] files = file.listFiles();
		if (files != null && files.length > 0) {
			for (File f : files) {
				initCapacity += calculateCapacity(f.getAbsolutePath(), initCapacity);
			}
		}
		return initCapacity;
	}

	/**
	 * @Description 删除文件或者文件夹
	 * @param path
	 *            文件或文件夹的路径
	 */
	public static void deleteFileOrDir(String path) {
		File file = new File(path);
		if (!file.exists()) { // 文件不存在
			return;
		}
		if (file.isFile()) {
			boolean b = file.delete();
			if (b) {
				Log.d(TAG, "file \"" + file.getAbsolutePath() + "\" deleted !");
			} else {
				Log.d(TAG, "file \"" + file.getAbsolutePath() + "\" deleting failure !");
			}
		} else {
			File[] files = file.listFiles();
			if (files != null && files.length > 0) {
				for (File f : files) {
					deleteFileOrDir(f.getAbsolutePath()); // 递归删除
				}
			}
			boolean b = file.delete(); // 删除空目录
			if (b) {
				Log.d(TAG, "directory \"" + file.getAbsolutePath() + "\" deleted !");
			} else {
				Log.d(TAG, "directory \"" + file.getAbsolutePath() + "\" deleting failure !");
			}
		}
	}
	public static void deleteGlideFileOrDir(String path) {
		Date date = new Date();
		Date oneDayBefore = DateUtils.getOneDayBefore(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(oneDayBefore);

		long oneDayBeforeTime = oneDayBefore.getTime();


		LogUtils.info("两天前filetime："+time+";oneDayBeforeTime:"+oneDayBeforeTime);


		File file = new File(path);
		if (!file.exists()) { // 文件不存在
			return;
		}
		if (file.isFile()) {
			LogUtils.info("filetime:"+file.lastModified()+";转换后："+DateUtils.getStrTime(file.lastModified()));
			;
			boolean b = file.delete();
			if (b) {
				Log.d(TAG, "file \"" + file.getAbsolutePath() + "\" deleted !");
			} else {
				Log.d(TAG, "file \"" + file.getAbsolutePath() + "\" deleting failure !");
			}
		} else {
			File[] files = file.listFiles();
			if (files != null && files.length > 0) {
				for (File f : files) {

					if (oneDayBeforeTime > f.lastModified()) {
						LogUtils.info("递归删除filetime:"+f.lastModified()+";转换后："+DateUtils.getStrTime(f.lastModified()));
						deleteFileOrDir(f.getAbsolutePath()); // 递归删除
					}

				}
			}
			boolean b = file.delete(); // 删除空目录
			if (b) {
				Log.d(TAG, "directory \"" + file.getAbsolutePath() + "\" deleted !");
			} else {
				Log.d(TAG, "directory \"" + file.getAbsolutePath() + "\" deleting failure !");
			}
		}
	}
	/**
	 * @Description 删除某个目录下的所有文件或文件夹(但是不删除此目录)
	 * @param path
	 *            目录
	 */
	public static void removeContentsAtPath(String path) {
		File file = new File(path);
		if (!file.exists())
			return;
		if (file.isFile())
			return;

		File[] files = file.listFiles();
		if (files != null && files.length > 0) {
			for (File f : files) {
				deleteFileOrDir(f.getAbsolutePath());
			}
		}
	}

	/**
	 * @Description 获取系统剩余空间
	 * @return 剩余空间大小 (以字节为单位)
	 */
	public static long getFreeSpace() {
		return Environment.getExternalStorageDirectory().getFreeSpace();
	}

	/**
	 * @Description sdcard是否可用
	 * @return true-可用, false-不可用
	 */
	public static boolean isSdcardAvailable() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	/**
	 * @Description 创建缓存目录
	 */
	public static void createCacheDirectory() {
		File rootDir = new File(NewsConstants.PATH_ROOT);
		if (!rootDir.exists()) {
			boolean b = rootDir.mkdirs();
			if (b) {
				Log.d(TAG, "path: " + rootDir.getAbsolutePath() + " make success !");
			} else {
				Log.d(TAG, "path: " + rootDir.getAbsolutePath() + " make failure !");
			}
		} else {
			Log.d(TAG, rootDir.getAbsolutePath() + " already exists !");
		}

		File ic = new File(NewsConstants.CACHE_IMAGE);
		if (!ic.exists()) {
			boolean b = ic.mkdirs();
			if (b) {
				Log.d(TAG, "path: " + ic.getAbsolutePath() + " make success !");
			} else {
				Log.d(TAG, "path: " + ic.getAbsolutePath() + " make failure !");
			}
		} else {
			Log.d(TAG, ic.getAbsolutePath() + " already exists !");
		}

		File ac = new File(NewsConstants.CACHE_AUDIO);
		if (!ac.exists()) {
			boolean b = ac.mkdirs();
			if (b) {
				Log.d(TAG, "path: " + ac.getAbsolutePath() + " make success !");
			} else {
				Log.d(TAG, "path: " + ac.getAbsolutePath() + " make failure !");
			}
		} else {
			Log.d(TAG, ac.getAbsolutePath() + " already exists !");
		}

		/*
		 * File newslist = new File(MyConstants.PATH_CACHE_NEWSLIST); if (!newslist.exists()) { boolean b =
		 * newslist.mkdirs(); if (b) { Log.d(TAG, "path: " + newslist.getAbsolutePath() + " make success !"); } else {
		 * Log.d(TAG, "path: " + newslist.getAbsolutePath() + " make failure !"); } } else { Log.d(TAG,
		 * newslist.getAbsolutePath() + " already exists !"); }
		 */

		File log = new File(NewsConstants.CACHE_LOG);
		if (!log.exists()) {
			boolean b = log.mkdirs();
			if (b) {
				Log.d(TAG, "path: " + log.getAbsolutePath() + " make success !");
			} else {
				Log.d(TAG, "path: " + log.getAbsolutePath() + " make failure !");
			}
		} else {
			Log.d(TAG, log.getAbsolutePath() + " already exists !");
		}

		File recording = new File(NewsConstants.PATH_AUDIO);
		if (!recording.exists()) {
			boolean b = recording.mkdirs();
			if (b) {
				Log.d(TAG, "path: " + recording.getAbsolutePath() + " make success !");
			} else {
				Log.d(TAG, "path: " + recording.getAbsolutePath() + " make failure !");
			}
		} else {
			Log.d(TAG, recording.getAbsolutePath() + " already exists !");
		}

	}

	/**
	 * @Description 读取"/<app_dir>/files/"下面的文件 (内部存储)
	 * @param context
	 * @param filename
	 *            文件名称
	 * @return 返回读取到的内容
	 */
//	public static String getFileContentFromInternal(Context context, String filename) {
//		InputStream in = null;
//		String result = null;
//		try {
//			in = context.openFileInput(filename);
//			if (in != null) {
//				result = HttpUtils.convertStream2String1(in);
//			}
//		} catch (Exception e) {
//			Log.d(TAG, "getFileContent error: " + e.getMessage());
//		} finally {
//			try {
//				if (in != null) {
//					in.close();
//					in = null;
//				}
//			} catch (Exception e2) {
//				in = null;
//				Log.d(TAG, "getFileContent close stream error: " + e2.getMessage());
//			}
//		}
//		return result;
//	}

	/**
	 * @Description 读取sdcard下面的文件 (外部存储)
	 * @param filename
	 *            文件名称
	 * @return 返回读取到的内容
	 */
//	public static String getFileContentFromExternal(String filepath) {
//		InputStream in = null;
//		String result = null;
//		try {
//			File f = new File(filepath);
//			if (!f.exists())
//				return null;
//			in = new FileInputStream(f);
//			if (in != null) {
//				result = HttpUtils.convertStream2String1(in);
//			}
//		} catch (Exception e) {
//			Log.d(TAG, "getFileContent error: " + e.getMessage());
//		} finally {
//			try {
//				if (in != null) {
//					in.close();
//					in = null;
//				}
//			} catch (Exception e2) {
//				in = null;
//				Log.d(TAG, "getFileContent close stream error: " + e2.getMessage());
//			}
//		}
//		return result;
//	}

	/**
	 * @Description 读取sdcard下面的文件 (外部存储)
	 * @param filename
	 *            文件名称
	 * @return 返回读取到的内容
	 */
	public static String readLogContentFromExternal(File file) {
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		try {
			if (!file.exists())
				return null;
			in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String temp = null;
			while ((temp = in.readLine()) != null) {
				sb.append(temp);
			}
		} catch (Exception e) {
			Log.d(TAG, "getFileContent error: " + e.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (Exception e2) {
				in = null;
				Log.d(TAG, "getFileContent close stream error: " + e2.getMessage());
			}
		}
		return sb.toString();
	}

	// 获取sdcard路径
	public static String getSDPATH() {
		if (isSdcardAvailable()) {
			return Environment.getExternalStorageDirectory() + "/";
		}

		return null;
	}

	// 判断安装包文件是否存在,如果不存在就创建，如果存在就删掉
	public static void createUpdateFile(String name) {
		File file = new File(getSDPATH() + name);

		if (file.exists()) {
			file.delete();
		} else {
			try {
				file = new File(getSDPATH() + name + "tmp");
				file.createNewFile();
			} catch (Exception e) {

			}
		}
	}

	// 将input文件流写入path路径下的fileName文件中，fileName是包的正式名字
	public static File writeToSDFromInput(String fileName, InputStream input) {
		File file = null;
		OutputStream output = null;

		try {
			// 在本地创建要下载的文件，然后读取数据放进去
			file = new File(getSDPATH() + fileName + "tmp");
			// 把创建好的文件用输出流打开，准备往里面写数据
			output = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int num = 0;
			while ((num = input.read(buffer)) != -1) {
				output.write(buffer, 0, num);
			}
			output.flush();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return file;
	}

	/**
	 * @Description 获取一个目录的容量大小(真实空间的大小)
	 * @param directory
	 * @return
	 */
	public long getDirectoryCapacity(File directory) {
		StatFs statFs = new StatFs("/mnt/sdcard");
		android.os.storage.StorageManager mgr = null;
		return 0;
	}

	/**
	 * 按照最后时间对文件夹里面的文件进行排序
	 * 
	 * @param path
	 *            文件夹路径
	 * @return 排序完毕的文件集合
	 */
	public static List<File> filesort(String path) {
		File dir = new File(path);
		File[] files = dir.listFiles();
		List<File> file_list = null;
		if (files != null && files.length > 0) {
			file_list = Arrays.asList(files);
			Collections.sort(file_list, new Comparator<File>() {

				@Override
				public int compare(File a, File b) {// 升序

					long timea = a.lastModified();
					long timeb = b.lastModified();
					long result = timea - timeb;
					if (result < 0) {
						return -1;
					} else if (result > 0) {
						return 1;
					} else {
						return 0;
					}
				}
			});

		}
		return file_list;
	}

	/**
	 * 计算ac和ic文件夹中的容量
	 * 
	 * @return 以兆显示
	 */
	public static long File_Room() {
		long room_ac = calculateCapacity(NewsConstants.CACHE_AUDIO, 0);
		// long room_ic = calculateCapacity(NewsConstants.CACHE_IMAGE, 0);
		// long room_all = (room_ac + room_ic) / 1024 / 1024;
		long room_all = room_ac / 1024 / 1024;

		return room_all;
	}

	public static long getMediaSize() {
		long room_ac = calculateCapacity(NewsConstants.CACHE_MEDIA, 0);
		// long room_ic = calculateCapacity(NewsConstants.CACHE_IMAGE, 0);
		// long room_all = (room_ac + room_ic) / 1024 / 1024;
		long room_all = room_ac / 1024 / 1024;

		return room_all;
	}

	public static String getAudioSize() {
		long room_ac = calculateCapacity(NewsConstants.PATH_AUDIO, 0);
		String bytes2kb = bytes2kb(room_ac);
		return bytes2kb;
	}

	public static String bytes2kb(long bytes) {
		BigDecimal filesize = new BigDecimal(bytes);
		BigDecimal megabyte = new BigDecimal(1024 * 1024);
		float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
		if (returnValue > 1)
			return (returnValue + "MB");
		BigDecimal kilobyte = new BigDecimal(1024);
		returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP).floatValue();
		return (returnValue + "KB");
	}

	/**
	 * 计算ac和ic文件夹中的容量
	 * 
	 * @return 以兆显示
	 */
	public static long File_AudioRoom() {
		long room_ac = calculateCapacity(NewsConstants.CACHE_AUDIO, 0);
		long room_all = room_ac / 1024 / 1024;
		return room_all;
	}

	/**
	 * 计算ac和ic文件夹中的容量
	 * 
	 * @return 以兆显示
	 */
	public static long File_Room_ic() {
		long room_ic = calculateCapacity(NewsConstants.CACHE_IMAGE, 0);
		long room_all = (room_ic) / 1024 / 1024;

		return room_all;
	}

	// 写入到系统日志
	public static void log2file(String message) {
		String filename = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss").format(new Date());
		OutputStream out = null;
		try {
			File file = new File(NewsConstants.CACHE_LOG + File.separator + filename + "-u.txt");
			out = new FileOutputStream(file);
			out.write(message.getBytes());
			out.flush();
		} catch (Exception e) {
			Log.d("log2file", "log2file error !");
		} finally {
			try {
				if (out != null) {
					out.close();
					out = null;
				}
			} catch (Exception e2) {
				out = null;
			}
		}
	}

	/**
	 * 最新修改的文件
	 * 
	 * @param path
	 * @return
	 */
	public static File lastest_file(String path) {
		// 按日期排序
		File dir = new File(path);
		File[] fs = dir.listFiles();
		File file = null;
		if (null != fs && fs.length > 0) {
			Arrays.sort(fs, new Comparator<File>() {
				public int compare(File f1, File f2) {
					long diff = f1.lastModified() - f2.lastModified();
					if (diff > 0)
						return 1;
					else if (diff == 0)
						return 0;
					else
						return -1;
				}

				public boolean equals(Object obj) {
					return true;
				}

			});
			if (fs.length > 0) {
				file = fs[fs.length - 1];
			}
		}
		return file;
	}

	/**
	 * @Description 将用户信息保存入内部文件
	 */
//	public static void saveUserInfo(Context context, UserInfo info) {
//		ObjectOutputStream out = null;
//		try {
//			out = new ObjectOutputStream(context.openFileOutput(NewsConstants.FILE_NMAE_USERINFO, Context.MODE_PRIVATE));
//			out.writeObject(info);
//			out.flush();
//		} catch (Exception e) {
//			Log.d(TAG, "Serialize playlist error: " + e.getMessage());
//		} finally {
//			try {
//				if (out != null) {
//					out.close();
//					out = null;
//				}
//			} catch (Exception e2) {
//				out = null;
//				Log.d(TAG, "Serialize playlist (close stream) error: " + e2.getMessage());
//			}
//		}
//	}
//
//	/**
//	 * @Description 将用户信息转化为实例
//	 */
//	public static UserInfo GetUserInfo(Context context) {
//		ObjectInputStream in = null;
//		UserInfo result = null;
//		try {
//			in = new ObjectInputStream(context.openFileInput(NewsConstants.FILE_NMAE_USERINFO));
//			result = (UserInfo) in.readObject();
//		} catch (Exception e) {
//			Log.d(TAG, "Unserialize playlist  error: " + e.getMessage());
//		} finally {
//			try {
//				if (in != null) {
//					in.close();
//					in = null;
//				}
//			} catch (Exception e3) {
//				in = null;
//				Log.d(TAG, "Unserialize playlist (close stream) error: " + e3.getMessage());
//			}
//		}
//		return result;
//	}

	/**
	 * 删除指定目录下文件及目录
	 * 
	 * @param deleteThisPath
	 * @param filepath
	 * @return
	 */
//	public static void deleteFolderFile(String filePath, boolean deleteThisPath, NewsDB mDb) {
//		if (!TextUtils.isEmpty(filePath)) {
//			try {
//				File file = new File(filePath);
//				if (file.isDirectory()) {// 处理目录
//					File files[] = file.listFiles();
//					for (int i = 0; i < files.length; i++) {
//						deleteFolderFile(files[i].getAbsolutePath(), true, mDb);
//					}
//				}
//				if (deleteThisPath) {
//					if (!file.isDirectory()) {// 如果是文件，删除
//						file.delete();
//						// TODO Download
//						// if (mDb != null)
//						// mDb.deleteMediaTaskByName(file.getName());
//					} else {// 目录
//						if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
//							file.delete();
//							// TODO Download
//							// if (mDb != null)
//							// mDb.deleteMediaTaskByName(file.getName());
//						}
//					}
//				}
//			} catch (Exception e) {
//
//				e.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * 删除一半的media 文件夹内容
//	 */
//	public static void deleteMutiLoadFile(String filePath, NewsDB mDb) {
//		if (!TextUtils.isEmpty(filePath)) {
//			try {
//				File file = new File(filePath);
//				File files[] = null;
//				if (file.isDirectory()) {// 处理目录
//					files = file.listFiles();
//				}
//				// List<String> mylist=new ArrayList<String>();
//				for (int i = 0; i < files.length; i++) {
//					for (int j = 0; j < files.length - i; j++) {
//						if (!files[j].getName().contains(".") || !files[j + 1].getName().contains(".")) {
//							break;
//						}
//						long l1 = Long.parseLong(files[j].getName().substring(0,
//								files[j].getName().lastIndexOf(".") - 1));
//						if (j == files.length - 2) {
//							break;
//						}
//						long l2 = Long.parseLong(files[j + 1].getName().substring(0,
//								files[j + 1].getName().lastIndexOf(".") - 1));
//						if (l1 < l2) {
//							File temp = files[j];
//							files[j] = files[j + 1];
//							files[j + 1] = temp;
//						}
//
//					}
//					// mylist.add(files[i].getName().substring(0, files[i].getName().lastIndexOf(".")));
//				}
//				if (files != null)
//					for (int i = 0; i < files.length / 2; i++) {
//						deleteFolderFile(files[i].getAbsolutePath(), true, mDb);
//					}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * 获取文件的名称
//	 * */
//	public static String getFileName(String str) {
//		int i = str.lastIndexOf('/');
//		if (i != -1) {
//			return str.substring(i + 1);
//		}
//		return str;
//	}

	/**
	 * 复制文件
	 *
	 * @param source 输入文件
	 * @param target 输出文件
	 */
	public static void copy(File source, File target) {
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileInputStream = new FileInputStream(source);
			fileOutputStream = new FileOutputStream(target);
			byte[] buffer = new byte[1024];
			while (fileInputStream.read(buffer) > 0) {
				fileOutputStream.write(buffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param bmp 获取的bitmap数据
	 * @param picName 自定义的图片名
	 */
	public static void saveBmp2Gallery(Context context, Bitmap bmp, String picName) {
//        saveImageToGallery(bmp,picName);
		String fileName = null;
		//系统相册目录
		String galleryPath = Environment.getExternalStorageDirectory()
				+ File.separator + Environment.DIRECTORY_DCIM
				+ File.separator + "Camera" + File.separator;


		// 声明文件对象
		File file = null;
		// 声明输出流
		FileOutputStream outStream = null;
		try {
			// 如果有目标文件，直接获得文件对象，否则创建一个以filename为名称的文件
			File  fileDir = new File(galleryPath);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}

			file = new File(galleryPath, picName + ".jpg");


			// 获得文件相对路径
			fileName = file.toString();
			// 获得输出流，如果文件中有内容，追加内容
			outStream = new FileOutputStream(fileName);
			if (null != outStream) {
				bmp.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
			}
		}catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				if (outStream != null) {
					outStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		MediaStore.Images.Media.insertImage(context.getContentResolver(),bmp,fileName,null);
		Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		Uri uri = Uri.fromFile(file);
		intent.setData(uri);
		context.sendBroadcast(intent);

		((Activity) context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
//				ZgToast.showZgToast("图片保存成功");
			}
		});

	}

}
