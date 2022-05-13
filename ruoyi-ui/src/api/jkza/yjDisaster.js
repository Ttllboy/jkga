import request from '@/utils/request'

// 查询避灾场所列表
export function listYjDisaster(query) {
  return request({
    url: '/jkza/yjDisaster/list',
    method: 'get',
    params: query
  })
}

// 查询避灾场所详细
export function getYjDisaster(id) {
  return request({
    url: '/jkza/yjDisaster/' + id,
    method: 'get'
  })
}

// 新增避灾场所
export function addYjDisaster(data) {
  return request({
    url: '/jkza/yjDisaster',
    method: 'post',
    data: data
  })
}

// 修改避灾场所
export function updateYjDisaster(data) {
  return request({
    url: '/jkza/yjDisaster',
    method: 'put',
    data: data
  })
}

// 删除避灾场所
export function delYjDisaster(id) {
  return request({
    url: '/jkza/yjDisaster/' + id,
    method: 'delete'
  })
}
