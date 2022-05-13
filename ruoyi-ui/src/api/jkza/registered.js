import request from '@/utils/request'

// 查询登记信息列表
export function listRegistered(query) {
  return request({
    url: '/jkza/registered/list',
    method: 'get',
    params: query
  })
}

// 查询登记信息详细
export function getRegistered(id) {
  return request({
    url: '/jkza/registered/' + id,
    method: 'get'
  })
}

// 新增登记信息
export function addRegistered(data) {
  return request({
    url: '/jkza/registered',
    method: 'post',
    data: data
  })
}

// 修改登记信息
export function updateRegistered(data) {
  return request({
    url: '/jkza/registered',
    method: 'put',
    data: data
  })
}

// 删除登记信息
export function delRegistered(id) {
  return request({
    url: '/jkza/registered/' + id,
    method: 'delete'
  })
}
