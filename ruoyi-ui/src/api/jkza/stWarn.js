import request from '@/utils/request'

// 查询预警信息列表
export function listStWarn(query) {
  return request({
    url: '/jkza/stWarn/list',
    method: 'get',
    params: query
  })
}

// 查询预警信息详细
export function getStWarn(id) {
  return request({
    url: '/jkza/stWarn/' + id,
    method: 'get'
  })
}

// 新增预警信息
export function addStWarn(data) {
  return request({
    url: '/jkza/stWarn',
    method: 'post',
    data: data
  })
}

// 修改预警信息
export function updateStWarn(data) {
  return request({
    url: '/jkza/stWarn',
    method: 'put',
    data: data
  })
}

// 删除预警信息
export function delStWarn(id) {
  return request({
    url: '/jkza/stWarn/' + id,
    method: 'delete'
  })
}
