package com.kaishengit;

import javax.swing.text.html.HTMLDocument;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/8/12.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
//        getAllFiles("F://tool");
        testIterator();
    }

    private static void testIterator() throws InterruptedException {
        List<String> ls = new ArrayList<String>();
        ls.add("中");
        ls.add("秋");
        ls.add("节");
        ls.add("快");
        ls.add("乐");
        ls.add("！");

       Iterator iter = ls.listIterator();
        while (iter.hasNext()){
            System.out.print(iter.next());
        }


        Date date1 = new Date();
        Thread.sleep(1000);
        Date date2 = new Date();
        System.out.println(date1.compareTo(date2));
    }

    public static void getAllFiles(String path){
        File f = new File(path);
        File[] fs = f.listFiles();
        for (int i=0;i<fs.length;i++){
            if(fs[i].isDirectory()){
                System.out.println("文件夹:"+fs[i].getName());
                getAllFiles(fs[i].getAbsolutePath());
            }else{
                System.out.println("文件:"+fs[i].getName());
            }
        }
    }
}
