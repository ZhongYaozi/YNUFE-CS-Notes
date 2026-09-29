<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="鲜花名称" prop="flowerName">
        <el-input
          v-model="queryParams.flowerName"
          placeholder="请输入鲜花名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="价格范围">
        <el-input
          v-model="queryParams.minPrice"
          placeholder="最低价格"
          style="width: 100px"
          clearable
        />
        <span style="margin: 0 5px">-</span>
        <el-input
          v-model="queryParams.maxPrice"
          placeholder="最高价格"
          style="width: 100px"
          clearable
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 购物车统计信息 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="18">
        <el-alert
          :title="cartSummaryText"
          type="success"
          :closable="false"
          show-icon>
        </el-alert>
      </el-col>
      <el-col :span="6">
        <div class="top-right-btn">
          <el-button 
            type="warning" 
            plain 
            size="small" 
            icon="Delete" 
            @click="handleClearCart" 
            :disabled="cartSummary.totalItems == 0"
          >
            清空购物车
          </el-button>
          <el-button 
            type="success" 
            size="small" 
            icon="ShoppingCart" 
            @click="handleCheckout" 
            :disabled="cartSummary.totalItems == 0"
          >
            去结算({{ cartSummary.totalItems }})
          </el-button>
        </div>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infoList">
      <el-table-column label="鲜花ID" align="center" prop="flowerId" width="80" />
      <el-table-column label="鲜花名称" align="center" prop="flowerName" />
      <el-table-column label="鲜花图片" align="center" prop="flowerImageUrl" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.flowerImageUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="单价(元)" align="center" prop="flowerPrice" width="100">
        <template #default="scope">
          <span class="price-text">¥{{ parseFloat(scope.row.flowerPrice || 0).toFixed(2) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注信息" align="center" prop="remark" />
      <el-table-column label="购买数量" align="center" width="150">
        <template #default="scope">
          <el-input-number 
            v-model="scope.row.cartQuantity" 
            :min="0" 
            :max="999"
            size="small"
            controls-position="right"
            @change="handleQuantityChange(scope.row)"
            style="width: 130px"
          />
        </template>
      </el-table-column>
      <el-table-column label="小计" align="center" width="100">
        <template #default="scope">
          <span class="subtotal-text">¥{{ calculateSubtotal(scope.row) }}</span>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 购物车详情对话框 -->
    <el-dialog title="购物车详情" v-model="cartDialogOpen" width="800px" append-to-body>
      <el-table :data="cartDetails" v-loading="cartLoading">
        <el-table-column label="鲜花名称" prop="flowerName" />
        <el-table-column label="单价" width="100">
          <template #default="scope">
            ¥{{ parseFloat(scope.row.flowerPrice || 0).toFixed(2) }}
          </template>
        </el-table-column>
        <el-table-column label="数量" prop="quantity" width="80" />
        <el-table-column label="小计" width="100">
          <template #default="scope">
            ¥{{ (scope.row.quantity * parseFloat(scope.row.flowerPrice || 0)).toFixed(2) }}
          </template>
        </el-table-column>
        <el-table-column label="操作" width="100">
          <template #default="scope">
            <el-button link type="primary" size="small" @click="removeFromCart(scope.row)">
              移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <template #footer>
        <div class="dialog-footer">
          <div style="float: left; font-size: 16px; font-weight: bold; color: #f56c6c;">
            总计：¥{{ cartSummary.totalAmount }}
          </div>
          <el-button @click="cartDialogOpen = false">关闭</el-button>
          <el-button type="primary" @click="handleCheckout">去结算</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="FlowerShopping">
import { listInfoplus, updateCartQuantity, getCartDetails, clearUserCart, getCartSummary } from "@/api/infoplus/infoplus"

const { proxy } = getCurrentInstance()

const infoList = ref([])
const loading = ref(true)
const showSearch = ref(true)
const total = ref(0)
const cartDialogOpen = ref(false)
const cartDetails = ref([])
const cartLoading = ref(false)

// 购物车统计数据
const cartSummary = ref({
  totalItems: 0,
  totalQuantity: 0,
  totalAmount: '0.00'
})

// 计算购物车统计文本
const cartSummaryText = computed(() => {
  return `购物车：${cartSummary.value.totalItems}种花卉，共${cartSummary.value.totalQuantity}支，总计¥${cartSummary.value.totalAmount}`
})

const data = reactive({
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    flowerName: null,
    minPrice: null,
    maxPrice: null
  }
})

const { queryParams } = toRefs(data)

/** 查询鲜花信息列表 */
function getList() {
  loading.value = true
  listInfoplus(queryParams.value).then(response => {
    infoList.value = response.rows.map(item => ({
      ...item,
      cartQuantity: 0 // 初始化购买数量
    }))
    total.value = response.total
    loading.value = false
    
    // 加载完列表后，更新购物车数量显示
    loadCartSummary()
  })
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  queryParams.value.minPrice = null
  queryParams.value.maxPrice = null
  handleQuery()
}

/** 计算小计 */
function calculateSubtotal(row) {
  const quantity = row.cartQuantity || 0
  const price = parseFloat(row.flowerPrice) || 0
  return (quantity * price).toFixed(2)
}

/** 数量变化处理 */
function handleQuantityChange(row) {
  const quantity = row.cartQuantity || 0
  
  const cartItem = {
    flowerId: row.flowerId,
    quantity: quantity
  }
  
  updateCartQuantity(cartItem).then(response => {
    if (response.code === 200) {
      proxy.$modal.msgSuccess(quantity > 0 ? "已更新购物车" : "已从购物车移除")
      loadCartSummary()
    }
  }).catch(() => {
    // 如果失败，恢复原始数量
    proxy.$nextTick(() => {
      row.cartQuantity = 0
    })
    proxy.$modal.msgError("操作失败")
  })
}

/** 加载购物车统计 */
function loadCartSummary() {
  getCartSummary().then(response => {
    if (response.code === 200) {
      cartSummary.value = response.data || {
        totalItems: 0,
        totalQuantity: 0,
        totalAmount: '0.00'
      }
    }
  })
  
  // 同时加载购物车详情来更新页面显示的数量
  getCartDetails().then(response => {
    if (response.code === 200) {
      const cartItems = response.data || []
      updateDisplayQuantities(cartItems)
    }
  })
}

/** 更新页面显示数量 */
function updateDisplayQuantities(cartItems) {
  infoList.value.forEach(flower => {
    const cartItem = cartItems.find(item => item.flowerId === flower.flowerId)
    flower.cartQuantity = cartItem ? cartItem.quantity : 0
  })
}

/** 清空购物车 */
function handleClearCart() {
  proxy.$modal.confirm('确认清空购物车吗？', "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning"
  }).then(() => {
    clearUserCart().then(response => {
      if (response.code === 200) {
        proxy.$modal.msgSuccess("购物车已清空")
        loadCartSummary()
        // 清空页面显示的数量
        infoList.value.forEach(item => {
          item.cartQuantity = 0
        })
      }
    })
  })
}

/** 查看购物车详情 */
function handleViewCart() {
  cartLoading.value = true
  cartDialogOpen.value = true
  
  getCartDetails().then(response => {
    if (response.code === 200) {
      cartDetails.value = response.data || []
    }
    cartLoading.value = false
  }).catch(() => {
    cartLoading.value = false
  })
}

/** 从购物车移除商品 */
function removeFromCart(cartItem) {
  const updateData = {
    flowerId: cartItem.flowerId,
    quantity: 0
  }
  
  updateCartQuantity(updateData).then(response => {
    if (response.code === 200) {
      proxy.$modal.msgSuccess("已从购物车移除")
      handleViewCart() // 刷新购物车详情
      loadCartSummary() // 刷新统计
      
      // 更新列表页面的数量显示
      const flower = infoList.value.find(item => item.flowerId === cartItem.flowerId)
      if (flower) {
        flower.cartQuantity = 0
      }
    }
  })
}

/** 去结算 */
function handleCheckout() {
  if (cartSummary.value.totalItems === 0) {
    proxy.$modal.msgWarning("购物车为空，请先选择商品")
    return
  }
  
  // 这里可以跳转到结算页面或者显示结算对话框
  proxy.$modal.msgInfo("跳转到结算页面功能待开发")
  // proxy.$router.push('/flower/checkout')
}

// 页面加载时获取列表
getList()
</script>

<style scoped>
.price-text {
  color: #e6a23c;
  font-weight: bold;
  font-size: 14px;
}

.subtotal-text {
  color: #f56c6c;
  font-weight: bold;
  font-size: 14px;
}

.top-right-btn {
  float: right;
}

.top-right-btn .el-button {
  margin-left: 8px;
}
</style>