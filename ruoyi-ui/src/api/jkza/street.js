import request from '@/utils/request'

// 查询街道信息列表
export function listStreet(query) {
  return request({
    url: '/jkza/street/list',
    method: 'get',
    params: query
  })
}

// 查询街道信息详细
export function getStreet(id) {
  return request({
    url: '/jkza/street/' + id,
    method: 'get'
  })
}

// 新增街道信息
export function addStreet(data) {
  return request({
    url: '/jkza/street',
    method: 'post',
    data: data
  })
}

// 修改街道信息
export function updateStreet(data) {
  return request({
    url: '/jkza/street',
    method: 'put',
    data: data
  })
}

// 删除街道信息
export function delStreet(id) {
  return request({
    url: '/jkza/street/' + id,
    method: 'delete'
  })
}
