package com.javachen.cshop.common.utils;

import java.io.*;

/***
 * 文件读写
 */
public class FileUtils implements Serializable{
    /****
     * 将content 写入文件
     * @param fileDir
     * @param content
     */
    public static void writeInFile(String fileDir,String content){
        File f=new File(fileDir);
        FileOutputStream fos=null;
        try {
            if(!f.exists()){
                f.createNewFile();
            }
            fos=new FileOutputStream(f);
            fos.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /****
     * 从fileDir 读取文件
     * @param fileDir
     */
    public static String readFileByLine(String fileDir){
        String s="";
        File f=new File(fileDir);
        BufferedReader br=null;
        try{
            br=new BufferedReader(new FileReader(f));
            String temp;
            while((temp=br.readLine())!=null){
                s+=temp;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    public static void createIfNotExist(String fileDir) throws IOException {
        File f=new File(fileDir);
        if(!f.exists()){
            f.createNewFile();
        }
    }
}
