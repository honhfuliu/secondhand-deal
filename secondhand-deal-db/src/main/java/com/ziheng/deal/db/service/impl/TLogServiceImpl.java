package com.ziheng.deal.db.service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import com.ziheng.deal.common.domain.DTO.PageDOT;
import com.ziheng.deal.common.domain.query.LogPage;
import com.ziheng.deal.common.domain.query.PageQueryBase;
import com.ziheng.deal.db.entity.TLog;
import com.ziheng.deal.db.service.TLogService;
import com.ziheng.deal.db.mapper.TLogMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【t_log】的数据库操作Service实现
* @createDate 2024-04-20 17:28:13
*/
@Service
public class TLogServiceImpl extends ServiceImpl<TLogMapper, TLog>
    implements TLogService{



    /**
     * 保存日志信息
     * @param log
     */
    @Override
    public void insertLog(TLog log) {
        boolean save = save(log);
    }

    /**
     * 分页日志查询
     * @param pageQueryBase 分页条件
     * @return
     */
    @Override
    public PageDOT<TLog> PageLogWhereQuery(LogPage pageQueryBase) {
        Page<TLog> page = Page.of(pageQueryBase.getPageNo(), pageQueryBase.getPageSize());

        // 判断排序字段是否为空
        if (pageQueryBase.getSortBy() != null) {
            page.addOrder(new OrderItem(pageQueryBase.getSortBy(), pageQueryBase.isAsc()));
        } else {
            page.addOrder(new OrderItem("create_time", false));
        }


        // 条件查询异常字段不为空
        Page<TLog> result;
        if (pageQueryBase.getException() != null) {
            result = lambdaQuery()
                    .isNotNull(TLog::getException)
                    .page(page);
        }else {
            result = lambdaQuery()
                    .page(page);
        }


        PageDOT<TLog> dot = new PageDOT<>();

        dot.setTotal((int) result.getTotal());

        List<TLog> records = result.getRecords();
        dot.setList(records);
        return dot;
    }


}




