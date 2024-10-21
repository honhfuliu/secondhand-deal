package com.ziheng.deal.db.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ziheng.deal.common.domain.DTO.CommodityDTO;
import com.ziheng.deal.common.domain.DTO.PageDOT;
import com.ziheng.deal.common.domain.DTO.commodityDisplayDTO;
import com.ziheng.deal.common.domain.DTO.commodityListDTO;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.domain.VO.CommodityVerifyVO;
import com.ziheng.deal.common.domain.VO.commodityPageQueryVO;
import com.ziheng.deal.common.domain.VO.commoditySearchPageVO;
import com.ziheng.deal.common.domain.query.PageQueryBase;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.*;
import com.ziheng.deal.common.domain.VO.CommodityVO;
import com.ziheng.deal.db.entity.CommodityPicture;
import com.ziheng.deal.db.entity.CommoditySku;
import com.ziheng.deal.db.entity.TClassification;
import com.ziheng.deal.db.entity.TCommodity;
import com.ziheng.deal.db.mapper.CommodityPictureMapper;
import com.ziheng.deal.db.mapper.CommoditySkuMapper;
import com.ziheng.deal.db.mapper.TClassificationMapper;
import com.ziheng.deal.db.service.CommoditySkuService;
import com.ziheng.deal.db.service.TClassificationService;
import com.ziheng.deal.db.service.TCommodityService;
import com.ziheng.deal.db.mapper.TCommodityMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
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

    // 商品sku Mapper
    @Resource
    private CommoditySkuMapper commoditySkuMapper;

    @Autowired
    private CommoditySkuService commoditySkuService;

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
        commodityVO.setCId(null);

        // 查询商品分类是否存在
        TClassification classification = classificationMapper.selectById(commodityVO.getClassifyId());
        if (classification == null) {
            throw new ClassificationInexistenceException("商品分类不存在");
        }


        // 将DTO中的对象copy到实体类中
        TCommodity commodity = new TCommodity();

        BeanUtil.copyProperties(commodityVO, commodity);


        // 商品所属用户id
        commodity.setUserId(userInfo.getUId());

        // sku规格设置
        HashMap<String, List<String>> stringListHashMap = new HashMap<>();
        for (HashMap<String, Object> map : commodityVO.getCommodityHeader()) {
//            System.out.println(map);
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                stringListHashMap.put(entry.getKey(), (List<String>) entry.getValue());
            }
        }

        commodity.setSkuHeader(JSONUtil.toJsonStr(stringListHashMap));

        // 补全日志
        commodity.setModifiedUser(userInfo.getUsername());
        commodity.setCreateUser(userInfo.username);
        commodity.setModifiedTime(new Date());
        commodity.setCreateTime(new Date());

        // 插入到数据库中
        int insert = commodityMapper.insert(commodity);
//        System.out.println("自动生成的id:" + commodity.getcId());

        if (insert != 1) {
            throw new InsertException("插入数据时产生未知的异常，请于管理员进行联系");
        }

        // 商品sku添加

        ArrayList<CommoditySku> commoditySkus = new ArrayList<>();

        for (HashMap<String, Object> map : commodityVO.getCommoditySku()) {
            CommoditySku commoditySku = new CommoditySku();
            commoditySku.setCommodityId(commodity.getcId()); //商品id
//            commoditySku.setPrice(new BigDecimal( Integer.parseInt ( map.get("price").toString() ))); // 价格
            commoditySku.setPrice(new BigDecimal(map.get("price").toString()));
            commoditySku.setCommodityNumber((Integer) map.get("stock")); // 库存
            commoditySku.setLowCommodityNumber((Integer) map.get("low_stock")); //预警库存
            map.remove("price");
            map.remove("stock");
            map.remove("low_stock");
            String jsonStr = JSONUtil.toJsonStr(map);
            commoditySku.setCommoditySku(jsonStr);

            commoditySkus.add(commoditySku);
        }


        // 插入数据
        boolean b = commoditySkuService.commoditySkuSave(commoditySkus);
        if (!b) {
            throw new InsertException();
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
                commodityPicture.setIsDisplayPicture(i + 1);
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
     * @return 分页数据
     */
    @Override
    public PageDOT<commodityListDTO> getByIdCommodityPageQuery(commodityPageQueryVO commodityPageQuery, Integer userId) {
        PageHelper.startPage(commodityPageQuery.getPageNo(), commodityPageQuery.getPageSize());
        List<commodityListDTO> lists = commodityMapper.getByIdPageCommodity(commodityPageQuery, userId);

        if (lists != null && lists.size() > 0) {
            // 根据用户的id查询商品sku的价格
            ArrayList<Integer> integers = new ArrayList<>();
            for (commodityListDTO list : lists) {
                integers.add(list.getId());
            }
            List<CommoditySku> commoditySkus = commoditySkuMapper.getCommoditySkuPrice(integers);

            for (commodityListDTO list : lists) {
                for (CommoditySku sku : commoditySkus) {
                    if (sku.getCommodityId().equals(list.getId())) {
                        list.setPrice(sku.getPrice());
                        list.setCNumber(sku.getCommodityNumber());
                    }
                }
            }
        }

        // 获取总条数
        PageInfo<Object> info = new PageInfo<>(lists);
//        System.out.println(info.getTotal());

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
        if (one == null || one.getcStatus() == 4) {
            throw new CommodityDoesNotExistException("商品不存在");
        }
        // 返回数据对象存储
        CommodityDTO dto = new CommodityDTO();

        //根据商品分类id查询出商品的父类

        TClassification tClassification = classificationMapper.selectById(one.getClassifyId());
        dto.setClassificationName(tClassification.getClassificationName());

        // 将商品数据拷贝对象到返回的DTO中
        BeanUtil.copyProperties(one, dto);

        // 商品sku规格
        dto.setCommoditySkuHeader(JSONUtil.toBean(one.getSkuHeader(), Map.class));

        // 商品sku信息
        LambdaQueryWrapper<CommoditySku> skuWrapper = new LambdaQueryWrapper<>();
        skuWrapper.eq(CommoditySku::getCommodityId, one.getcId());
        List<CommoditySku> commoditySkus = commoditySkuMapper.selectList(skuWrapper);
        ArrayList<Map<String, Object>> skuList = new ArrayList<>();
        for (CommoditySku sku : commoditySkus) {
//            HashMap<String, Object> skuMap = new HashMap<>();
            Map skuStrMap = JSONUtil.toBean(sku.getCommoditySku(), Map.class);
            skuStrMap.put("price", sku.getPrice());
            skuStrMap.put("stock", sku.getCommodityNumber());
            skuStrMap.put("low_stock", sku.getLowCommodityNumber());

            skuList.add(skuStrMap);
        }

        dto.setCommoditySkus(skuList);


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

    /**
     * 根据商品id修改商品
     * @param commodityVO 商品修改信息
     * @param userInfo 个人信息
     */
    @Override
    @Transactional
    public void getByCommodityIdUpdate(CommodityVO commodityVO, TokenInfo userInfo) {
        // 根据商品id和用户id查询出商品
        TCommodity one = lambdaQuery()
                .eq(commodityVO.getCId() != null, TCommodity::getcId, commodityVO.getCId())
                .eq(userInfo.getUId() != null, TCommodity::getUserId, userInfo.getUId())
                .one();
        if (one == null) {
            throw new CommodityDoesNotExistException("商品不存在");
        }

        // 判断该分类是否存在
        if (commodityVO.getClassifyId() != null) {
            TClassification tClassification = classificationMapper.selectById(commodityVO.getClassifyId());
            if (tClassification == null) {
                throw new ClassificationInexistenceException("分类不存在");
            }
        }

        // 创建新的 TCommodity将VO中的数据拷贝到新的 TCommodity对象中
        TCommodity updateInfo = new TCommodity();
        BeanUtil.copyProperties(commodityVO, updateInfo);

        // sku规格修改
        HashMap<String, List<String>> stringListHashMap = new HashMap<>();
        for (HashMap<String, Object> map : commodityVO.getCommodityHeader()) {
//            System.out.println(map);
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                stringListHashMap.put(entry.getKey(), (List<String>) entry.getValue());
            }
        }

        updateInfo.setSkuHeader(JSONUtil.toJsonStr(stringListHashMap));

        // 将商品状态设置为待审核
        updateInfo.setcStatus(0);

        // 补全信息
        updateInfo.setcId(one.getcId());
        updateInfo.setModifiedUser(userInfo.username);
        updateInfo.setModifiedTime(new Date());

        //进行更新
        int result = this.commodityMapper.updateById(updateInfo);
        if (result != 1) {
            throw new InsertException();
        }

        // sku信息修改
        LambdaQueryWrapper<CommoditySku> skuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        skuLambdaQueryWrapper.eq(CommoditySku::getCommodityId, one.getcId());

        //获取该商品的sku总条数
        Long skuCount = commoditySkuMapper.selectCount(skuLambdaQueryWrapper);
        // 删除该商品的sku数据
        int i1 = commoditySkuMapper.delete(skuLambdaQueryWrapper);
        if (skuCount != i1) {
            throw new DeleteException("删除数据失败请于管理员进行联系");
        }
        // 将新的数据添加到商品sku表中
        ArrayList<CommoditySku> commoditySkus = new ArrayList<>();

        for (HashMap<String, Object> map : commodityVO.getCommoditySku()) {
            CommoditySku commoditySku = new CommoditySku();
            commoditySku.setCommodityId(one.getcId()); //商品id
            commoditySku.setPrice(new BigDecimal( Integer.parseInt ( map.get("price").toString() ))); // 价格
            commoditySku.setCommodityNumber((Integer) map.get("stock")); // 库存
            commoditySku.setLowCommodityNumber((Integer) map.get("low_stock")); //预警库存
            map.remove("price");
            map.remove("stock");
            map.remove("low_stock");
            String jsonStr = JSONUtil.toJsonStr(map);
            commoditySku.setCommoditySku(jsonStr);

            commoditySkus.add(commoditySku);
        }
        // 插入数据
        boolean b = commoditySkuService.commoditySkuSave(commoditySkus);
        if (!b) {
            throw new InsertException();
        }


        // 对图片字段进行判断如果是null，那么就不需要修改图片。
        if (commodityVO.getCommodityPicturePaths() != null){
           // 根据商品外键删除这个商品的主图
            LambdaQueryWrapper<CommodityPicture> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(CommodityPicture::getCommodityId ,one.getcId());
            commodityPictureMapper.delete(wrapper);

            // 删除之后将新的图片添加到数据库中
            for (int i = 0; i < commodityVO.getCommodityPicturePaths().size(); i++)
            {
                CommodityPicture commodityPicture = new CommodityPicture();
                if (i == 0 )
                {
                    commodityPicture.setCommodityId(one.getcId());
                    commodityPicture.setpPath(commodityVO.getCommodityPicturePaths().get(i));
                    commodityPicture.setIsDisplayPicture(1);
                } else
                {
                    commodityPicture.setCommodityId(one.getcId());
                    commodityPicture.setpPath(commodityVO.getCommodityPicturePaths().get(i));
                    commodityPicture.setIsDisplayPicture(i + 1);
                }

                int insert1 = commodityPictureMapper.insert(commodityPicture);

                if (insert1 != 1) {

                    throw new InsertException("插入数据时产生未知的异常，请于管理员进行联系");
                }

            }
        }



    }

    /**
     * 根据商品id删除商品
     * @param commodityId 商品id
     */
    @Override
    @Transactional
    public void getByIdDeleteCommodity(Integer commodityId, Integer userId) {
        // 根据商品id和用户id查询出商品
        TCommodity one = lambdaQuery()
                .eq(commodityId != null, TCommodity::getcId, commodityId)
                .eq(userId != null, TCommodity::getUserId, userId)
                .one();
        if (one == null) {
            throw new CommodityDoesNotExistException("商品不存在");
        }
        /*
        // 删除商品主图
        LambdaQueryWrapper<CommodityPicture> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CommodityPicture::getCommodityId ,one.getcId());
        int i = commodityPictureMapper.delete(wrapper);

        // 删除商品的sku*/


        // 将商品的状态改为4已删除
        TCommodity commodity = new TCommodity();
        commodity.setcId(one.getcId());
        commodity.setcStatus(4);
        int i = commodityMapper.updateById(commodity);
        if (i != 1) {
            throw new DeleteException();
        }



    }

    /**
     * 商品下架
     * @param commodityId 商品id
     * @param userId 用户id
     */
    @Override
    public void removedFromShelvesCommodity(Integer commodityId, Integer userId) {
        if (commodityId == null){
            throw new StatusException();
        }
        // 根据商品id和用户id查询出下架的商品
        LambdaQueryWrapper<TCommodity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TCommodity::getcId, commodityId);
        wrapper.eq(TCommodity::getUserId, userId);
        TCommodity commodity = commodityMapper.selectOne(wrapper);
        if (commodity == null){
            throw new CommodityDoesNotExistException();
        }

        if (commodity.getcStatus() != 2){
            throw new StatusException();
        }

        TCommodity commodity1 = new TCommodity();
        commodity1.setcId(commodity.getcId());
        commodity1.setcStatus(3);
        commodity1.setModifiedTime(new Date());
        int i = commodityMapper.updateById(commodity1);
        if (i != 1){
            throw new UpdateException();
        }
    }

    /**
     * 上架商品
     * @param commodityId 上架商品的id
     * @param userId 用户id
     */
    @Override
    public void onshelvesCommodity(Integer commodityId, Integer userId) {
        if (commodityId == null){
            throw new StatusException();
        }
        // 根据商品id和用户id查询出下架的商品
        LambdaQueryWrapper<TCommodity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TCommodity::getcId, commodityId);
        wrapper.eq(TCommodity::getUserId, userId);
        TCommodity commodity = commodityMapper.selectOne(wrapper);
        if (commodity == null){
            throw new CommodityDoesNotExistException();
        }

        if (commodity.getcStatus() != 3){
            throw new StatusException();
        }

        TCommodity commodity1 = new TCommodity();
        commodity1.setcId(commodity.getcId());
        commodity1.setcStatus(0);
        commodity1.setModifiedTime(new Date());
        int i = commodityMapper.updateById(commodity1);
        if (i != 1){
            throw new UpdateException();
        }
    }

    /**
     * 随机获取40条数据用户首页展示商品
     * @return 商品列表
     */
    @Override
    public List<commodityDisplayDTO> getCommodity() {
        List<commodityDisplayDTO> commodity = commodityMapper.getCommodity();
        if (commodity != null && commodity.size() > 0) {
            // 根据用户的id查询商品sku的价格
            ArrayList<Integer> integers = new ArrayList<>();
            for (commodityDisplayDTO list : commodity) {
                integers.add(list.getId());
            }
            List<CommoditySku> commoditySkus = commoditySkuMapper.getCommoditySkuPrice(integers);

            for (commodityDisplayDTO list : commodity) {
                for (CommoditySku sku : commoditySkus) {
                    if (sku.getCommodityId().equals(list.getId())) {
                        list.setCPrice(sku.getPrice());
                    }
                }
            }
        }


        return commodity;
    }

    /**
     * 根据id查询商品详情信息
     * @param commodityId 商品id
     * @return 商品详情
     */
    @Override
    public CommodityDTO getByIdDetailsInfo(Integer commodityId) {
        // 根据商品id查询出商品
        TCommodity one = lambdaQuery()
                .eq(commodityId != null, TCommodity::getcId, commodityId)
                .one();

        if (one == null || one.getcStatus() == 4) {
            throw new CommodityDoesNotExistException("商品不存在");
        }

        // 根据商品id获取到商品的主图
        LambdaQueryWrapper<CommodityPicture> wrapperCommodityPicture = new LambdaQueryWrapper<>();
        wrapperCommodityPicture.select(CommodityPicture::getpPath);
        wrapperCommodityPicture.eq(CommodityPicture::getCommodityId, commodityId);

        List<CommodityPicture> selectList = commodityPictureMapper.selectList(wrapperCommodityPicture);

        // 提取路径字段并存储到单独的 List 中
        List<String> pathList = selectList.stream()
                .map(CommodityPicture::getpPath)
                .collect(Collectors.toList());


        // 拷贝为dto对象
        CommodityDTO dto = new CommodityDTO();
        BeanUtil.copyProperties(one, dto);

        dto.setCommoditySkuHeader(JSONUtil.toBean(one.getSkuHeader(), HashMap.class));

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(one.getcId());
        List<CommoditySku> commoditySkus = commoditySkuMapper.getCommoditySkuPrice(integers);

        dto.setCPrice(commoditySkus.get(0).getPrice());

        // 获取到商品的图片
        dto.setCommodityPicturePaths((ArrayList<String>) pathList);


        return dto;
    }

    /**
     * 商品搜素实现
     *
     * @param commoditySearch 搜素数据
     * @return 分页搜素数据
     */
    @Override
    public PageDOT<commodityDisplayDTO> searchCommodity(commoditySearchPageVO commoditySearch) {
        // 根据搜索名称查询是否有这个分类
        LambdaQueryWrapper<TClassification> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(TClassification::getClassificationId);
        queryWrapper.eq(TClassification::getClassificationName, commoditySearch.getSearchName());

        TClassification resultClassify = classificationMapper.selectOne(queryWrapper);

        // 查询搜素数据
        if (commoditySearch.getSortBy() != null){
            PageHelper.orderBy("buy_number desc");
        }
        PageHelper.startPage(commoditySearch.getPageNo(), commoditySearch.getPageSize());


        ArrayList<commodityDisplayDTO> dtoList ;
        if (resultClassify == null) {
            dtoList = (ArrayList<commodityDisplayDTO>) commodityMapper.searchCommodity(commoditySearch, null);
        } else {
            dtoList = (ArrayList<commodityDisplayDTO>) commodityMapper.searchCommodity(commoditySearch, resultClassify.getClassificationId());
        }

        // 根据用户的id查询商品sku的价格
        ArrayList<Integer> integers = new ArrayList<>();
        for (commodityDisplayDTO list : dtoList) {
            integers.add(list.getId());
        }
        List<CommoditySku> commoditySkus = commoditySkuMapper.getCommoditySkuPrice(integers);

        for (commodityDisplayDTO list : dtoList) {
            for (CommoditySku sku : commoditySkus) {
                if (sku.getCommodityId().equals(list.getId())) {
                    list.setCPrice(sku.getPrice());
                }
            }
        }

        // 封装返回结果
        PageDOT<commodityDisplayDTO> dot = new PageDOT<>();
        dot.setList(dtoList);

        // 获取总条数
        PageInfo<commodityDisplayDTO> info = new PageInfo<>(dtoList);
        dot.setTotal((int) info.getTotal());


        return dot;
    }



    /*管理员相关接口*/

    /**
     * 获取所有未审核的商品
     * @param pageQuery 分页条件
     * @return 分页商品数据
     */
    @Override
    public PageDOT<commodityListDTO> queryGetPaginationCommodity(PageQueryBase pageQuery) {
        // 进行分页
        PageHelper.startPage(pageQuery.getPageNo(), pageQuery.getPageSize());

        // 获取分页数据
        List<commodityListDTO> pages = commodityMapper.getPaginationCommodity();

        // 根据用户的id查询商品sku的价格
        if (pages != null && pages.size() > 0) {
            ArrayList<Integer> integers = new ArrayList<>();
            for (commodityListDTO list : pages) {
                integers.add(list.getId());
            }
            List<CommoditySku> commoditySkus = commoditySkuMapper.getCommoditySkuPrice(integers);

            for (commodityListDTO list : pages) {
                for (CommoditySku sku : commoditySkus) {
                    if (sku.getCommodityId().equals(list.getId())) {
                        list.setPrice(sku.getPrice());
                        list.setCNumber(sku.getCommodityNumber());
                    }
                }
            }
        }

        // 获取总条数
        PageInfo<Object> info = new PageInfo<>(pages);

        //封装返回数据
        PageDOT<commodityListDTO> dot = new PageDOT<>();
        dot.setList(pages);
        dot.setTotal((int) info.getTotal());



        return dot;
    }

    /**
     * 根据商品的id获取审核商品的详情信息
     * @param commodityId
     * @return
     */
    @Override
    public CommodityDTO getByIdDetailsCommodity(Integer commodityId) {
        // 根据商品id查询出商品
        TCommodity one = lambdaQuery()
                .eq(commodityId != null, TCommodity::getcId, commodityId)
                .eq(TCommodity::getcStatus, 0)
                .one();

        if (one == null) {
            throw new CommodityDoesNotExistException("商品不存在");
        }

        // 根据商品id获取到商品的主图
        LambdaQueryWrapper<CommodityPicture> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.select(CommodityPicture::getpPath);
        wrapper1.eq(CommodityPicture::getCommodityId, commodityId);

        List<CommodityPicture> selectList = commodityPictureMapper.selectList(wrapper1);

        // 提取路径字段并存储到单独的 List 中
        List<String> pathList = selectList.stream()
                .map(CommodityPicture::getpPath)
                .collect(Collectors.toList());

//        System.out.println(pathList);

        // 拷贝为dto对象
        CommodityDTO dto = new CommodityDTO();
        BeanUtil.copyProperties(one, dto);
//        System.out.println(dto);

        // 获取到商品的图片
        dto.setCommodityPicturePaths((ArrayList<String>) pathList);

        // 商品sku信息
        dto.setCommoditySkuHeader(JSONUtil.toBean(one.getSkuHeader(), HashMap.class));

        // 获取商品的价格
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(one.getcId());
        List<CommoditySku> commoditySkus = commoditySkuMapper.getCommoditySkuPrice(integers);

        dto.setCPrice(commoditySkus.get(0).getPrice());

        return dto;
    }

    @Override
    public void getByIdVerifyCommoditySbumit(CommodityVerifyVO commodityVerifyVO, String updateName) {
        // 判断状态
        if (!(commodityVerifyVO.getStatus() == 1 || commodityVerifyVO.getStatus() == 2)){
            throw new StatusException("商品状态错误");
        }

        // 根据商品id查询出商品
        TCommodity one = lambdaQuery()
                .eq(commodityVerifyVO.getCId() != null, TCommodity::getcId, commodityVerifyVO.getCId())
                .eq(TCommodity::getcStatus, 0)
                .one();

        if (one == null) {
            throw new CommodityDoesNotExistException("商品不存在");
        }

        TCommodity tCommodity = new TCommodity();
        tCommodity.setcId(commodityVerifyVO.getCId());
        tCommodity.setcStatus(commodityVerifyVO.getStatus());
        tCommodity.setRejectReason(commodityVerifyVO.getRejectReason());
        tCommodity.setModifiedUser(updateName);
        tCommodity.setModifiedTime(new Date());
        int i = this.commodityMapper.updateById(tCommodity);
        if (i != 1) {
            throw new UpdateException("修改数据产生未知的异常，请于管理员联系");
        }

    }


}




