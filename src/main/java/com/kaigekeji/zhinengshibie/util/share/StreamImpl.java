package com.kaigekeji.zhinengshibie.util.share;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;




/**
 * 流读写类
 */
public class StreamImpl implements IStream {
	private InputStream is = null;
	private BufferedInputStream bis = null;
	private BufferedReader br = null;
	private ObjectInputStream ois = null;
	private OutputStream os = null;
	private BufferedOutputStream bos = null;
	private BufferedWriter bw = null;
	private ObjectOutputStream oos = null;
	private static final String LINE = System.lineSeparator();

	private static final int BUFFERSIZE = 20480;
	private String encoding = "utf-8";

	public StreamImpl() {
	}

	/**
	 * 有参构造函数
	 * 
	 * @param encoding
	 *            字符编码
	 */
	public StreamImpl(String encoding) {
		this.encoding = encoding;
	}

	@Override
	public byte[] read(InputStream inputStream) {
		List<Byte> list = new LinkedList<Byte>();
		try {
			is = inputStream;
			bis = new BufferedInputStream(is);
			int bt = -1;
			while ((bt = bis.read()) != -1) {
				list.add((byte) bt);
			}
			byte[] bytes = new byte[list.size()];
			for (int i = 0; i < bytes.length; ++i) {
				bytes[i] = list.get(i);
			}
			return bytes;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String reader(InputStream inputStream) {
		StringBuilder sb = new StringBuilder();
		try {
			is = inputStream;
			br = new BufferedReader(new InputStreamReader(is, encoding));
			String str = null;
			while ((str = br.readLine()) != null) {
				sb.append(str + LINE);
			}
			return sb.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Object readObject(InputStream inputStream) {
		Object obj = null;
		try {
			is = inputStream;
			ois = new ObjectInputStream(is);
			ois.readObject();
			return obj;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void write(OutputStream outputStream, byte[] content) {
		try {
			os = outputStream;
			bos = new BufferedOutputStream(os);
			bos.write(content);
			bos.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void writer(OutputStream outputStream, String content) {
		try {
			os = outputStream;
			bw = new BufferedWriter(new OutputStreamWriter(os, encoding));
			bw.write(content);
			bw.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void writeObject(OutputStream outputStream, Object obj) {
		try {
			os = outputStream;
			oos = new ObjectOutputStream(os);
			oos.writeObject(obj);
			oos.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void doReadWrite(InputStream inputStream, OutputStream outputStream) {
		try {
			int size = 0;
			is = inputStream;
			os = outputStream;
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(os);
			byte[] bytes = new byte[BUFFERSIZE];
			while ((size = bis.read(bytes)) != -1) {
				bos.write(bytes, 0, size);
			}
			bos.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void closeAll() {
		closeInput();
		closeOutput();
	}

	@Override
	public void closeInput() {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {

			}
			is = null;
		}
		if (bis != null) {
			try {
				bis.close();
			} catch (IOException e) {

			}
			bis = null;
		}
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {

			}
			br = null;
		}
		if (ois != null) {
			try {
				ois.close();
			} catch (IOException e) {

			}
			ois = null;
		}
	}

	@Override
	public void closeOutput() {
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {

			}
			os = null;
		}
		if (bos != null) {
			try {
				bos.close();
			} catch (IOException e) {

			}
			bos = null;
		}
		if (bw != null) {
			try {
				bw.close();
			} catch (IOException e) {

			}
			bw = null;
		}
		if (oos != null) {
			try {
				oos.close();
			} catch (IOException e) {

			}
			oos = null;
		}
	}

	@Override
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	@Override
	public String getEncoding() {
		return encoding;
	}
}
