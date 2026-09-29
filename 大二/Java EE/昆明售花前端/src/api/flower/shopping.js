import request from '@/utils/request'

// 更新购物车数量
export function updateCartQuantity(data) {
  return request({
    url: '/flower/shopping/updateQuantity',
    method: 'put',
    data: data
  })
}

// 获取购物车详情
export function getCartDetails() {
  return request({
    url: '/flower/shopping/cart',
    method: 'get'
  })
}

// 清空购物车
export function clearUserCart() {
  return request({
    url: '/flower/shopping/clear',
    method: 'delete'
  })
}

// 获取购物车统计
export function getCartSummary() {
  return request({
    url: '/flower/shopping/summary',
    method: 'get'
  })
}