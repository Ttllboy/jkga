import request from '@/utils/request'

// 查询预警信息列表
export function listYjWarn(query) {
  return request({
    url: '/jkza/yjWarn/list',
    method: 'get',
    params: query
  })
}

// 查询预警信息详细
export function getYjWarn(id) {
  return request({
    url: '/jkza/yjWarn/' + id,
    method: 'get'
  })
}

// 新增预警信息
export function addYjWarn(data) {
  return request({
    url: '/jkza/yjWarn',
    method: 'post',
    data: data
  })
}

// 修改预警信息
export function updateYjWarn(data) {
  return request({
    url: '/jkza/yjWarn',
    method: 'put',
    data: data
  })
}

// 删除预警信息
export function delYjWarn(id) {
  return request({
    url: '/jkza/yjWarn/' + id,
    method: 'delete'
  })
}
