import request from '@/utils/request'

// 查询多跨协同列表
export function listYjSynergy(query) {
  return request({
    url: '/jkza/yjSynergy/list',
    method: 'get',
    params: query
  })
}

// 查询多跨协同详细
export function getYjSynergy(id) {
  return request({
    url: '/jkza/yjSynergy/' + id,
    method: 'get'
  })
}

// 新增多跨协同
export function addYjSynergy(data) {
  return request({
    url: '/jkza/yjSynergy',
    method: 'post',
    data: data
  })
}

// 修改多跨协同
export function updateYjSynergy(data) {
  return request({
    url: '/jkza/yjSynergy',
    method: 'put',
    data: data
  })
}

// 删除多跨协同
export function delYjSynergy(id) {
  return request({
    url: '/jkza/yjSynergy/' + id,
    method: 'delete'
  })
}
