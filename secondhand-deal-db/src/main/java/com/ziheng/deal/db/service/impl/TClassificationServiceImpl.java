package com.ziheng.deal.db.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.*;
import com.ziheng.deal.common.util.TokenUtil;
import com.ziheng.deal.db.domain.DTO.ClassificationDTO;
import com.ziheng.deal.db.entity.TClassification;
import com.ziheng.deal.db.service.TClassificationService;
import com.ziheng.deal.db.mapper.TClassificationMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
* @author Administrator
* @description 针对表【t_classification】的数据库操作Service实现 商品分类表
* @createDate 2024-04-25 20:51:45
*/
@Service
public class TClassificationServiceImpl extends ServiceImpl<TClassificationMapper, TClassification>
    implements TClassificationService{


    @Resource
    private TClassificationMapper classificationMapper;

    @Override
    public List<ClassificationDTO>  getAllClassification() {
        // 查找出所有的分类
        List<TClassification> classificationsAll = baseMapper.selectList(null);

        //
        ArrayList<ClassificationDTO> result = new ArrayList<>();

        //找到所有的父级分类
        for (TClassification itme : classificationsAll) {
            if (itme.getParentId().equals(0)){
                ClassificationDTO classificationDTO = new ClassificationDTO();
                BeanUtil.copyProperties(itme, classificationDTO);
                result.add(classificationDTO);
            }
        }
        
        // 递归查询所有下一级分类
        for (ClassificationDTO root : result) {
            root.setChildren(getChildrens(root, classificationsAll));
        }
        return result;
    }

    /**
     * 递归查找所有的下级分类
     * 在这一级别的分类中找下级分类
     *
     * @param root 当前级别的分类
     * @param all  全部分类
     * @return 下一级分类
     */
    public List<ClassificationDTO> getChildrens(ClassificationDTO root, List<TClassification> all)
    {
        ArrayList<ClassificationDTO> classifications = new ArrayList<>();
        for (TClassification a : all) {
            if (a.getParentId().equals(root.getClassificationId())) {

                ClassificationDTO classificationDTO = new ClassificationDTO();
                BeanUtil.copyProperties(a, classificationDTO);

                classificationDTO.setChildren(getChildrens(classificationDTO, all));
                classifications.add(classificationDTO);
            }
        }
        return classifications;
    }

    /**
     * 分类添加
     * @param tClassification 分类名称 父级id
     */
    @Override
    public void AddClassification(TClassification tClassification, String username) {
        // 查找该分类名称是否存在
        TClassification result = lambdaQuery()
                .eq(tClassification.getClassificationName() != null, TClassification::getClassificationName, tClassification.getClassificationName())
                .one();
        if (result != null) {
            throw new ClassificationNameExistsException("分类名称已存在");
        }

        TClassification addClassification = new TClassification();
        // 判断是顶级分类还是二级以下分类
        if (tClassification.getParentId() != null)
        {
            // 非顶级分类处理
            addClassification.setClassificationName(tClassification.getClassificationName());
            addClassification.setParentId(tClassification.getParentId());
            // 根据ParentId（也就是父级的id）查询出他的级别
            TClassification one = lambdaQuery()
                    .eq(TClassification::getClassificationId, tClassification.getParentId())
                    .one();
            if (one == null)
            {
                throw new ParentIdExistentException("父级id不存在");
            }
            addClassification.setLevel(one.getLevel() + 1);
            addClassification.setSort(one.getSort() + 1);


        }else {
            // 顶级分类处理
            addClassification.setClassificationName(tClassification.getClassificationName());
            addClassification.setParentId(0);
            addClassification.setSort(1);
            addClassification.setLevel(1);
        }
        // 补全日志信息
        addClassification.setCreateUser(username);
        addClassification.setCreateTime(new Date());
        addClassification.setModifiedUser(username);
        addClassification.setModifiedTime(new Date());

        // 添加到数据库中
        boolean save = save(addClassification);
        if (!save) {
            throw new InsertException("插入数据时产生未知异常");
        }

    }



    /**
     * 查询所有分类的前两级
     * @return ClassificationDTO
     */
    @Override
    public List<ClassificationDTO> GetClassificationTow() {
        // 查找出所有前两级分类
        List<TClassification> classificationsAll = lambdaQuery()
                .in(TClassification::getLevel, 1, 2)
                .list();

        ArrayList<ClassificationDTO> result = new ArrayList<>();

        //找到所有的父级分类
        for (TClassification itme : classificationsAll) {
            if (itme.getParentId().equals(0)){
                ClassificationDTO classificationDTO = new ClassificationDTO();
                BeanUtil.copyProperties(itme, classificationDTO);
                result.add(classificationDTO);
            }
        }

        // 递归查询所有下一级分类
        for (ClassificationDTO root : result) {
            root.setChildren(getChildrens(root, classificationsAll));

        }
        return result;
    }

    /**
     * 根据id查找分类的父级
     * @param id
     * @return
     */
    HashMap<Integer, TClassification> tClassificationHashMap = new HashMap<>();
    @Override
    public ClassificationDTO getByIdClassificationInfo(Integer id)
    {
        TClassification one = lambdaQuery()
                .eq(id != null, TClassification::getClassificationId, id)
                .one();

        if (one == null)
        {
            throw new ClassificationInexistenceException("分类不存在");
        }
        ClassificationDTO dto = new ClassificationDTO();

        if (one.getParentId().equals(0)) {
            BeanUtil.copyProperties(one, dto);
            return dto;
        }

        tClassificationHashMap.put(one.getSort(), one);
        // 获取到当前分类的上级分类并存储到tClassifications列表中；
        getChildrens1(one);
        if (tClassificationHashMap.size() == 3) {
            BeanUtil.copyProperties(tClassificationHashMap.get(1), dto);

            ArrayList<ClassificationDTO> tow = new ArrayList<>();
            ClassificationDTO classificationDTO = new ClassificationDTO();
            BeanUtil.copyProperties(tClassificationHashMap.get(2), classificationDTO);
            tow.add(classificationDTO);

            ClassificationDTO classificationDTO1 = new ClassificationDTO();
            BeanUtil.copyProperties(tClassificationHashMap.get(3), classificationDTO1);
            ArrayList<ClassificationDTO> tow1 = new ArrayList<>();
            tow1.add(classificationDTO1);

            tow.get(0).setChildren(tow1);
            dto.setChildren(tow);
        } else if (tClassificationHashMap.size() == 2) {
            BeanUtil.copyProperties(tClassificationHashMap.get(1), dto);

            ArrayList<ClassificationDTO> tow = new ArrayList<>();
            ClassificationDTO classificationDTO = new ClassificationDTO();
            BeanUtil.copyProperties(tClassificationHashMap.get(2), classificationDTO);
            tow.add(classificationDTO);
            dto.setChildren(tow);
        }


        return dto;
    }

    /**
     * 根据某一级分类获取上级分类
     * @param root 当前分类
     */
    public void getChildrens1(TClassification root){

        for (int item = 0; item < 1; item++) {
            TClassification one = lambdaQuery()
                    .eq(TClassification::getClassificationId, root.getParentId())
                    .one();
            if (one.getParentId().equals(0)){
                tClassificationHashMap.put(one.getSort(), one);
                break;
            } else {
                tClassificationHashMap.put(one.getSort(), one);
                getChildrens1(one);
            }
        }
                
    }

    /**
     * 根据id修改分类
     * @param classification 修改数据
     * @param username 修改人
     */
    @Override
    public void getByIdUpdateClassification(TClassification classification, String username) {
        // 根据id查找出需要修改的数据
        TClassification one = lambdaQuery()
                .eq(classification.getClassificationId() != null, TClassification::getClassificationId, classification.getClassificationId())
                .one();

        if (one == null) {
            throw new ClassificationInexistenceException("分类不存在");
        }

        // 判断是否是顶级分类
        if (classification.getParentId() != null) {
            // 根据parentId 查询出父级分类是否存在
            TClassification objectOne = lambdaQuery()
                    .eq(classification.getParentId() != null, TClassification::getClassificationId, classification.getParentId())
                    .one();

            if (objectOne == null) {
                throw new ParentIdExistentException("父级id不存在");
            }

            classification.setSort(objectOne.getSort() + 1);
            classification.setLevel(objectOne.getLevel() + 1);
        } else {
            classification.setParentId(0);
            classification.setSort(1);
            classification.setLevel(1);
        }




        // 设置日志
        classification.setModifiedUser(username);
        classification.setModifiedTime(new Date());

        int i = classificationMapper.updateById(classification);
        if (i != 1) {
            throw new UpdateException("修改数据时产生未知的异常，请于管理员联系");
        }
    }


    /**
     * 根据id删除分类信息
     * @param id 商品分类id
     */
    @Override
    public void getByIdDeleteClassification(Integer id) {
        TClassification one = lambdaQuery()
                .eq(id != null, TClassification::getClassificationId, id)
                .one();

        if (one == null) {
            throw new ClassificationInexistenceException("分类不存在");
        }
        /*
        * 进行判断根据Level的级别判断等级，并且删除其等级下的所有分类
        * */
        ArrayList<TClassification> classifications = new ArrayList<>();
        classifications.add(one);
        if (one.getLevel() == 1) {
            // 查询到所有的二级分类
            List<TClassification> TowList = lambdaQuery()
                    .eq(TClassification::getParentId, one.getClassificationId())
                    .list();
            // 根据二级分类查询出所有的三级分类
            for (TClassification item : TowList) {
                classifications.add(item);
                // 查询出所有的三级分类
                List<TClassification> list = lambdaQuery()
                        .eq(TClassification::getParentId, item.getClassificationId())
                        .list();
                for (TClassification j : list) {
                    classifications.add(j);
                }

            }
            // 循环删除
            for (TClassification item : classifications) {
                int i = classificationMapper.deleteById(item.getClassificationId());
                if (i != 1) {
                    throw new DeleteException("删除数据时产生未知的异常，请于管理员联系");
                }

            }

        } else if (one.getLevel() == 2) {
            // 查询到所有的二级分类
            List<TClassification> TowList = lambdaQuery()
                    .eq(TClassification::getParentId, one.getClassificationId())
                    .list();
            // 根据二级分类查询出所有的三级分类
            for (TClassification item : TowList) {
                classifications.add(item);
            }
            // 循环删除
            for (TClassification item : classifications) {
                int i = classificationMapper.deleteById(item.getClassificationId());
                if (i != 1) {
                    throw new DeleteException("删除数据时产生未知的异常，请于管理员联系");
                }

            }
        } else if (one.getLevel() == 3) {
            int i = classificationMapper.deleteById(one.getClassificationId());
            if (i != 1) {
                throw new DeleteException("删除数据时产生未知的异常，请于管理员联系");
            }
        }

    }


}




