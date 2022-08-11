import request from '@/utils/request'

// 查询布控预警列表
export function listBkyj(query) {
  return request({
    url: '/jkza/bkyj/list',
    method: 'get',
    params: query
  })
}

// 查询布控预警详细
export function getBkyj(id) {
  return request({
    url: '/jkza/bkyj/' + id,
    method: 'get'
  })
}

// 新增布控预警
export function addBkyj(data) {
  return request({
    url: '/jkza/bkyj',
    method: 'post',
    data: data
  })
}

// 修改布控预警
export function updateBkyj(data) {
  return request({
    url: '/jkza/bkyj',
    method: 'put',
    data: data
  })
}

// 删除布控预警
export function delBkyj(id) {
  return request({
    url: '/jkza/bkyj/' + id,
    method: 'delete'
  })
}
