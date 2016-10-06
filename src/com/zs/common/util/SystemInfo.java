package com.zs.common.util;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.Swap;

import com.zs.common.web.vo.ServerInfoFormMap;

/**
 * ϵͳ��Ϣ��ȡ
 * @author jiarui
 * @version 1.0
 * @createTime 2015.12.27
 */
public class SystemInfo {
	public static ServerInfoFormMap SystemProperty() {
		ServerInfoFormMap monitorMap = new ServerInfoFormMap();
		Runtime r = Runtime.getRuntime();
		Properties props = System.getProperties();
		InetAddress addr = null;
		String ip = "";
		String hostName = "";
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			ip = "�޷���ȡ����IP";
			hostName = "�޷���ȡ������";
		}
		if (null != addr) {
			try {
				ip = addr.getHostAddress();
			} catch (Exception e) {
				ip = "�޷���ȡ����IP";
			}
			try {
				hostName = addr.getHostName();
			} catch (Exception e) {
				hostName = "�޷���ȡ������";
			}
		}
		monitorMap.put("hostIp", ip);// ����ip��ַ
		monitorMap.put("hostName", hostName);// ����������
		monitorMap.put("osName", props.getProperty("os.name"));// ����ϵͳ������
		monitorMap.put("arch", props.getProperty("os.arch"));// ����ϵͳ�Ĺ���
		monitorMap.put("osVersion", props.getProperty("os.version"));// ����ϵͳ�İ汾
		monitorMap.put("processors", r.availableProcessors());// JVM����ʹ�õĴ���������
		monitorMap.put("javaVersion", props.getProperty("java.version"));// Java�����л����汾
		monitorMap.put("vendor", props.getProperty("java.vendor"));// Java�����л�����Ӧ��
		monitorMap.put("javaUrl", props.getProperty("java.vendor.url"));// Java��Ӧ�̵�URL
		monitorMap.put("javaHome", props.getProperty("java.home"));// Java�İ�װ·��
		monitorMap.put("tmpdir", props.getProperty("java.io.tmpdir"));// Ĭ�ϵ���ʱ�ļ�·��
		return monitorMap;
	}

	public static ServerInfoFormMap memory(Sigar sigar) {
		ServerInfoFormMap monitorMap = new ServerInfoFormMap();
		try {
			Runtime r = Runtime.getRuntime();
			monitorMap.put("jvmTotal", div(r.totalMemory(), (1024 * 1024), 2) + "M");// java���ڴ�
			monitorMap.put("jvmUse", div(r.totalMemory() - r.freeMemory(), (1024 * 1024), 2) + "M");// JVMʹ���ڴ�
			monitorMap.put("jvmFree", div(r.freeMemory(), (1024 * 1024), 2) + "M");// JVMʣ���ڴ�
			monitorMap.put("jvmUsage", div(r.totalMemory() - r.freeMemory(), r.totalMemory(), 2));// JVMʹ����

			Mem mem = sigar.getMem();
			// �ڴ�����
			monitorMap.put("ramTotal",div(mem.getTotal(), (1024 * 1024 * 1024), 2) + "G");// �ڴ�����
			monitorMap.put("ramUse", div(mem.getUsed(), (1024 * 1024 * 1024), 2) + "G");// ��ǰ�ڴ�ʹ����
			monitorMap.put("ramFree",div(mem.getFree(), (1024 * 1024 * 1024), 2) + "G");// ��ǰ�ڴ�ʣ����
			monitorMap.put("ramUsage",div(mem.getUsed(), mem.getTotal(), 2));// �ڴ�ʹ����

			Swap swap = sigar.getSwap();
			// ����������
			monitorMap.put("swapTotal",div(swap.getTotal(), (1024 * 1024 * 1024), 2) + "G");
			// ��ǰ������ʹ����
			monitorMap.put("swapUse",div(swap.getUsed(), (1024 * 1024 * 1024), 2) + "G");
			// ��ǰ������ʣ����
			monitorMap.put("swapFree",div(swap.getFree(), (1024 * 1024 * 1024), 2) + "G");
			monitorMap.put("swapUsage",div(swap.getUsed(), swap.getTotal(), 2));//

		} catch (Exception e) {
		}
		return monitorMap;
	}


	
	public static ServerInfoFormMap usage(Sigar sigar) {
		ServerInfoFormMap monitorMap = new ServerInfoFormMap();
		try {
			Runtime r = Runtime.getRuntime();
			monitorMap.put("jvmUsage", Math.round(div(r.totalMemory()-r.freeMemory(), r.totalMemory(), 2)*100));// JVMʹ����

			Mem mem = sigar.getMem();
			// �ڴ�����
			monitorMap.put("ramUsage", Math.round(div(mem.getUsed(), mem.getTotal(), 2)*100));// �ڴ�ʹ����

 			List<ServerInfoFormMap> cpu = cpuInfos(sigar);
			double b = 0.0;
			for (ServerInfoFormMap m : cpu) {
				b += Double.valueOf(m.get("cpuTotal")+"");
			}
			monitorMap.put("cpuUsage", Math.round(b/cpu.size()));// cpuʹ����
		} catch (Exception e) {
		}
		return monitorMap;
	}

	public static List<ServerInfoFormMap> cpuInfos(Sigar sigar) {
		List<ServerInfoFormMap> monitorMaps = new ArrayList<ServerInfoFormMap>();
		try {
			CpuPerc cpuList[] = sigar.getCpuPercList();
			for (CpuPerc cpuPerc : cpuList) {
				ServerInfoFormMap monitorMap = new ServerInfoFormMap();
				monitorMap.put("cpuUserUse", Math.round(cpuPerc.getUser()*100));// �û�ʹ����
				monitorMap.put("cpuSysUse", Math.round(cpuPerc.getSys()*100));// ϵͳʹ����
				monitorMap.put("cpuWait", Math.round(cpuPerc.getWait()*100));// ��ǰ�ȴ���
				monitorMap.put("cpuFree", Math.round(cpuPerc.getIdle()*100));// ��ǰ������
				monitorMap.put("cpuTotal",Math.round(cpuPerc.getCombined()*100));// �ܵ�ʹ����
				monitorMaps.add(monitorMap);
			}
		} catch (Exception e) {
		}
		return monitorMaps;
	}

	public List<ServerInfoFormMap> diskInfos(Sigar sigar) throws Exception {
		List<ServerInfoFormMap> monitorMaps = new ArrayList<ServerInfoFormMap>();
		FileSystem fslist[] = sigar.getFileSystemList();
		for (int i = 0; i < fslist.length; i++) {
			ServerInfoFormMap monitorMap = new ServerInfoFormMap();
			FileSystem fs = fslist[i];
			// �ļ�ϵͳ�����������籾��Ӳ�̡������������ļ�ϵͳ��
			FileSystemUsage usage = null;
			usage = sigar.getFileSystemUsage(fs.getDirName());
			switch (fs.getType()) {
			case 0: // TYPE_UNKNOWN ��δ֪
				break;
			case 1: // TYPE_NONE
				break;
			case 2: // TYPE_LOCAL_DISK : ����Ӳ��

				monitorMap.put("diskName", fs.getDevName());// ϵͳ������
				monitorMap.put("diskType", fs.getSysTypeName());// ������
				// �ļ�ϵͳ�ܴ�С
				monitorMap.put("diskTotal", fs.getSysTypeName());
				// �ļ�ϵͳʣ���С
				monitorMap.put("diskFree", usage.getFree());
				// �ļ�ϵͳ�Ѿ�ʹ����
				monitorMap.put("diskUse", usage.getUsed());
				double usePercent = usage.getUsePercent() * 100D;
				// �ļ�ϵͳ��Դ��������
				monitorMap.put("diskUsage", usePercent);// �ڴ�ʹ����
				monitorMaps.add(monitorMap);
				break;
			case 3:// TYPE_NETWORK ������
				break;
			case 4:// TYPE_RAM_DISK ������
				break;
			case 5:// TYPE_CDROM ������
				break;
			case 6:// TYPE_SWAP ��ҳ�潻��
				break;
			}
		}
		return monitorMaps;
	}
	
	/**
	 * �ṩ����ԣ���ȷ�ĳ������㡣�����������������ʱ����scale����ָ �����ȣ��Ժ�������������롣
	 * 
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @param scale
	 *            ��ʾ��ʾ��Ҫ��ȷ��С�����Ժ�λ��
	 * @return ������������
	 */
	private static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static void main(String[] args) {
		ServerInfoFormMap s = SystemInfo.usage(new Sigar());
		System.out.println(s.size());
	}

}
