import request from '@/utils/request'

// 查询低洼点列表
export function listYjLowPoint(query) {
  return request({
    url: '/jkza/yjLowPoint/list',
    method: 'get',
    params: query
  })
}

// 查询低洼点详细
export function getYjLowPoint(id) {
  return request({
    url: '/jkza/yjLowPoint/' + id,
    method: 'get'
  })
}

// 新增低洼点
export function addYjLowPoint(data) {
  return request({
    url: '/jkza/yjLowPoint',
    method: 'post',
    data: data
  })
}

// 修改低洼点
export function updateYjLowPoint(data) {
  return request({
    url: '/jkza/yjLowPoint',
    method: 'put',
    data: data
  })
}

// 删除低洼点
export function delYjLowPoint(id) {
  return request({
    url: '/jkza/yjLowPoint/' + id,
    method: 'delete'
  })
}
