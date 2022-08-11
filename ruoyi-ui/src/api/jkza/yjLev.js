import request from '@/utils/request'

// 查询应急等级列表
export function listYjLev(query) {
  return request({
    url: '/jkza/yjLev/list',
    method: 'get',
    params: query
  })
}

// 查询应急等级详细
export function getYjLev(id) {
  return request({
    url: '/jkza/yjLev/' + id,
    method: 'get'
  })
}

// 新增应急等级
export function addYjLev(data) {
  return request({
    url: '/jkza/yjLev',
    method: 'post',
    data: data
  })
}

// 修改应急等级
export function updateYjLev(data) {
  return request({
    url: '/jkza/yjLev',
    method: 'put',
    data: data
  })
}

// 删除应急等级
export function delYjLev(id) {
  return request({
    url: '/jkza/yjLev/' + id,
    method: 'delete'
  })
}
