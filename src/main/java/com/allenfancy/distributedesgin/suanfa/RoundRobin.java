package com.allenfancy.distributedesgin.suanfa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 
 * 1.轮询法 将请求按照顺序轮流地分配到后端服务器上，它均衡地对待后端每一台服务器，而不关心服务器实际的连接数和当前的系统负载、
 * 模拟：通过一个初始化serverWeightMap和Map变量来表示服务器地址和权重的映象，来模拟
 *
 * 2.随机法 通过系统随机函数，根据后端服务器列表的大小值来随机选取其中一台进行访问。由概率统计理论，随着调用量增加，
 * 其实际效果越来越接近于平均分配流量到每一台后端服务器，也就是轮询的效果
 * 
 * 3.源地址哈希法
 *   源地址哈希的思想是获取客户端访问的IP地址值，通过哈希函数计算得到一个数值，用该值对服务器列表的大小进行取模运算，得到的结果便是要访问的服务器的序列号。
 *   采用哈希法进行负载均衡，同一IP地址的客户端，当后端服务器列表不变时，它每次都会被映射到同一台后端服务器进行访问
 * 4.加权轮询(Weight Round Robin)法
 * 	 不同的后端服务器可能机器的配置和当前系统的负载并不相同，因此他们的抗压能力也不相同。给配置高，负载低的机器配置更高的权重，让他处理更多的请求，而配置低，负载高的机器，给他分配较低的权重，
 *   降低其系统负载，加轮询能很好地处理这一问题，并将请求顺序且按照权重分配到后端。
 * 5.加权随机法
 * 	 与加权轮询法类似，加权随机也根据后端服务不同的配置和负载情况，配置不同的权重。不同的是，它是随机选择服务器的，而非顺序的。
 * 6.最小链接数法
 * 	 最小链接数算法比较灵活和只能，由于后端服务器的配置不尽相同，对于请求的处理有快有慢，它正式根据后端服务器当前的链接情况，动态地选择其中当前积压链接数最少的太服务器来处理当前骑牛，尽可能地提高后端服务器的利用效率，将负载合理地分流到
 *   每一台服务器的利用效率，将负载合理地分流到每一台机器。最小链接数设计服务器链接数的汇总和感知，设计与实现较为繁琐。
 */
public class RoundRobin {

	public static void main(String[] args) {
		Map<String, Integer> serverWeightMap = new HashMap<String, Integer>();
		serverWeightMap.put("192.168.1.100", 1);
		serverWeightMap.put("192.168.1.101", 1);
		serverWeightMap.put("192.168.1.102", 4);
		serverWeightMap.put("192.168.1.103", 1);
		serverWeightMap.put("192.168.1.104", 1);
		serverWeightMap.put("192.168.1.105", 3);
		serverWeightMap.put("192.168.1.106", 1);
		serverWeightMap.put("192.168.1.107", 2);
		serverWeightMap.put("192.168.1.108", 1);
		serverWeightMap.put("192.168.1.109", 1);
		serverWeightMap.put("192.168.1.110", 1);
		
		System.out.println(RoundRobin(serverWeightMap));
		System.out.println(TestRandom(serverWeightMap));
		System.out.println(testConsumerHash("192.168.1.106",serverWeightMap));
		System.out.println(testWeightRoundRobin(serverWeightMap));
	}

	// 轮询算法
	public static String RoundRobin(Map<String, Integer> map) {
		// 重新创建一个Map，避免出现由于服务器上线和下线导致的并发问题
		Map<String, Integer> serverMap = new HashMap<String, Integer>();
		serverMap.putAll(map);
		Set<String> keySet = serverMap.keySet();
		ArrayList<String> keyList = new ArrayList<String>();

		keyList.addAll(keySet);

		String server = null;
		Integer pos = 0;
		synchronized (pos) {
			if (pos >= keySet.size()) {
				pos = 0;
			}
			server = keyList.get(pos);
			pos++;
		}
		return server;
	}

	//随机算法
	public static String TestRandom(Map<String,Integer> map) {
		// 重新创建一个Map，避免出现由于服务器上线和下线导致的并发问题
		Map<String, Integer> serverMap = new HashMap<String, Integer>();
		serverMap.putAll(map);
		Set<String> keySet = serverMap.keySet();
		ArrayList<String> keyList = new ArrayList<String>();

		keyList.addAll(keySet);

		Random random = new Random();
		int randomPos = random.nextInt(keyList.size());
		String server = keyList.get(randomPos);
		return server;
	}
	
	//源地址哈希算法
	public static String testConsumerHash(String remoteIp,Map<String,Integer> map){
		Map<String, Integer> serverMap = new HashMap<String, Integer>();
		serverMap.putAll(map);
		Set<String> keySet = serverMap.keySet();
		ArrayList<String> keyList = new ArrayList<String>();
		
		keyList.addAll(keySet);
		int hashCode = remoteIp.hashCode();
		hashCode = Math.abs(hashCode);
		int serverListSize = keyList.size();
		int serverPos = hashCode % serverListSize;
		System.out.println(serverPos);
		return keyList.get(serverPos);
	}
	//加权轮询
	public static String testWeightRoundRobin(Map<String,Integer> map){
		Map<String, Integer> serverMap = new HashMap<String, Integer>();
		serverMap.putAll(map);
		//获取IP地址
		Set<String> keySet = serverMap.keySet();

		Iterator<String> it = keySet.iterator();
		List<String> serverList = new ArrayList<String>();
		
		while(it.hasNext()){
			String server = it.next();
			Integer weight = serverMap.get(server);
			for(int i = 0 ; i< weight;i++){
				serverList.add(server);
			}
		}
		
		String server = null;
		
		Integer pos = 0;
		
		synchronized (pos) {
			if(pos >= serverList.size()){
				pos = 0 ;
			}
			server = serverList.get(pos);
			pos++;
		}
		return server;
	}
	
	
	
	public static String testWeightRandom(Map<String,Integer> map){
		Map<String, Integer> serverMap = new HashMap<String, Integer>();
		serverMap.putAll(map);
		//获取IP地址
		Set<String> keySet = serverMap.keySet();

		Iterator<String> it = keySet.iterator();
		List<String> serverList = new ArrayList<String>();
		
		while(it.hasNext()){
			String server = it.next();
			Integer weight = serverMap.get(server);
			for(int i = 0 ; i< weight;i++){
				serverList.add(server);
			}
		}
		Random r = new Random();
		int random = r.nextInt(serverList.size());
		String server = serverList.get(random);
		return server;
	}
}
