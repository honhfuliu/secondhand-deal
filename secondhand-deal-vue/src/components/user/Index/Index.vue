<template>
  <div style="width: 100%;background-color: rgb(244, 244, 244)">

    <Common></Common>
    <!--body start-->
    <div style="width: 100%; height: 100%; margin: auto">
      <!--search start-->
      <div style="width: 100%; height: 160px; margin-bottom: 10px; background-color: rgb(255, 255, 255)">
        <el-row style="justify-content: center">
          <el-col :span="7" style="margin-top: 15px">
            <img src="/static/20240509.png" style="height: 145px">
          </el-col>

          <el-col :span="13" style="margin-top: 50px; height: 100%; display: flex">
            <el-input placeholder="商品搜索" style="width: 75%; height: 41px; margin-right: 15%; float: inherit">
              <template slot="append">
                <el-button>
                  <i class="el-icon-search" style="font-size: 19px; font-weight: 700"></i>
                </el-button>
              </template>
            </el-input>

            <div style="width: 128px; height: 45px;margin: 0px -112px; border: 1px solid #e0dddd;  display: flex">
              <div style="margin: 10px 3px">
                <i class="el-icon-shopping-cart-2" style="font-size: 23px"></i>
              </div>
              <div style="margin: 10px 3px">
                <el-link :underline="false" >我的购物车</el-link>
              </div>


            </div>
          </el-col>


        </el-row>

      </div>
      <!--search end-->

      <!--商品类型 走马灯 start-->
      <div style="width: 85%; height: 600px; margin: auto">
        <el-row>
          <el-col :span="4" style="box-shadow: 2px 4px 7px rgb(200 206 213);height: 600px">
            <el-menu
              style="height: 600px"
              class="el-menu-vertical-demo"
              :unique-opened="true"
              background-color="#ffffff"
              :router="true"
              >

              <el-menu-item  @mouseover.native="handleMouseMove(1)" >
                <el-link href="/login" :underline="false">家用电器</el-link>
              </el-menu-item>



              <el-menu-item @mouseover.native="handleMouseMove(2)">
                <el-link>手机</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>运营商</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>数码</el-link>
              </el-menu-item>

              <el-menu-item @mouseover.native="handleMouseMove(3)">
                <el-link>电脑</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>办公</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>文具用品</el-link>
              </el-menu-item>

              <el-menu-item>
                <el-link>家居</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>家居</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>家装</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>厨具</el-link>
              </el-menu-item>

              <el-menu-item>
                <el-link>男装</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>女装</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>童装</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>内衣</el-link>
              </el-menu-item>

              <el-menu-item>
                <el-link>美妆</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>个护清洁</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>宠物</el-link>
              </el-menu-item>

              <el-menu-item>
                <el-link>女鞋</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>箱包</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>钟表</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>珠宝</el-link>
              </el-menu-item>

              <el-menu-item>
                <el-link>女鞋</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>箱包</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>钟表</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>珠宝</el-link>
              </el-menu-item>

              <el-menu-item>
                <el-link>女鞋</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>箱包</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>钟表</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>珠宝</el-link>
              </el-menu-item>

              <el-menu-item>
                <el-link>女鞋</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>箱包</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>钟表</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>珠宝</el-link>
              </el-menu-item>

              <el-menu-item>
                <el-link>女鞋</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>箱包</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>钟表</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>珠宝</el-link>
              </el-menu-item>

              <el-menu-item>
                <el-link>女鞋</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>箱包</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>钟表</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>珠宝</el-link>
              </el-menu-item>

              <el-menu-item>
                <el-link>女鞋</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>箱包</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>钟表</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>珠宝</el-link>
              </el-menu-item>

              <el-menu-item>
                <el-link>女鞋</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>箱包</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>钟表</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>珠宝</el-link>
              </el-menu-item>

              <el-menu-item>
                <el-link>女鞋</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>箱包</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>钟表</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>珠宝</el-link>
              </el-menu-item>

              <el-menu-item>
                <el-link>女鞋</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>箱包</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>钟表</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>珠宝</el-link>
              </el-menu-item>

              <el-menu-item>
                <el-link>女鞋</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>箱包</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>钟表</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>珠宝</el-link>
              </el-menu-item>

              <el-menu-item>
                <el-link>女鞋</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>箱包</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>钟表</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>珠宝</el-link>
              </el-menu-item>

              <el-menu-item>
                <el-link>女鞋</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>箱包</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>钟表</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>珠宝</el-link>
              </el-menu-item>

              <el-menu-item>
                <el-link>女鞋</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>箱包</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>钟表</el-link>
                <span class="cate_menu_line">/</span>
                <el-link>珠宝</el-link>
              </el-menu-item>


              <div>
                <div class="cate_item"  v-show="this.hovers === 1"  @mouseleave="handleMouseLeave">
                  <div style="width: 100%; display: flex; padding: 24px 24px;">
                    <el-link style="width: 82px; height: 31px; background-color: black; color: #FFFFFF;">
                      运营商
                      <i class="el-icon-arrow-right"></i>
                    </el-link>
                    <el-link style="width: 172px; height: 31px; background-color: black; color: #FFFFFF; margin-left: 5px">
                      开店设备一站购买
                      <i class="el-icon-arrow-right"></i>
                    </el-link>

                    <el-link style="width: 100px; height: 31px; background-color: black; color: #FFFFFF; margin-left: 5px">
                      企业采购
                      <i class="el-icon-arrow-right"></i>
                    </el-link>
                  </div>

                  <div style="width: 100%">

                    <dl>
                      <dt style="float: left; margin-left: 80px;">
                        <el-link class="font">
                          电视
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>

                      <dd>
                        <el-link>游戏电视</el-link>
                        <el-link>艺术电视</el-link>
                        <el-link>miniled电视</el-link>
                        <el-link>会议电视</el-link>
                        <el-link>电视音响套装</el-link>
                        <el-link>75寸电视</el-link>
                        <el-link>85寸电视</el-link>
                        <el-link>98寸电视</el-link>
                        <el-link>100寸电视</el-link>
                        <el-link>电视挂架</el-link>
                        <el-link>电视遥控器</el-link>
                        <el-link>电视推车</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 80px;">
                        <el-link class="font">
                          空调
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd>
                        <el-link>新风空调</el-link>
                        <el-link>以旧换新</el-link>
                        <el-link>空调挂机</el-link>
                        <el-link>空调机柜</el-link>
                        <el-link>空调套装</el-link>
                        <el-link>新一级能效</el-link>
                        <el-link>挂机1.5匹</el-link>
                        <el-link>柜机3匹</el-link>
                        <el-link>变频空调</el-link>
                        <el-link>中央空调</el-link>
                        <el-link>移动空调</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 65px;">
                        <el-link class="font">
                          洗衣机
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd style="display: flex">
                        <el-link>滚筒洗衣机</el-link>
                        <el-link>洗烘一体机</el-link>
                        <el-link>波轮洗衣机</el-link>
                        <el-link>洗烘套装</el-link>
                        <el-link>迷你洗衣机</el-link>
                        <el-link>洗衣机配件</el-link>
                        <el-link>烘干机</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 80px;">
                        <el-link class="font">
                          冰箱
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd style="display: flex">
                        <el-link>多门</el-link>
                        <el-link>对开门</el-link>
                        <el-link>三门</el-link>
                        <el-link>双门</el-link>
                        <el-link>冰洗套装</el-link>
                        <el-link>冷柜/冰吧</el-link>
                        <el-link>酒柜</el-link>
                        <el-link>冰箱配件</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 47px;">
                        <el-link class="font">
                          厨卫大电
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd>
                        <el-link>油烟机</el-link>
                        <el-link>灶具</el-link>
                        <el-link>烟灶套装</el-link>
                        <el-link>集成灶</el-link>
                        <el-link>集成水槽</el-link>
                        <el-link>消毒柜</el-link>
                        <el-link>洗碗机</el-link>
                        <el-link>电热水器</el-link>
                        <el-link>燃气热水器</el-link>
                        <el-link>壁挂炉</el-link>
                        <el-link>空气能热水</el-link>
                        <el-link>嵌入式厨电</el-link>
                        <el-link>太阳能热水</el-link>
                        <el-link>烟机灶具配件</el-link>
                        <el-link>热水龙头</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 45px;">
                        <el-link class="font">
                          厨卫小电
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd>
                        <el-link>破壁机</el-link>
                        <el-link>咖啡机</el-link>
                        <el-link>榨汁机</el-link>
                        <el-link>电炖锅</el-link>
                        <el-link>果蔬净化清洗机</el-link>
                        <el-link>早餐机</el-link>
                        <el-link>电烤箱</el-link>
                        <el-link>厨师机</el-link>
                        <el-link>料理机</el-link>
                        <el-link>电饼铛</el-link>
                        <el-link>电热水壶</el-link>
                        <el-link>面包机</el-link>
                        <el-link>电火锅</el-link>
                        <el-link>空气炸锅</el-link>
                        <el-link>养生壶</el-link>
                        <el-link>电磁炉</el-link>
                        <el-link>电饭煲</el-link>
                        <el-link>电压力锅</el-link>
                        <el-link>微波炉</el-link>
                        <el-link>面条机</el-link>
                        <el-link>电陶炉</el-link>
                        <el-link>点烧烤炉</el-link>
                        <el-link>煮蛋器</el-link>
                        <el-link>电热盒饭</el-link>
                        <el-link>豆浆机</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 45px;">
                        <el-link class="font">
                          环境电器
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd style="display: flex">
                        <el-link>取暖器</el-link>
                        <el-link>空气净化器</el-link>
                        <el-link>加湿器</el-link>
                        <el-link>净化器</el-link>
                        <el-link>饮水机</el-link>
                        <el-link>茶吧机</el-link>
                        <el-link>除湿机</el-link>
                        <el-link>电话机</el-link>
                        <el-link>电风扇</el-link>
                        <el-link>生活电器配件</el-link>
                        <el-link>冷风扇</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 47px;">
                        <el-link class="font">
                          个护健康
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd>
                        <el-link>剃须刀</el-link>
                        <el-link>电动牙刷</el-link>
                        <el-link>冲牙器</el-link>
                        <el-link>电吹风</el-link>
                        <el-link>直发棒</el-link>
                        <el-link>理发器</el-link>
                        <el-link>美容仪</el-link>
                        <el-link>脱毛仪</el-link>
                        <el-link>按摩器</el-link>
                        <el-link>按摩椅</el-link>
                        <el-link>足浴盆</el-link>
                        <el-link>挂烫机</el-link>
                        <el-link>电动牙刷头</el-link>
                        <el-link>干衣机</el-link>
                      </dd>
                    </dl>

                  </div>
                </div>

                <div class="cate_item" v-show="this.hovers === 2" @mouseleave="handleMouseLeave">
                  <div style="width: 100%; display: flex; padding: 24px 24px;">
                    <el-link style="width: 82px; height: 31px; background-color: black; color: #FFFFFF;">
                      电脑
                      <i class="el-icon-arrow-right"></i>
                    </el-link>
                    <el-link style="width: 172px; height: 31px; background-color: black; color: #FFFFFF; margin-left: 5px">
                      开店设备一站购买
                      <i class="el-icon-arrow-right"></i>
                    </el-link>

                    <el-link style="width: 100px; height: 31px; background-color: black; color: #FFFFFF; margin-left: 5px">
                      企业采购
                      <i class="el-icon-arrow-right"></i>
                    </el-link>
                  </div>

                  <div style="width: 100%">

                    <dl>
                      <dt style="float: left; margin-left: 80px;">
                        <el-link class="font">
                          电视
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>

                      <dd>
                        <el-link>游戏电视</el-link>
                        <el-link>艺术电视</el-link>
                        <el-link>miniled电视</el-link>
                        <el-link>会议电视</el-link>
                        <el-link>电视音响套装</el-link>
                        <el-link>75寸电视</el-link>
                        <el-link>85寸电视</el-link>
                        <el-link>98寸电视</el-link>
                        <el-link>100寸电视</el-link>
                        <el-link>电视挂架</el-link>
                        <el-link>电视遥控器</el-link>
                        <el-link>电视推车</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 80px;">
                        <el-link class="font">
                          空调
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd>
                        <el-link>新风空调</el-link>
                        <el-link>以旧换新</el-link>
                        <el-link>空调挂机</el-link>
                        <el-link>空调机柜</el-link>
                        <el-link>空调套装</el-link>
                        <el-link>新一级能效</el-link>
                        <el-link>挂机1.5匹</el-link>
                        <el-link>柜机3匹</el-link>
                        <el-link>变频空调</el-link>
                        <el-link>中央空调</el-link>
                        <el-link>移动空调</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 65px;">
                        <el-link class="font">
                          洗衣机
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd style="display: flex">
                        <el-link>滚筒洗衣机</el-link>
                        <el-link>洗烘一体机</el-link>
                        <el-link>波轮洗衣机</el-link>
                        <el-link>洗烘套装</el-link>
                        <el-link>迷你洗衣机</el-link>
                        <el-link>洗衣机配件</el-link>
                        <el-link>烘干机</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 80px;">
                        <el-link class="font">
                          冰箱
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd style="display: flex">
                        <el-link>多门</el-link>
                        <el-link>对开门</el-link>
                        <el-link>三门</el-link>
                        <el-link>双门</el-link>
                        <el-link>冰洗套装</el-link>
                        <el-link>冷柜/冰吧</el-link>
                        <el-link>酒柜</el-link>
                        <el-link>冰箱配件</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 47px;">
                        <el-link class="font">
                          厨卫大电
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd>
                        <el-link>油烟机</el-link>
                        <el-link>灶具</el-link>
                        <el-link>烟灶套装</el-link>
                        <el-link>集成灶</el-link>
                        <el-link>集成水槽</el-link>
                        <el-link>消毒柜</el-link>
                        <el-link>洗碗机</el-link>
                        <el-link>电热水器</el-link>
                        <el-link>燃气热水器</el-link>
                        <el-link>壁挂炉</el-link>
                        <el-link>空气能热水</el-link>
                        <el-link>嵌入式厨电</el-link>
                        <el-link>太阳能热水</el-link>
                        <el-link>烟机灶具配件</el-link>
                        <el-link>热水龙头</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 45px;">
                        <el-link class="font">
                          厨卫小电
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd>
                        <el-link>破壁机</el-link>
                        <el-link>咖啡机</el-link>
                        <el-link>榨汁机</el-link>
                        <el-link>电炖锅</el-link>
                        <el-link>果蔬净化清洗机</el-link>
                        <el-link>早餐机</el-link>
                        <el-link>电烤箱</el-link>
                        <el-link>厨师机</el-link>
                        <el-link>料理机</el-link>
                        <el-link>电饼铛</el-link>
                        <el-link>电热水壶</el-link>
                        <el-link>面包机</el-link>
                        <el-link>电火锅</el-link>
                        <el-link>空气炸锅</el-link>
                        <el-link>养生壶</el-link>
                        <el-link>电磁炉</el-link>
                        <el-link>电饭煲</el-link>
                        <el-link>电压力锅</el-link>
                        <el-link>微波炉</el-link>
                        <el-link>面条机</el-link>
                        <el-link>电陶炉</el-link>
                        <el-link>点烧烤炉</el-link>
                        <el-link>煮蛋器</el-link>
                        <el-link>电热盒饭</el-link>
                        <el-link>豆浆机</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 45px;">
                        <el-link class="font">
                          环境电器
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd style="display: flex">
                        <el-link>取暖器</el-link>
                        <el-link>空气净化器</el-link>
                        <el-link>加湿器</el-link>
                        <el-link>净化器</el-link>
                        <el-link>饮水机</el-link>
                        <el-link>茶吧机</el-link>
                        <el-link>除湿机</el-link>
                        <el-link>电话机</el-link>
                        <el-link>电风扇</el-link>
                        <el-link>生活电器配件</el-link>
                        <el-link>冷风扇</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 47px;">
                        <el-link class="font">
                          个护健康
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd>
                        <el-link>剃须刀</el-link>
                        <el-link>电动牙刷</el-link>
                        <el-link>冲牙器</el-link>
                        <el-link>电吹风</el-link>
                        <el-link>直发棒</el-link>
                        <el-link>理发器</el-link>
                        <el-link>美容仪</el-link>
                        <el-link>脱毛仪</el-link>
                        <el-link>按摩器</el-link>
                        <el-link>按摩椅</el-link>
                        <el-link>足浴盆</el-link>
                        <el-link>挂烫机</el-link>
                        <el-link>电动牙刷头</el-link>
                        <el-link>干衣机</el-link>
                      </dd>
                    </dl>
                  </div>
                </div>
                <div class="cate_item" v-show="this.hovers === 3" @mouseleave="handleMouseLeave">
                  <div style="width: 100%; display: flex; padding: 24px 24px;">
                    <el-link style="width: 82px; height: 31px; background-color: black; color: #FFFFFF;">
                      家电馆
                      <i class="el-icon-arrow-right"></i>
                    </el-link>
                    <el-link style="width: 172px; height: 31px; background-color: black; color: #FFFFFF; margin-left: 5px">
                      开店设备一站购买
                      <i class="el-icon-arrow-right"></i>
                    </el-link>

                    <el-link style="width: 100px; height: 31px; background-color: black; color: #FFFFFF; margin-left: 5px">
                      企业采购
                      <i class="el-icon-arrow-right"></i>
                    </el-link>
                  </div>

                  <div style="width: 100%">

                    <dl>
                      <dt style="float: left; margin-left: 80px;">
                        <el-link class="font">
                          电视
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>

                      <dd>
                        <el-link>游戏电视</el-link>
                        <el-link>艺术电视</el-link>
                        <el-link>miniled电视</el-link>
                        <el-link>会议电视</el-link>
                        <el-link>电视音响套装</el-link>
                        <el-link>75寸电视</el-link>
                        <el-link>85寸电视</el-link>
                        <el-link>98寸电视</el-link>
                        <el-link>100寸电视</el-link>
                        <el-link>电视挂架</el-link>
                        <el-link>电视遥控器</el-link>
                        <el-link>电视推车</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 80px;">
                        <el-link class="font">
                          空调
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd>
                        <el-link>新风空调</el-link>
                        <el-link>以旧换新</el-link>
                        <el-link>空调挂机</el-link>
                        <el-link>空调机柜</el-link>
                        <el-link>空调套装</el-link>
                        <el-link>新一级能效</el-link>
                        <el-link>挂机1.5匹</el-link>
                        <el-link>柜机3匹</el-link>
                        <el-link>变频空调</el-link>
                        <el-link>中央空调</el-link>
                        <el-link>移动空调</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 65px;">
                        <el-link class="font">
                          洗衣机
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd style="display: flex">
                        <el-link>滚筒洗衣机</el-link>
                        <el-link>洗烘一体机</el-link>
                        <el-link>波轮洗衣机</el-link>
                        <el-link>洗烘套装</el-link>
                        <el-link>迷你洗衣机</el-link>
                        <el-link>洗衣机配件</el-link>
                        <el-link>烘干机</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 80px;">
                        <el-link class="font">
                          冰箱
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd style="display: flex">
                        <el-link>多门</el-link>
                        <el-link>对开门</el-link>
                        <el-link>三门</el-link>
                        <el-link>双门</el-link>
                        <el-link>冰洗套装</el-link>
                        <el-link>冷柜/冰吧</el-link>
                        <el-link>酒柜</el-link>
                        <el-link>冰箱配件</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 47px;">
                        <el-link class="font">
                          厨卫大电
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd>
                        <el-link>油烟机</el-link>
                        <el-link>灶具</el-link>
                        <el-link>烟灶套装</el-link>
                        <el-link>集成灶</el-link>
                        <el-link>集成水槽</el-link>
                        <el-link>消毒柜</el-link>
                        <el-link>洗碗机</el-link>
                        <el-link>电热水器</el-link>
                        <el-link>燃气热水器</el-link>
                        <el-link>壁挂炉</el-link>
                        <el-link>空气能热水</el-link>
                        <el-link>嵌入式厨电</el-link>
                        <el-link>太阳能热水</el-link>
                        <el-link>烟机灶具配件</el-link>
                        <el-link>热水龙头</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 45px;">
                        <el-link class="font">
                          厨卫小电
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd>
                        <el-link>破壁机</el-link>
                        <el-link>咖啡机</el-link>
                        <el-link>榨汁机</el-link>
                        <el-link>电炖锅</el-link>
                        <el-link>果蔬净化清洗机</el-link>
                        <el-link>早餐机</el-link>
                        <el-link>电烤箱</el-link>
                        <el-link>厨师机</el-link>
                        <el-link>料理机</el-link>
                        <el-link>电饼铛</el-link>
                        <el-link>电热水壶</el-link>
                        <el-link>面包机</el-link>
                        <el-link>电火锅</el-link>
                        <el-link>空气炸锅</el-link>
                        <el-link>养生壶</el-link>
                        <el-link>电磁炉</el-link>
                        <el-link>电饭煲</el-link>
                        <el-link>电压力锅</el-link>
                        <el-link>微波炉</el-link>
                        <el-link>面条机</el-link>
                        <el-link>电陶炉</el-link>
                        <el-link>点烧烤炉</el-link>
                        <el-link>煮蛋器</el-link>
                        <el-link>电热盒饭</el-link>
                        <el-link>豆浆机</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 45px;">
                        <el-link class="font">
                          环境电器
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd style="display: flex">
                        <el-link>取暖器</el-link>
                        <el-link>空气净化器</el-link>
                        <el-link>加湿器</el-link>
                        <el-link>净化器</el-link>
                        <el-link>饮水机</el-link>
                        <el-link>茶吧机</el-link>
                        <el-link>除湿机</el-link>
                        <el-link>电话机</el-link>
                        <el-link>电风扇</el-link>
                        <el-link>生活电器配件</el-link>
                        <el-link>冷风扇</el-link>
                      </dd>
                    </dl>

                    <dl>
                      <dt style="float: left; margin-left: 47px;">
                        <el-link class="font">
                          个护健康
                          <i class="el-icon-arrow-right"></i>
                        </el-link>
                      </dt>
                      <dd>
                        <el-link>剃须刀</el-link>
                        <el-link>电动牙刷</el-link>
                        <el-link>冲牙器</el-link>
                        <el-link>电吹风</el-link>
                        <el-link>直发棒</el-link>
                        <el-link>理发器</el-link>
                        <el-link>美容仪</el-link>
                        <el-link>脱毛仪</el-link>
                        <el-link>按摩器</el-link>
                        <el-link>按摩椅</el-link>
                        <el-link>足浴盆</el-link>
                        <el-link>挂烫机</el-link>
                        <el-link>电动牙刷头</el-link>
                        <el-link>干衣机</el-link>
                      </dd>
                    </dl>
                  </div>
                </div>
              </div>




            </el-menu>

          </el-col>
          <el-col :span="13" style="margin-left: 10px; z-index: 1">
            <template>
              <el-carousel indicator-position="outside" height="600px">
                <el-carousel-item v-for="item in imgList" :key="item">
<!--                  <h3>{{ item }}</h3>-->
                  <el-link :underline="false" :href="item.a">
                    <img :src="item.img" style="height: 600px; width: 890px">
                  </el-link>
                </el-carousel-item>
              </el-carousel>
            </template>
          </el-col>

          <!--展示栏第三块start-->
          <el-col :span="6" style="margin-left: 10px">
            <div style="width: 100%; height: 600px; background-color: #FFFFFF">
              <div style="height: 190px;margin-bottom: 15px;">
                <div style="height: 94px">
                  <img src="/static/16196625695897672.jpg" style="width: 70px; height: 70px; border-radius: 50%; margin: 20px">
                </div>
                <div style="margin-bottom: 20px">
                  <span>你好欢迎来到二手交易</span>
                </div>
                <div>
                  <el-button type="warning" round>我要买</el-button>
                  <el-button type="warning" round>我要卖</el-button>
                </div>
              </div>

              <div style="height: 190px;">
                <el-menu mode="horizontal" style="display: inline-block; margin-bottom: 20px">
                  <el-menu-item style="font-size: 18px;" @mouseover.native="handleMouseHelp(1)">公告</el-menu-item>
                  <el-menu-item style="font-size: 18px; margin: 0px 60px"  @mouseover.native="handleMouseHelp(2)">指南</el-menu-item>
                  <el-menu-item style="font-size: 18px; margin-left: 15px"  @mouseover.native="handleMouseHelp(3)">帮助</el-menu-item>
                </el-menu>
                <div>
                  <div style="width: 80%;display: grid;" v-if="this.helpElement === 1">
                    <el-link style="margin-bottom: 15px; margin-left: 44px" :underline="false">关于“终身找回包赔”服务的有关声明</el-link>
                    <el-link style="margin-bottom: 15px" :underline="false">【公告】行业倡议书5645</el-link>
                    <el-link style="margin-bottom: 15px" :underline="false">【公告】行业倡议书6666</el-link>
                  </div>

                  <div style="width: 80%;display: grid;" v-else-if="this.helpElement === 2">
                    <el-link style="margin-bottom: 15px" :underline="false">【指南】如何注册二手交易平台？</el-link>
                    <el-link style="margin-bottom: 15px" :underline="false">【指南】如何登录二手交易平台？</el-link>
                    <el-link style="margin-bottom: 15px" :underline="false">【指南】忘记密码如何找回？</el-link>
                  </div>

                  <div style="width: 80%;display: grid;" v-else-if="this.helpElement === 3">
                    <el-link style="margin-bottom: 15px" :underline="false">66“终身找回包赔”服务的有关声明</el-link>
                    <el-link style="margin-bottom: 15px" :underline="false">【公告】行业倡议书5645</el-link>
                    <el-link style="margin-bottom: 15px" :underline="false">【公告】行业倡议书6666</el-link>
                  </div>
                </div>

              </div>

              <div style="height: 190px; margin: 14px 0px">
                <div style="padding: 0 25px; height: 100%;">
                  <el-menu
                    mode="horizontal"
                    style="height: 100%; width: 100%; justify-content: center"
                  >
                    <el-menu-item style="height:80px; width: 84px " class="li-a">
                      <el-link :underline="false" >
                        <div style="display: inline-grid; justify-content: center">
                          <i class="icon iconfont icon-zhanghao" style="font-size: 40px"></i>
                          <span style="margin-top: -20px">安心购</span>
                        </div>
                      </el-link>
                    </el-menu-item>

                    <el-menu-item style="height:80px; width: 84px " class="li-a">
                      <el-link :underline="false" >
                        <div style="display: inline-grid; justify-content: center">
                          <i class="icon iconfont icon-zhanghao" style="font-size: 40px"></i>
                          <span style="margin-top: -20px">收藏的宝贝</span>
                        </div>
                      </el-link>
                    </el-menu-item>

                    <el-menu-item style="height:80px; width: 84px " class="li-a">
                      <el-link :underline="false" >
                        <div style="display: inline-grid; justify-content: center">
                          <i class="icon iconfont icon-zhanghao" style="font-size: 40px"></i>
                          <span style="margin-top: -20px">我的足迹</span>
                        </div>
                      </el-link>
                    </el-menu-item>

                    <el-menu-item style="height:80px; width: 84px " class="li-a">
                      <el-link :underline="false" >
                        <div style="display: inline-grid; justify-content: center">
                          <i class="icon iconfont icon-zhanghao" style="font-size: 40px"></i>
                          <span style="margin-top: -20px">买过的店铺</span>
                        </div>
                      </el-link>
                    </el-menu-item>

                    <el-menu-item style="height:80px; width: 84px " class="li-a">
                      <el-link :underline="false" >
                        <div style="display: inline-grid; justify-content: center">
                          <i class="icon iconfont icon-zhanghao" style="font-size: 40px"></i>
                          <span style="margin-top: -20px">收藏的店铺</span>
                        </div>
                      </el-link>
                    </el-menu-item>

                    <el-menu-item style="height:80px; width: 84px " class="li-a">
                      <el-link :underline="false" >
                        <div style="display: inline-grid; justify-content: center">
                          <i class="icon iconfont icon-zhanghao" style="font-size: 40px"></i>
                          <span style="margin-top: -20px">未知</span>
                        </div>
                      </el-link>
                    </el-menu-item>

                    <el-menu-item style="height:80px; width: 84px " class="li-a">
                      <el-link :underline="false" >
                        <div style="display: inline-grid; justify-content: center">
                          <i class="icon iconfont icon-zhanghao" style="font-size: 40px"></i>
                          <span style="margin-top: -20px">未知</span>
                        </div>
                      </el-link>
                    </el-menu-item>

                    <el-menu-item style="height:80px; width: 84px " class="li-a">
                      <el-link :underline="false" >
                        <div style="display: inline-grid; justify-content: center">
                          <i class="icon iconfont icon-zhanghao" style="font-size: 40px"></i>
                          <span style="margin-top: -20px">未知</span>
                        </div>
                      </el-link>
                    </el-menu-item>
                  </el-menu>
                </div>
              </div>


            </div>

          </el-col>
          <!--展示栏第三块end-->
        </el-row>
      </div>
      <!--商品类型 走马灯 end-->


      <!--推荐商品 start-->
      <div style="width: 85%; height: 70px; margin: auto; margin-top: 50px; ">
        <h3 style="font-size: 30px">为你推荐</h3>
      </div>

      <div style="width: 85%; margin: auto">
        <el-row style="margin-left: 70px; margin-bottom: 100px">
          <el-col span="5" class="col-recommend">
            <el-link :underline="false" style="margin-top: 20px" href="/login">
              <div>
                <img src="/static/2-2.png" style="width: 280px; height: 280px">
              </div>
              <div>
                <p class="info_name">叕二叕 渐变色丝袜女辣妹黑丝纯欲白丝夏季超薄款防勾丝灰色连裤袜子 渐变灰黑</p>
                <div style="display: flex">
                  <i style="font-size: 18px; color: red; font-weight: 600">￥</i>
                  <span style="font-size: 20px; color: red; font-weight: 600">699</span>
                </div>
              </div>
              <div></div>
            </el-link>
          </el-col>

          <el-col span="4.8" class="col-recommend">
            <el-link :underline="false" style="margin-top: 20px">
              <div>
                <img src="/static/2-2.png" style="width: 280px; height: 280px">
              </div>
              <div>
                <p class="info_name">叕二叕 渐变色丝袜女辣妹黑丝纯欲白丝夏季超薄款防勾丝灰色连裤袜子 渐变灰黑</p>
                <div style="display: flex">
                  <i style="font-size: 18px; color: red; font-weight: 600">￥</i>
                  <span style="font-size: 20px; color: red; font-weight: 600">699</span>
                </div>
              </div>
              <div></div>
            </el-link>
          </el-col>

          <el-col span="4.8" class="col-recommend">
            <el-link :underline="false" style="margin-top: 20px">
              <div>
                <img src="/static/2-2.png" style="width: 280px; height: 280px">
              </div>
              <div>
                <p class="info_name">叕二叕 渐变色丝袜女辣妹黑丝纯欲白丝夏季超薄款防勾丝灰色连裤袜子 渐变灰黑</p>
                <div style="display: flex">
                  <i style="font-size: 18px; color: red; font-weight: 600">￥</i>
                  <span style="font-size: 20px; color: red; font-weight: 600">699</span>
                </div>
              </div>
              <div></div>
            </el-link>
          </el-col>

          <el-col span="4.8" class="col-recommend">
            <el-link :underline="false" style="margin-top: 20px">
              <div>
                <img src="/static/2-2.png" style="width: 280px; height: 280px">
              </div>
              <div>
                <p class="info_name">叕二叕 渐变色丝袜女辣妹黑丝纯欲白丝夏季超薄款防勾丝灰色连裤袜子 渐变灰黑</p>
                <div style="display: flex">
                  <i style="font-size: 18px; color: red; font-weight: 600">￥</i>
                  <span style="font-size: 20px; color: red; font-weight: 600">699</span>
                </div>
              </div>
              <div></div>
            </el-link>
          </el-col>

          <el-col span="4.8" class="col-recommend">
            <el-link :underline="false" style="margin-top: 20px">
              <div>
                <img src="/static/2-2.png" style="width: 280px; height: 280px">
              </div>
              <div>
                <p class="info_name">叕二叕 渐变色丝袜女辣妹黑丝纯欲白丝夏季超薄款防勾丝灰色连裤袜子 渐变灰黑</p>
                <div style="display: flex">
                  <i style="font-size: 18px; color: red; font-weight: 600">￥</i>
                  <span style="font-size: 20px; color: red; font-weight: 600">699</span>
                </div>
              </div>
              <div></div>
            </el-link>
          </el-col>
        </el-row>
      </div>
      <!--推荐商品 end-->

      <!--公共start-->
      <CommonEnd></CommonEnd>
      <!--公共end-->

    </div>
    <!--body end-->

  </div>
</template>

<script>
import Common from "../common/Common.vue";
import CommonEnd from "../common/CommonEnd.vue";

export default {
  name: "Index",
  components: {CommonEnd, Common},

  data(){
    return{
      imgList: [
        {a:"/login", img:"/static/1-1.png"},
        {a:"/login", img:"/static/1-2.png"},
        {a:"/login", img:"static/1-3.png"},
        {a:"/login", img:"static/1-4.png"},
      ],
      // 商品分类影藏元素控制
      hovers: null,

      // 公共 指南 帮助 影藏元素控制
      helpElement: 1,


    }
  },

  created() {
  },

  methods:{
    // 商品分类影藏元素控制
    handleMouseMove(number) {
      this.hovers = number;

    },
    handleMouseLeave(){
      this.hovers = null
    },

    // 公告影藏元素控制
    handleMouseHelp(number){
      this.helpElement = null
      this.helpElement = number
      console.log(this.helpElement)
    },

  },
}
</script>

<style scoped>



.li-a {
  height: 60px;
  width: 84px;
  justify-content: center;
}

.font{
  font-weight: 600;
}

.cate_item{
  width: 1000px;
  height: 600px;
  background-color: #fdfdfd;
  margin-left: 280px;
  transform: translateY(-601px);
  position: relative;
  z-index: 999;
  box-shadow: 5px 5px 10px rgb(200 206 213);
}





.cate_menu_line{
  padding: 0 2px;
  font-size: 20px;
  margin-top: -13px;
}


.el-menu-item[data-v-70e3473e]{
  height: 30px;
  /*float: left;*/
}

.col-recommend{
  height: 405px;
  box-shadow: 5px 5px 10px rgb(200 206 213);
  margin-right: 13px;

}

.info_name{
  width: 260px;
  color: #666;
  font-size: 18px;
  line-height: 1.2;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
}

.el-link.el-link--default{
  color: black;
  padding: 0 2px;
}

.el-link.el-link--default {
  font-size: 17px;
}
.el-menu-item {
  /*height: 40px; !* Set the desired height (e.g., 50px) *!*/
  /*margin: auto;*/
  /*font-size: 14px;*/
  padding: 0 2px;
  display: flex;
  /*justify-content: center;*/
}


/*走马灯依赖*/
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}




</style>
