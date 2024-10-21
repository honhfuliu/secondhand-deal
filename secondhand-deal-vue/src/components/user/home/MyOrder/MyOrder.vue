<template>
  <div style="position: fixed; width: 72%; z-index: 999;">
<!-- max-height: 100vh; overflow-y: auto-->
    <el-tabs v-model="activeName">
      <!--所有订单start-->
      <el-tab-pane  name="allOrder">
        <span slot="label" style="font-size: 16px" @click="getMyOrder">
          <span>所有订单</span>
        </span>

        <div>
          <div style="display: flex; margin-bottom: 30px">
            <div>
              <el-input placeholder="根据订单号搜索" style="width: 400px" v-model="PageData.orderNumber">
                <template #suffix>
                  <el-button style="margin-right: -5px" @click="getMyOrder">搜索</el-button>
                </template>
              </el-input>
            </div>
            <div style="padding: 5px 10px">
              <div style="cursor: pointer; line-height: 36px" v-show="!more" @click="moreStart">
                <span>
                  更多筛选
                </span>
                <img src="/static/shang.png" style="width: 12px; margin: 3px">
              </div>
              <div style="cursor: pointer; line-height: 36px" v-show="more" @click="moreEnd">
                <span>
                  精简筛选
                </span>
                <img src="/static/xia.png" style="width: 12px; margin: 3px">
              </div>
            </div>
          </div>

          <div v-if="more" style="display: flex">
            <el-form :inline="true">
              <el-form-item label="成交时间">
                <el-date-picker
                  @change="moreSearch1"
                  v-model="PageData.startTime"
                  type="datetimerange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  align="right">
                </el-date-picker>
              </el-form-item>
              <el-form-item label="订单状态">
                <el-select v-model="PageData.orderStatus" placeholder="请选择" @change="moreSearch1">
                  <el-option
                    v-for="item in orderStatus"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>

              <el-form-item v-show="moreSearch">
                <el-button @click="getMyOrder">搜素</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>

        <div>
          <el-table
            :header-cell-style="{ fontSize: '15px', background: '#f7fafd', color: 'black', textAlign: 'center'}"
            max-height="640"
            :row-height="50"
            height="50px"
            :style="{ border: 'none' }"
          >
            <el-table-column label="名称" width="530"></el-table-column>
            <el-table-column label="单价" width="110"></el-table-column>
            <el-table-column label="数量" ></el-table-column>
            <el-table-column label="商品操作" ></el-table-column>
            <el-table-column label="实付款" width="160"></el-table-column>
            <el-table-column label="交易状态" width="200"></el-table-column>
            <el-table-column label="交易操作" width="200"></el-table-column>
          </el-table>
          <div style="width: 100%; height: 580px; overflow-y: auto">
            <div style="padding: 16px; margin-bottom: 5px">
              <div style="border: 1px solid #dcd4d4; margin-bottom: 15px" v-for="item in this.MyOrderData" :key="item">
                <div>
                  <div style="height: 50px; background-color: #f5f5f5">
                    <div style="display: flex; line-height: 53px; width: 95%; margin: auto">
                      <div style="font-weight: 600; width: 110px">2024-08-10</div>
                      <div style="margin-left: 20px; width: 400px">订单号：{{ item.orderNumber }}</div>
                      <div style="width: 450px">
                        <div style="float: right">
                          <el-link :underline="false">与我联系</el-link>
                        </div>
                      </div>
                      <div style="width: 466px">
                        <div style="float: right">
                          <i class="el-icon-delete" style="cursor: pointer"></i>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                <div>
                  <!--订单信息展示start-->
                  <el-row>
                    <el-col :span="9" style="margin: 15px 0 11px 0">
                      <div style="display: flex; width: 95%; margin: auto auto 8px;" v-for="j in item.myOrderItemList" :key="j">
                        <div>
                          <img :src="`${img}/${j.commodityPath}`"  style="height: 80px">
                        </div>
                        <div style="width: 81%; margin: auto">
                          <div style="text-align: left; margin-bottom: 5px; font-weight: 600">{{ j.commodityTitle }}</div>
                          <div style="display: flex">
                            <div style="font-weight: 600; color: #a6a4a4" v-for="(value, key) in j.commoditySku" :key="key">
                              <span>{{ key }}:</span>
                              <span>{{ value }}</span>
                              <span>&nbsp;</span>
                            </div>
                          </div>
                        </div>
                      </div>
                    </el-col>
                    <el-col :span="2">
                      <div>
                        <div style="line-height: 92px" v-for="j in item.myOrderItemList" :key="j">
                          ￥{{ j.commodityUnitPrice }}
                        </div>
                      </div>
                    </el-col>
                    <el-col :span="2">
                      <div>
                        <div style="line-height: 92px"  v-for="j in item.myOrderItemList" :key="j">
                          {{ j.commodityNumber }}
                        </div>
                      </div>
                    </el-col>
                    <el-col :span="3" class="div-c" style="left: 58%">
                      <div style="line-height: 26px; margin: 18px 0 5px 0; display: grid">
                        <el-link :underline="false" class="link-font">退货/退款</el-link>
                        <el-link :underline="false" class="link-font">取消订单</el-link>
                      </div>
                    </el-col>
                    <el-col :span="3" class="div-c" style="left: 68%">
                      <div style="margin: 18px 0 5px 0" >
                        <div style="font-weight: 600; font-size: 19px">￥{{  item.myOrderItemList.reduce((total, item) => total + item.commodityNumber * item.commodityUnitPrice + item.deliveryFee - item.discountedPrice, 0) }}</div>
                        <div style="color: #918f8f">(含运费: ￥{{   item.myOrderItemList.reduce((total, item) => total + item.deliveryFee, 0)  }})</div>
                      </div>
                    </el-col>
                    <el-col :span="3" class="div-c" style="left: 81%">
                      <div style="margin: 18px 0 5px 0; line-height: 26px; display: grid">
                        <el-link :underline="false" class="link-font">订单详情</el-link>
                      </div>
                    </el-col>

                    <el-col :span="3" class="div-c" style="left: 95%">
                      <div style="margin: 18px 0 5px 0; line-height: 26px; display: grid">
                        <el-link :underline="false" class="link-font">订单详情</el-link>
                      </div>
                    </el-col>
                  </el-row>
                  <!--订单信息展示end-->
                </div>
              </div>
            </div>

          </div>

          <!--分页start-->
          <el-pagination
          :total="total"
          :page-size="PageData.PageSize"
          :current-page="PageData.PageNo"
          layout="total, sizes, prev, pager, next, jumper"
          :page-sizes="[5,10,15,20]"
          @current-change="currentChange"
          @size-change="sizeChange"
          >

          </el-pagination>
          <!--分页end-->
        </div>

      </el-tab-pane>
      <!--所有订单end-->

      <!--待付款start-->
      <el-tab-pane label="待付款" name="pendingPayment">
        <span slot="label" style="font-size: 16px" @click="getStayPayMyOrder">
          <span>待付款</span>
        </span>
        <div>
          <div style="height: 30px"></div>
          <el-table
            :header-cell-style="{ fontSize: '15px', background: '#f7fafd', color: 'black', textAlign: 'center'}"
            max-height="640"
            :row-height="50"
            height="50px"
            :style="{ border: 'none' }"
          >
            <el-table-column label="名称" width="530"></el-table-column>
            <el-table-column label="单价" width="110"></el-table-column>
            <el-table-column label="数量" ></el-table-column>
            <el-table-column label="商品操作" ></el-table-column>
            <el-table-column label="实付款" width="160"></el-table-column>
            <el-table-column label="交易状态" width="200"></el-table-column>
            <el-table-column label="交易操作" width="200"></el-table-column>
          </el-table>
          <div style="width: 100%; height: 580px; overflow-y: auto">
            <div style="padding: 16px; margin-bottom: 5px">
              <div style="border: 1px solid #dcd4d4; margin-bottom: 15px" v-for="item in this.MyOrderData" :key="item">
                <div>
                  <div style="height: 50px; background-color: #f5f5f5">
                    <div style="display: flex; line-height: 53px; width: 95%; margin: auto">
                      <div style="font-weight: 600; width: 110px">2024-08-10</div>
                      <div style="margin-left: 20px; width: 400px">订单号：{{ item.orderNumber }}</div>
                      <div style="width: 450px">
                        <div style="float: right">
                          <el-link :underline="false">与我联系</el-link>
                        </div>
                      </div>
                      <div style="width: 466px">
                        <div style="float: right">
                          <i class="el-icon-delete" style="cursor: pointer"></i>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                <div>
                  <!--订单信息展示start-->
                  <el-row>
                    <el-col :span="9" style="margin: 15px 0 11px 0">
                      <div style="display: flex; width: 95%; margin: auto auto 8px;" v-for="j in item.myOrderItemList" :key="j">
                        <div>
                          <img :src="`${img}/${j.commodityPath}`" style="height: 80px">
                        </div>
                        <div style="width: 81%; margin: auto">
                          <div style="text-align: left; margin-bottom: 5px; font-weight: 600">{{ j.commodityTitle }}</div>
                          <div style="display: flex">
                            <div style="font-weight: 600; color: #a6a4a4" v-for="(value, key) in j.commoditySku" :key="key">
                              <span>{{ key }}:</span>
                              <span>{{ value }}</span>
                              <span>&nbsp;</span>
                            </div>
                          </div>
                        </div>
                      </div>
                    </el-col>
                    <el-col :span="2">
                      <div>
                        <div style="line-height: 92px" v-for="j in item.myOrderItemList" :key="j">
                          ￥{{ j.commodityUnitPrice }}
                        </div>
                      </div>
                    </el-col>
                    <el-col :span="2">
                      <div>
                        <div style="line-height: 92px"  v-for="j in item.myOrderItemList" :key="j">
                          {{ j.commodityNumber }}
                        </div>
                      </div>
                    </el-col>
                    <el-col :span="3" class="div-c" style="left: 58%">
                      <div style="line-height: 26px; margin: 18px 0 5px 0; display: grid">
                        <el-link :underline="false" class="link-font">退货/退款</el-link>
                        <el-link :underline="false" class="link-font">取消订单</el-link>
                      </div>
                    </el-col>
                    <el-col :span="3" class="div-c" style="left: 68%">
                      <div style="margin: 18px 0 5px 0" >
                        <div style="font-weight: 600; font-size: 19px">￥{{  item.myOrderItemList.reduce((total, item) => total + item.commodityNumber * item.commodityUnitPrice + item.deliveryFee - item.discountedPrice, 0) }}</div>
                        <div style="color: #918f8f">(含运费: ￥{{   item.myOrderItemList.reduce((total, item) => total + item.deliveryFee, 0)  }})</div>
                      </div>
                    </el-col>
                    <el-col :span="3" class="div-c" style="left: 81%">
                      <div style="margin: 18px 0 5px 0; line-height: 26px; display: grid">
                        <el-link :underline="false" class="link-font">订单详情</el-link>
                      </div>
                    </el-col>

                    <el-col :span="3" class="div-c" style="left: 95%">
                      <div style="margin: 18px 0 5px 0; line-height: 26px; display: grid">
                        <el-link :underline="false" class="link-font">订单详情</el-link>
                      </div>
                    </el-col>
                  </el-row>
                  <!--订单信息展示end-->
                </div>
              </div>
            </div>

          </div>
        </div>
        <el-pagination
          :total="stayPayTotal"
          :page-size="PageData.PageSize"
          :current-page="PageData.PageNo"
          layout="total, sizes, prev, pager, next, jumper"
          :page-sizes="[5,10,15,20]"
          @current-change="stayPayCurrentChange"
          @size-change="stayPaySizeChange"
        />
      </el-tab-pane>
      <!--待付款end-->

      <!--待发货start-->
      <el-tab-pane label="待发货" name="awaitingShipment">
        <span slot="label" style="font-size: 16px" @click="stayDelivery">
          <span>待发货</span>
        </span>
        <div>
          <el-table
            :header-cell-style="{ fontSize: '15px', background: '#f7fafd', color: 'black', textAlign: 'center'}"
            max-height="640"
            :row-height="50"
            height="50px"
            :style="{ border: 'none' }"
          >
            <el-table-column label="名称" width="530"></el-table-column>
            <el-table-column label="单价" width="110"></el-table-column>
            <el-table-column label="数量" ></el-table-column>
            <el-table-column label="商品操作" ></el-table-column>
            <el-table-column label="实付款" width="160"></el-table-column>
            <el-table-column label="交易状态" width="200"></el-table-column>
            <el-table-column label="交易操作" width="200"></el-table-column>
          </el-table>

          <div style="width: 100%; height: 580px; overflow-y: auto">
            <div style="padding: 16px; margin-bottom: 5px">
              <div style="border: 1px solid #dcd4d4; margin-bottom: 15px" v-for="item in this.MyOrderData" :key="item">
                <div>
                  <div style="height: 50px; background-color: #f5f5f5">
                    <div style="display: flex; line-height: 53px; width: 95%; margin: auto">
                      <div style="font-weight: 600; width: 110px">2024-08-10</div>
                      <div style="margin-left: 20px; width: 400px">订单号：{{ item.orderNumber }}</div>
                      <div style="width: 450px">
                        <div style="float: right">
                          <el-link :underline="false">与我联系</el-link>
                        </div>
                      </div>
                      <div style="width: 466px">
                        <div style="float: right">
                          <i class="el-icon-delete" style="cursor: pointer"></i>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                <div>
                  <!--订单信息展示start-->
                  <el-row>
                    <el-col :span="9" style="margin: 15px 0 11px 0">
                      <div style="display: flex; width: 95%; margin: auto auto 8px;" v-for="j in item.myOrderItemList" :key="j">
                        <div>
                          <img :src="`${img}/${j.commodityPath}`" style="height: 80px">
                        </div>
                        <div style="width: 81%; margin: auto">
                          <div style="text-align: left; margin-bottom: 5px; font-weight: 600">{{ j.commodityTitle }}</div>
                          <div style="display: flex">
                            <div style="font-weight: 600; color: #a6a4a4" v-for="(value, key) in j.commoditySku" :key="key">
                              <span>{{ key }}:</span>
                              <span>{{ value }}</span>
                              <span>&nbsp;</span>
                            </div>
                          </div>
                        </div>
                      </div>
                    </el-col>
                    <el-col :span="2">
                      <div>
                        <div style="line-height: 92px" v-for="j in item.myOrderItemList" :key="j">
                          ￥{{ j.commodityUnitPrice }}
                        </div>
                      </div>
                    </el-col>
                    <el-col :span="2">
                      <div>
                        <div style="line-height: 92px"  v-for="j in item.myOrderItemList" :key="j">
                          {{ j.commodityNumber }}
                        </div>
                      </div>
                    </el-col>
                    <el-col :span="3" class="div-c" style="left: 58%">
                      <div style="line-height: 26px; margin: 18px 0 5px 0; display: grid">
                        <el-link :underline="false" class="link-font">退货/退款</el-link>
                        <el-link :underline="false" class="link-font">取消订单</el-link>
                      </div>
                    </el-col>
                    <el-col :span="3" class="div-c" style="left: 68%">
                      <div style="margin: 18px 0 5px 0" >
                        <div style="font-weight: 600; font-size: 19px">￥{{  item.myOrderItemList.reduce((total, item) => total + item.commodityNumber * item.commodityUnitPrice + item.deliveryFee - item.discountedPrice, 0) }}</div>
                        <div style="color: #918f8f">(含运费: ￥{{   item.myOrderItemList.reduce((total, item) => total + item.deliveryFee, 0)  }})</div>
                      </div>
                    </el-col>
                    <el-col :span="3" class="div-c" style="left: 81%">
                      <div style="margin: 18px 0 5px 0; line-height: 26px; display: grid">
                        <el-link :underline="false" class="link-font">订单详情</el-link>
                      </div>
                    </el-col>

                    <el-col :span="3" class="div-c" style="left: 95%">
                      <div style="margin: 18px 0 5px 0; line-height: 26px; display: grid">
                        <el-link :underline="false" class="link-font">订单详情</el-link>
                      </div>
                    </el-col>
                  </el-row>
                  <!--订单信息展示end-->
                </div>
              </div>
            </div>

          </div>

          <!--分页start-->
          <el-pagination
            :total="stayDeliveryTotal"
            :page-size="PageData.PageSize"
            :current-page="PageData.PageNo"
            layout="total, sizes, prev, pager, next, jumper"
            :page-sizes="[5,10,15,20]"
            @current-change="stayDeliveryCurrentChange"
            @size-change="stayDeliverySizeChange"
          >

          </el-pagination>
        </div>
      </el-tab-pane>
      <!--待发货end-->

      <!--待收货start-->
      <el-tab-pane label="待收货" name="awaitingReceipt">
        <span slot="label" style="font-size: 16px" @click="stayTakeDelivery">
          <span>待收货</span>
        </span>
        <div>
          <el-table
            :header-cell-style="{ fontSize: '15px', background: '#f7fafd', color: 'black', textAlign: 'center'}"
            max-height="640"
            :row-height="50"
            height="50px"
            :style="{ border: 'none' }"
          >
            <el-table-column label="名称" width="530"></el-table-column>
            <el-table-column label="单价" width="110"></el-table-column>
            <el-table-column label="数量" ></el-table-column>
            <el-table-column label="商品操作" ></el-table-column>
            <el-table-column label="实付款" width="160"></el-table-column>
            <el-table-column label="交易状态" width="200"></el-table-column>
            <el-table-column label="交易操作" width="200"></el-table-column>
          </el-table>
          <div style="width: 100%; height: 580px; overflow-y: auto">
            <div style="padding: 16px; margin-bottom: 5px">
              <div style="border: 1px solid #dcd4d4; margin-bottom: 15px" v-for="item in this.MyOrderData" :key="item">
                <div>
                  <div style="height: 50px; background-color: #f5f5f5">
                    <div style="display: flex; line-height: 53px; width: 95%; margin: auto">
                      <div style="font-weight: 600; width: 110px">2024-08-10</div>
                      <div style="margin-left: 20px; width: 400px">订单号：{{ item.orderNumber }}</div>
                      <div style="width: 450px">
                        <div style="float: right">
                          <el-link :underline="false">与我联系</el-link>
                        </div>
                      </div>
                      <div style="width: 466px">
                        <div style="float: right">
                          <i class="el-icon-delete" style="cursor: pointer"></i>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                <div>
                  <!--订单信息展示start-->
                  <el-row>
                    <el-col :span="9" style="margin: 15px 0 11px 0">
                      <div style="display: flex; width: 95%; margin: auto auto 8px;" v-for="j in item.myOrderItemList" :key="j">
                        <div>
                          <img :src="`${img}/${j.commodityPath}`" style="height: 80px">
                        </div>
                        <div style="width: 81%; margin: auto">
                          <div style="text-align: left; margin-bottom: 5px; font-weight: 600">{{ j.commodityTitle }}</div>
                          <div style="display: flex">
                            <div style="font-weight: 600; color: #a6a4a4" v-for="(value, key) in j.commoditySku" :key="key">
                              <span>{{ key }}:</span>
                              <span>{{ value }}</span>
                              <span>&nbsp;</span>
                            </div>
                          </div>
                        </div>
                      </div>
                    </el-col>
                    <el-col :span="2">
                      <div>
                        <div style="line-height: 92px" v-for="j in item.myOrderItemList" :key="j">
                          ￥{{ j.commodityUnitPrice }}
                        </div>
                      </div>
                    </el-col>
                    <el-col :span="2">
                      <div>
                        <div style="line-height: 92px"  v-for="j in item.myOrderItemList" :key="j">
                          {{ j.commodityNumber }}
                        </div>
                      </div>
                    </el-col>
                    <el-col :span="3" class="div-c" style="left: 58%">
                      <div style="line-height: 26px; margin: 18px 0 5px 0; display: grid">
                        <el-link :underline="false" class="link-font">退货/退款</el-link>
                        <el-link :underline="false" class="link-font">取消订单</el-link>
                      </div>
                    </el-col>
                    <el-col :span="3" class="div-c" style="left: 68%">
                      <div style="margin: 18px 0 5px 0" >
                        <div style="font-weight: 600; font-size: 19px">￥{{  item.myOrderItemList.reduce((total, item) => total + item.commodityNumber * item.commodityUnitPrice + item.deliveryFee - item.discountedPrice, 0) }}</div>
                        <div style="color: #918f8f">(含运费: ￥{{   item.myOrderItemList.reduce((total, item) => total + item.deliveryFee, 0)  }})</div>
                      </div>
                    </el-col>
                    <el-col :span="3" class="div-c" style="left: 81%">
                      <div style="margin: 18px 0 5px 0; line-height: 26px; display: grid">
                        <el-link :underline="false" class="link-font">订单详情</el-link>
                      </div>
                    </el-col>

                    <el-col :span="3" class="div-c" style="left: 95%">
                      <div style="margin: 18px 0 5px 0; line-height: 26px; display: grid">
                        <el-link :underline="false" class="link-font">订单详情</el-link>
                      </div>
                    </el-col>
                  </el-row>
                  <!--订单信息展示end-->
                </div>
              </div>
            </div>

          </div>

          <!--分页start-->
          <el-pagination
            :total="stayTakeDeliveryTotal"
            :page-size="PageData.PageSize"
            :current-page="PageData.PageNo"
            layout="total, sizes, prev, pager, next, jumper"
            :page-sizes="[5,10,15,20]"
            @current-change="stayTakeDeliveryCurrentChange"
            @size-change="stayTakeDeliverySizeChange"
          >

          </el-pagination>
        </div>
      </el-tab-pane>
      <!--待收货end-->

      <!--待评价start-->
      <el-tab-pane label="待评价" name="AwaitingEvaluation">
        <span slot="label" style="font-size: 16px">
          <span>待评价</span>
        </span>
        <div>
          <el-table
            :header-cell-style="{ fontSize: '15px', background: '#f7fafd', color: 'black', textAlign: 'center'}"
            max-height="640"
          >
            <el-table-column label="名称"></el-table-column>
            <el-table-column label="单价"></el-table-column>
            <el-table-column label="数量"></el-table-column>
            <el-table-column label="商品操作"></el-table-column>
            <el-table-column label="实付款"></el-table-column>
            <el-table-column label="交易状态"></el-table-column>

          </el-table>
        </div>
      </el-tab-pane>
      <!--待评价end-->

    </el-tabs>
  </div>
</template>

<script>
export default {
  name: "MyOrder",
  data(){
    return{

      img: this.$axios.defaults.baseURL,
      // 待评价信息

      // 待收货信息
      stayTakeDeliveryTotal: 0, // 分页总条数
      // 分页数据存储
      stayTakeDeliveryPageData: {
        PageSize: 5, // 每页数据
        PageNo: 1, // 第几页
        orderStatus: 3,
      },

      // 待发货信息
      stayDeliveryTotal: 0, // 分页总条数
      // 分页数据存储
      stayDeliveryPageData: {
        PageSize: 5, // 每页数据
        PageNo: 1, // 第几页
        orderStatus: 2,
      },

      // 待付款信息
      stayPayTotal: 0, // 分页总条数
      // 分页数据存储
      stayPayPageData: {
        PageSize: 5, // 每页数据
        PageNo: 1, // 第几页
        orderStatus: 1,
      },

      // 订单信息接收
      MyOrderData: [],

      testData: [
        {
          createTime: "2024-08-12",
          orderNumber: "20240812-1456748523478541521053",
        },
        {
          createTime: "2024-08-12",
          orderNumber: "20240812-1456748523478541521053",
        },
        {
          createTime: "2024-08-12",
          orderNumber: "20240812-1456748523478541521053",
        },
      ],

      // 所以订单信息
      activeName: "allOrder",
      more: false,
      moreSearch: false,

      // 订单状态
      orderStatus: [
        {value: 1, label: '待付款'},
        {value: 2, label: '已付款'},
        {value: 3, label: '已发货'},
        {value: 4, label: '已收货'},
        {value: 5, label: '已完成'},
        {value: 6, label: '已退款'},
        {value: 7, label: '已取消'},
      ],
      total: 0, // 分页总条数
      // 分页数据存储
      PageData: {
        PageSize: 5, // 每页数据
        PageNo: 1, // 第几页
        startTime: null,
        endTime: null,
        orderNumber: null,
        orderStatus: null,
      }
    }
  },
  created() {
    this.getMyOrder()


  },
  methods:{
    // 待收货相关函数
    stayTakeDelivery(){
      this.MyOrderData = []
      this.$axios({
        url: "order",
        method: "get",
        params: this.stayTakeDeliveryPageData,
        headers: {
          token: sessionStorage.getItem("token")
        }
      }).then(msg =>{
        if (msg.data.code === 200) {
          this.MyOrderData = msg.data.data.list[0]
          this.stayTakeDeliveryTotal = msg.data.data.total

        }else {
          this.$notify.warning({
            title: "警告",
            message: msg.data.message
          })
        }
      })
    },
    // 捕获页数切换之后将其赋值给queryDto的相关属性。
    stayTakeDeliveryCurrentChange(page) {
      this.PageData.PageNo = page;
      this.stayTakeDelivery()
    },

    // 捕获页面显示总数，之后将其赋值给queryDto中的相关属性
    stayTakeDeliverySizeChange(size){
      this.PageData.PageSize = size;
      this.stayTakeDelivery()
    },


    // 待发货相关接口
    // 捕获页数切换之后将其赋值给queryDto的相关属性。
    stayDeliveryCurrentChange(page) {
      this.PageData.PageNo = page;
      this.stayDelivery()
    },

    // 捕获页面显示总数，之后将其赋值给queryDto中的相关属性
    stayDeliverySizeChange(size){
      this.PageData.PageSize = size;
      this.stayDelivery()
    },
    stayDelivery(){
      this.MyOrderData = []
      this.$axios({
        url: "order",
        method: "get",
        params: this.stayDeliveryPageData,
        headers: {
          token: sessionStorage.getItem("token")
        }
      }).then(msg =>{
        if (msg.data.code === 200) {
          this.MyOrderData = msg.data.data.list[0]
          this.stayDeliveryTotal = msg.data.data.total

        }else {
          this.$notify.warning({
            title: "警告",
            message: msg.data.message
          })
        }
      })
    },


    // 待付款相关函数
    // 捕获页数切换之后将其赋值给queryDto的相关属性。
    stayPayCurrentChange(page) {
      this.PageData.PageNo = page;
      this.getStayPayMyOrder()
    },

    // 捕获页面显示总数，之后将其赋值给queryDto中的相关属性
    stayPaySizeChange(size){
      this.PageData.PageSize = size;
      this.getStayPayMyOrder()
    },

    getStayPayMyOrder(){
      this.MyOrderData = []
      this.$axios({
        url: "order",
        method: "get",
        params: this.stayPayPageData,
        headers: {
          token: sessionStorage.getItem("token")
        }
      }).then(msg =>{
        if (msg.data.code === 200) {
          this.MyOrderData = msg.data.data.list[0]
          this.stayPayTotal = msg.data.data.total

        }else {
          this.$notify.warning({
            title: "警告",
            message: msg.data.message
          })
        }
      })
    },



    // 获取所有订单
    getMyOrder(){
      this.MyOrderData = []
      if (this.PageData.startTime !== null){
        this.PageData.endTime = this.PageData.startTime[1].toLocaleDateString()
        this.PageData.startTime = this.PageData.startTime[0].toLocaleDateString()
      }
      if (this.PageData.orderNumber === ""){
        this.PageData.orderNumber = null
      }
      this.$axios({
        url: "order",
        method: "get",
        params: this.PageData,
        headers: {
          token: sessionStorage.getItem("token")
        }
      }).then(msg =>{
        if (msg.data.code === 200) {
          // console.log(msg.data)
          this.MyOrderData = msg.data.data.list[0]
          this.total = msg.data.data.total

        }else {
          this.$notify.warning({
            title: "警告",
            message: msg.data.message
          })
        }
      })
    },


    // 捕获页数切换之后将其赋值给queryDto的相关属性。
    currentChange(page) {
      this.PageData.PageNo = page;
      this.getMyOrder()

    },

    // 捕获页面显示总数，之后将其赋值给queryDto中的相关属性
    sizeChange(size){
      this.PageData.PageSize = size;
      this.getMyOrder()
    },

    moreStart(){
      this.more = true
    },
    moreEnd(){
      this.more = false
    },
    moreSearch1(){
      this.moreSearch = true;

    },

  }
}
</script>

<style scoped>
.link-font{
  font-size: 16px;
}
.div-c{
  position: absolute;
  top: 50%;
  transform: translate(-50%, -50%);
}
.el-tab-pane label{
  font-size: 30px !important;
}

</style>
