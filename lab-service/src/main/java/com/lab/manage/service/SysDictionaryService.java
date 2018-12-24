package com.lab.manage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.manage.domain.SysDictionary;
import com.lab.manage.form.SysDictionaryForm;
import com.lab.manage.mapper.SysDictionaryMapper;
import com.lab.manage.pojo.SysDictionaryPojo;
import com.lab.manage.result.SysDictionaryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Chengcheng on 2018/12/20.
 */
@RequestMapping("/service/sys/dictionary")
@RestController
public class SysDictionaryService {

    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;

    /**
     * @Author Chengcheng
     * @Description : 获取字典列表
     * @Date 2018/12/21 下午4:46
     */
    @RequestMapping("/findList")
    public Object findList(@RequestBody SysDictionaryForm form){
        PageHelper.startPage(form.getOffset(),form.getLimit());
        List<SysDictionaryResult> resultList = sysDictionaryMapper.findList(form);
        return new PageInfo<>(resultList);
    }

    /**
     * @Author Chengcheng
     * @Description : 查询字典信息
     * @Date 2018/12/21 下午4:46
     */
    @RequestMapping("/findById")
    public SysDictionaryResult findById(@RequestParam("id") Integer id){
        return sysDictionaryMapper.findById(id);
    }

    /**
     * @Author Chengcheng
     * @Description : 添加字典
     * @Date 2018/12/21 下午4:46
     */
    @RequestMapping("/addDictionary")
    public void addDictionary(@RequestBody SysDictionary sysDictionary){
        sysDictionaryMapper.insert(new SysDictionaryPojo(sysDictionary));
    }

    /**
     * @Author Chengcheng
     * @Description : 修改字典
     * @Date 2018/12/21 下午4:46
     */
    @RequestMapping("/editDictionary")
    public void editDictionary(@RequestBody SysDictionary sysDictionary){
        sysDictionaryMapper.updateById(new SysDictionaryPojo(sysDictionary));
    }


}
