import request from '@/utils/request'

// 查询鲜花信息列表（购花中心）
export function listInfoplus(query) {
  return request({
    url: '/info/info/list', // 复用原来的鲜花信息接口
    method: 'get',
    params: query
  })
}

// 更新购物车数量
export function updateCartQuantity(data) {
  return request({
    url: '/infoplus/infoplus/updateQuantity',
    method: 'put',
    data: data
  })
}

// 获取购物车详情
export function getCartDetails() {
  return request({
    url: '/infoplus/infoplus/cart',
    method: 'get'
  })
}

// 清空购物车
export function clearUserCart() {
  return request({
    url: '/infoplus/infoplus/clear',
    method: 'delete'
  })
}

// 获取购物车统计
export function getCartSummary() {
  return request({
    url: '/infoplus/infoplus/summary',
    method: 'get'
  })
}