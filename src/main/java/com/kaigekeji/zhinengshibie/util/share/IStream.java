package com.kaigekeji.zhinengshibie.util.share;

import java.io.InputStream;
import java.io.OutputStream;

public interface IStream {
	/**
	 * 读取指定输入流字节数据
	 * 
	 * @param inputStream
	 *            输入流
	 * @return 字节数据
	 */
	public byte[] read(InputStream inputStream);

	/**
	 * 读取指定输入流字符数据
	 * 
	 * @param inputStream
	 *            输入流
	 * @return 数据字符串
	 */
	public String reader(InputStream inputStream);

	/**
	 * 读取指定输入流对象数据
	 * 
	 * @param inputStream
	 *            输入流
	 * @return 数据对象
	 */
	public Object readObject(InputStream inputStream);

	/**
	 * 写入字节数据到指定的输出流
	 * 
	 * @param outputStream
	 *            输出流
	 * @param content
	 *            字节数据内容
	 */
	public void write(OutputStream outputStream, byte[] content);

	/**
	 * 写入字符数据到指定的输出流
	 * 
	 * @param outputStream
	 *            输出流
	 * @param content
	 *            字符串内容
	 */
	public void writer(OutputStream outputStream, String content);

	/**
	 * 写入对象数据到指定的输出流
	 * 
	 * @param outputStream
	 *            输出流
	 * @param obj
	 *            对象内容
	 */
	public void writeObject(OutputStream outputStream, Object obj);

	/**
	 * 输入字节流转输出字节流
	 * 
	 * @param inputStream
	 *            输入流
	 * @param outputStream
	 *            输出流
	 */
	public void doReadWrite(InputStream inputStream, OutputStream outputStream);

	/**
	 * 关闭所有数据流资源
	 */
	public void closeAll();

	/**
	 * 关闭输入流资源
	 */
	public void closeInput();

	/**
	 * 关闭输出流资源
	 */
	public void closeOutput();

	/**
	 * 设置字符编码
	 * 
	 * @param encoding
	 *            字符编码
	 */
	public void setEncoding(String encoding);

	/**
	 * 获取字符编码
	 * 
	 * @return 字符编码
	 */
	public String getEncoding();
}
