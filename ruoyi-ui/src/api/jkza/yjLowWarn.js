import request from '@/utils/request'

// 查询低洼点预警列表
export function listYjLowWarn(query) {
  return request({
    url: '/jkza/yjLowWarn/list',
    method: 'get',
    params: query
  })
}

// 查询低洼点预警详细
export function getYjLowWarn(id) {
  return request({
    url: '/jkza/yjLowWarn/' + id,
    method: 'get'
  })
}

// 新增低洼点预警
export function addYjLowWarn(data) {
  return request({
    url: '/jkza/yjLowWarn',
    method: 'post',
    data: data
  })
}

// 修改低洼点预警
export function updateYjLowWarn(data) {
  return request({
    url: '/jkza/yjLowWarn',
    method: 'put',
    data: data
  })
}

// 删除低洼点预警
export function delYjLowWarn(id) {
  return request({
    url: '/jkza/yjLowWarn/' + id,
    method: 'delete'
  })
}
