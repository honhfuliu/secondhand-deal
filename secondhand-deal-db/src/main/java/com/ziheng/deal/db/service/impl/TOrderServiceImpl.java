package com.ziheng.deal.db.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ziheng.deal.common.domain.DTO.MyOrderDTO;
import com.ziheng.deal.common.domain.DTO.MyOrderItem;
import com.ziheng.deal.common.domain.DTO.PageDOT;
import com.ziheng.deal.common.domain.DTO.SettlementCommodityDTO;
import com.ziheng.deal.common.domain.VO.CommodityOrderVO;
import com.ziheng.deal.common.domain.VO.CommoditySettlementVO;
import com.ziheng.deal.common.domain.VO.MyOrderVO;
import com.ziheng.deal.common.domain.VO.ShoppingCartCommoditySettlementVO;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.*;
import com.ziheng.deal.db.entity.*;
import com.ziheng.deal.db.mapper.*;
import com.ziheng.deal.db.service.TOrderService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
* @author Administrator
* @description 针对表【t_order(订单表)】的数据库操作Service实现
* @createDate 2024-08-04 20:25:57
*/
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder>
    implements TOrderService{

    @Resource
    private  TOrderMapper orderMapper;

    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    @Resource
    private TCommodityMapper commodityMapper;

    @Resource
    private CommodityPictureMapper commodityPictureMapper;

    @Resource
    private TAddressMapper addressMapper;

    @Resource
    private TUserMapper userMapper;

    @Resource
    private CommoditySkuMapper commoditySkuMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 分页条件查询我的订单
     * @param orderVO 查询条件
     * @return
     */
    @Override
    public PageDOT<List<MyOrderDTO>> showMyOrder(MyOrderVO orderVO, Integer userId) {


        // 分页查询出所有订单记录
        Page<TOrder> page = Page.of(orderVO.getPageNo(), orderVO.getPageSize());
//        Page<TOrder> page = Page.of(orderVO.getPageNo(), orderVO.getPageSize());

        LambdaQueryWrapper<TOrder> orderWrapper = new LambdaQueryWrapper<>();
        orderWrapper.eq(TOrder::getBuyerId, userId);
        orderWrapper.eq(TOrder::getSort, 1); // 订单排序必须为1
        // 根据订单号查询
        if (orderVO.getOrderNumber() != null){
            orderWrapper.eq(TOrder::getOrderNumber, orderVO.getOrderNumber());
        }
        // 根据时间范围查询
        if (orderVO.getStartTime() != null && orderVO.getEndTime() != null){
            orderWrapper.ge(TOrder::getCreateTime, orderVO.getStartTime())
                        .le(TOrder::getCreateTime, orderVO.getEndTime());
        }
        // 根据状态查询
        if (orderVO.getOrderStatus() != null){
            orderWrapper.eq(TOrder::getOrderStatus, orderVO.getOrderStatus());
        }

        Page<TOrder> orderPage = orderMapper.selectPage(page, orderWrapper);

        PageDOT<List<MyOrderDTO>> resultDTO = new PageDOT<>();
        resultDTO.setTotal((int) orderPage.getTotal());  // 总条数
        resultDTO.setPage((int) orderPage.getPages()); // 页数


        List<TOrder> records = orderPage.getRecords();
        ArrayList<MyOrderDTO> myOrderDTOS = new ArrayList<>();
        for (TOrder record : records) {
            MyOrderDTO myOrderDTO = new MyOrderDTO();
            myOrderDTO.setOrderNumber(record.getOrderNumber());
            myOrderDTO.setCreateTime(record.getCreateTime());
            // 根据订单号查询出这个订单号中的商品
            LambdaQueryWrapper<TOrder> orderWrapper1 = new LambdaQueryWrapper<>();
            orderWrapper1.eq(TOrder::getOrderNumber, record.getOrderNumber());
            List<TOrder> orderList = orderMapper.selectList(orderWrapper1);

            ArrayList<MyOrderItem> myOrderItems = new ArrayList<>();
            for (TOrder order : orderList) {
                MyOrderItem orderItem = new MyOrderItem();
                orderItem.setCommodityId(order.getCommodityId());
                orderItem.setCommodityNumber(order.getCommodityNumber());
                orderItem.setCommodityUnitPrice(order.getCommodityUnitPrice());
                orderItem.setOrderPrice(order.getOrderPrice());
                orderItem.setDeliveryFee(order.getDeliveryFee());
                orderItem.setDiscountedPrice(order.getDiscountedPrice());
                // 获取到商品的标题
                TCommodity commodity = commodityMapper.selectById(order.getCommodityId());
                orderItem.setCommodityTitle(commodity.getCommodityTitle());
                // 获取到该商品的主图
                orderItem.setCommodityPath(InquireCommodityMainPicture(order.getCommodityId()));
                // 获取到商品的sku
                LambdaQueryWrapper<CommoditySku> skuWrapper = new LambdaQueryWrapper<>();
                skuWrapper.eq(CommoditySku::getSkuId, order.getSkuId());
                skuWrapper.eq(CommoditySku::getCommodityId, commodity.getcId());
                CommoditySku commoditySku = commoditySkuMapper.selectOne(skuWrapper);
                Map map = JSONUtil.toBean(commoditySku.getCommoditySku(), Map.class);
                orderItem.setCommoditySku(map);
                myOrderItems.add(orderItem);
            }
            myOrderDTO.setMyOrderItemList(myOrderItems);
            myOrderDTOS.add(myOrderDTO);
        }
        resultDTO.setList(Collections.singletonList(myOrderDTOS));



        return resultDTO;
    }

    /**
     * 过期订单处理
     * @param orderNumber 订单好
     */
    @Override
    @Transactional
    public void ExpiredCommodityOrderSolve(String orderNumber) {
        // 根据订单号查询出订单
        LambdaQueryWrapper<TOrder> orderWrapper = new LambdaQueryWrapper<>();
        orderWrapper.eq(TOrder::getOrderNumber, orderNumber);
        List<TOrder> orderList = orderMapper.selectList(orderWrapper);

        // 将订单状态修改为已取消
        for (TOrder order : orderList) {
            TOrder order1 = new TOrder();
            order1.setOrderId(order.getOrderId());
            order1.setOrderEndTime(new Date());
            order1.setOrderStatus("7");
            order.setUpdateTime(new Date());

            int update = orderMapper.updateById(order1);
            if (update != 1) {
                throw new UpdateException();
            }
        }

        // 将商品的sku库存增加
        for (TOrder order : orderList) {
            CommoditySku commoditySku = commoditySkuMapper.selectById(order.getSkuId());
            CommoditySku updateCommoditySku = new CommoditySku();
            updateCommoditySku.setSkuId(commoditySku.getSkuId());
            updateCommoditySku.setCommodityNumber(commoditySku.getCommodityNumber() + order.getCommodityNumber());
            int result = commoditySkuMapper.updateById(updateCommoditySku);
            if (result != 1) {
                throw new UpdateException();
            }
        }

        // 用户积分增加
        for (TOrder order : orderList) {
            if (order.getWhetherIntegral().equals("1")){
                TUser user = userMapper.selectById(order.getBuyerId());

                TUser updateUser = new TUser();
                updateUser.setuId(user.getuId());

                BigDecimal decimal = new BigDecimal(1000);
                BigDecimal discountedPrice = order.getDiscountedPrice();
                discountedPrice = discountedPrice.multiply(decimal);
                updateUser.setScore(user.getScore() +  discountedPrice.intValue());
                updateUser.setModifiedTime(new Date());
                int result = userMapper.updateById(updateUser);
                if (result != 1) {
                    throw new UpdateException();
                }
            }
        }

    }

    /**
     * 商品订单支付
     * @param orderNumber 订单号
     * @param userId 用户id
     * @param updateUser 用户名
     */
    @Override
    @Transactional
    public void CommodityOrderPay(String orderNumber, Integer userId, String updateUser) {
        // 判断订单号不能为空
        if (orderNumber == null) {
            // 抛出异常订单号不能为空
            throw new MissingOrderNumberException("订单号不能为空");
        }

        // 根据订单号和用户id查询出订单信息
        LambdaQueryWrapper<TOrder> orderWrapper = new LambdaQueryWrapper<>();
        orderWrapper.eq(TOrder:: getOrderNumber, orderNumber);
        orderWrapper.eq(TOrder::getBuyerId, userId);
        orderWrapper.eq(TOrder::getOrderStatus, "1");

        List<TOrder> resultOrderList = orderMapper.selectList(orderWrapper);
        if (resultOrderList.size() == 0){
            // 抛出异常订单不存在
            throw  new OrderNotFoundException("订单不存在");
        }

        // 将订单状态改为已支付
        Date date = new Date(); //获取到当前时间
        for (TOrder order : resultOrderList) {
            TOrder updateOrder = new TOrder();
            updateOrder.setOrderId(order.getOrderId());
            updateOrder.setOrderStatus("2"); // 将订单状态修改为已付款
            updateOrder.setUpdateUser(updateUser); // 修改人
            updateOrder.setUpdateTime(date); //修改时间
            updateOrder.setPaymentTime(date); // 付款时间
            
            // 跟新数据
            int updateResult = orderMapper.updateById(updateOrder);

            if (updateResult != 1) {
                // 抛出异常
                throw new UpdateException();
            }

        }

        // 增加商品的销量
        for (TOrder order : resultOrderList) {
            LambdaQueryWrapper<TCommodity> commodityWrapper = new LambdaQueryWrapper<>();
            commodityWrapper.eq(TCommodity::getcId, order.getCommodityId());
            TCommodity resultCommodity = commodityMapper.selectOne(commodityWrapper);

            // 增加销量
            TCommodity updateCommodity = new TCommodity();
            updateCommodity.setcId(resultCommodity.getcId());
            updateCommodity.setBuyNumber(resultCommodity.getBuyNumber() + order.getCommodityNumber());
            updateCommodity.setModifiedTime(new Date());
            int i = commodityMapper.updateById(updateCommodity);
            if (i != 1){
                throw  new UpdateException();
            }
        }

        // 减少购物车中的数量
        for (TOrder order : resultOrderList) {
            // 根据用户id和购买商品id和skuid查询这个商品是否是购物车中的
            LambdaQueryWrapper<ShoppingCart> shoppingCartWrapper = new LambdaQueryWrapper<>();
            shoppingCartWrapper.eq(ShoppingCart::getUserId, userId);
            shoppingCartWrapper.eq(ShoppingCart::getCommodityId, order.getCommodityId());
            shoppingCartWrapper.eq(ShoppingCart::getSkuId, order.getSkuId());
            ShoppingCart result = shoppingCartMapper.selectOne(shoppingCartWrapper);
            if (result != null){
                ShoppingCart updateCart = new ShoppingCart();
                updateCart.setShoppingId(result.getShoppingId());
                if (order.getCommodityNumber().compareTo(result.getShoppingNumber()) > 0
                    || order.getCommodityNumber().compareTo(result.getShoppingNumber()) == 0){
                     updateCart.setShoppingStatus(1);
                } else {
                    updateCart.setShoppingNumber(result.getShoppingNumber() - order.getCommodityNumber());
                }
                int updateResult = shoppingCartMapper.updateById(updateCart);
                if (updateResult != 1) {
                    throw new UpdateException();
                }
            }
        }


        // 删除redisKey
        redisTemplate.delete(orderNumber);

    }

    /**
     * 商品订单创建
     * @param commodityOrderVO 订单信息
     * @param userId 用户id
     * @param createUser 用户名
     * @return 订单号与总金额
     */
    @Override
    @Transactional
    public HashMap<String, String> CommodityOrderCreate(CommodityOrderVO commodityOrderVO, Integer userId, String createUser) {
        // 查询商品信息是否存在，并判断是不是自己的商品
        List<CommoditySettlementVO> commodityInfos = commodityOrderVO.getCommodityInfos();

        ArrayList<TCommodity> commodities = new ArrayList<>();
        for (int i = 0; i < commodityInfos.size(); i++) {
            LambdaQueryWrapper<TCommodity> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(TCommodity::getcId, commodityInfos.get(i).getCommodityId());
            TCommodity resultCommodity = commodityMapper.selectOne(wrapper);
            if (resultCommodity == null) {
                // 抛出异常商品不存在
                throw new CommodityDoesNotExistException("商品不存在");
            }

            if (resultCommodity.getUserId().equals(userId)) {
                // 抛出异常 不能购买自己的商品
                throw  new SelfPurchaseProhibitedException("禁止购买自己的商品");
            }

            if (resultCommodity.getcStatus() !=2 ) {
                // 抛出异常 商品状态错误
                throw  new StatusException();
            }

            commodities.add(resultCommodity);
        }


        // 查询商品sku,并判断库存
        ArrayList<CommoditySku> commoditySkusList = new ArrayList<>();
        for (int i = 0; i < commodities.size(); i++) {
            LambdaQueryWrapper<CommoditySku> skuLambdaQueryWrapper = new LambdaQueryWrapper<>();
            skuLambdaQueryWrapper.eq(CommoditySku::getCommodityId, commodities.get(i).getcId());
            List<CommoditySku> commoditySkus = commoditySkuMapper.selectList(skuLambdaQueryWrapper);
            CommoditySku commoditySku = null;
            String buyCommoditySkuStr = JSONUtil.toJsonStr(commodityOrderVO.getCommodityInfos().get(i).getCommoditySku());
            for (CommoditySku sku : commoditySkus) {
                if (sku.getCommoditySku().equals(buyCommoditySkuStr)){
                    commoditySku = sku;
                    // 库存判断
                    if (commodityOrderVO.getCommodityInfos().get(i).getBuyQuantity().compareTo(sku.getCommodityNumber())  > 0){
                        // 抛出异常 库存不足
                        throw new InventoryShortageException();
                    }
                    break;
                }
            }
            commoditySkusList.add(commoditySku);
        }

        if (commoditySkusList.size() != commodityOrderVO.getCommodityInfos().size()){
            throw new CommoditySkuException();
        }

        // 收货地址查询
        LambdaQueryWrapper<TAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TAddress::getUserId, userId);
        wrapper.eq(TAddress::getAddressId, commodityOrderVO.getAddressId());
        TAddress address = addressMapper.selectOne(wrapper);
        if (address == null) {
            // 抛出异常收货地址不存在
            throw new DeliveryAddressDoesNotExistException();
        }

        // 查询积分是否足够
        TUser user = userMapper.selectById(userId);
        if (commodityOrderVO.isWhetherIntegral()){
            if (commodityOrderVO.getIntegral() < user.getScore()){
                //抛出异常，积分不足
                throw new IntegralNotSufficientException();
            }
        }


        //扣减库存
        for (int i = 0; i < commodityInfos.size(); i++) {
            CommoditySku updateSku = new CommoditySku();
            updateSku.setSkuId(commoditySkusList.get(i).getSkuId());
            updateSku.setCommodityNumber(commoditySkusList.get(i).getCommodityNumber() - commodityInfos.get(i).getBuyQuantity());
            int result = commoditySkuMapper.updateById(updateSku);
            if (result != 1) {
                throw new UpdateException();
            }
        }



        // 扣减用户积分
        if (commodityOrderVO.isWhetherIntegral()){
            TUser updateUser = new TUser();
            updateUser.setuId(userId);
            updateUser.setScore(user.getScore() - commodityOrderVO.getIntegral());
            updateUser.setModifiedTime(new Date());
            int result = userMapper.updateById(updateUser);
            if (result != 1) {
                throw new UpdateException();
            }
        }

        // 将商品创建订单，并返回
        List<TOrder> orderList = new ArrayList<>();
        // 总金额存储
        BigDecimal totalAmount = new BigDecimal(0.00);
        // 订单号生成
        String orderNumber = getOrderNumber();
        for (int i = 0; i < commodityInfos.size(); i++) {
            TOrder order = new TOrder();
            order.setSort(i + 1);
            order.setOrderNumber(orderNumber); //订单编号
            order.setSellerId(commodities.get(i).getUserId()); // 卖家id
            order.setBuyerId(userId); //买家id
            order.setCommodityId(commodities.get(i).getcId()); // 商品id
            order.setSkuId(commoditySkusList.get(i).getSkuId()); // skuId
            order.setAddressId(commodityOrderVO.getAddressId()); // 收货地址
            order.setCommodityNumber(commodityInfos.get(i).getBuyQuantity()); // 购物数量
            order.setCommodityUnitPrice(commoditySkusList.get(i).getPrice()); // 商品价格
            order.setDeliveryMethod(commodities.get(i).getDeliveryMethod()); // 配送方式
            order.setDeliveryFee(commodities.get(i).getShippingFees()); // 配送费用
            BigDecimal totalPrice = null;
            // 判断是否使用积分优惠
            if (commodityOrderVO.isWhetherIntegral() && commodityOrderVO.getUseIntegralId().equals(commodities.get(i).getcId())){
                BigDecimal reliefPrice = BigDecimal.valueOf(commodityOrderVO.getIntegral()).divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP); // 优惠积分价格，使用 BigDecimal 进行除法
                order.setWhetherIntegral("1"); // 使用积分优惠
                order.setDiscountedPrice(reliefPrice); // 优惠金额
                totalPrice = commoditySkusList.get(i).getPrice().multiply(BigDecimal.valueOf(commodityInfos.get(i).getBuyQuantity()));
                totalPrice = totalPrice.subtract(reliefPrice);
            } else {
                order.setWhetherIntegral("0"); // 未使用积分优惠
                totalPrice = commoditySkusList.get(i).getPrice().multiply(BigDecimal.valueOf(commodityInfos.get(i).getBuyQuantity()));
            }

            totalPrice = totalPrice.add(commodities.get(i).getShippingFees()); // 加上配送费用

            totalAmount = totalAmount.add(totalPrice); // 总金额

            order.setOrderPrice(totalPrice.setScale(2, RoundingMode.HALF_UP)); //设置总价保留两位小数
            order.setOrderStatus("1"); //待付款
            order.setOrderStartTime(new Date()); // 订单创建时间
            order.setCreateUser(createUser); // 创建人
            order.setCreateTime(new Date()); // 创建时间
            order.setUpdateTime(new Date()); // 更新时间
            
            orderList.add(order);
        }
        
        // 将数据存储到数据库中
        boolean saveOutcome = saveBatch(orderList);
        if (!saveOutcome){
            throw  new InsertException();
        }

        // 订单过期实现
        // 获取当前时间
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        // 添加15分钟
        calendar.add(Calendar.MINUTE, 15);

        // 获取新的时间
        Date newTime = calendar.getTime();

        // 将 Date 转换为 LocalDateTime
        Instant instant = newTime.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        // 格式化输出
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = localDateTime.format(formatter);

        // 将数据返回到付款页面
        HashMap<String, String> resultMap = new HashMap<>();
        resultMap.put("orderNumber", orderNumber);
        resultMap.put("Amount", String.valueOf(totalAmount));
        resultMap.put("overdueTime", formattedTime);

        // 将订单号存储到redis中
        redisTemplate.opsForValue().set(orderNumber,  orderNumber, 900, TimeUnit.SECONDS);

        return resultMap;
    }

    /**
     * 商品详情页订单结算
     * @param commoditySettlement 用户订单数据
     * @param userId 用户id
     * @param createUser 用户名称
     * @return 订单信息
     */
    @Override
    public List<SettlementCommodityDTO> CommoditySettlement(CommoditySettlementVO commoditySettlement, Integer userId, String createUser) {
        // 查询商品是否存在，并判断是不是自己的商品
        LambdaQueryWrapper<TCommodity> commodityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        commodityLambdaQueryWrapper.eq(TCommodity::getcId, commoditySettlement.getCommodityId());
        TCommodity resultCommodity = commodityMapper.selectOne(commodityLambdaQueryWrapper);
        if (resultCommodity == null) {
            // 抛出异常商品不存在
            throw new CommodityDoesNotExistException("商品不存在");
        }

        if (resultCommodity.getUserId().equals(userId)) {
            // 抛出异常 不能购买自己的商品
            throw  new SelfPurchaseProhibitedException("禁止购买自己的商品");
        }

        if (resultCommodity.getcStatus() !=2 ) {
            // 抛出异常 商品状态错误
            throw  new StatusException();
        }

        // 根据商品的id查询出商品的sku
        CommoditySku commoditySku = null;
        LambdaQueryWrapper<CommoditySku> skuWrapper = new LambdaQueryWrapper<>();
        skuWrapper.eq(CommoditySku::getCommodityId, resultCommodity.getcId());
        List<CommoditySku> skuList = commoditySkuMapper.selectList(skuWrapper);
        String commoditySkuStr = JSONUtil.toJsonStr(commoditySettlement.getCommoditySku());

        for (CommoditySku sku : skuList) {
            if (sku.getCommoditySku().equals(commoditySkuStr)) {
                commoditySku = sku;
                break;
            }
        }

        if (commoditySku == null) {
            throw new CommoditySkuException();
        }

        // 判断库存是否足够
        if (commoditySettlement.getBuyQuantity().compareTo(commoditySku.getCommodityNumber())  > 0){
            // 抛出异常 库存不足
            throw new InventoryShortageException();
        }




        List<SettlementCommodityDTO> dtos = new ArrayList<>();
        // 获取返回的数据
        SettlementCommodityDTO settlement = new SettlementCommodityDTO();
        settlement.setCommodityId(resultCommodity.getcId());
        settlement.setTitle(resultCommodity.getCommodityTitle());
        settlement.setDeliveryMethod(resultCommodity.getDeliveryMethod());
        settlement.setDeliveryFee(resultCommodity.getShippingFees());
        settlement.setUnitPrice(commoditySku.getPrice());
        settlement.setBuyQuantity(commoditySettlement.getBuyQuantity());
        settlement.setCommoditySku(JSONUtil.toBean(commoditySku.getCommoditySku(), HashMap.class));
        // 获取商品主图
        settlement.setPath(InquireCommodityMainPicture(resultCommodity.getcId()));
        dtos.add(settlement);



        return dtos;
    }

    /**
     * 购物商品结算处理
     * @param commoditySettlements 结算商品信息
     * @param userId 购买用户信息
     */
    @Override
    @Transactional
    public List<SettlementCommodityDTO> ShoppingCartCommoditySettlement(List<ShoppingCartCommoditySettlementVO> commoditySettlements, Integer userId, String createUser) {
        // 根据 商品id和购物车id和用户查询该商品是否存在于购物车中和商品sku规格是否正确
        List<ShoppingCart> shoppingCarts = InquireCommodityWhetherExistsShoppingCart(commoditySettlements, userId);

        // 根据购物车中查询到的商品信息和用户信息查询该商品是否存在，并且判断该商品是不是自己的
        List<TCommodity> commodities = InquireCommodityWhetherExistsAndWhetherBelongOneself(shoppingCarts, userId);

        // 核算商品sku库存
        ArrayList<CommoditySku> commoditySkus = CalculateCommodityInventory(commoditySettlements, shoppingCarts);


        // 返回数据存储
        return generateSettlementDetails(commoditySettlements, commodities, commoditySkus);

    }




    /**
     * 处理返回给前端的订单信息
     * @param commoditySettlements 用户提交结算商品信息
     * @param commodities 结算商品信息
     * @param commoditySkus 商品sku信息
     * @return List<SettlementCommodityDTO> 订单信息
     */
    public List<SettlementCommodityDTO> generateSettlementDetails(List<ShoppingCartCommoditySettlementVO> commoditySettlements,
                                                                  List<TCommodity> commodities,
                                                                  List<CommoditySku> commoditySkus){
        List<SettlementCommodityDTO> dtos = new ArrayList<>();

        for (int i = 0; i < commoditySettlements.size(); i++) {
            // 获取返回的数据
            SettlementCommodityDTO settlement = new SettlementCommodityDTO();
            settlement.setCommodityId(commodities.get(i).getcId());
            settlement.setTitle(commodities.get(i).getCommodityTitle());
            settlement.setDeliveryMethod(commodities.get(i).getDeliveryMethod());
            settlement.setDeliveryFee(commodities.get(i).getShippingFees());
            settlement.setUnitPrice(commoditySkus.get(i).getPrice());
            settlement.setBuyQuantity(commoditySettlements.get(i).getBuyQuantity());
            settlement.setShoppingId(commoditySettlements.get(i).getShoppingCartId()); // 购物车id
            settlement.setCommoditySku(commoditySettlements.get(i).getCommoditySku()); // 商品sku规格
            // 获取商品主图
            settlement.setPath(InquireCommodityMainPicture(commodities.get(i).getcId()));
            dtos.add(settlement);
        }

        return dtos;
    }

    /**
     * 获取商品主图
     * @param commodityId 商品id
     * @return 商品主图
     */
    public String InquireCommodityMainPicture(Integer commodityId){
        LambdaQueryWrapper<CommodityPicture> pictureLambdaQueryWrapper = new LambdaQueryWrapper<>();
        pictureLambdaQueryWrapper.eq(CommodityPicture::getCommodityId, commodityId);
        pictureLambdaQueryWrapper.eq(CommodityPicture::getIsDisplayPicture, 1);
        CommodityPicture commodityPicture = commodityPictureMapper.selectOne(pictureLambdaQueryWrapper);
        return commodityPicture.getpPath();
    }

    /**
     * 用户订单创建数据处理
     * @param commoditySettlements 用户提交结算商品信息
     * @param commodities 结算商品信息
     * @param userId 用户id
     * @param createUser 创建人
     * @return TOrder List 对象数据
     */
    public List<TOrder> createOrderData(List<ShoppingCartCommoditySettlementVO> commoditySettlements, List<TCommodity> commodities, Integer userId, String createUser){
        ArrayList<TOrder> orders = new ArrayList<>();
        String orderNumber = getOrderNumber();
        for (int i = 0; i < commoditySettlements.size(); i++) {
            TOrder order = new TOrder();
            order.setOrderNumber(orderNumber); //订单编号
            order.setSellerId(commodities.get(i).getUserId()); // 卖家id
            order.setBuyerId(userId); //买家id
            order.setCommodityId(commodities.get(i).getcId()); // 商品id
            order.setCommodityNumber(commoditySettlements.get(i).getBuyQuantity()); // 购物数量
            order.setCommodityUnitPrice(commodities.get(i).getcPrice()); // 商品价格
            order.setDeliveryMethod(commodities.get(i).getDeliveryMethod()); // 配送方式
            order.setDeliveryFee(commodities.get(i).getShippingFees()); // 配送费用
            BigDecimal totalPrice = commodities.get(i).getcPrice().multiply(BigDecimal.valueOf(commoditySettlements.get(i).getBuyQuantity())); //总价计算
            totalPrice =  totalPrice.add(commodities.get(i).getShippingFees()); // 加上配送费用
            order.setOrderPrice(totalPrice.setScale(2, RoundingMode.HALF_UP)); //设置总价保留两位小数
            order.setOrderStatus("0"); //待确认状态
            order.setOrderStartTime(new Date()); // 订单创建时间
            order.setCreateUser(createUser); // 创建人
            order.setCreateTime(new Date()); // 创建时间
            order.setUpdateTime(new Date()); // 更新时间

            orders.add(order);
        }
        return orders;
    }

    /**
     * 判断购买商品库存是否够
     * @param commoditySettlements 结算商品信息数据
     * @param shoppingCarts 购物车数据
     * @return 商品sku信息
     */
    public ArrayList<CommoditySku> CalculateCommodityInventory(List<ShoppingCartCommoditySettlementVO> commoditySettlements, List<ShoppingCart> shoppingCarts){
        ArrayList<CommoditySku> commoditySkus = new ArrayList<>();
        for (int i = 0; i < commoditySettlements.size(); i++) {
            LambdaQueryWrapper<CommoditySku> skuWrapper = new LambdaQueryWrapper<>();
            skuWrapper.eq(CommoditySku::getSkuId, shoppingCarts.get(i).getSkuId());

            CommoditySku sku = commoditySkuMapper.selectOne(skuWrapper);

            if (sku.getCommodityNumber() < commoditySettlements.get(i).getBuyQuantity()){
                // 抛出异常 库存不足
                throw new InventoryShortageException();
            }
            commoditySkus.add(sku);
        }
        return commoditySkus;
    }

    /**
     * 根据购物车中查询到的商品信息和用户信息查询商品表中该商品是否存在，并且判断该商品是不是自己的
     * @param shoppingCarts 购物车商品数据
     * @param userId 用户id
     * @return 商品信息
     */
    public ArrayList<TCommodity> InquireCommodityWhetherExistsAndWhetherBelongOneself(List<ShoppingCart> shoppingCarts, Integer userId){
        ArrayList<TCommodity> commodities = new ArrayList<>();
        for (ShoppingCart shoppingCart : shoppingCarts) {
            LambdaQueryWrapper<TCommodity> commodityLambdaQueryWrapper = new LambdaQueryWrapper<>();
            commodityLambdaQueryWrapper.eq(TCommodity::getcId, shoppingCart.getCommodityId());
            TCommodity resultCommodity = commodityMapper.selectOne(commodityLambdaQueryWrapper);
            if (resultCommodity == null) {
                // 抛出异常商品不存在
                throw new CommodityDoesNotExistException("商品不存在");
            }

            if (resultCommodity.getUserId().equals(userId)) {
                // 抛出异常 不能购买自己的商品
                throw  new SelfPurchaseProhibitedException("禁止购买自己的商品");
            }

            if (resultCommodity.getcStatus() !=2 ) {
                // 抛出异常 商品状态错误
                throw  new StatusException();
            }

            commodities.add(resultCommodity);

        }
        return commodities;

    }

    /**
     * 根据购物车id和商品id和用户id 查询该商品是否存在于我的购物车中
     * @param commoditySettlements 购物车数据
     * @param userId 用户id
     * @return 购物车中的数据
     */
    public List<ShoppingCart> InquireCommodityWhetherExistsShoppingCart(List<ShoppingCartCommoditySettlementVO> commoditySettlements, Integer userId){
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        for (ShoppingCartCommoditySettlementVO commodity : commoditySettlements) {
            // 购物车数据验证
            LambdaQueryWrapper<ShoppingCart> ShoppingCartWrapper = new LambdaQueryWrapper<>();
            ShoppingCartWrapper.eq(ShoppingCart::getShoppingId,commodity.getShoppingCartId());
            ShoppingCartWrapper.eq(ShoppingCart::getCommodityId, commodity.getCommodityId());
            ShoppingCartWrapper.eq(ShoppingCart::getUserId, userId);

            ShoppingCart cart = shoppingCartMapper.selectOne(ShoppingCartWrapper);
            if (cart == null){
                //抛出异常 商品不存在购物车中
                throw new ProductNotInCartException("商品不存在购物车中");
            }

            // 商品sku规格验证
            LambdaQueryWrapper<CommoditySku> skuLambdaQueryWrapper = new LambdaQueryWrapper<>();
            skuLambdaQueryWrapper.eq(CommoditySku::getSkuId, cart.getSkuId());
            CommoditySku commoditySku = commoditySkuMapper.selectOne(skuLambdaQueryWrapper);
            if (commoditySku == null || !JSONUtil.toJsonStr(commodity.getCommoditySku()).equals(commoditySku.getCommoditySku())){
                throw new CommoditySkuException(); //商品规格错误
            }

            shoppingCarts.add(cart);
        }

        return shoppingCarts;
    }


    /**
     * 订单号生成
     * @return 订单号
     */
    public String getOrderNumber(){
        SimpleDateFormat dateFormat  = new SimpleDateFormat("yyMMdd");
        String day = dateFormat.format(new Date());

        Random random = new Random();
        int i = random.nextInt(900000) + 100000;
        return day + '-' + new Date().getTime() + i;
    }
}




