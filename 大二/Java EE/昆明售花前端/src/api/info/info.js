import request from '@/utils/request'

// 查询鲜花信息列表
export function listInfo(query) {
  return request({
    url: '/info/info/list',
    method: 'get',
    params: query
  })
}

// 查询鲜花信息详细
export function getInfo(flowerId) {
  return request({
    url: '/info/info/' + flowerId,
    method: 'get'
  })
}

// 新增鲜花信息
export function addInfo(data) {
  return request({
    url: '/info/info',
    method: 'post',
    data: data
  })
}

// 修改鲜花信息
export function updateInfo(data) {
  return request({
    url: '/info/info',
    method: 'put',
    data: data
  })
}

// 删除鲜花信息
export function delInfo(flowerId) {
  return request({
    url: '/info/info/' + flowerId,
    method: 'delete'
  })
}
