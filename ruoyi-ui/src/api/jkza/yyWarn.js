import request from '@/utils/request'

// 查询预警信息列表
export function listYyWarn(query) {
  return request({
    url: '/jkza/yyWarn/list',
    method: 'get',
    params: query
  })
}

// 查询预警信息详细
export function getYyWarn(id) {
  return request({
    url: '/jkza/yyWarn/' + id,
    method: 'get'
  })
}

// 新增预警信息
export function addYyWarn(data) {
  return request({
    url: '/jkza/yyWarn',
    method: 'post',
    data: data
  })
}

// 修改预警信息
export function updateYyWarn(data) {
  return request({
    url: '/jkza/yyWarn',
    method: 'put',
    data: data
  })
}

// 删除预警信息
export function delYyWarn(id) {
  return request({
    url: '/jkza/yyWarn/' + id,
    method: 'delete'
  })
}
