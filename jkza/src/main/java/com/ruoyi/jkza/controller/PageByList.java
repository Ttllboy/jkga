package com.ruoyi.jkza.controller;

import java.util.List;

public class PageByList {
    public static List starPage(List list, Integer pageNum, Integer pageSize){
        if(list == null){
            return null;
        }
        if(list.size() == 0){
            return null;
        }
        Integer count = list.size();//记录总数
        Integer pageCount = 0;//页数
        if(count%pageSize == 0){
            pageCount = count/pageSize;
        }else {
            pageCount = count/pageSize + 1;
        }
        int fromIndex = 0;//开始索引
        int toIndex = 0;//结束索引
        if(pageNum != pageCount){
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        }else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }
        List pageList = list.subList(fromIndex,toIndex);
        return pageList;
    }
}
