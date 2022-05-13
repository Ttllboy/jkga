import request from '@/utils/request'

// 查询基础数据列表
export function listBase(query) {
  return request({
    url: '/jkza/base/list',
    method: 'get',
    params: query
  })
}

// 查询基础数据详细
export function getBase(id) {
  return request({
    url: '/jkza/base/' + id,
    method: 'get'
  })
}

// 新增基础数据
export function addBase(data) {
  return request({
    url: '/jkza/base',
    method: 'post',
    data: data
  })
}

// 修改基础数据
export function updateBase(data) {
  return request({
    url: '/jkza/base',
    method: 'put',
    data: data
  })
}

// 删除基础数据
export function delBase(id) {
  return request({
    url: '/jkza/base/' + id,
    method: 'delete'
  })
}
