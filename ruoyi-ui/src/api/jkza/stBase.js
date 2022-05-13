import request from '@/utils/request'

// 查询基础数据列表
export function listStBase(query) {
  return request({
    url: '/jkza/stBase/list',
    method: 'get',
    params: query
  })
}

// 查询基础数据详细
export function getStBase(id) {
  return request({
    url: '/jkza/stBase/' + id,
    method: 'get'
  })
}

// 新增基础数据
export function addStBase(data) {
  return request({
    url: '/jkza/stBase',
    method: 'post',
    data: data
  })
}

// 修改基础数据
export function updateStBase(data) {
  return request({
    url: '/jkza/stBase',
    method: 'put',
    data: data
  })
}

// 删除基础数据
export function delStBase(id) {
  return request({
    url: '/jkza/stBase/' + id,
    method: 'delete'
  })
}
