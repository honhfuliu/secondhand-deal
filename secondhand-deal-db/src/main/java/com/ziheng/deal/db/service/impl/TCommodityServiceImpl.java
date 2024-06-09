package com.ziheng.deal.db.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ziheng.deal.common.domain.DTO.CommodityDTO;
import com.ziheng.deal.common.domain.DTO.PageDOT;
import com.ziheng.deal.common.domain.DTO.commodityListDTO;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.domain.VO.commodityPageQueryVO;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.CommodityDoesNotExistException;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.InsertException;
import com.ziheng.deal.common.domain.VO.CommodityVO;
import com.ziheng.deal.db.entity.CommodityPicture;
import com.ziheng.deal.db.entity.TClassification;
import com.ziheng.deal.db.entity.TCommodity;
import com.ziheng.deal.db.mapper.CommodityPictureMapper;
import com.ziheng.deal.db.mapper.TClassificationMapper;
import com.ziheng.deal.db.service.TClassificationService;
import com.ziheng.deal.db.service.TCommodityService;
import com.ziheng.deal.db.mapper.TCommodityMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Administrator
* @description 针对表【t_commodity(商品表)】的数据库操作Service实现
* @createDate 2024-05-27 22:18:22
*/
@Service
public class TCommodityServiceImpl extends ServiceImpl<TCommodityMapper, TCommodity>
    implements TCommodityService{

    @Resource
    private CommodityPictureMapper commodityPictureMapper;

    @Resource
    private TClassificationMapper classificationMapper;

    @Resource
    private TCommodityMapper commodityMapper;

    @Resource
    private TClassificationService classificationService;


    /**
     * 商品信息保存
     * @param commodityVO 商品信息
     * @param userInfo 操作人信息
     */
    @Override
    @Transactional
    public void CommodityAdd(CommodityVO commodityVO, TokenInfo userInfo) {
        System.out.println(commodityVO);

        // 将DTO中的对象copy到实体类中
        TCommodity commodity = new TCommodity();

        BeanUtil.copyProperties(commodityVO, commodity);
        System.out.println(commodity);

        // 商品所属用户id
        commodity.setUserId(userInfo.getUId());

        // 补全日志
        commodity.setModifiedUser(userInfo.getUsername());
        commodity.setCreateUser(userInfo.username);
        commodity.setModifiedTime(new Date());
        commodity.setCreateTime(new Date());

        // 插入到数据库中
        int insert = commodityMapper.insert(commodity);

        if (insert != 1) {
            throw new InsertException("插入数据时产生未知的异常，请于管理员进行联系");
        }


        // 根据id将商品的详情图插入到数据库中
        ArrayList<String> paths = commodityVO.getCommodityPicturePaths();
        for (int i = 0; i < paths.size(); i++)
        {
            CommodityPicture commodityPicture = new CommodityPicture();
            if (i == 0 )
            {
                commodityPicture.setCommodityId(commodity.getcId());
                commodityPicture.setpPath(paths.get(i));
                commodityPicture.setIsDisplayPicture(1);
            } else
            {
                commodityPicture.setCommodityId(commodity.getcId());
                commodityPicture.setpPath(paths.get(i));
                commodityPicture.setIsDisplayPicture(0);
            }

            int insert1 = commodityPictureMapper.insert(commodityPicture);

            if (insert1 != 1) {

                throw new InsertException("插入数据时产生未知的异常，请于管理员进行联系");
            }

        }


    }

    /**
     * 根据用户条件查询商品信息并分页
     * @param commodityPageQuery 查询条件
     * @param userId 用户id
     * @return
     */
    @Override
    public PageDOT<commodityListDTO> getByIdCommodityPageQuery(commodityPageQueryVO commodityPageQuery, Integer userId) {
        PageHelper.startPage(commodityPageQuery.getPageNo(), commodityPageQuery.getPageSize());
        List<commodityListDTO> lists = commodityMapper.getByIdPageCommodity(commodityPageQuery, userId);

        // 获取总条数
        PageInfo<Object> info = new PageInfo<>(lists);
        System.out.println(info.getTotal());

        // 封装成返回DTO对象
        PageDOT<commodityListDTO> dot = new PageDOT<>();
        dot.setTotal((int) info.getTotal());

        dot.setList(lists);

        return dot;
    }

    /**
     * 根据商品id查询商品信息
     * @param commodityId 商品id
     * @return 商品信息
     */
    @Override
    public CommodityDTO getByIdCommodity(Integer commodityId) {
        // 根据商品id查询出数据
        TCommodity one = lambdaQuery()
                .eq(commodityId != null, TCommodity::getcId, commodityId)
                .one();
        // 判断商品是否存在
        if (one == null) {
            throw new CommodityDoesNotExistException("商品不存在");
        }
        // 返回数据对象存储
        CommodityDTO dto = new CommodityDTO();

        //根据商品分类id查询出商品的父类

        TClassification tClassification = classificationMapper.selectById(one.getClassifyId());
        dto.setClassificationName(tClassification.getClassificationName());

        // 将商品数据拷贝对象到返回的DTO中
        BeanUtil.copyProperties(one, dto);

        // 根据商品id查询出商品主图
        LambdaQueryWrapper<CommodityPicture> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(CommodityPicture::getpPath);
        wrapper.eq(CommodityPicture::getCommodityId, commodityId);

        List<CommodityPicture> selectList = commodityPictureMapper.selectList(wrapper);

        // 提取路径字段并存储到单独的 List 中
        List<String> pathList = selectList.stream()
                .map(CommodityPicture::getpPath)
                .collect(Collectors.toList());

        dto.setCommodityPicturePaths((ArrayList<String>) pathList);

        // 返回商品数据
        return dto;
    }
}




