package com.ziheng.deal.db.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ziheng.deal.common.domain.DTO.AddressDTO;
import com.ziheng.deal.common.domain.VO.AddressVO;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.*;
import com.ziheng.deal.db.entity.TAddress;
import com.ziheng.deal.db.entity.TUser;
import com.ziheng.deal.db.mapper.TUserMapper;
import com.ziheng.deal.db.service.TAddressService;
import com.ziheng.deal.db.mapper.TAddressMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
* @author Administrator
* @description 针对表【t_address】的数据库操作Service实现
* @createDate 2024-07-19 21:33:59
*/
@Service
public class TAddressServiceImpl extends ServiceImpl<TAddressMapper, TAddress>
    implements TAddressService{

    @Resource
    private TAddressMapper addressMapper;

    @Resource
    private TUserMapper userMapper;

    /**
     * 根据用户ID新增收货地址
     * @param userId 用户ID
     * @param createUser 创建人
     * @param addressVO 地址信息
     */
    @Override
    public void AddShippingAddressBasedUserId(Integer userId, String createUser, AddressVO addressVO) {
        // 根据用户id查询该用户是否存在
        TUser resultUser = userMapper.selectById(userId);
        if (resultUser == null) {
            throw  new UserDataDoesNotExistException();
        }

        // 创建shoppingAddress对象，并补全数据
        TAddress address = new TAddress();
        BeanUtil.copyProperties(addressVO, address);

        // 补全日志
        address.setDefaultAddress(0);
        address.setUserId(userId);
        address.setCreateUser(createUser);
        address.setCreateTime(new Date());
        address.setModifiedUser(createUser);
        address.setModifiedTime(new Date());



        // 将数据插入到数据库中
        int result = addressMapper.insert(address);
        if (result != 1) {
            throw new InsertException();
        }


    }

    /**
     * 根据用户id获取到该用户的所有收货地址
     * @param userId 用户id
     * @return 用户地址信息
     */
    @Override
    public List<AddressDTO> getAllAddress(Integer userId) {
        List<AddressDTO> dtoList = addressMapper.getAllAddress(userId);
        return dtoList;
    }

    /**
     * g根据用户id和收货地址id修改用户的收货地址
     * @param addressVO 修改地址信息
     * @param userId 用户id
     */
    @Override
    public void updateAddress(AddressVO addressVO, Integer userId) {

        if (addressVO.getAddressId() == null) {
            throw new ShippingAddressIDCannotBeEmptyException("收货地址id不能为空");
        }

        LambdaQueryWrapper<TAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TAddress::getAddressId, addressVO.getAddressId());
        wrapper.eq(TAddress::getUserId, userId);

        TAddress result = addressMapper.selectOne(wrapper);
        if (result == null) {
            throw new DeliveryAddressDoesNotExistException("收货地址不存在");
        }

        TAddress address = new TAddress();
        BeanUtil.copyProperties(addressVO, address);


        int i = addressMapper.updateById(address);
        if (i != 1) {
            throw new UpdateException();
        }


    }

    /**
     * 根据用户id和收货地址id删除收获地址
     * @param userId 用户id
     * @param addressId 收货地址id
     */
    @Override
    public void deleteAddress(Integer userId, Integer addressId) {
        // 根据用户id和收货地址id查询数据是否存在
        LambdaQueryWrapper<TAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TAddress::getAddressId, addressId);
        wrapper.eq(TAddress::getUserId, userId);

        TAddress result = addressMapper.selectOne(wrapper);
        if (result == null) {
            throw new DeliveryAddressDoesNotExistException("收货地址不存在");
        }

        // 删除收获地址

        int i = addressMapper.deleteById(result.getAddressId());

        if (i != 1) {
            throw new DeleteException();
        }
    }

    /**
     * 修改默认收货地址
     * @param oldAddressId 旧默认收货地址 id
     * @param newAddressId 新默认收货地址 id
     * @param userId 用户 id
     */
    @Override
    @Transactional
    public void setAcquiesceAddress(Integer newAddressId, Integer userId) {
        // 根据用户id和旧的收货地址id 查询出默认的收货地址
        LambdaQueryWrapper<TAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TAddress::getDefaultAddress, 1);
        wrapper.eq(TAddress::getUserId, userId);

        TAddress result = addressMapper.selectOne(wrapper);
        if (result == null) {
            throw new DeliveryAddressDoesNotExistException("收货地址不存在");
        }

        // 将旧的收货地址设置为不是默认的
        result.setDefaultAddress(0);

        int i = addressMapper.updateById(result);

        if (i != 1) {
            throw  new UpdateException();
        }

        // 根据用户id和新的收货地址查询该收货地址是否存在
        LambdaQueryWrapper<TAddress> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(TAddress::getAddressId, newAddressId);
        wrapper1.eq(TAddress::getUserId, userId);

        TAddress result1 = addressMapper.selectOne(wrapper1);

        if (result1 == null) {
            throw new DeliveryAddressDoesNotExistException("收货地址不存在");
        }

        // 如果收货地址存储则设置为新的默认收货地址
        result1.setDefaultAddress(1);
        int i1 = addressMapper.updateById(result1);

        if (i1 != 1) {
            throw  new UpdateException();
        }
    }
}




