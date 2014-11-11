package edu.sjsu.cmpe.cache.client;

import java.util.ArrayList;
import java.util.List;

import com.google.common.hash.Hashing;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Cache Client...");
        char[] value = {'0','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        List<DistributedCacheService> CS = new ArrayList<DistributedCacheService>();
        CS.add(new DistributedCacheService("http://localhost:3000"));
        CS.add(new DistributedCacheService("http://localhost:3001"));
        CS.add(new DistributedCacheService("http://localhost:3002"));
        
        for(int putkey=1; putkey<=10; putkey++)	{
        	int key = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(putkey)), CS.size());
        	CS.get(key).put(putkey, Character.toString(value[putkey]));
        	System.out.println("The key value pair " + putkey +"-" + value[putkey]+ " is assigned to server " + key);
        }
        for(int getkey=1; getkey<=10; getkey++)	{
        	int key2 = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(getkey)), CS.size());
        	System.out.println("The key value pair " + getkey +"-" + CS.get(key2).get(getkey)+ " is received to server " + key2);
        	
        }
        
    }

}
