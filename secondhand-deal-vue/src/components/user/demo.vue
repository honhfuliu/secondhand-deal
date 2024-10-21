<template>
  <div style="width: 100%; float: left">
    <div v-for="(values, key) in commodityInfo.commoditySkuHeader" :key="key">
      <div>
        <span class="span4 title_span" style="float: left; margin: 10px 0">{{ key }}</span>
        <span style="float: left; margin: 7px 4px">:</span>
      </div>

      <div style="display: flex; flex-wrap: wrap; width: 70%">
        <div
          tabindex="1"
          class="sku-div-header"
          :class="{ 'focus': selectedSkus[key] === value }"
          v-for="(value, index) in values"
          :key="index"
          @click="selectSku(key, value)"
        >{{ value }}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // SKU 规格选项
      commodityInfo: {
        commoditySkuHeader: {
          颜色: ['黑色', '白色', '紫色'],
          尺寸: ['5.5', '5.6'],
        },
      },
      // 记录每个规格的选中状态
      selectedSkus: {}, // 每个规格选中的值
    };
  },
  methods: {
    selectSku(specKey, specValue) {
      // 更新选中状态，只会改变当前规格的选中项
      this.$set(this.selectedSkus, specKey, specValue);
    },
  },
};
</script>

<style>
.sku-div-header {
  padding: 10px;
  border: 1px solid #ccc;
  cursor: pointer;
  margin: 5px;
  border-radius: 4px;
}

.focus {
  background-color: lightcoral; /* 选中的背景颜色 */
  border-color: orange; /* 选中的边框颜色 */
  color: white;
}
</style>
